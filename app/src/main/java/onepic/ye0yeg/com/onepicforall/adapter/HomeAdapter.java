package onepic.ye0yeg.com.onepicforall.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import onepic.ye0yeg.com.onepicforall.R;
import onepic.ye0yeg.com.onepicforall.entity.HomePicEntity;

/**
 * Created by ye on 2018/1/17.
 */

public class HomeAdapter extends SimpleAdapter {
    private static final int PICTURE = 1;
    private static final int TEXT = 2;
    private Context mContext;
    private ArrayList<HomePicEntity> mHomePicEntities;

    /**
     * Constructor
     *
     * @param context  The context where the View associated with this SimpleAdapter is running
     * @param data     A List of Maps. Each entry in the List corresponds to one row in the list. The
     *                 Maps contain the data for each row, and should include all the entries specified in
     *                 "from"
     * @param resource Resource identifier of a view layout that defines the views for this list
     *                 item. The layout file should include at least those named views defined in "to"
     * @param from     A list of column names that will be added to the Map associated with each
     *                 item.
     * @param to       The views that should display column in the "from" parameter. These should all be
     *                 TextViews. The first N views in this list are given the values of the first N columns
     */
    public HomeAdapter(Context context, List<HomePicEntity> list) {
        super(context, null, R.layout.list_home_picture_item, null, null);
        mContext = context;
        mHomePicEntities = (ArrayList<HomePicEntity>) list;
    }


    @Override
    public int getItemViewType(int position) {
        HomePicEntity homePicEntity = mHomePicEntities.get(position);
        if ("picture".equals(homePicEntity.getType())) {
            return PICTURE;
        } else {
            return TEXT;
        }
    }

    @Override
    public int getCount() {
        return mHomePicEntities.size();
    }

    @Override
    public Object getItem(int position) {
        return mHomePicEntities.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private ViewHolder mViewHolder;

    private String title = "1";
    private String author = "1";
    private String picUrl = "1";
    private String date = "1";
    private String description = "1";
    private String category = "1";
    private Integer like = 1;
    private String type = "1";
    private String text = "1";

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        HomePicEntity homePicEntity = mHomePicEntities.get(position);
        int itemType = getItemViewType(position);
        mViewHolder = new ViewHolder();
        switch (itemType) {
            case PICTURE:
                //加载数据和视频和样式
                //获取各种东西
                title = homePicEntity.getTitle();
                picUrl = homePicEntity.getPicUrl();
                category = homePicEntity.getCategory();
                description = homePicEntity.getDescription();
                date = homePicEntity.getDate();
                like = homePicEntity.getLike();
                author = homePicEntity.getAuthor();
                type = homePicEntity.getType();
                category = "#" + category + " / ";
                author = "author : " + author;

                View view = LayoutInflater.from(mContext).inflate(R.layout.list_home_picture_item, parent, false);
                convertView = view;
                if (convertView == null) {
                    mViewHolder.mImageView = convertView.findViewById(R.id.iv);
                    mViewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
                    mViewHolder.tvTime = convertView.findViewById(R.id.tv_time);
                    convertView.setTag(mViewHolder);
                } else {
                    if (convertView.getTag() instanceof ViewHolder) {
                        mViewHolder = (ViewHolder) convertView.getTag();
                    } else {
                        convertView = view;
                        mViewHolder.mImageView = convertView.findViewById(R.id.iv);
                        mViewHolder.tvTitle = convertView.findViewById(R.id.tv_title);
                        mViewHolder.tvTime = convertView.findViewById(R.id.tv_time);
                        convertView.setTag(mViewHolder);
                    }
                }

                Uri uri = Uri.parse(picUrl);
                SimpleDraweeView draweeView = convertView.findViewById(R.id.iv);
                draweeView.setImageURI(uri);

                mViewHolder.tvTitle.setText(title);
                mViewHolder.tvTime.setText("-" + category + author);

                return convertView;

            case TEXT:
                convertView = LayoutInflater.from(mContext).inflate(R.layout.list_home_text_item, parent, false);
                TextView textView = (TextView) convertView.findViewById(R.id.tv_home_text);
                //set data
                text = homePicEntity.getDate();
                textView.setText(text);
                return convertView;

            default:
                return null;
        }
    }

    static class ViewHolder {
        ImageView mImageView;
        TextView tvTitle;
        TextView tvTime;
    }
}
