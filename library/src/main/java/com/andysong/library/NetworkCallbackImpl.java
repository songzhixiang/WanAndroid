package com.andysong.library;

import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.util.Log;

import com.andysong.library.core.net.NetStatusReceiver;

/**
 * @author andysong
 * @data 2019-05-20
 * @discription xxx
 */
public class NetworkCallbackImpl extends ConnectivityManager.NetworkCallback {


    private NetStatusReceiver mNetStatusReceiver;

    public NetworkCallbackImpl(NetStatusReceiver netStatusReceiver) {
        mNetStatusReceiver = netStatusReceiver;
    }

    //网络已连接
    @Override
    public void onAvailable(Network network) {
        super.onAvailable(network);
    }

    //网络已中断
    @Override
    public void onLost(Network network) {
        super.onLost(network);
    }

    //网络发生变更
    @Override
    public void onCapabilitiesChanged(Network network, NetworkCapabilities networkCapabilities) {
        super.onCapabilitiesChanged(network, networkCapabilities);
        if (networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_VALIDATED)){
            if (networkCapabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)){
                //类型是wifi

            }else {
                //类型为其他
            }
        }
    }
}
