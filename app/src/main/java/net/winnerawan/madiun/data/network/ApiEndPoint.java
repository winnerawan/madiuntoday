package net.winnerawan.madiun.data.network;

import net.winnerawan.madiun.BuildConfig;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public class ApiEndPoint {

    public static final String ENDPOINT_SEARCH= BuildConfig.BASE_URL + "/search";
    public static final String ENDPOINT_APP= BuildConfig.BASE_URL + "/app";

    public static final String ENDPOINT_CATGORIES = BuildConfig.BASE_URL + "/wp-json/wp/v2/categories";
    public static final String ENDPOINT_POST = BuildConfig.BASE_URL + "/wp-json/wp/v2/posts";
    public static final String ENDPOINT_POST_BY_CATEGORY = BuildConfig.BASE_URL + "/wp-json/wp/v2/posts";
    public static final String ENDPOINT_GALLERY = "http://api.go-aplikasi.co/scraper/mdt/img.php";

}