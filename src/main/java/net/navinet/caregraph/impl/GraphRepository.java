package net.navinet.caregraph.impl;

import net.navinet.domain.Patient;
import net.navinet.domain.Subscriber;
import org.neo4j.rest.graphdb.RestAPI;
import org.neo4j.rest.graphdb.query.RestCypherQueryEngine;
import org.neo4j.rest.graphdb.util.QueryResult;
import static org.neo4j.helpers.collection.MapUtil.map;

import java.util.HashMap;
import java.util.Map;

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
    public void addOrUpdateSubscriber(Subscriber subscriber)
    {

    }

    @Override
    public void addOrUpdatePatient(Patient patient)
    {
        _queryEngine.query("MERGE (x:Patient {member_id:{member_id}}) SET x.first_name = {first_name}, x.last_name = {last_name}, x.gender = {gender}, x.date_of_birth = {dob}",
                map("first_name", patient.getFirstName(), "last_name", patient.getLastName(), "member_id", patient.getMemberId(), "gender", patient.getGender(), "dob", patient.getDateOfBirth()));
    }

    @Override
    public void addOrUpdatePatient(Patient patient, Subscriber subscriber)
    {
        _queryEngine.query("MERGE (x:Patient {member_id:{member_id}}) SET x.first_name = {first_name}, x.last_name = {last_name}, x.gender = {gender}, x.date_of_birth = {dob}",
                map("first_name", patient.getFirstName(), "last_name", patient.getLastName(), "member_id", patient.getMemberId(), "gender", patient.getGender(), "dob", patient.getDateOfBirth()));

        _queryEngine.query("MERGE (x:Patient {member_id:{member_id}}) SET x.first_name = {first_name}, x.last_name = {last_name}",
                map("first_name", subscriber.getFirstName(), "last_name", subscriber.getLastName(), "member_id", subscriber.getMemberId()));

        _queryEngine.query("MATCH (pat:Patient {member_id:{pat_member_id}}), (sub:Patient {member_id:{sub_member_id}}) MERGE (pat)-[r:DEPENDS_ON]->(sub) RETURN r",
                map("pat_member_id", patient.getMemberId(), "sub_member_id", subscriber.getMemberId()));
    }
}
