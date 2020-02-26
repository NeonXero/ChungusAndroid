package net.neonlotus.chungusadmin.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import net.neonlotus.chungusadmin.ChungusApp;
import net.neonlotus.chungusadmin.R;
import net.neonlotus.chungusadmin.networking.models.PlayerModel;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import butterknife.ButterKnife;

public class PlayerAdapter extends RecyclerView.Adapter<PlayerAdapter.MyViewHolder> implements Filterable {
    private List<PlayerModel> mDataset;
    private List<PlayerModel> mDatasetFiltered;
    private Context context;
    public ChungusApp mApplication;

    private String TAG = getClass().getSimpleName();

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout parent;
        public TextView tvPlayerName;
        public TextView tvPlayerData;

        public MyViewHolder(ConstraintLayout v) {
            super(v);
            this.parent = v.findViewById(R.id.parent);
            this.tvPlayerName = v.findViewById(R.id.tvPlayerName);
            this.tvPlayerData = v.findViewById(R.id.tvPlayerData);
        }
    }

    public PlayerAdapter(List<PlayerModel> myDataset, Context c) {
        context = c;
        mApplication = (ChungusApp) context.getApplicationContext();

        mDataset = myDataset;
        mDatasetFiltered = myDataset;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        ConstraintLayout v = (ConstraintLayout) LayoutInflater.from(parent.getContext()).inflate(R.layout.player_row, parent, false);

        ButterKnife.bind(this, parent);
        return new MyViewHolder(v);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        PlayerModel bindMe = mDatasetFiltered.get(position);

        holder.tvPlayerName.setText(bindMe.getPlayerName() + " (" + bindMe.getHeroLevel() + ")" + " - Age: " + bindMe.getDaysInGuildFormatted());

        holder.tvPlayerData.setText("Gold - All Time: " + bindMe.getGoldTotalFormatted() + " - This Week: " + bindMe.getGoldCurrentFormatted() +
                "\n" +
                "Seals - All Time: " + bindMe.getSealsTotalFormatted() + " - This Week: " + bindMe.getSealsCurrentFormatted() +
                "\n" +
                "Trophies - All Time: " + bindMe.getTrophiesTotalFormatted() + " - This Week: " + bindMe.getTrophiesCurrentFormatted());

    }

    @Override
    public int getItemCount() {
        return mDatasetFiltered != null ? mDatasetFiltered.size() : 0;
    }

    public Context getContext() {
        return context;
    }

    public List<PlayerModel> getmDatasetFiltered() {
        return mDatasetFiltered;
    }

    public void setData(List<PlayerModel> data) {
        if (mDataset != null)
            mDataset.clear();
        if (mDatasetFiltered != null)
            mDatasetFiltered.clear();

        mDataset = data;
        mDatasetFiltered = data;
        notifyDataSetChanged();
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    mDatasetFiltered = mDataset;
                } else {
                    List<PlayerModel> filteredList = new ArrayList<>();
                    for (PlayerModel row : mDataset) {
                        if (row.getPlayerName().toLowerCase().contains(charString)) {
                            filteredList.add(row);
                        }
                    }

                    mDatasetFiltered = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = mDatasetFiltered;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                mDatasetFiltered = (ArrayList<PlayerModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }
}