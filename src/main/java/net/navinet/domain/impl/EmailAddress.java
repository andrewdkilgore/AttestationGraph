package net.navinet.domain.impl;

import net.navinet.domain.EmailType;

/**
 * Created by Andrew on 23/02/14.
 */
public class EmailAddress implements net.navinet.domain.EmailAddress
{
    private EmailType _emailType;
    private String _address;

    @Override
    public EmailType getAddressType()
    {
        return _emailType;
    }

    public void setAddressType(EmailType emailType)
    {
        _emailType = emailType;
    }

    @Override
    public String getAddress()
    {
        return _address;
    }

    public void setAddress(String address)
    {
        _address = address;
    }
}
