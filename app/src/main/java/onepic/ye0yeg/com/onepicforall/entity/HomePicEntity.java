package onepic.ye0yeg.com.onepicforall.entity;

import java.io.Serializable;

/**
 * Created by ye on 2018/1/17.
 */

public class HomePicEntity implements Serializable{
    /*
    * 原著是2个
    * getIssueList
    * nextPageUrl
    * */
    //标题
    private String title;
    //    日期
    private String date;
    //    描述
    private String description;
    //    分类
    private String category;
    //    图片的url
    private String picUrl;
    //    作者
    private String author;
    //    评论
    private String commit;
    //    点赞
    private Integer like;
    //    種類
    private String type;
    //如果type == data？

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCommit() {
        return commit;
    }

    public void setCommit(String commit) {
        this.commit = commit;
    }

    public Integer getLike() {
        return like;
    }

    public void setLike(Integer like) {
        this.like = like;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
