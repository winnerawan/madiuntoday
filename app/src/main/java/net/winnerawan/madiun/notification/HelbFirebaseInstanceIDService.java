/*
 * Copyright (c) 2018. Winnerawan T - All Rights Reserved
 * Unauthorized copying of this file, via any medium is strictly prohibited
 * Proprietary and confidential|
 * Written by Winnerawan T <winnerawan@gmail.com>, September 2018
 */

package net.winnerawan.madiun.notification;

import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;


public class HelbFirebaseInstanceIDService extends FirebaseInstanceIdService {
    private static final String TAG = HelbFirebaseInstanceIDService.class.getSimpleName();

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String refreshedToken = FirebaseInstanceId.getInstance().getToken();

        // Saving reg id to shared preferences
        storeRegIdInPref(refreshedToken);

        // sending reg id to your server
        sendFcmTokenToServer(refreshedToken);

        // Notify UI that registration has completed, so the progress indicator can be hidden.
        Intent registrationComplete = new Intent("KEY_REG_COMPLETE");
        registrationComplete.putExtra("token", refreshedToken);
        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
    }

    private void sendFcmTokenToServer(String token) {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences(PREF_NAME, 0);
//        String api_token = pref.getString(KEY_TOKEN, "");
//        Log.e(TAG, "sendRegistrationToServer: " + token);
//
//        AndroidNetworking.put(ApiEndPoint.UPDATE_FCM_TOKEN)
//                .setContentType("application/x-www-form-urlencoded")
//                .addHeaders("Authorization", "Bearer" + api_token)
//                .addBodyParameter("fcm_token", token)
//                .build()
//                .getAsJSONObject(new JSONObjectRequestListener() {
//                    @Override
//                    public void onResponse(JSONObject response) {
//
//                    }
//
//                    @Override
//                    public void onError(ANError anError) {
//
//                    }
//                });
    }

    private void sendRegistrationToServer(final String token) {
        // sending gcm token to server
        Log.e(TAG, "sendRegistrationToServer: " + token);
    }

    private void storeRegIdInPref(String token) {
//        SharedPreferences pref = getApplicationContext().getSharedPreferences(AppConstants.PREF_NAME, 0);
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putString(KEY_FCM_TOKEN, token).apply();
    }
}
