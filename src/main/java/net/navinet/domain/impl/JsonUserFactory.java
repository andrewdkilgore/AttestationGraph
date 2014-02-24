package net.navinet.domain.impl;

import com.jayway.jsonpath.JsonPath;
import net.navinet.domain.UserFactory;

/**
 * Created by Andrew on 23/02/14.
 */
public class JsonUserFactory implements UserFactory
{
    private static final String USER_NODE = SelectConstants.EVENT_PAYLOAD_PATH + ".user";

    private static final JsonPath _userIdPath = JsonPath.compile(USER_NODE + ".nid");
    private static final JsonPath _userRoleIdPath = JsonPath.compile(USER_NODE + ".role_id");
    private static final JsonPath _userOfficeIdPath = JsonPath.compile(USER_NODE + ".office_nid");
    private static final JsonPath _userGroupIdPath = JsonPath.compile(USER_NODE + ".group_nid");

    @Override
    public net.navinet.domain.User createFrom(Object value)
    {
        String jsonBody = (String)value;
        User user = new User();

        user.setId((Integer)_userIdPath.read(jsonBody));
        user.setRoleId((Integer)_userRoleIdPath.read(jsonBody));
        user.setOfficeId((Integer)_userOfficeIdPath.read(jsonBody));
        user.setGroupId((Integer)_userGroupIdPath.read(jsonBody));

        return user;
    }
}
