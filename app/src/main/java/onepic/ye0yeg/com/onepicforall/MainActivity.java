package onepic.ye0yeg.com.onepicforall;

import android.os.Bundle;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.stone.card.library.CardAdapter;
import com.stone.card.library.CardSlidePanel;

import java.util.ArrayList;

import butterknife.BindView;
import onepic.ye0yeg.com.onepicforall.base.BaseActivity;

public class MainActivity extends BaseActivity {


    private ArrayList<MyPicOnePic> mMyPicOnePics;

    @BindView(R.id.image_slide_panel)
    CardSlidePanel mCardSlidePanel;


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        initListener();


    }

    private void initListener() {
        mCardSlidePanel.setCardSwitchListener(new CardSlidePanel.CardSwitchListener() {
            @Override
            public void onShow(int index) {
                //let me try some new data
                //FOR THOSE PIC
                //开始创造数据吧
                Log.d("Card", "正在显示-" + dataList.get(index).userName);
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("Card", "正在消失-" + dataList.get(index).userName + " 消失type=" + type);
            }
        });

        mCardSlidePanel.setAdapter(new CardAdapter() {
            @Override
            public int getLayoutId() {
                return R.layout.card_item;
            }

            @Override
            public int getCount() {
                return 0;
            }

            @Override
            public void bindView(View view, int index) {

            }

            @Override
            public Object getItem(int index) {
                return null;
            }
        });
    }

}
