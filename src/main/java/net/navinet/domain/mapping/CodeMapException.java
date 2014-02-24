package net.navinet.domain.mapping;

/**
 * Created by Andrew on 23/02/14.
 */
public class CodeMapException extends Throwable
{
    public CodeMapException(String lookupValue)
    {
        super(String.format("Unable to find mapping for value [%s]", lookupValue));
    }
}
