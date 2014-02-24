package net.navinet.domain;

/**
 * Created by Andrew on 23/02/14.
 */
public interface PatientFactory
{
    Patient createFrom(Object value);
}
