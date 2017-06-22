package regions;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import grsu.bnicky.by.countries.CountriesList;
import grsu.bnicky.by.countries.MainActivity;
import grsu.bnicky.by.countries.R;
import util.Utils;

public class Africa extends Fragment{

    private FloatingActionButton fabAfrica;

    public Africa() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_africa, container, false);

        fabAfrica = (FloatingActionButton)view.findViewById(R.id.fab_AfricaRegion);
        fabAfrica.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity().getBaseContext(), CountriesList.class);
                intent.putExtra("region_name", Utils.region_africa);
                getActivity().startActivity(intent);
            }
        });

        return view;
    }
}
