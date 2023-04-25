package com.mvpgs.utils;

import com.gslibrary.http.XCallBack;

import org.json.JSONException;
import org.json.JSONObject;

/*********************************************
 ***       XCallBack实现类                 ***
 ***                                       ***
 ***       Created by HC on 2017/4/27.     ***
 *********************************************/

public class MyCallBack extends XCallBack {

    @Override
    public boolean onResponse(String result) {
        super.onResponse(result);
        try {
            JSONObject jsonObject=new JSONObject(result);
            String resultcode = jsonObject.getString("resultcode");
            if("200".equals(resultcode)){
                onSuccess(jsonObject.getString("result"));
            }else{
                onFail(jsonObject.getString("reason"));
            }
        } catch (JSONException e) {

//            LogUtil.e(e.toString());
        }
        return true;
    }

    public void onSuccess(String result){

    }

    @Override
    public void onFail(String result) {
    }
}
