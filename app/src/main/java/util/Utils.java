package util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.ImageView;

import org.json.JSONException;
import org.json.JSONObject;

import grsu.bnicky.by.countries.R;

public class Utils {

    public static final String REGION_URL = "https://restcountries.eu/rest/v2/region/";
    public static final String COUNTRY_URL = "https://restcountries.eu/rest/v2/name/";
    public static final String CAPITAL_URL = "https://restcountries.eu/rest/v2/capital/";

    public static final String region_africa = "Africa";
    public static final String region_americas = "Americas";
    public static final String region_asia = "Asia";
    public static final String region_europe = "Europe";
    public static final String region_oceania = "Oceania";

    public static JSONObject getObject(String tagName, JSONObject jsonObject) throws JSONException {
        return  jsonObject.getJSONObject(tagName);
    }

    public static String getString(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getString(tagName);
    }

    public static float getFloat(String tagName, JSONObject jsonObject) throws JSONException{
        return (float)jsonObject.getDouble(tagName);
    }

    public static double getDouble(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getDouble(tagName);
    }

    public static int getInt(String tagName, JSONObject jsonObject) throws JSONException{
        return jsonObject.getInt(tagName);
    }

    public static void iconRegionSetter(String name, ImageView imageView){
        if(name.equals(region_africa)){
            imageView.setImageResource(R.drawable.africa);
            imageView.setBackgroundResource(R.drawable.africa_icon);
        }
        if(name.equals(region_americas)){
            imageView.setImageResource(R.drawable.americas);
            imageView.setBackgroundResource(R.drawable.americas_icon);
        }
        if(name.equals(region_asia)){
            imageView.setImageResource(R.drawable.asia);
            imageView.setBackgroundResource(R.drawable.asia_icon);
        }
        if(name.equals(region_europe)){
            imageView.setImageResource(R.drawable.europe);
            imageView.setBackgroundResource(R.drawable.europe_icon);
        }
        if(name.equals(region_oceania)){
            imageView.setImageResource(R.drawable.oceania);
            imageView.setBackgroundResource(R.drawable.oceania_icon);
        }
    }

    public static boolean isOnline(Context context)
    {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo != null && netInfo.isConnectedOrConnecting())
        {
            return true;
        }
        return false;
    }
}
