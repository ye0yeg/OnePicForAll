package onepic.ye0yeg.com.onepicforall.activity;

import android.content.Intent;

import butterknife.BindView;
import cn.bingoogolapple.bgabanner.BGABanner;
import onepic.ye0yeg.com.onepicforall.R;
import onepic.ye0yeg.com.onepicforall.base.BaseActivity;

/**
 * Created by ye on 2018/1/5.
 */

public class SplashActivity extends BaseActivity {

    @BindView(R.id.banner_guide_background)
    BGABanner mBackgroundBanner;

    @BindView(R.id.banner_guide_foreground)
    BGABanner mForegroundBanner;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_guide;
    }

    @Override
    protected void initView() {
        setListener();
        processLogic();
    }

    private void setListener() {
        mForegroundBanner.setEnterSkipViewIdAndDelegate(R.id.btn_guide_enter, R.id.tv_guide_skip, new BGABanner.GuideDelegate() {
            @Override
            public void onClickEnterOrSkip() {
                //跳转
//                startActivity(new Intent(.this, MainActivity.class));
                finish();
            }
        });
    }


    private void processLogic() {
        // 设置数据源
        //设置滑动资源
        mBackgroundBanner.setData(R.drawable.uoko_guide_background_1, R.drawable.uoko_guide_background_2, R.drawable.uoko_guide_background_3);
        mForegroundBanner.setData(R.drawable.uoko_guide_foreground_1, R.drawable.uoko_guide_foreground_2, R.drawable.uoko_guide_foreground_3);
    }
}
