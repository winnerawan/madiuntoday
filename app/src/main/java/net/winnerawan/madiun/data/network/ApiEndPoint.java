package net.winnerawan.madiun.data.network;

import net.winnerawan.madiun.BuildConfig;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class ApiEndPoint {

    public static final String ENDPOINT_CATGORIES = BuildConfig.BASE_URL + "/wp-json/wp/v2/categories";
    public static final String ENDPOINT_POST = BuildConfig.BASE_URL + "/wp-json/wp/v2/posts";
    public static final String ENDPOINT_POST_BY_CATEGORY = BuildConfig.BASE_URL + "/wp-json/wp/v2/posts";
    public static final String ENDPOINT_APP= "https://api.go-aplikasi.co/scraper/mdt/index.php";
    public static final String ENDPOINT_GALLERY = "https://api.go-aplikasi.co/scraper/mdt/img.php";
    public static final String ENDPOINT_ARTICLE = "https://api.go-aplikasi.co/scraper/mdt/imgs.php";
    public static final String ENDPOINT_YOUTUBE = "https://www.googleapis.com/youtube/v3/search";
}