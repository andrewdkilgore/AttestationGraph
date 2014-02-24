package net.navinet.domain.impl;

import com.jayway.jsonpath.JsonPath;
import net.navinet.domain.Gender;
import net.navinet.domain.PatientFactory;
import net.navinet.domain.RelationshipToSubscriber;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrew on 23/02/14.
 */
public class JsonPatientFactory implements PatientFactory
{
    private static final String PATIENT_NODE = SelectConstants.EVENT_PAYLOAD_PATH + ".patient";

    private static final JsonPath _patientLastNamePath = JsonPath.compile(PATIENT_NODE + SelectConstants.LAST_NAME);
    private static final JsonPath _patientFirstNamePath = JsonPath.compile(PATIENT_NODE + SelectConstants.FIRST_NAME);
    private static final JsonPath _patientMiddleNamePath = JsonPath.compile(PATIENT_NODE + SelectConstants.MIDDLE_NAME);

    private static final JsonPath _patientDoBPath = JsonPath.compile(PATIENT_NODE + ".birth_date");
    private static final JsonPath _patientMemberIdPath = JsonPath.compile(PATIENT_NODE + ".member_id");
    private static final JsonPath _patientGenderPath = JsonPath.compile(PATIENT_NODE + ".gender");
    private static final JsonPath _patientRelationshipToSubscriberPath = JsonPath.compile(PATIENT_NODE + ".relationship_to_subscriber");

    private static Logger _logger = Logger.getLogger(JsonPatientFactory.class.getName());

    public net.navinet.domain.Patient createFrom(Object value)
    {
        String jsonBody = (String)value;
        Patient patient = new Patient();

        patient.setFirstName((String)_patientFirstNamePath.read(jsonBody));
        patient.setLastName((String)_patientLastNamePath.read(jsonBody));
        patient.setMiddleName((String)_patientMiddleNamePath.read(jsonBody));
        patient.setMemberId((String)_patientMemberIdPath.read(jsonBody));

        String patientGender = _patientGenderPath.read(jsonBody);
        if(!StringUtils.isEmpty(patientGender))
        {
            patient.setGender(Gender.valueOf(patientGender.toUpperCase()));
        }

        String patientRelationshipToSubscriber = _patientRelationshipToSubscriberPath.read(jsonBody);
        if(!StringUtils.isEmpty(patientRelationshipToSubscriber))
        {
            patient.setRelationshipToSubscriber(RelationshipToSubscriber.valueOf(patientRelationshipToSubscriber.toUpperCase()));
        }

        String patientDateOfBirth = _patientDoBPath.read(jsonBody);
        if(!StringUtils.isEmpty(patientDateOfBirth))
        {
            try
            {
                patient.setDateOfBirth(new SimpleDateFormat("yyyy-MM-dd").parse(patientDateOfBirth));
            }
            catch(ParseException ex)
            {
                _logger.log(Level.WARNING, ex.getMessage());
            }
        }

        return patient;
    }
}
