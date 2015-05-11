package com.gabilheri.octokitten.utils;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.text.format.DateFormat;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by <a href="mailto:marcusandreog@gmail.com">Marcus Gabilheri</a>
 *
 * @author Marcus Gabilheri
 * @version 1.0
 * @since 1/27/15.
 */
public class CustomUtils {


    public static Typeface getGithubTypeface(Context context) {
        return Typeface.createFromAsset(context.getApplicationContext().getAssets(), "octicons.ttf");
    }

    public static String getBase64string(String base64) {
        byte[] data = Base64.decode(base64, Base64.DEFAULT);
        try {
            return new String(data, "UTF-8");
        } catch (UnsupportedEncodingException ex) {
            return "Problem decoding base 64 String!!";
        }
    }

    /**
     *
     * @param date
     *        Date to be formatted
     * @param context
     *        Context to be used by the DateFormat
     * @return
     *      The formatted date in medium format.
     */
    public static String getMediumDate(Date date, Context context) {
        java.text.DateFormat format = DateFormat.getMediumDateFormat(context);
        return format.format(date);
    }

    public static String getMediumDate(String str, Context context) {
        return getMediumDate(getDateFromString(str), context);
    }

    public static String formatDate(int year, int month, int day) {
        String strMonth = (month + 1) > 9 ? "" + (month + 1) : "0" + (month + 1);
        return strMonth + "/" + day + "/" + year;
    }

    public static Date getDate(String dateString) {
        String[] components = dateString.split("/");
        int month = Integer.parseInt(components[0]) - 1;
        int dayOfMonth = Integer.parseInt(components[1]);
        int year = Integer.parseInt(components[2]);
        return getDate(year, month, dayOfMonth);
    }

    public static Date getDate(int year, int month, int day) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, month, day);
        return calendar.getTime();
    }

    public static Date getDateFromString(String dateString) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");

        try {
            return sdf.parse(dateString);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static String getFancyDayNumber(String day) {
        int iDay = Integer.parseInt(day);
        if(iDay < 10) {
            if(day.endsWith("1")) {
                return day + "st";
            } else if(day.endsWith("2")) {
                return day + "nd";
            } else if(day.endsWith("3")) {
                return day + "rd";
            }
        }
        return day + "th";
    }

    public static boolean isValidEmailAddress(String email) {
        String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
        java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
        java.util.regex.Matcher m = p.matcher(email);
        return m.matches();
    }

    public static double roundDouble(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static java.sql.Date getSqlDate(Date date) {
        return new java.sql.Date(date.getTime());
    }

    public static String getDateToSubmit(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return sdf.format(date);
    }

    public static String getDateNow() {
        Calendar c = Calendar.getInstance();
        return formatDate(c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
    }

    public static void lockOrientation(Activity context) {
        context.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT);
    }

    public static void addFragmentToContainer(Fragment fragment, int containerID, Activity activity) {
        FragmentManager fragmentManager = activity.getFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(containerID, fragment).addToBackStack(null).commit();
    }

    public static boolean hasNumber(String str) {
        for(int i = 0; i < str.length(); i++) {
            if(Character.isDigit(str.charAt(i))) {
                return true;
            }
        }
        return false;
    }

    public static void hideSoftKeyboard (Context context, View view) {
        try {
            InputMethodManager imm = (InputMethodManager)context.getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getApplicationWindowToken(), 0);
        }
        catch (Exception ex) {
            Log.w("HIDE KEYBOARD", ex.toString());
        }
    }
}
