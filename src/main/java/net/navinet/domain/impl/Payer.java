package net.navinet.domain.impl;

/**
 * Created by Andrew on 23/02/14.
 */
public class Payer implements net.navinet.domain.Payer
{
    private int _planId = SelectConstants.INVALID_VALUE;
    private String _planShortName;

    @Override
    public int getPlanId()
    {
        return _planId;
    }

    public void setPlanId(int planId)
    {
        _planId = planId;
    }

    @Override
    public String getPlanShortName()
    {
        return _planShortName;
    }

    public void setPlanShortName(String planShortName)
    {
        _planShortName = planShortName;
    }
}
