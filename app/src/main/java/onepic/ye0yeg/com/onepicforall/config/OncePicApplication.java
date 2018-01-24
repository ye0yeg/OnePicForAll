package onepic.ye0yeg.com.onepicforall.config;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;

import cn.bmob.v3.Bmob;

/**
 * Created by ye on 2018/1/8.
 */

public class OncePicApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        //开始注册比目的东西
        Bmob.initialize(this, "a0804a0e9e1d596b0f8ab93fd2b5b5d5"); //使用
        Fresco.initialize(this);

    }
}
