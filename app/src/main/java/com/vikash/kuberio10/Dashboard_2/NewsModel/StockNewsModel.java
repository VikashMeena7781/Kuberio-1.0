package com.vikash.kuberio10.Dashboard_2.NewsModel;

public class StockNewsModel{
    private String title;
    private String link;
    private String pubDate;
    private String source;

    public StockNewsModel(){

    }
    public StockNewsModel(String title, String link, String pubDate,String source ){
        this.title=title;
        this.link=link;
        this.pubDate=pubDate;
        this.source=source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPubDate() {
        return pubDate;
    }

    public void setPubDate(String pubDate) {
        this.pubDate = pubDate;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

}
