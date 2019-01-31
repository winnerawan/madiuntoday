package net.winnerawan.madiun.data.network;

import com.google.gson.annotations.SerializedName;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

@Entity(nameInDb = "ads")
public class Ads {

    @Id
    private int id;
    
    @SerializedName("banner")
    private String banner;

    @SerializedName("inter")
    private String inter;

    public String getBanner() {
        return banner;
    }

    public void setBanner(String banner) {
        this.banner = banner;
    }

    public String getInter() {
        return inter;
    }

    public void setInter(String inter) {
        this.inter = inter;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Generated(hash = 1013671065)
    public Ads(int id, String banner, String inter) {
        this.id = id;
        this.banner = banner;
        this.inter = inter;
    }

    @Generated(hash = 1639366411)
    public Ads() {
    }


}
