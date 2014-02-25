package net.navinet.caregraph.impl;

import net.navinet.domain.*;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import static org.neo4j.helpers.collection.MapUtil.map;

/**
 * Created by Andrew on 24/02/14.
 */
public class GraphRepository implements net.navinet.caregraph.GraphRepository
{
    private RestAPI _graphApi;
    private RestCypherQueryEngine _queryEngine;

    public void setRestAPI(RestAPI restAPI) { _graphApi = restAPI; }

    public void init()
    {
        _queryEngine = new RestCypherQueryEngine(_graphApi);
    }

    @Override
    public void buildOrUpdateCareGraph(Patient patient, Subscriber subscriber, Provider provider, User user, Payer payer)
    {
        addOrUpdatePatient(patient, subscriber);
        addOrUpdateProvider(provider);
        addOrUpdatePayer(payer);
        addOrUpdateOffice(user);

        addOrUpdateCareRelationship(patient, provider);
        addOrUpdatePatientInsurance(patient, subscriber, payer);
        addOrUpdatePatientVisits(patient, user);
        addOrUpdateOfficeInsurance(user, payer);
    }

    private void addOrUpdatePatient(Patient patient, Subscriber subscriber)
    {
        _queryEngine.query("MERGE (x:Patient {member_id:{member_id}}) SET x.first_name = {first_name}, x.last_name = {last_name}, x.gender = {gender}, x.date_of_birth = {dob}, x.is_subscriber = {is_subscriber}",
                map("first_name", patient.getFirstName(), "last_name", patient.getLastName(), "member_id", patient.getMemberId(),
                        "gender", patient.getGender(), "dob", patient.getDateOfBirth(), "is_subscriber", (patient.getRelationshipToSubscriber() == RelationshipToSubscriber.SELF)));

        if(subscriber != null)
        {
            _queryEngine.query("MERGE (x:Patient {member_id:{member_id}}) SET x.first_name = {first_name}, x.last_name = {last_name}",
                    map("first_name", subscriber.getFirstName(), "last_name", subscriber.getLastName(), "member_id", subscriber.getMemberId()));

            _queryEngine.query("MATCH (pat:Patient {member_id:{pat_member_id}}), (sub:Patient {member_id:{sub_member_id}}) MERGE (pat)-[r:DEPENDS_ON]->(sub) RETURN r",
                    map("pat_member_id", patient.getMemberId(), "sub_member_id", subscriber.getMemberId()));
        }
    }

    private void addOrUpdateProvider(Provider provider)
    {
        _queryEngine.query("MERGE (x:Provider {tax_id_number:{provider_tin}}) SET x.type={provider_type}, x.last_name={last_name}",
                map("provider_tin", provider.getTaxIdNumber(), "provider_type", provider.getProviderType(), "last_name", provider.getLastName()));

        _queryEngine.query("MERGE (x:Address {address_line1:{address_line1}, city:{city}, state:{state}, zip_code:{zip_code}})",
                map("address_line1", provider.getAddress().getAddressLine1(), "city", provider.getAddress().getCity(), "state", provider.getAddress().getState(), "zip_code", provider.getAddress().getZipCode()));

        _queryEngine.query("MATCH (prov:Provider {tax_id_number:{provider_tin}}),(addr:Address {address_line1:{address_line1}, city:{city}, state:{state}, zip_code:{zip_code}}) MERGE (prov)-[r:WORKS_AT]->(addr) RETURN r",
                map("provider_tin", provider.getTaxIdNumber(), "address_line1", provider.getAddress().getAddressLine1(), "city", provider.getAddress().getCity(), "state", provider.getAddress().getState(), "zip_code", provider.getAddress().getZipCode()));

        for(PhoneNumber phoneNumber : provider.getPhoneNumbers())
        {
            _queryEngine.query("MERGE (x:PhoneNumber {type:{type}, number:{number}})",
                    map("type", phoneNumber.getNumberType(), "number", phoneNumber.getNumber()));

            _queryEngine.query("MATCH (prov:Provider {tax_id_number:{provider_tin}}),(phone:PhoneNumber {type:{type}, number:{number}}) MERGE (prov)-[r:CONTACT {type:'phone'}]->(phone) RETURN r",
                    map("provider_tin", provider.getTaxIdNumber(), "type", phoneNumber.getNumberType(), "number", phoneNumber.getNumber()));
        }

        for(EmailAddress emailAddress : provider.getEmailAddresses())
        {
            _queryEngine.query("MERGE (x:EmailAddress {type:{type}, address:{address}})", 
                    map("type", emailAddress.getAddressType(), "address", emailAddress.getAddress()));

            _queryEngine.query("MATCH (prov:Provider {tax_id_number:{provider_tin}}),(email:EmailAddress {type:{type}, address:{address}}) MERGE (prov)-[r:CONTACT {type:'email'}]->(email) RETURN r",
                    map("provider_tin", provider.getTaxIdNumber(), "type", emailAddress.getAddressType(), "address", emailAddress.getAddress()));
        }
    }

