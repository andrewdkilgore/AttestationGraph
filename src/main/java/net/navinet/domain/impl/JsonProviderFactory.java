package net.navinet.domain.impl;

import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import net.navinet.domain.ProviderFactory;
import net.navinet.domain.ProviderType;
import net.navinet.domain.AddressFactory;
import net.navinet.domain.ContactFactory;
import net.navinet.domain.mapping.CodeMapException;
import net.navinet.domain.mapping.CodeMapper;
import org.apache.commons.lang3.StringUtils;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Andrew on 23/02/14.
 */
public class JsonProviderFactory implements ProviderFactory
{
    private static final String PROVIDER_NODE = SelectConstants.EVENT_PAYLOAD_PATH + ".provider";

    private static final JsonPath _providerTaxIdNumberPath = JsonPath.compile(PROVIDER_NODE + ".provider_tin");
    private static final JsonPath _providerTypeCodePath = JsonPath.compile(PROVIDER_NODE + ".provider_type_code");
    private static final JsonPath _providerLastNamePath = JsonPath.compile(PROVIDER_NODE + SelectConstants.LAST_NAME);
    private static final JsonPath _providerAddressPath = JsonPath.compile(PROVIDER_NODE + ".contact.address");
    private static final JsonPath _providerPhonePath = JsonPath.compile(PROVIDER_NODE + ".contact.phone");
    private static final JsonPath _providerEmailPath = JsonPath.compile(PROVIDER_NODE + ".contact.email");

    private final Logger _logger = Logger.getLogger(JsonProviderFactory.class.getName());

    private CodeMapper<ProviderType> _providerTypeMapper;
    private AddressFactory _addressFactory;
    private ContactFactory _contactFactory;

    public void setProviderTypeMapper(CodeMapper<ProviderType> providerTypeMapper) { _providerTypeMapper = providerTypeMapper; }

    public void setAddressFactory(AddressFactory addressFactory) { _addressFactory = addressFactory; }

    public void setContactFactory(ContactFactory contactFactory) { _contactFactory = contactFactory; }

    @Override
    public net.navinet.domain.Provider createFrom(Object value)
    {
        String jsonBody = (String)value;
        Provider provider = new Provider();

        provider.setTaxIdNumber((String)_providerTaxIdNumberPath.read(jsonBody));
        provider.setLastName((String)_providerLastNamePath.read(jsonBody));
        provider.setAddress(_addressFactory.createFrom(_providerAddressPath.read(jsonBody)));

        JSONArray phoneContacts = (JSONArray)_providerPhonePath.read(jsonBody);
        if(phoneContacts != null)
        {
            for(Object phoneContact : phoneContacts)
            {
                provider.addPhoneNumber(_contactFactory.createPhoneNumberFrom(phoneContact));
            }
        }

        JSONArray emailContacts = (JSONArray)_providerEmailPath.read(jsonBody);
        if(emailContacts != null)
        {
            for(Object emailContact : emailContacts)
            {
                provider.addEmailAddress(_contactFactory.createEmailAddressFrom(emailContact));
            }
        }

        try
        {
            String providerTypeCode = _providerTypeCodePath.read(jsonBody);

            if(!StringUtils.isEmpty(providerTypeCode))
            {
                provider.setProviderType((ProviderType)_providerTypeMapper.getCode(providerTypeCode));
            }
        }
        catch(CodeMapException ex)
        {
            _logger.log(Level.WARNING, ex.getMessage());
        }

        return provider;
    }
}
