package net.navinet.caregraph;

import net.navinet.domain.Patient;
import net.navinet.domain.Subscriber;

/**
 * Created by Andrew on 24/02/14.
 */
public interface GraphRepository
{
    void addOrUpdateSubscriber(Subscriber subscriber);

    void addOrUpdatePatient(Patient patient);

    void addOrUpdatePatient(Patient patient, Subscriber subscriber);
}
