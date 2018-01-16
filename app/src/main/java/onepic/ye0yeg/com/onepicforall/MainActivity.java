package onepic.ye0yeg.com.onepicforall;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageButton;

import butterknife.BindView;
import es.dmoral.toasty.Toasty;
import onepic.ye0yeg.com.onepicforall.base.BaseActivity;
import onepic.ye0yeg.com.onepicforall.fragment.DailyFragment;
import onepic.ye0yeg.com.onepicforall.view.CustomTextView;

public class MainActivity extends BaseActivity {
    /*
*adb connect 127.0.0.1:21503
* */
    @BindView(R.id.main_toolbar)
    Toolbar mainToolbar;

    @BindView(R.id.main_toolbar_tv_time)
    CustomTextView mCustomTextView;

    @BindView(R.id.main_toolbar_tv_time)
    ImageButton mImageButton;

    private FragmentTransaction mFragmentTransaction;
    private DailyFragment mDailyFragment;
    private FragmentManager mFragmentManager;
    private long mExitTiem = 0;

    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mFragmentManager = getSupportFragmentManager();
        setSupportActionBar(mainToolbar);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        mainToolbar.setNavigationIcon(R.drawable.ic_action_menu);
        setChocie(1);
        setListener();
    }

    private void setListener() {
        mainToolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toasty.info(MainActivity.this, "Click !").show();
            }
        });
    }

    //多目标点击<-<-<-<-<-<-<-<-
    private void setChocie(int i) {
        mFragmentTransaction = mFragmentManager.beginTransaction();
        hideFragment(mFragmentTransaction);
    }


    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (mDailyFragment != null) {
            fragmentTransaction.hide(mDailyFragment);
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - mExitTiem) > 2000) {
                Toasty.info(MainActivity.this, "再按一次退出程序").show();
                mExitTiem = System.currentTimeMillis();
            } else {
                finish();
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }
}
