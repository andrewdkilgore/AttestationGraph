package net.navinet.domain;

/**
 * Created by Andrew on 23/02/14.
 */
public interface ProviderFactory
{
    Provider createFrom(Object value);
}
