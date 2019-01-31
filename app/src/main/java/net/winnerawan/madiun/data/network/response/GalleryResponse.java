package net.winnerawan.madiun.data.network.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.winnerawan.madiun.data.network.model.Image;

public class GalleryResponse {

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("images")
    @Expose
    private List<Image> images = null;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
