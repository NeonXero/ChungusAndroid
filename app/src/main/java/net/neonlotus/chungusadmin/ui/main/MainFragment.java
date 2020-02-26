package net.neonlotus.chungusadmin.ui.main;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import net.neonlotus.chungusadmin.R;
import net.neonlotus.chungusadmin.adapters.PlayerAdapter;
import net.neonlotus.chungusadmin.networking.ErrorUtils;
import net.neonlotus.chungusadmin.networking.ResponseCarrier;
import net.neonlotus.chungusadmin.networking.models.GuildModel;
import net.neonlotus.chungusadmin.networking.models.PlayerModel;
import net.neonlotus.chungusadmin.tools.Constants;
import net.neonlotus.chungusadmin.tools.Utils;
import net.neonlotus.chungusadmin.ui.BaseFragment;
import net.neonlotus.chungusadmin.ui.views.RecyclerViewDivider;

import java.util.ArrayList;
import java.util.Map;

import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.BindView;
import butterknife.OnClick;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainFragment extends BaseFragment {
    private final String TAG = getClass().getSimpleName();
    private Context context;

    private ArrayList<PlayerModel> data;
    private PlayerAdapter adapter;

    @BindView(R.id.rvPlayers)
    RecyclerView rvPlayers;
    @BindView(R.id.playerFilterInput)
    TextInputEditText playerFilterInput;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getActivity();

    }

    @Override
    public void onPause() {
        super.onPause();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onResume() {
        super.onResume();

        playerFilterInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (adapter != null)
                    adapter.getFilter().filter(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });


    }

    @Override
    public View provideYourFragmentView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public int setActionbarVisibility() {
        /*
        Choices are NO_BUTTONS, BACK_ARROW, HAMBURGER_MENU, NO_BAR
         */
        return Constants.HAMBURGER_MENU;
    }

    @Override
    public String setFragmentTag() {
        return nc.getCurrentDestination().getLabel().toString();
    }

    @OnClick(R.id.week)
    void clickweek() {

        /*
        https://chungus-crew.mybluemix.net/api?req=weeklyStatsGrab&guildId=FcIj2voa5B&dl=5217332654608149P&guildName=ChungusCrew

        https://chungus-crew.mybluemix.net/kH8b6dvhuLDvtQvgFdPx/#flow/635ea456.028994

        https://www.taransworld.com/GuildView/main.pl?guildId=FcIj2voa5B
         */

        //mApplication.getRest().getWeeklyStats();
        //mApplication.getRest().test();

        //Call<ResponseBody> request = mApplication.getRest().test();
        Call<ResponseBody> request = mApplication.getRest().getWeeklyStats();

        request.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if (response.isSuccessful()) {
                    ResponseCarrier resp = ErrorUtils.checkResponse(response);

                    if (resp.isSuccess()) {
                        GuildModel goodResp = gson.fromJson(resp.getResponse(), GuildModel.class);

                        Map<String, PlayerModel> boyz = goodResp.getChungusCrew();

                        ArrayList<PlayerModel> playas = new ArrayList<>();
                        for (Map.Entry e : boyz.entrySet()) {
                            PlayerModel p = (PlayerModel) e.getValue();
                            p.setPlayerName((String) e.getKey());
                            playas.add(p);
                        }

                        listUI(playas);

                    } else {

                    }
                } else {

                }

                mApplication.stopProgress();
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Toast.makeText(context, "woo boo", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @OnClick(R.id.refresh)
    void clickRefresh() {
//        Call<ResponseBody> request = mApplication.getRestUpdater().updateGuild();
//
//        request.enqueue(new Callback<ResponseBody>() {
//            @Override
//            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//                if (response.isSuccessful()) {
//                    ResponseCarrier resp = ErrorUtils.checkResponse(response);
//
//                    if (resp.isSuccess()) {
//                        Toast.makeText(context, "Good update", Toast.LENGTH_SHORT).show();
//                    } else {
//                        Toast.makeText(context, "not good update", Toast.LENGTH_SHORT).show();
//                    }
//                } else {
//                    Toast.makeText(context, "other not good update", Toast.LENGTH_SHORT).show();
//                }
//
//                mApplication.stopProgress();
//            }
//
//            @Override
//            public void onFailure(Call<ResponseBody> call, Throwable t) {
//                Toast.makeText(context, "update failure", Toast.LENGTH_SHORT).show();
//            }
//        });

        Uri uri = Uri.parse("https://www.taransworld.com/GuildView/update.pl?guildId=FcIj2voa5B");
//        Uri uri = Uri.parse("https://www.taransworld.com/GuildView/main.pl?guildId=FcIj2voa5B");
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        startActivity(intent);
        //Toast.makeText(context, "TODO figure out auth to make refresh call", Toast.LENGTH_SHORT).show();

        /*

        neonxero
        RaaYXq
         */
    }

    private void listUI(ArrayList<PlayerModel> passed) {
        data = new ArrayList<>();
        data.clear();
        data = passed;

        adapter = new PlayerAdapter(data, context);
        rvPlayers.setAdapter(adapter);
        rvPlayers.setHasFixedSize(true);
        rvPlayers.setLayoutManager(new LinearLayoutManager(context));
        while (rvPlayers.getItemDecorationCount() > 0) {
            rvPlayers.removeItemDecorationAt(0);
        }
        rvPlayers.addItemDecoration(new RecyclerViewDivider(Utils.dpToPx(context, 16), Utils.dpToPx(context, 8)));
    }


    // ============================================================
    // ============================================================
    // BRAND NEW OBSERVABLE SECTION TO MATCH OTHER FRAGMENTS ======
    // ============================================================
    // ============================================================

    private Observer<String> registerObs = new Observer<String>() {
        @Override
        public void onChanged(String s) {
            // TODO: 2020-02-16
        }
    };


}