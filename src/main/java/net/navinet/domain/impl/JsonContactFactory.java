package net.navinet.domain.impl;

import net.minidev.json.JSONObject;
import net.navinet.domain.*;
import net.navinet.domain.mapping.CodeMapException;
import net.navinet.domain.mapping.impl.CodeMapper;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrew on 23/02/14.
 */
public class JsonContactFactory implements net.navinet.domain.ContactFactory
{
    private final Logger _logger = Logger.getLogger(JsonContactFactory.class.getName());

    private CodeMapper<PhoneType> _phoneTypeMapper;
    private CodeMapper<EmailType> _emailTypeMapper;

    public void setPhoneTypeMapper(CodeMapper<PhoneType> phoneTypeMapper) { _phoneTypeMapper = phoneTypeMapper; }

    public void setEmailTypeMapper(CodeMapper<EmailType> emailTypeMapper) { _emailTypeMapper = emailTypeMapper; }

    @Override
    public net.navinet.domain.PhoneNumber createPhoneNumberFrom(Object value)
    {
        JSONObject jsonObject = (JSONObject)value;
        PhoneNumber number = new PhoneNumber();

        number.setNumber((String)jsonObject.get("phone_number"));

        try
        {
            number.setNumberType(_phoneTypeMapper.getCode((String)jsonObject.get("phone_type_code")));
        }
        catch(CodeMapException ex)
        {
            _logger.log(Level.WARNING, ex.getMessage());
        }

        return number;
    }

    @Override
    public net.navinet.domain.EmailAddress createEmailAddressFrom(Object value)
    {
        JSONObject jsonObject = (JSONObject)value;
        EmailAddress emailAddress = new EmailAddress();

        emailAddress.setAddress((String) jsonObject.get("email_address"));

        try
        {
            emailAddress.setAddressType(_emailTypeMapper.getCode((String)jsonObject.get("email_type_code")));
        }
        catch(CodeMapException ex)
        {
            _logger.log(Level.WARNING, ex.getMessage());
        }

        return emailAddress;
    }
}
