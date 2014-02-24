package net.navinet.domain.impl;

/**
 * Created by Andrew on 23/02/14.
 */
public class User implements net.navinet.domain.User
{
    private int _id = SelectConstants.INVALID_VALUE;
    private int _roleId = SelectConstants.INVALID_VALUE;
    private int _officeId = SelectConstants.INVALID_VALUE;
    private int _groupId = SelectConstants.INVALID_VALUE;

    @Override
    public int getId()
    {
        return _id;
    }

    public void setId(int id)
    {
        _id = id;
    }

    @Override
    public int getRoleId()
    {
        return _roleId;
    }

    public void setRoleId(int roleId)
    {
        _roleId = roleId;
    }

    @Override
    public int getOfficeId()
    {
        return _officeId;
    }

    public void setOfficeId(int officeId)
    {
        _officeId = officeId;
    }

    @Override
    public int getGroupId()
    {
        return _groupId;
    }

    public void setGroupId(int groupId)
    {
        _groupId = groupId;
    }
}
