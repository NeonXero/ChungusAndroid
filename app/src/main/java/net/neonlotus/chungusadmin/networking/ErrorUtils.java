package net.neonlotus.chungusadmin.networking;

import android.util.Log;

import com.google.gson.Gson;

import net.neonlotus.chungusadmin.ChungusApp;
import net.neonlotus.chungusadmin.R;
import net.neonlotus.chungusadmin.networking.models.ErrorResponse;

import org.json.JSONException;
import org.json.JSONObject;

import okhttp3.ResponseBody;
import retrofit2.Response;

public class ErrorUtils {
    private static final String TAG = "ErrorUtils";

    public static ResponseCarrier checkResponse(Response<ResponseBody> rb) {
        String raw = "";
        try {
            raw = rb.body().string();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return checkStatus(raw);
    }

    public static ResponseCarrier checkStatus(String s) {
        Gson gson = new Gson();
        try {
            JSONObject job = new JSONObject(s);

            if (didResponseSucceed(job)) {
                return new ResponseCarrier(s, true, "");
            } else {
                ErrorResponse err = gson.fromJson(s, ErrorResponse.class);
                Log.d(TAG, "Rest error = " + err.getErrorMessage());
                return new ResponseCarrier(s, false, err.getErrorMessage());
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return new ResponseCarrier(s, false, ChungusApp.getContext().getString(R.string.api_error));
        }
    }

    public static boolean didResponseSucceed(JSONObject json) {
        try {
            return !json.has("Error");
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}