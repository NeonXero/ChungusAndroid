package net.neonlotus.chungusadmin.networking.models;

import com.google.gson.annotations.SerializedName;

import java.util.Map;

public class GuildModel {
//    PlayerModel[] ChungusCrew;
//
//    public PlayerModel[] getChungusCrew() {
//        return ChungusCrew;
//    }

    @SerializedName("ChungusCrew")
    Map<String, PlayerModel> chungusCrew;

    public Map<String, PlayerModel> getChungusCrew() {
        return chungusCrew;
    }
}
