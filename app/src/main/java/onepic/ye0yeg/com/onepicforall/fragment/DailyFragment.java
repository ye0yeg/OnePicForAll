package onepic.ye0yeg.com.onepicforall.fragment;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.ListView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrFrameLayout;
import in.srain.cube.views.ptr.PtrHandler;
import onepic.ye0yeg.com.onepicforall.R;
import onepic.ye0yeg.com.onepicforall.adapter.HomeAdapter;
import onepic.ye0yeg.com.onepicforall.entity.HomePicEntity;
import onepic.ye0yeg.com.onepicforall.utils.HttpAddress;

/**
 * Created by ye on 2018/1/16.
 */

public class DailyFragment extends Fragment {
    private static final String TAG = "DailyFragment";
    private static final int GOTDATA = 1001;

    @BindView(R.id.lv_home)
    ListView lvHome;
    @BindView(R.id.ptr)
    PtrClassicFrameLayout ptr;

    private String mResult;
    private Gson mGson;
    private Context mContext;
    private View mView;

    private View rlText;
    private String nextUrl;
    private RequestQueue mRequestQueue;

    private HomeAdapter mHomeAdapter;

    private List<HomePicEntity> mHomePicEntities;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case GOTDATA:
                    setLvAdapter();
                    break;
            }

        }
    };
    private boolean mIsRefresh;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_daily, container, false);
        mHomePicEntities = new ArrayList<>();
        mContext = getContext();
        ButterKnife.bind(this, mView);
        findView();
        init();
        setListener();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    private void setListener() {
        ptr.setPtrHandler(new PtrHandler() {
            @Override
            public boolean checkCanDoRefresh(PtrFrameLayout frame, View content, View header) {

                return !canChildScrollUp();
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mIsRefresh = true;
                init();
            }
        });

    }

    private void downLoad(String url) {


        //正常获取数据的代码块，不用他们的数据，用自己的数据
        //关于数据的获取。
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        mRequestQueue.add(request);
        mRequestQueue.start();

    }

    private void init() {
        clear();
        BmobQuery<HomePicEntity> homePicEntityBmobQuery = new BmobQuery<>();
        homePicEntityBmobQuery.findObjects(mContext, new FindListener<HomePicEntity>() {
            @Override
            public void onSuccess(List<HomePicEntity> list) {
                if (list.size() != 0) {
                    mHomePicEntities = list;
                    mHandler.sendEmptyMessage(GOTDATA);
                }
            }

            @Override
            public void onError(int i, String s) {
                Log.e(TAG, "错误信息:" + s);

            }
        });
    }

    public boolean canChildScrollUp() {
        if (android.os.Build.VERSION.SDK_INT < 14) {
            if (lvHome instanceof AbsListView) {
                final AbsListView absListView = (AbsListView) lvHome;
                return absListView.getChildCount() > 0 &&
                        (absListView.getFirstVisiblePosition() > 0 ||
                                absListView.getChildAt(0).getTop() < absListView.getPaddingTop());

            } else {
                return ViewCompat.canScrollVertically(lvHome, -1) || lvHome.getScrollY() > 0;
            }

        } else {

            return ViewCompat.canScrollVertically(lvHome, -1);

        }
    }

    private void clear() {
        if (mIsRefresh) {
            mHomePicEntities.clear();
            ptr.refreshComplete();
            mIsRefresh = false;
        }
    }

    private void findView() {
        rlText = mView.findViewById(R.id.rl_text);
    }

    private void setLvAdapter() {
        mHomeAdapter = new HomeAdapter(mContext,mHomePicEntities);
        lvHome.setAdapter(mHomeAdapter);
    }

}
