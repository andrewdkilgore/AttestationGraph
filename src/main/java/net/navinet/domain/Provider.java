package net.navinet.domain;

import java.util.List;

/**
 * Created by Andrew on 23/02/14.
 */
public interface Provider
{
    String getTaxIdNumber();

    String getLastName();

    ProviderType getProviderType();

    Address getAddress();

    List<PhoneNumber> getPhoneNumbers();

    List<EmailAddress> getEmailAddresses();
}
