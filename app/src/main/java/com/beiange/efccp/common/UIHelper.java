package com.beiange.efccp.common;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.beiange.efccp.R;
import com.beiange.efccp.base.AppManager;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Set;

/**
 * Created by Administrator on 2016/12/13 0013.
 */

public class UIHelper{
    /**
     * 发送App异常崩溃报告
     * @param cont
     * @param crashReport
     */
    public static void sendAppCrashReport(final Context cont, final String crashReport) {

        AlertDialog.Builder builder = new AlertDialog.Builder(cont);
        builder.setIcon(android.R.drawable.ic_dialog_info);
        builder.setTitle(R.string.app_error);
        builder.setMessage(R.string.app_error_message);
        builder.setPositiveButton(R.string.submit_report, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //发送异常报告
                Intent i = new Intent(Intent.ACTION_SEND);
                //i.setType("text/plain"); //模拟器
                i.setType("message/rfc822") ; //真机
                i.putExtra(Intent.EXTRA_EMAIL, new String[]{"jxsmallmouse@163.com"});
                i.putExtra(Intent.EXTRA_SUBJECT,"开源中国Android客户端 - 错误报告");
                i.putExtra(Intent.EXTRA_TEXT,crashReport);
                cont.startActivity(Intent.createChooser(i, "发送错误报告"));
                //退出
                AppManager.getAppManager().AppExit(cont);
            }
        });
        builder.setNegativeButton(R.string.sure, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                //退出
                AppManager.getAppManager().AppExit(cont);
            }
        });
        builder.show();
    }


    /**
     * 跳转到下一个Activity
     *
     * @param  当前Activity
     * @param  跳转到的Activity
     */
    public static void Go(Context context, Class<?> cls) {

        Intent i1 = new Intent();
        i1.setClass(context, cls);
        context.startActivity(i1);
    }

    /**
     * 跳转到下一个Activity
     *
     * @param  当前Activity
     * @param  跳转到的Activity
     * @param  两个Activity之间的参数
     */
    public static void Go(Context context, Class<?> cls, Bundle bundle) {

        Intent i1 = new Intent();
        if (null != bundle) {
            i1.putExtras(bundle);
        }
        i1.setClass(context, cls);
        context.startActivity(i1);

    }

    /**
     * 跳转
     *
     * @param 当前Activity
     * @param 跳转到的Activity
     * @param 数据集合
     */
    public static void Go(Context context, Class<?> cls,
                          HashMap<String, Serializable> map) {

        Intent i1 = new Intent();
        if (null != map) {
            Set<String> keys = map.keySet();
            for (String key : keys) {
                i1.putExtra(key, map.get(key));
            }
        }
        i1.setClass(context, cls);
        context.startActivity(i1);
    }

    /**
     * 弹出Toast消息
     * @param msg
     */
    public static void ToastMessage(Context cont,String msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont,int msg) {
        Toast.makeText(cont, msg, Toast.LENGTH_SHORT).show();
    }

    public static void ToastMessage(Context cont,String msg,int time) {
        Toast.makeText(cont, msg, time).show();
    }

    /**
     * 点击返回监听事件
     * @param activity
     * @return
     */
    public static View.OnClickListener finish(final Activity activity)
    {
        return new View.OnClickListener() {
            public void onClick(View v) {
                activity.finish();
            }
        };
    }

}
