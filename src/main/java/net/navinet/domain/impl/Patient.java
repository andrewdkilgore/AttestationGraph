package net.navinet.domain.impl;

import net.navinet.domain.Gender;
import net.navinet.domain.RelationshipToSubscriber;
import org.omg.DynamicAny._DynAnyFactoryStub;

import java.util.Date;

/**
 * Created by Andrew on 23/02/14.
 */
public class Patient implements net.navinet.domain.Patient
{
    private String _firstName;
    private String _lastName;
    private String _middleName;
    private String _memberId;
    private Gender _gender;
    private RelationshipToSubscriber _relationshipToSubscriber;
    private Date _dateOfBirth;

    public Patient()
    {
        _gender = Gender.U;
        _relationshipToSubscriber = RelationshipToSubscriber.SELF;
    }

    @Override
    public String getFirstName()
    {
        return _firstName;
    }

    public void setFirstName(String firstName)
    {
        _firstName = firstName;
    }

    @Override
    public String getLastName()
    {
        return _lastName;
    }

    public void setLastName(String lastName)
    {
        _lastName = lastName;
    }

    @Override
    public String getMiddleName()
    {
        return _middleName;
    }

    public void setMiddleName(String middleName)
    {
        _middleName = middleName;
    }

    @Override
    public String getMemberId()
    {
        return _memberId;
    }

    public void setMemberId(String memberId)
    {
        _memberId = memberId;
    }

    @Override
    public Gender getGender()
    {
        return _gender;
    }

    public void setGender(Gender gender)
    {
        _gender = gender;
    }

    @Override
    public RelationshipToSubscriber getRelationshipToSubscriber()
    {
        return _relationshipToSubscriber;
    }

    public void setRelationshipToSubscriber(RelationshipToSubscriber relationshipToSubscriber)
    {
        _relationshipToSubscriber = relationshipToSubscriber;
    }

    @Override
    public Date getDateOfBirth()
    {
        return _dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth)
    {
        _dateOfBirth = dateOfBirth;
    }
}
