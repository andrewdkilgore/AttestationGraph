package net.navinet.domain.impl;

import com.jayway.jsonpath.JsonPath;
import net.navinet.domain.SubscriberFactory;

/**
 * Created by Andrew on 23/02/14.
 */
public class JsonSubscriberFactory implements SubscriberFactory
{
    private static final String SUBSCRIBER_NODE = SelectConstants.EVENT_PAYLOAD_PATH + ".subscriber";

    private static final JsonPath _subscriberLastNamePath = JsonPath.compile(SUBSCRIBER_NODE + SelectConstants.LAST_NAME);
    private static final JsonPath _subscriberFirstNamePath = JsonPath.compile(SUBSCRIBER_NODE + SelectConstants.FIRST_NAME);
    private static final JsonPath _subscriberMiddleNamePath = JsonPath.compile(SUBSCRIBER_NODE + SelectConstants.MIDDLE_NAME);

    private static final JsonPath _subscriberMemberIdPath = JsonPath.compile(SUBSCRIBER_NODE + ".member_id");
    private static final JsonPath _subscriberEmployerGroupIdPath = JsonPath.compile(SUBSCRIBER_NODE + ".employer_group_id");

    @Override
    public net.navinet.domain.Subscriber createFrom(Object value)
    {
        String jsonBody = (String)value;
        Subscriber subscriber = new Subscriber();

        subscriber.setFirstName((String)_subscriberFirstNamePath.read(jsonBody));
        subscriber.setLastName((String)_subscriberLastNamePath.read(jsonBody));
        subscriber.setMiddleName((String)_subscriberMiddleNamePath.read(jsonBody));
        subscriber.setMemberId((String)_subscriberMemberIdPath.read(jsonBody));
        subscriber.setEmployerGroupId((String)_subscriberEmployerGroupIdPath.read(jsonBody));

        return subscriber;
    }
}
