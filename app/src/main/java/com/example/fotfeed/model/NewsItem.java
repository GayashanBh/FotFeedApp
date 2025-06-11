package com.example.fotfeed.model;

public class NewsItem {
    private String title;
    private String date;
    private int imageResId;
    private String category;

    public NewsItem(String title, String date, int imageResId, String category) {
        this.title = title;
        this.date = date;
        this.imageResId = imageResId;
        this.category = category;
    }

    /* getters */
    public String getTitle()     { return title; }
    public String getDate()      { return date; }
    public int    getImageResId(){ return imageResId; }
    public String getCategory()  { return category; }   // NEW
}
