package com.beiange.efccp.app;

import android.app.Application;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager.NameNotFoundException;

import com.beiange.efccp.api.ApiClient;
import com.beiange.efccp.common.StringUtils;

import java.util.UUID;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class AppContext extends Application {
    private static AppContext mInstance = null;
    private boolean login = false;	//登录状态
    private int loginUid = 0;	//登录用户的id

    @Override
    public void onCreate() {

        //注册App异常崩溃处理器
        Thread.setDefaultUncaughtExceptionHandler(AppException.getAppExceptionHandler());
        mInstance = this;
        initEngineManager(this);
    }

    @Override
    // 建议在您app的退出之前调用mapadpi的destroy()函数，避免重复初始化带来的时间消耗
    public void onTerminate() {
        // TODO Auto-generated method stub
        super.onTerminate();
    }

    public void initEngineManager(Context context) {
    }

    /**
     * 获取应用程序实例 单例模式中获取唯一的Application 实例
     *
     * @return 当前应用程序
     */
    public static AppContext getInstance() {

        if (null == mInstance) {
            mInstance = new AppContext();
        }
        return mInstance;

    }

    /**
     * 获取App安装包信息
     * @return
     */
    public PackageInfo getPackageInfo() {
        PackageInfo info = null;
        try {
            info = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (NameNotFoundException e) {
            e.printStackTrace(System.err);
        }
        if (info == null)
            info = new PackageInfo();
        return info;
    }

    /**
     * 获取App唯一标识
     * @return
     */
    public String getAppId() {
        String uniqueID = getProperty(AppConfig.CONF_APP_UNIQUEID);
        if(StringUtils.isEmpty(uniqueID)){
            uniqueID = UUID.randomUUID().toString();
            setProperty(AppConfig.CONF_APP_UNIQUEID, uniqueID);
        }
        return uniqueID;
    }

    /**
     * 清除保存的缓存
     */
    public void cleanCookie() {
        removeProperty(AppConfig.CONF_COOKIE);
    }

    public void removeProperty(String...key) {
        AppConfig.getAppConfig(this).remove(key);
    }

    /**
     * 用户注销
     */
    public void Logout() {
        ApiClient.cleanCookie();
        this.cleanCookie();
        this.login = false;
        this.loginUid = 0;
    }

    public void setProperty(String key,String value){
        AppConfig.getAppConfig(this).set(key, value);
    }

    public String getProperty(String key){
        return AppConfig.getAppConfig(this).get(key);
    }
}
