package net.winnerawan.madiun.ui.dbhcht;

import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.ui.base.MvpPresenter;

public interface DBHCHTMvpPresenter<V extends DBHCHTView> extends MvpPresenter<V> {

    void getDbhCht(Category category, int page);

    String getInters();
}
