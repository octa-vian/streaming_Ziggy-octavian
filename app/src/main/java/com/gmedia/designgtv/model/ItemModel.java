package com.gmedia.designgtv.model;

public class ItemModel {
    String id,title,icon,url,kategori;

    public ItemModel(String id, String title, String icon,String url,String kategori){
        this.id = id;
        this.title = title;
        this.icon = icon;
        this.url = url;
        this.kategori =kategori;
    }

    public ItemModel(String id, String icon){
        this.id = id;
        this.icon = icon;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) {
        this.kategori = kategori;
    }
}
