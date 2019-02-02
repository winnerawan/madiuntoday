package net.winnerawan.madiun.di.component;

import net.winnerawan.madiun.ui.about.AboutFragment;
import net.winnerawan.madiun.ui.category.CategoryActivity;
import net.winnerawan.madiun.ui.content_news.ContentNewsFragment;
import net.winnerawan.madiun.ui.dbhcht.DBHCHTFragment;
import net.winnerawan.madiun.ui.detail.DetailActivity;
import net.winnerawan.madiun.ui.gallery.GalleryFragment;
import net.winnerawan.madiun.ui.main.MainActivity;
import net.winnerawan.madiun.ui.news.NewsFragment;
import net.winnerawan.madiun.ui.player.PlayerActivity;
import net.winnerawan.madiun.ui.search.SearchActivity;
import net.winnerawan.madiun.ui.splash.SplashActivity;
import dagger.Component;
import net.winnerawan.madiun.di.PerActivity;
import net.winnerawan.madiun.di.module.ActivityModule;
import net.winnerawan.madiun.ui.stream.StreamActivity;
import net.winnerawan.madiun.ui.tv.TvFragment;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

    void inject(SplashActivity activity);

    void inject(MainActivity mainActivity);

    void inject(DetailActivity detailActivity);

    void inject(NewsFragment newsFragment);

    void inject(TvFragment tvFragment);

    void inject(ContentNewsFragment contentNewsFragment);

    void inject(DBHCHTFragment dbhchtFragment);

    void inject(GalleryFragment galleryFragment);

    void inject(AboutFragment aboutFragment);

    void inject(SearchActivity searchActivity);

    void inject(CategoryActivity categoryActivity);

    void inject(PlayerActivity playerActivity);

    void inject(StreamActivity streamActivity);
}