    private void addOrUpdatePayer(Payer payer)
    {
        _queryEngine.query("MERGE (x:Payer {plan_nid:{plan_nid}}) SET x.plan_short_name = {plan_short_name}",
                map("plan_nid", payer.getPlanId(), "plan_short_name", payer.getPlanShortName()));
    }

    private void addOrUpdateOffice(User user)
    {
        _queryEngine.query("MERGE (x:User {user_nid:{user_nid}})", map("user_nid", user.getId()));
        _queryEngine.query("MERGE (x:Office {office_nid:{office_nid}})", map("office_nid", user.getOfficeId()));
        _queryEngine.query("MERGE (x:Group {group_nid:{group_nid}})", map("group_nid", user.getGroupId()));

        _queryEngine.query("MATCH (office:Office {office_nid:{office_nid}}),(group:Group {group_nid:{group_nid}}) MERGE (office)-[r:MEMBER_OF]->(group) RETURN r",
                map("office_nid", user.getOfficeId(), "group_nid", user.getGroupId()));

        _queryEngine.query("MATCH (user:User {user_nid:{user_nid}}),(office:Office {office_nid:{office_nid}}) MERGE (user)-[r:WORKS_AT {role_id:{role_id}}]->(office) RETURN r",
                map("user_nid", user.getId(), "office_nid", user.getOfficeId(), "role_id", user.getRoleId()));
    }


    private void addOrUpdateCareRelationship(Patient patient, Provider provider)
    {
        _queryEngine.query("MATCH (pat:Patient {member_id:{pat_member_id}}),(prov:Provider {tax_id_number:{provider_tin}}) MERGE (prov)-[r:CARES_FOR]->(pat) RETURN r",
                map("pat_member_id", patient.getMemberId(), "provider_tin", provider.getTaxIdNumber()));
    }

    private void addOrUpdatePatientInsurance(Patient patient, Subscriber subscriber, Payer payer)
    {
        String memberId = (subscriber != null) ? subscriber.getMemberId() : patient.getMemberId();

        _queryEngine.query("MATCH (pat:Patient {member_id:{member_id}}),(payer:Payer {plan_nid:{plan_nid}}) MERGE (pat)-[r:COVERED_BY]->(payer) RETURN r",
                map("member_id", memberId, "plan_nid", payer.getPlanId()));
    }

    private void addOrUpdatePatientVisits(Patient patient, User user)
    {
        _queryEngine.query("MATCH (pat:Patient {member_id:{member_id}}),(office:Office {office_nid:{office_nid}}) MERGE (pat)-[r:MEMBER_OF]->(office) RETURN r",
                map("member_id", patient.getMemberId(), "office_nid", user.getOfficeId()));
    }

    private void addOrUpdateOfficeInsurance(User user, Payer payer)
    {
        _queryEngine.query("MATCH (payer:Payer {plan_nid:{plan_nid}}),(office:Office {office_nid:{office_nid}}) MERGE (office)-[r:PROVIDES_FOR]->(payer) RETURN r",
                map("plan_nid", payer.getPlanId(), "office_nid", user.getOfficeId()));
    }
}
