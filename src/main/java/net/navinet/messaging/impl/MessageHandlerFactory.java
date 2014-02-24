package net.navinet.messaging.impl;

import net.navinet.messaging.MessageHandler;
import org.springframework.web.method.HandlerMethod;

import java.util.Map;

/**
 * Created by Andrew on 23/02/14.
 */
public class MessageHandlerFactory implements net.navinet.messaging.MessageHandlerFactory
{
    private Map<String, MessageHandler> _handlerMap;

    public Map<String, MessageHandler> getHandlerMap()
    {
        return _handlerMap;
    }

    public void setHandlerMap(Map<String, MessageHandler> handlerMap)
    {
        _handlerMap = handlerMap;
    }

    @Override
    public MessageHandler createHandler(String messageType)
    {
        MessageHandler handler = _handlerMap.get(messageType);
        return (handler != null) ? handler : new NullMessageHandlerImpl();
    }
}
