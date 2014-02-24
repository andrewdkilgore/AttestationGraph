package net.navinet.domain.mapping.impl;

import net.navinet.domain.mapping.CodeMapException;

import java.util.Map;

/**
 * Created by Andrew on 23/02/14.
 */
public class CodeMapper<T extends Enum> implements net.navinet.domain.mapping.CodeMapper
{
    public static final String UNKNOWN = "UNKNOWN";

    private Map<String, T> _codeMap;

    public void setCodeMap(Map<String, T> codeMap)
    {
        _codeMap = codeMap;
    }

    @Override
    public T getCode(String value) throws CodeMapException
    {
        if(!_codeMap.containsKey(value))
        {
            throw new CodeMapException(value);
        }

        return _codeMap.get(value);
    }
}
