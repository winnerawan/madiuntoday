package net.winnerawan.madiun.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class App {

    @SerializedName("youtube_key")
    @Expose
    private String youtubeKey;
    @SerializedName("ads_enable")
    private boolean adsEnable;
    @SerializedName("ads")
    private Ads ads;

    public String getYoutubeKey() {
        return youtubeKey;
    }

    public void setYoutubeKey(String youtubeKey) {
        this.youtubeKey = youtubeKey;
    }

    public boolean isAdsEnable() {
        return adsEnable;
    }

    public void setAdsEnable(boolean adsEnable) {
        this.adsEnable = adsEnable;
    }

    public Ads getAds() {
        return ads;
    }

    public void setAds(Ads ads) {
        this.ads = ads;
    }
}
