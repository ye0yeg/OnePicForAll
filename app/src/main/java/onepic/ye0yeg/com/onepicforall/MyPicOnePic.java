package onepic.ye0yeg.com.onepicforall;

import android.content.Intent;

import java.util.List;

import cn.bmob.v3.BmobObject;

/**
 * Created by ye on 2018/1/8.
 */


class MyPicOnePic extends BmobObject {
    private String picUrl;
    private List<userComment> mUserComments;

    private Integer like;
    private Integer unlike;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public List<userComment> getUserComments() {
        return mUserComments;
    }

    public void setUserComments(List<userComment> userComments) {
        mUserComments = userComments;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public Integer getUnlike() {
        return unlike;
    }

    public void setUnlike(Integer unlike) {
        this.unlike = unlike;
    }

    class userComment {
        //开始测试5张图
        String userName;
        String userAvtor;
        String commit;
        //还有一个原子计数器
        int likeCount;
        //不开心的点赞
        int unLikeCount;

        public String getUserName() {
            return userName;
        }

        public void setUserName(String userName) {
            this.userName = userName;
        }

        public String getUserAvtor() {
            return userAvtor;
        }

        public void setUserAvtor(String userAvtor) {
            this.userAvtor = userAvtor;
        }

        public String getCommit() {
            return commit;
        }

        public void setCommit(String commit) {
            this.commit = commit;
        }

        public int getLikeCount() {
            return likeCount;
        }

        public void setLikeCount(int likeCount) {
            this.likeCount = likeCount;
        }

        public int getUnLikeCount() {
            return unLikeCount;
        }

        public void setUnLikeCount(int unLikeCount) {
            this.unLikeCount = unLikeCount;
        }
    }

}
