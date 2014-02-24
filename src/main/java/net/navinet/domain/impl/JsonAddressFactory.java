package net.navinet.domain.impl;

import net.minidev.json.JSONObject;

/**
 * Created by Andrew on 23/02/14.
 */
public class JsonAddressFactory implements net.navinet.domain.AddressFactory
{
    public net.navinet.domain.Address createFrom(Object value)
    {
        JSONObject jsonObject = (JSONObject)value;

        Address address = new Address();

        address.setAddressLine1((String)jsonObject.get("address_line1"));
        address.setCity((String)jsonObject.get("city"));
        address.setState((String)jsonObject.get("state_code"));
        address.setZipCode((String)jsonObject.get("zip_code"));

        return address;
    }
}
