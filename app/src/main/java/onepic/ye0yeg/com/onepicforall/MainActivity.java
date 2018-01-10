package onepic.ye0yeg.com.onepicforall;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.stone.card.library.CardAdapter;
import com.stone.card.library.CardSlidePanel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;
import cn.bmob.v3.listener.UpdateListener;
import es.dmoral.toasty.Toasty;
import onepic.ye0yeg.com.onepicforall.base.BaseActivity;

public class MainActivity extends BaseActivity {


    private static final int GETDATA = 1001;
    private ArrayList<MyPicOnePic> mMyPicOnePics;

    @BindView(R.id.image_slide_panel)
    CardSlidePanel mCardSlidePanel;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (msg.what == GETDATA) {
                //扔一个handler吧

                mCardSlidePanel.setAdapter(new CardAdapter() {
                    @Override
                    public int getLayoutId() {
                        return R.layout.card_item;
                    }

                    @Override
                    public int getCount() {
                        return mMyPicOnePics.size();
                    }

                    @Override
                    public void bindView(View view, int index) {
                        Object tag = view.getTag();
                        ViewHolder viewHolder;
                        if (null != tag) {
                            viewHolder = (ViewHolder) tag;
                        } else {
                            viewHolder = new ViewHolder(view);
                            view.setTag(viewHolder);
                        }

                        viewHolder.bindData(mMyPicOnePics.get(index));
                    }

                    @Override
                    public Object getItem(int index) {
                        return mMyPicOnePics.get(index);
                    }

                    @Override
                    public Rect obtainDraggableArea(View view) {
                        View contentView = view.findViewById(R.id.card_item_content);
                        View topLayout = view.findViewById(R.id.card_top_layout);
                        View bottomLayout = view.findViewById(R.id.card_bottom_layout);

                        int left = view.getLeft() + contentView.getPaddingLeft() + topLayout.getPaddingLeft();
                        int right = view.getRight() - contentView.getPaddingRight() - topLayout.getPaddingRight();
                        int top = view.getTop() + contentView.getPaddingTop() + topLayout.getPaddingTop();
                        int bottom = view.getBottom() - contentView.getPaddingBottom() - bottomLayout.getPaddingBottom();
                        return new Rect(left, top, right, bottom);
                    }
                });


                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mCardSlidePanel.getAdapter().notifyDataSetChanged();
                    }
                }, 500);

                //继续

            }

        }
    };


    @Override
    protected int getContentViewLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        mMyPicOnePics = new ArrayList<>();
        initData();
        initListener();


    }

    private void initData() {
        //getData();
        final BmobQuery<MyPicOnePic> myPicOnePicBmobQuery = new BmobQuery<>();
        myPicOnePicBmobQuery.setLimit(5);
        myPicOnePicBmobQuery.findObjects(MainActivity.this, new FindListener<MyPicOnePic>() {
            @Override
            public void onSuccess(List<MyPicOnePic> list) {
                mMyPicOnePics = (ArrayList<MyPicOnePic>) list;
                if (mMyPicOnePics.size() != 0) {
                    mHandler.sendEmptyMessage(GETDATA);
                }
            }

            @Override
            public void onError(int i, String s) {

            }

        });
    }

    private void initListener() {
        mCardSlidePanel.setCardSwitchListener(new CardSlidePanel.CardSwitchListener() {
            @Override
            public void onShow(int index) {
                //let me try some new data
                //FOR THOSE PIC
                //开始创造数据吧
                Log.d("Card", "正在显示-" + mMyPicOnePics.get(index).getPicUrl());
            }

            @Override
            public void onCardVanish(int index, int type) {
                Log.d("Card", "正在消失-" + mMyPicOnePics.get(index).getPicUrl() + " 消失type=" + type);
            }
        });
    }

    class ViewHolder {

        ImageView imageView;
        TextView mTvLike;
        TextView mTvUnlike;

        public ViewHolder(View view) {
            imageView = view.findViewById(R.id.card_image_view);
            mTvLike = view.findViewById(R.id.tv_like);
            mTvUnlike = view.findViewById(R.id.tv_unlike);
        }

        public void bindData(MyPicOnePic itemData) {
            Glide.with(MainActivity.this).load(itemData.getPicUrl()).into(imageView);
            //绑定原子计数器，然后显示，其次点击，点击事件以后，原子+1。
            mTvLike.setText(itemData.getLike().toString());
            mTvUnlike.setText(itemData.getUnlike().toString());
            initViewHolderListener();
        }

        private void initViewHolderListener() {
            mTvLike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toasty.info(MainActivity.this, "Like").show();
                    MyPicOnePic onePic = new MyPicOnePic();
                    onePic.increment("like");
                    onePic.update(MainActivity.this);
                    mTvLike.setText((Integer.valueOf(mTvLike.getText().toString()) + 1) + "");
                }
            });

            mTvUnlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toasty.error(MainActivity.this, "UnLike").show();
                    MyPicOnePic onePic = new MyPicOnePic();
                    onePic.increment("unlike");
                    onePic.update(MainActivity.this, new UpdateListener() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onFailure(int i, String s) {
                            System.out.println(s + "UNLIKE");
                        }
                    });
                    mTvUnlike.setText(Integer.valueOf(mTvUnlike.getText().toString()) + 1 + "");
                }
            });
        }

    }
    //外观

}
