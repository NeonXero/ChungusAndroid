package net.neonlotus.chungusadmin;

import android.app.Application;
import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.google.gson.Gson;

import net.neonlotus.chungusadmin.networking.RestClient;
import net.neonlotus.chungusadmin.roompersistence.BuddyDatabase;

import androidx.appcompat.app.AlertDialog;
import androidx.lifecycle.MutableLiveData;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import uk.co.chrisjenx.calligraphy.CalligraphyConfig;

public class ChungusApp extends Application {
    private final String TAG = getClass().getSimpleName();
    private static Context context;

    //New for Retrofit
    Retrofit retrofit;
    Retrofit updater;
    RestClient rest;
    RestClient restUpdater;
    HttpLoggingInterceptor logging;
    OkHttpClient.Builder httpClient;


    private final Gson gson = new Gson();


    public BuddyDatabase buddyDatabase;


    private ProgressDialog pd;
    private AlertDialog dialog;


    public static MutableLiveData<String> friendId;


    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "on create");
        context = getApplicationContext();


        //https://futurestud.io/tutorials/retrofit-2-log-requests-and-responses
        logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient = new OkHttpClient.Builder();
        httpClient.addInterceptor(logging);

        /*
        <string name="api_base_url">http://gps.spectrumvoice.com/</string>
    <string name="api_base_url_dev">http://gps-dev.spectrumvoice.com/</string>

    https://chungus-crew.mybluemix.net/api?req=weeklyStatsGrab&guildId=FcIj2voa5B&dl=5217332654608149P&guildName=ChungusCrew
         */

        retrofit = new Retrofit.Builder()
                .baseUrl("https://chungus-crew.mybluemix.net/")
//                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        updater = new Retrofit.Builder()
                .baseUrl("https://www.taransworld.com/GuildView/")
//                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        //https://www.taransworld.com/GuildView/update.pl?guildId=FcIj2voa5B

        rest = retrofit.create(RestClient.class);
        restUpdater = updater.create(RestClient.class);

        //Set up live data ; just to clean up a bit
        initiateLiveData();

        CalligraphyConfig.initDefault(new CalligraphyConfig.Builder()
                .setDefaultFontPath("fonts/HelveticaNeue-Condensed.ttf")
                .setFontAttrId(R.attr.fontPath)
                .build()
        );


        buddyDatabase = buddyDatabase.getDatabase(getAppContext());


    }

    private void initiateLiveData() {
        friendId = new MutableLiveData<>();

    }


    public static Context getAppContext() {
        return ChungusApp.context;
    }

    public void startProgress(Context c) {
        stopProgress();

//        mainActivity.runOnUiThread(() -> {
        pd = new ProgressDialog(c);
        pd.setCancelable(false);
        pd.setMessage(getString(R.string.progress_dialog_text));
        pd.show();
//        });
    }

    public void stopProgress() {
//        mainActivity.runOnUiThread(() -> {
        if (pd != null && pd.isShowing()) {
            pd.cancel();
            pd.hide();
        }
//        });
    }


    public RestClient getRest() {
        return rest;
    }

    public RestClient getRestUpdater() {
        return restUpdater;
    }

    public static Context getContext() {
        return context;
    }

}