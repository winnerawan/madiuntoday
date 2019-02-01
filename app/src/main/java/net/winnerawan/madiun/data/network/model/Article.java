package net.winnerawan.madiun.data.network.model;

import java.io.Serializable;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Article implements Serializable {

    @SerializedName("first")
    @Expose
    private String first;
    @SerializedName("center")
    @Expose
    private String center;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getFirst() {
        return first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

}
