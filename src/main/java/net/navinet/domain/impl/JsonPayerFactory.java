package net.navinet.domain.impl;

import com.jayway.jsonpath.JsonPath;
import net.navinet.domain.PayerFactory;

/**
 * Created by Andrew on 23/02/14.
 */
public class JsonPayerFactory implements PayerFactory
{
    private static final String PAYER_NODE = SelectConstants.EVENT_PAYLOAD_PATH + ".payer";

    private static final JsonPath _payerPlanIdPath = JsonPath.compile(PAYER_NODE + ".plan_nid");
    private static final JsonPath _payerPlanShortNamePath = JsonPath.compile(PAYER_NODE + ".plan_short_name");

    @Override
    public net.navinet.domain.Payer createFrom(Object value)
    {
        String jsonBody = (String)value;
        Payer payer = new Payer();

        payer.setPlanId((Integer)_payerPlanIdPath.read(jsonBody));
        payer.setPlanShortName((String)_payerPlanShortNamePath.read(jsonBody));

        return payer;
    }
}
