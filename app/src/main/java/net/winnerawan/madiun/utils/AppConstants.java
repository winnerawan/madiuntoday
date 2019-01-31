package net.winnerawan.madiun.utils;


/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, June 2017
 */

public final class AppConstants {

    public static final String STATUS_CODE_SUCCESS = "success";
    public static final String STATUS_CODE_FAILED = "failed";
    public static final String MAIL_TO = "mailto: winnerawan@gmail.com";
    public static final String CONTACT = "Contact";
    public static final String PROFILE_LINK = "https://www.facebook.com/winnerawan";

    public static final int API_STATUS_CODE_LOCAL_ERROR = 0;

    public static final String DB_NAME = "madiun.db";
    public static final String PREF_NAME = "madiun_pref";

    public static final long NULL_INDEX = -1L;


    public static final String TIMESTAMP_FORMAT = "yyyyMMdd_HHmmss";
    public static final String EXTRAS_DATA_CATEGORY = "category";


    /**
     * CONSTANT VALUE TO SET
     * EXCLUDE CATEGORY ID FROM TAB NEWS
     */

    public static final int CATEGORY_MADIUN_THIS_WEEK = 115;
    public static final int CATEGORY_FOTO_BERITA = 8;
    public static final int CATEGORY_TV = 29;
    public static final int CATEGORY_DBH_CHT = 105;
    public static final String SLUG_MTDTV = "mtdtv";
    public static final String NAME_MTDTV = "MadiunTodayTV";


    private AppConstants() {
        // This utility class is not publicly instantiable
    }
}
