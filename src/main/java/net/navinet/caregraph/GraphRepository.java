package net.navinet.caregraph;

import net.navinet.domain.*;

/**
 * Created by Andrew on 24/02/14.
 */
public interface GraphRepository
{
    void buildOrUpdateCareGraph(Patient patient, Subscriber subscriber, Provider provider, User user, Payer payer);
}
