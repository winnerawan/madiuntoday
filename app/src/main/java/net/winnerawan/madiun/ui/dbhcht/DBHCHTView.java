package net.winnerawan.madiun.ui.dbhcht;

import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.ui.base.MvpView;

import java.util.List;

public interface DBHCHTView extends MvpView {
    
    void showContents(List<Post> posts);
    void setEnableRefreshLayout();
    void stopShimmer();
    void setDisableRefreshLayout();
}
