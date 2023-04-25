package com.mvpgs.utils;

import java.util.HashMap;
import java.util.Map;

/*********************************************
 ***       河南坚磐科技电子有限公司        ***
 ***                                       ***
 ***       Created by HC on 2017/4/24.       ***
 *********************************************/

public class RMPparams {

    private Map<String, String> params;

    public RMPparams() {
        params = new HashMap<String, String>();
    }

    public Map<String, String> getLogin(String username, String password) {
        params.put("cityname", username);
        params.put("key", password);
        return params;
    }

    public Map<String, String> getAus() {
        params.put("begin", "1");
        params.put("cid", "b6baf4b6333741fe8370f050e8826f99");
        params.put("en", "0");
        params.put("interval", "1");
        params.put("page", "1");
        params.put("paymentStatus", "");
        params.put("rows", "10");
        params.put("sid", "");
        params.put("status", "");
        return params;
    }
}
