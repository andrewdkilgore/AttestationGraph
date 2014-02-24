package net.navinet.domain.impl;

/**
 * Created by Andrew on 23/02/14.
 */
public class Address implements net.navinet.domain.Address
{
    private String _addressLine1;
    private String _city;
    private String _state;
    private String _zipCode;

    @Override
    public String getAddressLine1()
    {
        return _addressLine1;
    }

    public void setAddressLine1(String addressLine1)
    {
        _addressLine1 = addressLine1;
    }

    @Override
    public String getCity()
    {
        return _city;
    }

    public void setCity(String city)
    {
        _city = city;
    }

    @Override
    public String getState()
    {
        return _state;
    }

    public void setState(String state)
    {
        _state = state;
    }

    @Override
    public String getZipCode()
    {
        return _zipCode;
    }

    public void setZipCode(String zipCode)
    {
        _zipCode = zipCode;
    }
}
