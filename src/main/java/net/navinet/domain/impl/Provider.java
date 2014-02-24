package net.navinet.domain.impl;

import net.navinet.domain.Address;
import net.navinet.domain.EmailAddress;
import net.navinet.domain.PhoneNumber;
import net.navinet.domain.ProviderType;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Andrew on 23/02/14.
 */
public class Provider implements net.navinet.domain.Provider
{
    private String _taxIdNumber;
    private String _lastName;
    private ProviderType _providerType;
    private Address _address;
    private final List<PhoneNumber> _phoneNumbers = new LinkedList<PhoneNumber>();
    private final List<EmailAddress> _emailAddresses = new LinkedList<EmailAddress>();

    @Override
    public String getTaxIdNumber()
    {
        return _taxIdNumber;
    }

    public void setTaxIdNumber(String taxIdNumber)
    {
        _taxIdNumber = taxIdNumber;
    }

    @Override
    public String getLastName() { return _lastName; }

    public void setLastName(String lastName) { _lastName = lastName; }

    @Override
    public ProviderType getProviderType()
    {
        return _providerType;
    }

    public void setProviderType(ProviderType providerType)
    {
        _providerType = providerType;
    }

    @Override
    public Address getAddress()
    {
        return _address;
    }

    public void setAddress(Address address)
    {
        _address = address;
    }

    @Override
    public List<PhoneNumber> getPhoneNumbers()
    {
        return _phoneNumbers;
    }

    public void addPhoneNumber(PhoneNumber phoneNumber)
    {
        if(phoneNumber != null)
        {
            _phoneNumbers.add(phoneNumber);
        }
    }

    @Override
    public List<EmailAddress> getEmailAddresses()
    {
        return _emailAddresses;
    }

    public void addEmailAddress(EmailAddress emailAddress)
    {
        if(emailAddress != null)
        {
            _emailAddresses.add(emailAddress);
        }
    }
}
