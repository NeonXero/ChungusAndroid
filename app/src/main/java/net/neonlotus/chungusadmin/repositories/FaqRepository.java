package net.neonlotus.chungusadmin.repositories;

import android.content.Context;

import com.google.gson.Gson;

import net.neonlotus.chungusadmin.ChungusApp;

import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

public class FaqRepository {
    private static final String TAG = FaqRepository.class.getSimpleName();
    private static Gson gson = new Gson();
    private ChungusApp mApplication = (ChungusApp) ChungusApp.getAppContext();
    //private RestCalls restCalls = new RestCalls(ChungusApp.getAppContext());
    private Context context;

    public static MutableLiveData<String> restError;

    private LifecycleOwner lifeCycleOwner;

    public FaqRepository(Context context) {
        setLiveData(context);
    }

    private void setLiveData(Context c) {
        this.context = c;
        lifeCycleOwner = (LifecycleOwner) context;

        if (this.restError == null)
            this.restError = new MutableLiveData<>();
    }

    public void getFAQs() {
        /*if (mApplication.isConnected()) {
            restCalls.getFAQs().observe(lifeCycleOwner, resp -> {
                if (resp != null) {
                    if (Constants.LOG_REST_RESPONSE)
                        Log.d(TAG, resp.status + " on getFAQ; " + gson.toJson(resp.data));

                    if (resp.status == 200) {
                        persist(resp.data);

                        GetFaqsTask task = new GetFaqsTask();
                        task.execute();
                    } else {
                        getFaqError();
                    }
                } else {
                    getFaqError();
                    mApplication.stopProgress();
                }
            });

        } else {
            GetFaqsTask task = new GetFaqsTask();
            task.execute();
        }*/
    }


    /*public static void persist(FaqResponse response) {
        try {
            UpsertFaq upsert = new UpsertFaq(response);
            upsert.execute();
        } catch (Exception e) {
            Log.d(TAG, "problem persisting faq " + e.getLocalizedMessage());
        }
    }*/

    /*private static class GetFaqsTask extends AsyncTask<Void, Void, List<FaqObject>> {
        ChungusApp app = (ChungusApp) ChungusApp.getAppContext();

        @Override
        protected List<FaqObject> doInBackground(Void... param) {
            List<FaqObject> fromDB = new ArrayList<>();
            for (FaqObject f : app.buddyDatabase.faqDAO().getAllFaqs()) {
                fromDB.add(f);
            }

            return fromDB;
        }

        @Override
        protected void onPostExecute(List<FaqObject> faqs) {
            getFaqsSuccess.postValue(faqs);
        }
    }*/
}