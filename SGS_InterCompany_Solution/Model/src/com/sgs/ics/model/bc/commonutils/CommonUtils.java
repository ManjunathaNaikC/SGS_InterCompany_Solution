package com.sgs.ics.model.bc.commonutils;

import java.util.Map;

import oracle.adf.share.ADFContext;

public class CommonUtils {
    public CommonUtils() {
        super();
    }
    
    public Object getSessionScopeValue(String name) {
        ADFContext adfCtx = ADFContext.getCurrent();
        Map sessionScope = adfCtx.getApplicationScope();
        Object val = sessionScope.get(name);

        if (val == null)
            return "0";
        else
            return val;
    }
    
    public void setSessionScopeValue(String name, String value) {
        ADFContext adfCtx = ADFContext.getCurrent();
        Map sessionScope = adfCtx.getSessionScope();
        sessionScope.put(name, value);
    }
}
