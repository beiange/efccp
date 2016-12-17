package com.beiange.efccp.bean;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class URLs implements Serializable {
    public final static String HOST = "192.168.1.1:8080";

    public final static String HTTP = "http://";
    public final static String HTTPS = "https://";

    private final static String URL_SPLITTER = "/";
    private final static String URL_UNDERLINE = "_";

    private final static String project = "/calltaxi";

    private final static String URL_PASSENGER = "passenger";
    private final static String URL_COOPERATION = "cooperation";

    private final static String URL_API_HOST = HTTP + HOST + URL_SPLITTER;
    public final static String FAVORITE_ADD = URL_API_HOST+"ipms-core/mobile!fetchHistoryParkItem";
}
