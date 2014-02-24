package net.navinet.domain.impl;

import net.navinet.domain.PhoneType;

/**
 * Created by Andrew on 23/02/14.
 */
public class PhoneNumber implements net.navinet.domain.PhoneNumber
{
    private PhoneType _numberType;
    private String _number;

    @Override
    public PhoneType getNumberType()
    {
        return _numberType;
    }

    public void setNumberType(PhoneType numberType)
    {
        _numberType = numberType;
    }

    @Override
    public String getNumber()
    {
        return _number;
    }

    public void setNumber(String number)
    {
        _number = number;
    }
}
