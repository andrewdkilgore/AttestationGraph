package net.navinet.domain;

/**
 * Created by Andrew on 23/02/14.
 */
public interface ContactFactory
{
    PhoneNumber createPhoneNumberFrom(Object value);

    EmailAddress createEmailAddressFrom(Object value);
}
