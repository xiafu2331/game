package com.example.food.bean;

import org.litepal.crud.DataSupport;

import java.io.Serializable;


/**
 * 菜品
 */
public class Fruit extends DataSupport implements Serializable {
    private Integer typeId;//类型 0科技，1娱乐，2体育，3军事,4汽车,5健康
    private String title;//标题
    private String img;//图片
    private String content;//内容
    private String issuer;//发布人
    private String date;//时间

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getIssuer() {
        return issuer;
    }

    public void setIssuer(String issuer) {
        this.issuer = issuer;
    }

    public Fruit(Integer typeId, String title, String img, String content, String issuer, String date) {
        this.typeId = typeId;
        this.title = title;
        this.img = img;
        this.content = content;
        this.issuer = issuer;
        this.date = date;
    }
}
