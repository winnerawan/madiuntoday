package net.winnerawan.madiun.data.network;


import android.text.TextUtils;
import com.rx2androidnetworking.Rx2AndroidNetworking;
import io.reactivex.Single;
import net.winnerawan.madiun.data.network.model.Article;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Gallery;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.data.network.response.GalleryResponse;
import net.winnerawan.madiun.data.network.response.YoutubeResponse;
import net.winnerawan.madiun.utils.AppConstants;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;


/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

@Singleton
public class AppApiHelper implements ApiHelper {

    @Inject
    public AppApiHelper() {

    }

    @Override
    public Single<App> getAds() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_APP)
                .doNotCacheResponse()
                .build()
                .getObjectSingle(App.class);
    }

    @Override
    public Single<List<Category>> getCategories() {
        List<Integer> excludes = new ArrayList<>();
        excludes.add(AppConstants.CATEGORY_DBH_CHT);
        excludes.add(AppConstants.CATEGORY_FOTO_BERITA);
        excludes.add(AppConstants.CATEGORY_MADIUN_THIS_WEEK);
        excludes.add(AppConstants.CATEGORY_TV);

        String excludeCategory = android.text.TextUtils.join(",", excludes);
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CATGORIES)
                .addQueryParameter("exclude", excludeCategory)
                .build()
                .getObjectListSingle(Category.class);
    }

    @Override
    public Single<List<Post>> getPosts() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_POST)
                .build()
                .getObjectListSingle(Post.class);
    }

    @Override
    public Single<List<Post>> getNews(Category category, int page) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_POST_BY_CATEGORY)
                .addQueryParameter("page", String.valueOf(page))
                .addQueryParameter("per_page", String.valueOf(10))
                .addQueryParameter("categories", String.valueOf(category.getId()))
                .build()
                .getObjectListSingle(Post.class);
    }

    @Override
    public Single<GalleryResponse> fetchGallery(String url) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_GALLERY)
                .addQueryParameter("link", url)
                .build()
                .getObjectSingle(GalleryResponse.class);
    }

    @Override
    public Single<List<Gallery>> getGalleries(Category category, int page) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_POST_BY_CATEGORY)
                .addQueryParameter("categories", String.valueOf(category.getId()))
                .addQueryParameter("page", String.valueOf(page))
                .build()
                .getObjectListSingle(Gallery.class);
    }

    @Override
    public Single<Article> getArticle(String url) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_ARTICLE)
                .addQueryParameter("link", url)
                .build()
                .getObjectSingle(Article.class);
    }

    @Override
    public Single<List<Post>> search(String keyword) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_POST)
                .addQueryParameter("search", keyword)
                .build()
                .getObjectListSingle(Post.class);
    }

    @Override
    public Single<YoutubeResponse> getVideos(String key) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_YOUTUBE)
                .addQueryParameter("key", key)
                .addQueryParameter("channelId", "UCVzwFHg3cxZ4I6N7XWYu_Pw")
                .addQueryParameter("part", "snippet,id")
                .addQueryParameter("order", "date")
                .addQueryParameter("maxResults", "50")
                .build()
                .getObjectSingle(YoutubeResponse.class);
    }
}
