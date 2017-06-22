package grsu.bnicky.by.countries;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.text.DecimalFormat;

import data.JSONCountryInfoPareser;
import model.Country;
import model.CountryInfo;
import svg.SvgSoftwareLayerSetter;
import util.Utils;

public class CountryDetailInfo extends AppCompatActivity{

    private ImageView imgDetCountryFlag;
    private TextView textDetCountryName;
    private TextView textDetailRegionName;
    private TextView textDetailCapitalName;
    private TextView textDetailLanguageName;
    private TextView textDetailTerritoryName;
    private TextView textDetailPopulationName;
    private TextView textDetailDensityName;
    private TextView textDetailCurrencyName;
    private TextView textDetailInternetDomainName;
    private TextView textDetailTelephoneCodeName;

    private ImageView imgDetailRegionView;

    private Toolbar toolbarCountryDetailInfo;

    private RequestBuilder<PictureDrawable> requestBuilder;
    CountryInfo countryInfo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.country_detail_info);
        countryInfo = new CountryInfo();

        imgDetCountryFlag = (ImageView)findViewById(R.id.imgViewDetailCountryFlag);
        textDetCountryName = (TextView)findViewById(R.id.textDetailCountryName);
        textDetailRegionName = (TextView)findViewById(R.id.textDetailRegionName);
        textDetailCapitalName = (TextView)findViewById(R.id.textDetailCapitalName);
        textDetailLanguageName = (TextView)findViewById(R.id.textDetailLanguageName);
        textDetailTerritoryName = (TextView)findViewById(R.id.textDetailTerritoryName);
        textDetailPopulationName = (TextView)findViewById(R.id.textDetailPopulationName);
        textDetailDensityName = (TextView)findViewById(R.id.textDetailDensityName);
        textDetailCurrencyName = (TextView)findViewById(R.id.textDetailCurrencyName);
        textDetailInternetDomainName = (TextView)findViewById(R.id.textDetailInternetDomainName);
        textDetailTelephoneCodeName = (TextView)findViewById(R.id.textDetailTelephoneCodeName);

        imgDetailRegionView = (ImageView)findViewById(R.id.imgDetailRegionView);

        toolbarCountryDetailInfo = (Toolbar)findViewById(R.id.toolbarCountryDetailInfo);
        setSupportActionBar(toolbarCountryDetailInfo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        String countryName = getIntent().getStringExtra("country_name");
        String countryCapital = getIntent().getStringExtra("country_capital");

        if(!Utils.isOnline(CountryDetailInfo.this)) {
            connectionDialog();
        }else {

            if (countryName != null) {
                if (countryName.contains("Å")) {
                    countryName = countryName.replace("Å", "A");
                }

                String neededCountryName = countryName.replaceAll(" ", "%20");
                String url_country = Utils.COUNTRY_URL;
                loadDetailCountryData(neededCountryName, url_country);
            }
            if (countryCapital != null) {
                if (countryCapital.contains("Å")) {
                    countryCapital = countryCapital.replace("Å", "A");
                }

                String neededCountryCapital = countryCapital.replaceAll(" ", "%20");
                String url_capital = Utils.CAPITAL_URL;
                loadDetailCountryData(neededCountryCapital, url_capital);
            }
        }
    }

    private void loadDetailCountryData(final String countryName, final String url){
        final ProgressDialog progressDialog = new ProgressDialog(this);
        progressDialog.setMessage("Loading data...");
        progressDialog.show();

        final RequestQueue requestQueue = Volley.newRequestQueue(this);

        StringRequest stringRequest = new StringRequest(Request.Method.GET,
                url + countryName,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressDialog.dismiss();
                        Log.v("Response",response);
                        countryInfo = JSONCountryInfoPareser.getCountryInfo(response);
                        requestQueue.stop();
                        textDetCountryName.setText(countryInfo.getCountryInfoName());
                        textDetailRegionName.setText(countryInfo.getCountryInfoRegion());
                        getSupportActionBar().setTitle(countryInfo.getCountryInfoName());


                        Utils.iconRegionSetter(countryInfo.getCountryInfoRegion(),imgDetailRegionView);

                        textDetailCapitalName.setText(countryInfo.getCountryInfoCapital());
                        textDetailLanguageName.setText(countryInfo.getCountryInfoLanguage());
                        textDetailTerritoryName.setText(countryInfo.getCountryInfoTerritory());
                        textDetailPopulationName.setText(countryInfo.getCountryInfoPopulation());

                        if(!countryInfo.getCountryInfoTerritory().equals("null")) {
                            double density = (Double.valueOf(countryInfo.getCountryInfoPopulation())) /
                                    (Double.valueOf(countryInfo.getCountryInfoTerritory()));

                            DecimalFormat decimalFormat = new DecimalFormat("#.#");
                            String densityInfo = decimalFormat.format(density);
                            countryInfo.setCountryInfoDensity(densityInfo);
                            textDetailDensityName.setText(densityInfo);
                        }else{
                            textDetailDensityName.setText("-");
                            textDetailTerritoryName.setText("-");
                        }

                        textDetailCurrencyName.setText(countryInfo.getCountryInfoCurrency());
                        textDetailInternetDomainName.setText(countryInfo.getCountryInfoInternetDomain());
                        textDetailTelephoneCodeName.setText(countryInfo.getCountryInfoPhoneCode());

                        requestBuilder = Glide.with(CountryDetailInfo.this).as(PictureDrawable.class)
                                .listener(new SvgSoftwareLayerSetter());
                        Uri uri = Uri.parse(countryInfo.getCountryInfoFlag());
                        requestBuilder.load(uri).into(imgDetCountryFlag);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        progressDialog.dismiss();
                        Toast.makeText(getApplicationContext(), "There is no such country.", Toast.LENGTH_LONG).show();
                        finish();
                    }
                });


        requestQueue.add(stringRequest);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void connectionDialog() {
        AlertDialog.Builder checkBuilder = new AlertDialog.Builder(CountryDetailInfo.this);
        checkBuilder.setIcon(R.drawable.ic_problem_report);
        checkBuilder.setTitle("Network error.");
        checkBuilder.setMessage("Check your Internet connection");

        checkBuilder.setNegativeButton("Back", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = checkBuilder.create();
        alertDialog.show();
    }
}
