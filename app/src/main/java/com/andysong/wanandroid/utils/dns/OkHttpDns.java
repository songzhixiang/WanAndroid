package com.andysong.wanandroid.utils.dns;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.sdk.android.httpdns.HttpDns;
import com.alibaba.sdk.android.httpdns.HttpDnsService;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.UnknownHostException;
import java.util.Arrays;
import java.util.List;

import okhttp3.Dns;

/**
 * @author andysong
 * @data 2019-07-01
 * @discription dns解析
 */
public class OkHttpDns implements Dns {

    private static OkHttpDns instance =null;

    public static OkHttpDns getInstance(Context context){
        if (instance == null){
            synchronized (OkHttpDns.class){
                if (instance == null){
                    instance = new OkHttpDns(context);
                }
            }
        }
        return instance;
    }

    private HttpDnsService mHttpDnsService;

    private OkHttpDns(Context context) {
        mHttpDnsService = HttpDns.getService(context,"");
    }



    @Override
    public List<InetAddress> lookup(String hostname) throws UnknownHostException {
        String ip = mHttpDnsService.getIpByHostAsync(hostname);
        if (!TextUtils.isEmpty(ip)){
            List<InetAddress> inetAddresses = Arrays.asList(InetAddress.getAllByName(ip));
            return inetAddresses;
        }
        return Dns.SYSTEM.lookup(hostname);
    }
}
