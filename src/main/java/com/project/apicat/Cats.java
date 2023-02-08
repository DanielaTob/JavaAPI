package com.project.apicat;

public class Cats {

    int id;
    String url;
    String apikey = "live_KS3fiWEpwLbHDkySxwGXBhxjFMaBHVrJekk2wzHvhsC11z8OfYyDA6ER2ZBRmYFb";
    String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
