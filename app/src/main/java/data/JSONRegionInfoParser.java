package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import model.Country;
import util.Utils;

public class JSONRegionInfoParser {

    public static List<Country> getCountriesInRegion(String data){

        List<Country> countries = new ArrayList<>();
        try {

            JSONArray jsonArray = new JSONArray(data);

            for(int i = 0; i < jsonArray.length(); i++){

                JSONObject countryInfo = jsonArray.getJSONObject(i);
                Country country = new Country();

                country.setCountryName(Utils.getString("name",countryInfo));
                country.setCountryCapital(Utils.getString("capital",countryInfo));
                country.setCountryFlag(Utils.getString("flag",countryInfo));

                countries.add(country);
            }

            return countries;
        } catch (JSONException e) {
            return null;
        }
    }
}
