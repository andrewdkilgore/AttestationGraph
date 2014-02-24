package net.navinet.messaging;

/**
 * Created by Andrew on 23/02/14.
 */
public interface MessageHandlerFactory
{
    MessageHandler createHandler(String messageType);
}
