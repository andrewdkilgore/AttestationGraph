package net.navinet.domain.impl;

/**
 * Created by Andrew on 23/02/14.
 */
public class Subscriber implements net.navinet.domain.Subscriber
{
    private String _firstName;
    private String _lastName;
    private String _middleName;
    private String _memberId;
    private String _employerGroupId;

    @Override
    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName(String firstName)
    {
        _firstName = firstName;
    }

    @Override
    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName(String lastName)
    {
        _lastName = lastName;
    }

    @Override
    public String getMiddleName()
    {
        return _middleName;
    }

    public void setMiddleName(String middleName)
    {
        _middleName = middleName;
    }

    @Override
    public String getMemberId()
    {
        return _memberId;
    }

    public void setMemberId(String memberId)
    {
        _memberId = memberId;
    }

    @Override
    public String getEmployerGroupId()
    {
        return _employerGroupId;
    }

    public void setEmployerGroupId(String employerGroupId)
    {
        _employerGroupId = employerGroupId;
    }
}
