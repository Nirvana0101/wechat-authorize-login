package com.momo.wechatauthorizelogin.model;

import java.io.Serializable;

public class WxSessionModel implements Serializable {
//    {"session_key":"xmXOfSn8Sm+hTT1ZPeO4nw==","openid":"oroYZ44pK2FBlygBmTqm8cWk5KGc"}
    private String session_key;
    private String openid;

    public String getSession_key() {
        return session_key;
    }

    public void setSession_key(String session_key) {
        this.session_key = session_key;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
