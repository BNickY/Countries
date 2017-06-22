package adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.PictureDrawable;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;

import java.util.ArrayList;
import java.util.List;

import grsu.bnicky.by.countries.CountryDetailInfo;
import grsu.bnicky.by.countries.R;
import model.Country;
import svg.SvgSoftwareLayerSetter;

import static com.bumptech.glide.request.RequestOptions.errorOf;
import static com.bumptech.glide.request.RequestOptions.fitCenterTransform;
import static com.bumptech.glide.request.RequestOptions.overrideOf;
import static com.bumptech.glide.request.RequestOptions.placeholderOf;


public class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder>{

    private List<Country> listCountries;
    private Context context;
    private RequestBuilder<PictureDrawable> requestBuilder;

    public MyAdapter(List<Country> listItems, Context context) {
        this.listCountries = listItems;
        this.context = context;

    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.countries_list_item,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Country countryListItem = listCountries.get(position);

        holder.textViewCountry.setText(countryListItem.getCountryName());
        holder.textViewCapital.setText(countryListItem.getCountryCapital());

        requestBuilder = Glide.with(context).as(PictureDrawable.class)
                .listener(new SvgSoftwareLayerSetter());
        Uri uri = Uri.parse(countryListItem.getCountryFlag());
        requestBuilder.load(uri).apply(errorOf(R.drawable.ic_problem_report)).into(holder.imgFlagView);


        holder.relativeCountryItemLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CountryDetailInfo.class);
                intent.putExtra("country_name",countryListItem.getCountryName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listCountries.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        public TextView textViewCountry;
        public TextView textViewCapital;
        public ImageView imgFlagView;
        public RelativeLayout relativeCountryItemLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            textViewCountry = (TextView)itemView.findViewById(R.id.textViewCountry);
            textViewCapital = (TextView)itemView.findViewById(R.id.textViewCapital);
            imgFlagView = (ImageView)itemView.findViewById(R.id.imgFlagView);
            relativeCountryItemLayout = (RelativeLayout)itemView.findViewById(R.id.relativeItemLayout);
        }
    }

    public void setFilter(ArrayList<Country> newList){
        listCountries = new ArrayList<>();
        listCountries.addAll(newList);
        notifyDataSetChanged();
    }
}