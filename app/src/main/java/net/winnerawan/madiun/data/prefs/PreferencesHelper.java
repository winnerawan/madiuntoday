package net.winnerawan.madiun.data.prefs;

import net.winnerawan.madiun.data.network.model.Categories;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface PreferencesHelper {

    boolean isLoggedIn();
    void setLoggedIn(boolean loggedIn);

    boolean isFirstTime();
    void setFirstTime(boolean isFirstTime);
    void setCategories(Categories categories);
    void clearCategoriesFromPref();
    Categories getCategoriesFromPref();

    void setBanner(String banner);
    void setInters(String inters);
    void setReward(String reward);

    String getBanner();
    String getInters();
    String getReward();

}
