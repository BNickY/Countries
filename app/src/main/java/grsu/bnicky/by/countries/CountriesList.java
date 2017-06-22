package grsu.bnicky.by.countries;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import adapter.MyAdapter;
import data.JSONRegionInfoParser;
import data.RegionHttpClient;
import model.Country;
import util.Utils;

public class CountriesList extends AppCompatActivity implements SearchView.OnQueryTextListener{

    private RecyclerView recyclerView;
    private MyAdapter adapter;
    private List<Country> listCountries;

    private Toolbar toolbarCountryList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.countries_list);

        recyclerView = (RecyclerView)findViewById(R.id.recycleViewCountriesList);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        toolbarCountryList = (Toolbar)findViewById(R.id.toolbarCountryListInfo);
        setSupportActionBar(toolbarCountryList);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        listCountries = new ArrayList<>();

        Intent intent = getIntent();
        String region = intent.getStringExtra("region_name");

        if(!Utils.isOnline(CountriesList.this)){
            connectionDialog();
        }else {
            renderRegionData(region);
        }
    }

    public void renderRegionData(String region){
        RegionTask regionTask = new RegionTask();
        regionTask.execute(new String[]{region});
    }


    private class RegionTask extends AsyncTask<String, Void, List<Country>> {
        @Override
        protected List<Country> doInBackground(String... params) {
            String data = new RegionHttpClient().getRegionCountriesData(params[0]);
            listCountries = JSONRegionInfoParser.getCountriesInRegion(data);
            return listCountries;
        }

        @Override
        protected void onPostExecute(List<Country> listCountries) {
            super.onPostExecute(listCountries);
            adapter = new MyAdapter(listCountries,CountriesList.this);
            recyclerView.setAdapter(adapter);
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_items,menu);
        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView)MenuItemCompat.getActionView(menuItem);
        searchView.setOnQueryTextListener(this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {

        newText = newText.toLowerCase();
        ArrayList<Country> newList = new ArrayList<>();
        for(Country country:listCountries){
            String name = country.getCountryName().toLowerCase();
            String capital = country.getCountryCapital().toLowerCase();
            if(name.contains(newText) || capital.contains(newText)){
                newList.add(country);
            }
        }

        adapter.setFilter(newList);
        return true;
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
        AlertDialog.Builder checkBuilder = new AlertDialog.Builder(CountriesList.this);
        checkBuilder.setIcon(R.drawable.ic_problem_report);
        checkBuilder.setTitle("Error!");
        checkBuilder.setMessage("Check your Internet connection");

        checkBuilder.setPositiveButton("Retry", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                CountriesList.this.recreate();
            }
        });

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
