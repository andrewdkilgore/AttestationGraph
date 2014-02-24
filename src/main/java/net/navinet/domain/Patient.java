package net.navinet.domain;

import java.util.Date;

/**
 * Created by Andrew on 23/02/14.
 */
public interface Patient
{
    String getFirstName();

    String getLastName();

    String getMiddleName();

    String getMemberId();

    Gender getGender();

    RelationshipToSubscriber getRelationshipToSubscriber();

    Date getDateOfBirth();
}
