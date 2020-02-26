package net.neonlotus.chungusadmin.tools;

import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import eu.giovannidefrancesco.easysharedprefslib.IStorage;
import eu.giovannidefrancesco.easysharedprefslib.SharedPreferenceStorage;

import static android.content.Context.MODE_PRIVATE;

public class PrefUtil {
    private static IStorage storage;
    private static final String PREF_NAME = "Prefs";
    private static final String TAG = "PrefUtil";
    private static final Gson gson = new Gson();

    //Default values for missing entries
    private static final String defaultString = "";
    private static int defaultNumber = -1;
    private static final boolean defaultBoolean = false;

    //Tag block
    private static final String temptag1 = "temp1";
    private static final String temptag2 = "temp2";


    public static void initStorage(Context c) {
        if (storage == null)
            storage = new SharedPreferenceStorage(c, PREF_NAME, MODE_PRIVATE);
    }


    public static void saveJWT(String s, Context c) {
        initStorage(c);
        storage.store(temptag1, s);
        Log.d(TAG, "JWT Updated: " + s);
    }

    public static String getJWT(Context c) {
        initStorage(c);
        return storage.get(temptag1, defaultString);
    }


    public static void saveUserID(String s, Context c) {
        initStorage(c);
        storage.store(temptag2, s);
    }

    public static String getUserID(Context c) {
        initStorage(c);
        return storage.get(temptag2, defaultString);
    }


}