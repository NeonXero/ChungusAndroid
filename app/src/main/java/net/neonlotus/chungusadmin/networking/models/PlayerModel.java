package net.neonlotus.chungusadmin.networking.models;

import android.os.Build;

import com.google.gson.annotations.SerializedName;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.Period;

import androidx.annotation.RequiresApi;

public class PlayerModel {

    @SerializedName("Guild Rank")
    private int guildRank;
    @SerializedName("Hero Level")
    private int heroLevel;
    @SerializedName("Days in Guild")
    private int daysInGuild;
    @SerializedName("Last Played")
    private String lastPlayed;
    @SerializedName("Gold Total")
    private int goldTotal;
    @SerializedName("Seals Total")
    private int sealsTotal;
    @SerializedName("Trophies Total")
    private int trophiesTotal;
    @SerializedName("Gold Current Wk")
    private int goldCurrent;
    @SerializedName("Seals Current Wk")
    private int sealsCurrent;
    @SerializedName("Trophies Current Wk")
    private int trophiesCurrent;
    @SerializedName("Invasion Current wk")
    private int invasionCurrent;

    private transient String playerName;

    public int getGuildRank() {
        return guildRank;
    }

    public void setGuildRank(int guildRank) {
        this.guildRank = guildRank;
    }

    public int getHeroLevel() {
        return heroLevel;
    }

    public void setHeroLevel(int heroLevel) {
        this.heroLevel = heroLevel;
    }

    public int getDaysInGuild() {
        return daysInGuild;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    public String getDaysInGuildFormatted() {
        LocalDate initial = LocalDate.now();
        LocalDate start = initial.minusDays(getDaysInGuild());

        Period p = Period.between(start, initial);

        int years = p.getYears();
        int months = p.getMonths();
        int days = p.getDays();

        StringBuilder sb = new StringBuilder();
        if (years > 0)
            sb.append(years).append("Y ");
        if (months > 0)
            sb.append(months).append("M ");
        if (days > 0)
            sb.append(days + "D");

        return sb.toString();
    }

    public void setDaysInGuild(int daysInGuild) {
        this.daysInGuild = daysInGuild;
    }

    public String getLastPlayed() {
        return lastPlayed;
    }

    public void setLastPlayed(String lastPlayed) {
        this.lastPlayed = lastPlayed;
    }

    public int getGoldTotal() {
        return goldTotal;
    }

    public String getGoldTotalFormatted() {
        return NumberFormat.getInstance().format(getGoldTotal());
    }

    public void setGoldTotal(int goldTotal) {
        this.goldTotal = goldTotal;
    }

    public int getSealsTotal() {
        return sealsTotal;
    }

    public String getSealsTotalFormatted() {
        return NumberFormat.getInstance().format(getSealsTotal());
    }

    public void setSealsTotal(int sealsTotal) {
        this.sealsTotal = sealsTotal;
    }

    public int getTrophiesTotal() {
        return trophiesTotal;
    }

    public String getTrophiesTotalFormatted() {
        return NumberFormat.getInstance().format(getTrophiesTotal());
    }

    public void setTrophiesTotal(int trophiesTotal) {
        this.trophiesTotal = trophiesTotal;
    }

    public int getGoldCurrent() {
        return goldCurrent;
    }

    public String getGoldCurrentFormatted() {
        return NumberFormat.getInstance().format(getGoldCurrent());
    }

    public void setGoldCurrent(int goldCurrent) {
        this.goldCurrent = goldCurrent;
    }

    public int getSealsCurrent() {
        return sealsCurrent;
    }

    public String getSealsCurrentFormatted() {
        return NumberFormat.getInstance().format(getSealsCurrent());
    }

    public void setSealsCurrent(int sealsCurrent) {
        this.sealsCurrent = sealsCurrent;
    }

    public int getTrophiesCurrent() {
        return trophiesCurrent;
    }

    public String getTrophiesCurrentFormatted() {
        return NumberFormat.getInstance().format(getTrophiesCurrent());
    }

    public void setTrophiesCurrent(int trophiesCurrent) {
        this.trophiesCurrent = trophiesCurrent;
    }

    public int getInvasionCurrent() {
        return invasionCurrent;
    }

    public void setInvasionCurrent(int invasionCurrent) {
        this.invasionCurrent = invasionCurrent;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
