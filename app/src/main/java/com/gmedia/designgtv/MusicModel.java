package com.gmedia.designgtv;

public class MusicModel {
    public String title;

    public int drawable;

    public MusicModel() {
    }

    public MusicModel(String title,int image) {
        this.title = title;
        this.drawable = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String text) {
        this.title = text;
    }

    public int getDrawable() {
        return drawable;
    }

    public void setDrawable(int drawable) {
        this.drawable = drawable;
    }


}
