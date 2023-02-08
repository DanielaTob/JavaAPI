package com.project.apicat;

public class CatsFav {

    public Cats image;
    String id;
    String image_id;
    String apikey = "live_KS3fiWEpwLbHDkySxwGXBhxjFMaBHVrJekk2wzHvhsC11z8OfYyDA6ER2ZBRmYFb";
    ImageX imageX;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getImage_id() {
        return image_id;
    }

    public void setImage_id(String image_id) {
        this.image_id = image_id;
    }

    public String getApikey() {
        return apikey;
    }

    public void setApikey(String apikey) {
        this.apikey = apikey;
    }

    public ImageX getImageX() {
        return imageX;
    }

    public void setImageX(ImageX imageX) {
        this.imageX = imageX;
    }
}
