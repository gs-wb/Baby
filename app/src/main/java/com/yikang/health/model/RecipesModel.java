package com.yikang.health.model;

public class RecipesModel{// extends BaseModel{
    private String title;
    private String content;
    private String img_url;

    public RecipesModel(String title, String content, String img_url) {
        this.title = title;
        this.content = content;
        this.img_url = img_url;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }
}
