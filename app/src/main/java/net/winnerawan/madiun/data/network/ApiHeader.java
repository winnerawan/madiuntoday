package net.winnerawan.madiun.data.network;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Copyright 2017 Winnerawan T
 * Unauthorized copying of this file, via any medium is strictly
 * prohibited Proprietary and confidential
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2017
 */

//@Singleton
public class ApiHeader {

    @Expose
    @SerializedName("Authorization")
    private String authorization;

}
