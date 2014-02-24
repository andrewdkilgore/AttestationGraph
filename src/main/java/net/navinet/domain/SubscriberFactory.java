package net.navinet.domain;

/**
 * Created by Andrew on 23/02/14.
 */
public interface SubscriberFactory
{
    Subscriber createFrom(Object value);
}
