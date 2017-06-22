package regions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grsu.bnicky.by.countries.CountriesList;
import grsu.bnicky.by.countries.R;
import util.Utils;


public class Americas extends Fragment {

    private FloatingActionButton fabAmericas;

    public Americas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_americas, container, false);

        fabAmericas = (FloatingActionButton)view.findViewById(R.id.fab_AmericasRegion);
        fabAmericas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), CountriesList.class);
                intent.putExtra("region_name", Utils.region_americas);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
