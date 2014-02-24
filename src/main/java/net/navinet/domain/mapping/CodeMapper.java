package net.navinet.domain.mapping;

/**
 * Created by Andrew on 23/02/14.
 */
public interface CodeMapper<T extends Enum>
{
    <T> T getCode(String value) throws CodeMapException;
}
