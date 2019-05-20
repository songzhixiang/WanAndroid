package com.andysong.library.core.net;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkRequest;
import android.os.Build;

import com.andysong.library.NetworkCallbackImpl;

/**
 * @author andysong
 * @data 2019-05-20
 * @discription xxx
 */
public class NetStatusBus {
    private Context mContext;
    private NetStatusReceiver mNetStatusReceiver;
    private ConnectivityManager.NetworkCallback mNetworkCallback;

    private NetStatusBus() {

        mNetStatusReceiver = new NetStatusReceiver();
    }

    private static class HolderClass{
        private static final NetStatusBus instance = new NetStatusBus();
    }

    public static NetStatusBus getInstance(){
        return HolderClass.instance;
    }

    public Context getContext() {
        return mContext;
    }


    public void init(Context mContext){
        if (mContext == null){
            throw new IllegalArgumentException("mContext is empty");
        }
        this.mContext = mContext;
        ConnectivityManager.NetworkCallback networkCallback = new NetworkCallbackImpl(mNetStatusReceiver);
        NetworkRequest.Builder builder = new NetworkRequest.Builder();
        NetworkRequest request = builder.build();
        ConnectivityManager manager = (ConnectivityManager) NetStatusBus.getInstance().getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (null!=manager){
            manager.registerNetworkCallback(request,networkCallback);
        }

    }

    public void register(Object context){

    }


}
