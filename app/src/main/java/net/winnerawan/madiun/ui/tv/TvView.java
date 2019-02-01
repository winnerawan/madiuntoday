package net.winnerawan.madiun.ui.tv;

import net.winnerawan.madiun.data.network.model.YoutubeItem;
import net.winnerawan.madiun.data.network.response.YoutubeResponse;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;

public interface TvView extends MvpView {
    void showVideos(List<YoutubeItem> videos);
    void stopShimmer();

}
