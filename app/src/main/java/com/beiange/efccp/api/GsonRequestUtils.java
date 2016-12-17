package com.beiange.efccp.api;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class GsonRequestUtils {
    public static Gson gson = null; // 声明gson对象

    static {
        gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create(); // 创建gson对象，并设置日期格式
    }
}
