package com.momo.wechatauthorizelogin.controller;

import com.momo.wechatauthorizelogin.commmon.HttpClientUtil;
import com.momo.wechatauthorizelogin.commmon.JSONResult;
import com.momo.wechatauthorizelogin.commmon.JsonUtils;
import com.momo.wechatauthorizelogin.commmon.RedisOperator;
import com.momo.wechatauthorizelogin.model.WxSessionModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class WxLoginController {
    @Value("${wxLogin.url}")
    private String url;
    @Value("${wxLogin.appid}")
    private String appid;
    @Value("${wxLogin.secret}")
    private String secret;
    @Autowired
    private RedisOperator redis;
    @PostMapping("/wxLogin")
    public JSONResult wxLogin(String code){
//     https://api.weixin.qq.com/sns/jscode2session?appid=APPID&secret=SECRET&js_code=JSCODE&
//     grant_type=authorization_code
        Map<String,String> param=new HashMap<>();
        param.put("appid",appid);
        param.put("secret",secret);
        param.put("js_code",code);
        param.put("grant_type","authorization_code");
        String wxResult = HttpClientUtil.doGet(url,param);
        WxSessionModel wxSessionModel=JsonUtils.jsonToPojo(wxResult, WxSessionModel.class);
        redis.set("User-Session:"+wxSessionModel.getOpenid(),
                        wxSessionModel.getSession_key(),
                1000*60*60*24);
        return JSONResult.ok();
    }
}
