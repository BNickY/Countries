package data;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import model.Country;
import model.CountryInfo;
import util.Utils;

public class JSONCountryInfoPareser {

    public static CountryInfo getCountryInfo(String data){
        CountryInfo countryInfo = new CountryInfo();
        try {

            JSONArray jsonArray = new JSONArray(data);

            JSONObject countryInfoObject = jsonArray.getJSONObject(0);

            //имя страны
            countryInfo.setCountryInfoName(Utils.getString("name",countryInfoObject));
            //столица страны
            countryInfo.setCountryInfoCapital(Utils.getString("capital",countryInfoObject));
            //флаг страны
            countryInfo.setCountryInfoFlag(Utils.getString("flag",countryInfoObject));

            //интерент домен страны
            JSONArray domainArray = countryInfoObject.getJSONArray("topLevelDomain");
            countryInfo.setCountryInfoInternetDomain(domainArray.getString(0));

            //телефонный код страны
            JSONArray phoneArray = countryInfoObject.getJSONArray("callingCodes");
            countryInfo.setCountryInfoPhoneCode(phoneArray.getString(0));

            //регион
            countryInfo.setCountryInfoRegion(Utils.getString("region",countryInfoObject));

            //население
            countryInfo.setCountryInfoPopulation(Utils.getString("population",countryInfoObject));

            //территория
            countryInfo.setCountryInfoTerritory(Utils.getString("area",countryInfoObject));


            //курс
            JSONArray currencyArray =  countryInfoObject.getJSONArray("currencies");
            StringBuilder stringBuilderCurrency = new StringBuilder();
            for(int i = 0; i < currencyArray.length();i++){
                JSONObject currencyObj = currencyArray.getJSONObject(i);
                if(i == 0){
                    stringBuilderCurrency.append(Utils.getString("code",currencyObj));
                }
                else stringBuilderCurrency.append(", " +Utils.getString("code",currencyObj));
            }
            countryInfo.setCountryInfoCurrency(stringBuilderCurrency.toString());

            //языки
            JSONArray languageArray =  countryInfoObject.getJSONArray("languages");
            StringBuilder stringBuilderLanguage = new StringBuilder();
            for(int i = 0; i < languageArray.length();i++){
                JSONObject languageObj = languageArray.getJSONObject(i);
                if(i == 0){
                    stringBuilderLanguage.append(Utils.getString("name",languageObj));
                }
                else stringBuilderLanguage.append(", " +Utils.getString("name",languageObj));
            }
            countryInfo.setCountryInfoLanguage(stringBuilderLanguage.toString());

            return countryInfo;
        } catch (JSONException e) {
            return null;
        }
    }
}
