package net.neonlotus.chungusadmin.roompersistence.AsyncTaskOperators;

import android.os.AsyncTask;

import net.neonlotus.chungusadmin.ChungusApp;
import net.neonlotus.chungusadmin.ui.main.MainFragment;

import androidx.fragment.app.Fragment;

public class ChatGetMessagesTask extends AsyncTask<String, Void, String> {
    static final String TAG = ChatGetMessagesTask.class.getSimpleName();
    ChungusApp mApplication = (ChungusApp) ChungusApp.getAppContext();
    Fragment frag;
    String retMe;

    public ChatGetMessagesTask(Fragment f) {
        this.frag = f;
    }

    @Override
    protected String doInBackground(String... params) {
        return "dong";

    }

    @Override
    protected void onPostExecute(String returnString) {
        if (frag instanceof MainFragment) {
            //((FriendChatFragment) frag).handleMessageTask(retFriend);
        }
    }
}