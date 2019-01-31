package net.winnerawan.madiun.data.network;

import io.reactivex.Single;
import net.winnerawan.madiun.data.network.model.Category;
import net.winnerawan.madiun.data.network.model.Gallery;
import net.winnerawan.madiun.data.network.model.Post;
import net.winnerawan.madiun.data.network.response.GalleryResponse;

import java.util.List;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public interface ApiHelper {

    Single<Ads> getAds();

    Single<List<Category>> getCategories();

    Single<List<Post>> getPosts();

    Single<List<Post>> getNews(Category category);

    Single<List<Gallery>> getGalleries(Category category);

    Single<GalleryResponse> fetchGallery(String url);

}
