package com.ycjcjy.gene.web.action.system.util;

public enum BaseConstans {
    SESSION_OPENID("SESSION_OPENID"), SESSION_ANCHANG_USERTYPE("SESSION_ANCHANG_USERTYPE"),  SESSION_CODE("SESSION_CODE"), DELFALT_ADDRESS(
        "DELFALT_ADDRESS"), SESSION_USERINO("SESSION_USERINO"), SESSION_WXUSER("SESSION_WXUSER"),SESSION_SYSUSER("SESSION_SYSUSER"),SESSION_TEACHER("SESSION_TEACHER");
    String value;

    public String getValue()
    {
        return value;
    }

    public void setValue(String value)
    {
        this.value = value;
    }

    BaseConstans(String value)
    {
        this.value = value;
    }

    @Override
    public String toString()
    {
        return this.value;
    }
}