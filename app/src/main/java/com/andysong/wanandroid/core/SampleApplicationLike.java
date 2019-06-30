package com.andysong.wanandroid.core;

import android.annotation.TargetApi;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.StrictMode;
import android.support.multidex.MultiDex;
import android.widget.Toast;

import com.andysong.library.core.net.NetStatusBus;
import com.andysong.wanandroid.BuildConfig;
import com.andysong.wanandroid.core.task.NetStatusBusTask;
import com.andysong.wanandroid.core.task.RealmTask;
import com.andysong.wanandroid.core.task.UtilsTask;
import com.andysong.wanandroid.di.component.AppComponent;
import com.andysong.wanandroid.di.component.DaggerAppComponent;
import com.andysong.wanandroid.di.module.AppModule;
import com.andysong.wanandroid.di.module.HttpModule;
import com.andysong.wanandroid.utils.task.TaskDispatcher;
import com.blankj.utilcode.util.Utils;
import com.meituan.android.walle.WalleChannelReader;
import com.tencent.bugly.Bugly;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.beta.interfaces.BetaPatchListener;
import com.tencent.tinker.entry.DefaultApplicationLike;

import java.util.Locale;

import io.realm.Realm;

/**
 * @author andysong
 * @data 2019-06-21
 * @discription xxx
 */
public class SampleApplicationLike extends DefaultApplicationLike {

    private static Application instance;

    public static synchronized Application getInstance() {
        return instance;
    }

    public static final String APP_ID = "68e92bd04f";

    public static final String TAG = "Tinker.SampleApplicationLike";



    public static AppComponent appComponent;

    public SampleApplicationLike(Application application, int tinkerFlags,
                                 boolean tinkerLoadVerifyFlag, long applicationStartElapsedTime,
                                 long applicationStartMillisTime, Intent tinkerResultIntent) {
        super(application, tinkerFlags, tinkerLoadVerifyFlag, applicationStartElapsedTime, applicationStartMillisTime, tinkerResultIntent);
    }


    @Override
    public void onCreate() {
        super.onCreate();

        configTinker();

        instance = getApplication();

//        Utils.init(getApplication());

//        Realm.init(getApplication());

//        NetStatusBus.getInstance().init(getApplication());

        TaskDispatcher.init(getApplication());

        TaskDispatcher dispatcher = TaskDispatcher.createInstance();

        dispatcher.addTask(new UtilsTask())
                .addTask(new RealmTask())
                .addTask(new NetStatusBusTask())
                .start();

        //对于设置了依赖关系的，必须在onCreate执行完成的,这里调用等待
        dispatcher.await();

        initStrictMode();

        registerActivityLifecycleCallback(new AppManagerCall());
    }

    private void initStrictMode() {
        if (BuildConfig.DEBUG){
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectCustomSlowCalls()
                    .detectDiskReads()
                    .detectDiskWrites()
                    .detectNetwork()
                    .penaltyLog()//logcat中打印违规信息
                    .build());

            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectLeakedSqlLiteObjects()
                    .setClassInstanceLimit(SampleApplicationLike.class,1)
                    .detectLeakedClosableObjects()//API等级11
                    .penaltyLog()
                    .build());
        }
    }

    private void configTinker() {
        // 设置是否开启热更新能力，默认为true
        Beta.enableHotfix = true;
        // 设置是否自动下载补丁，默认为true
        Beta.canAutoDownloadPatch = true;
        // 设置是否自动合成补丁，默认为true
        Beta.canAutoPatch = true;
        // 设置是否提示用户重启，默认为false
        Beta.canNotifyUserRestart = true;
        // 补丁回调接口
        Beta.betaPatchListener = new BetaPatchListener() {
            @Override
            public void onPatchReceived(String patchFile) {
                Toast.makeText(getApplication(), "补丁下载地址" + patchFile, Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadReceived(long savedLength, long totalLength) {
                Toast.makeText(getApplication(),
                        String.format(Locale.getDefault(), "%s %d%%",
                                Beta.strNotificationDownloading,
                                (int) (totalLength == 0 ? 0 : savedLength * 100 / totalLength)),
                        Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadSuccess(String msg) {
                Toast.makeText(getApplication(), "补丁下载成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDownloadFailure(String msg) {
                Toast.makeText(getApplication(), "补丁下载失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplySuccess(String msg) {
                Toast.makeText(getApplication(), "补丁应用成功", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onApplyFailure(String msg) {
                Toast.makeText(getApplication(), "补丁应用失败", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPatchRollback() {

            }
        };

        // 设置开发设备，默认为false，上传补丁如果下发范围指定为“开发设备”，需要调用此接口来标识开发设备
        Bugly.setIsDevelopmentDevice(getApplication(), false);
        // 多渠道需求塞入
        String channel = WalleChannelReader.getChannel(getApplication());
        Bugly.setAppChannel(getApplication(), channel);
        // 这里实现SDK初始化，appId替换成你的在Bugly平台申请的appId
        Bugly.init(getApplication(), APP_ID, true);
    }



    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    @Override
    public void onBaseContextAttached(Context base) {
        super.onBaseContextAttached(base);
        // you must install multiDex whatever tinker is installed!
        MultiDex.install(base);

        // 安装tinker
        // TinkerManager.installTinker(this); 替换成下面Bugly提供的方法
        Beta.installTinker(this);
    }

    @TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
    public void registerActivityLifecycleCallback(Application.ActivityLifecycleCallbacks callbacks) {
        getApplication().registerActivityLifecycleCallbacks(callbacks);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Beta.unInit();
    }


    public static AppComponent getAppComponent(){
        if (appComponent == null) {
            appComponent = DaggerAppComponent.builder()
                    .appModule(new AppModule(instance))
                    .httpModule(new HttpModule())
                    .build();
        }
        return appComponent;
    }

}

