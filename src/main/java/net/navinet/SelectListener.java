package net.navinet;

import net.navinet.messaging.MessageHandler;
import net.navinet.messaging.MessageHandlerFactory;

import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SelectListener implements MessageListener
{
    private Logger _logger;
    private MessageHandlerFactory _handlerFactory;

    public void init() throws Exception
    {
        _logger = Logger.getLogger("net.navinet.SelectListener");
        _logger.log(Level.WARNING, "In init method");
    }

    public void destroy() throws Exception
    {
        _logger.log(Level.WARNING, "In destroy method");
    }

    public MessageHandlerFactory getMessageHandlerFactory()
    {
        return _handlerFactory;
    }

    public void setMessageHandlerFactory(MessageHandlerFactory messageHandlerFactory)
    {
        _handlerFactory = messageHandlerFactory;
    }

    @Override
    public void onMessage(Message message)
    {
        try
        {
            TextMessage textMessage = message instanceof TextMessage ? (TextMessage)message : null;

            if(textMessage != null)
            {
                MessageHandler handler = _handlerFactory.createHandler(textMessage.getJMSType());
                handler.processMessage(textMessage.getText());
            }
        }
        catch(Exception ex)
        {
            _logger.log(Level.WARNING, ex.getMessage());
        }
    }
}
