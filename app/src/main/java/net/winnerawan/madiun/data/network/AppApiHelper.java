package net.winnerawan.madiun.data.network;


import com.rx2androidnetworking.Rx2AndroidNetworking;
import io.reactivex.Single;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Gallery;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.data.network.response.GalleryResponse;

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
    public Single<Ads> getAds() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_APP)
                .doNotCacheResponse()
                .build()
                .getObjectSingle(Ads.class);
    }

    @Override
    public Single<List<Category>> getCategories() {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_CATGORIES)
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
    public Single<List<Post>> getNews(Category category) {
        return Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_POST_BY_CATEGORY)
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
    public Single<List<Gallery>> getGalleries(Category category) {
        return  Rx2AndroidNetworking.get(ApiEndPoint.ENDPOINT_POST_BY_CATEGORY)
                .addQueryParameter("categories", String.valueOf(category.getId()))
                .build()
                .getObjectListSingle(Gallery.class);
    }
}
