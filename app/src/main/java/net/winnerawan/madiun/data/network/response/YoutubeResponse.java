package net.winnerawan.madiun.data.network.response;

import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import net.winnerawan.madiun.data.network.model.YoutubeItem;

public class YoutubeResponse {

    @SerializedName("items")
    @Expose
    private List<YoutubeItem> items = null;

    public List<YoutubeItem> getItems() {
        return items;
    }

    public void setItems(List<YoutubeItem> items) {
        this.items = items;
    }
}
