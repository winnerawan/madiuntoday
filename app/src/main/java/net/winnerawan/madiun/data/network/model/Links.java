package net.winnerawan.madiun.data.network.model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Links implements Serializable {


    @SerializedName("collection")
    @Expose
    private List<Collection> collection = null;
    @SerializedName("about")
    @Expose
    private List<About> about = null;


    public List<Collection> getCollection() {
        return collection;
    }

    public void setCollection(List<Collection> collection) {
        this.collection = collection;
    }

    public List<About> getAbout() {
        return about;
    }

    public void setAbout(List<About> about) {
        this.about = about;
    }


}