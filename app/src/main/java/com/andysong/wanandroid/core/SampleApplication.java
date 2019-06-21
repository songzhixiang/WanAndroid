package com.andysong.wanandroid.core;

import com.tencent.tinker.loader.app.TinkerApplication;
import com.tencent.tinker.loader.shareutil.ShareConstants;

/**
 * @author andysong
 * @data 2019-06-21
 * @discription Tinker需要的组件
 */
public class SampleApplication extends TinkerApplication {
    public SampleApplication() {
        super(ShareConstants.TINKER_ENABLE_ALL, "com.andysong.wanandroid.core.SampleApplicationLike",
                "com.tencent.tinker.loader.TinkerLoader", false);
    }
}
