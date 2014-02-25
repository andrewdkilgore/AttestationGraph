package net.navinet.messaging.impl;

import net.navinet.caregraph.GraphRepository;
import net.navinet.domain.*;
import net.navinet.messaging.MessageHandler;
import java.util.logging.Logger;

/**
 * Created by Andrew on 23/02/14.
 */
public class SelectMessageHandlerImpl implements MessageHandler
{
    private final Logger _logger = Logger.getLogger(SelectMessageHandlerImpl.class.getName());

    private PatientFactory _patientFactory;
    private SubscriberFactory _subscriberFactory;
    private ProviderFactory _providerFactory;
    private UserFactory _userFactory;
    private PayerFactory _payerFactory;

    private GraphRepository _graphRepository;

    public void setPatientFactory(PatientFactory patientFactory) { _patientFactory = patientFactory; }

    public void setSubscriberFactory(SubscriberFactory subscriberFactory)
    {
        _subscriberFactory = subscriberFactory;
    }

    public void setProviderFactory(ProviderFactory providerFactory)
    {
        _providerFactory = providerFactory;
    }

    public void setUserFactory(UserFactory userFactory)
    {
        _userFactory = userFactory;
    }

    public void setPayerFactory(PayerFactory payerFactory)
    {
        _payerFactory = payerFactory;
    }

    public void setGraphRepository(GraphRepository graphRepository) { _graphRepository = graphRepository; }

    @Override
    public void processMessage(String body)
    {
        Patient patient = _patientFactory.createFrom(body);
        Subscriber subscriber = (patient.getRelationshipToSubscriber() != RelationshipToSubscriber.SELF) ? _subscriberFactory.createFrom(body) : null;
        Provider provider = _providerFactory.createFrom(body);
        User user = _userFactory.createFrom(body);
        Payer payer = _payerFactory.createFrom(body);

        _graphRepository.buildOrUpdateCareGraph(patient, subscriber, provider, user, payer);
    }
}
