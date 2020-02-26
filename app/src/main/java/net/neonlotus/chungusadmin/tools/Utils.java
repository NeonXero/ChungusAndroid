package net.neonlotus.chungusadmin.tools;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import net.neonlotus.chungusadmin.BuildConfig;
import net.neonlotus.chungusadmin.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;


public class Utils {
    private static String TAG = "Utils";


    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        assert imm != null;
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }


    public static String getFormattedDate(String s, String inputFormat, String outputFormat) {
        /*
        Since right now this is just used for chats...
        If no output format is specified, we'll use the constant for d/t/ format
        If an output is specified, use it
        Similarly, if no input is specified, we'll assume the string came in with our DateFormat constant
        If an input is specified, remake the dateformatter with that format
         */
        if (outputFormat.isEmpty())
            outputFormat = Constants.CHAT_MESSAGE_DATE;
        if (!inputFormat.isEmpty()) {
            Constants.df = new SimpleDateFormat(inputFormat);
        }

        Date msgDate = convertUTCToLocal(s);
        SimpleDateFormat format = new SimpleDateFormat(outputFormat);
        return format.format(msgDate);

    }

    //DP to Pixel
    public static int dpToPx(Context c, int dp) {
        DisplayMetrics displayMetrics = c.getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }


    public static Date convertUTCToLocal(String date) {
        TimeZone tz = TimeZone.getDefault();
        Date now;
        try {
            now = Constants.df.parse(date);
            int offsetFromUtc = tz.getOffset(now.getTime()) / 1000 / 60 / 60;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.HOUR, offsetFromUtc); //adds negative hours

            return calendar.getTime();
        } catch (ParseException e) {
            try {
                now = Constants.df2.parse(date);
                int offsetFromUtc = tz.getOffset(now.getTime()) / 1000 / 60 / 60;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.add(Calendar.HOUR, offsetFromUtc); //adds negative hours

                return calendar.getTime();
            } catch (ParseException e2) {
                return new Date();
            }
        }
    }

    public static Date convertUTCToLocal(Date d) {
        TimeZone tz = TimeZone.getDefault();
        int offsetFromUtc = tz.getOffset(d.getTime()) / 1000 / 60 / 60;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, offsetFromUtc); //adds negative hours

        return calendar.getTime();
    }

    public static Date convertLocalToUTC(Date d) {
        TimeZone tz = TimeZone.getDefault();
        int offsetFromUtc = tz.getOffset(d.getTime()) / 1000 / 60 / 60;

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(d);
        calendar.add(Calendar.HOUR, -offsetFromUtc); //subtracts hours

        return calendar.getTime();
    }

    public static Date convertLocalToUTC(String date) {
        TimeZone tz = TimeZone.getDefault();
        try {
            Date now = Constants.df.parse(date);
            int offsetFromUtc = tz.getOffset(now.getTime()) / 1000 / 60 / 60;

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(now);
            calendar.add(Calendar.HOUR, -offsetFromUtc); //subtracts hours

            return calendar.getTime();
        } catch (ParseException e) {
            try {
                Date now = Constants.df2.parse(date);
                int offsetFromUtc = tz.getOffset(now.getTime()) / 1000 / 60 / 60;

                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.add(Calendar.HOUR, -offsetFromUtc); //subtracts hours

                return calendar.getTime();
            } catch (ParseException e2) {
                Date now = new Date();
                int offsetFromUtc = tz.getOffset(now.getTime()) / 1000 / 60 / 60;
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(now);
                calendar.add(Calendar.HOUR, -offsetFromUtc); //subtracts hours
                return calendar.getTime();
            }
        }
    }


    public static int findFragmentIDByStringTag(String s) {
        switch (s) {

            case ("mainFragment"):
                return R.id.mainFragment;
            default:
                return R.id.mainFragment;
        }
    }

    public static String findFragmentTagByID(int id) {
        switch (id) {
            case R.id.mainFragment:
                return "mainFragment";
            default:
                return "";
        }
    }


    public static String getVersionInformation(int type) {
        switch (type) {
            case Constants.VERSION_NAME:
                return "Version: " + BuildConfig.VERSION_NAME;
            case Constants.VERSION_CODE:
                return "Version Code: " + BuildConfig.VERSION_CODE;
            case Constants.VERSION_BOTH:
                return "Version: " + BuildConfig.VERSION_NAME + " Code: " + BuildConfig.VERSION_CODE;
            default:
                return "";
        }
    }

}