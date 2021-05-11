package com.example.retro_fit;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class MyAdapter extends ArrayAdapter<Country_Model>
{
    private Context context;
    private List<Country_Model> country_modelList;
    private List<Country_Model> country_modelListFiltered;

    public MyAdapter( Context context,  List<Country_Model> country_modelList)
    {
        super(context, R.layout.lsit_custom_item,country_modelList);

        this.context = context;
        this.country_modelList = country_modelList;
        this.country_modelListFiltered = country_modelList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lsit_custom_item,null,true);
        TextView CountryName = view.findViewById(R.id.countryName);
        ImageView imageView = view.findViewById(R.id.imageFlag);

        CountryName.setText(country_modelListFiltered.get(position).getCountries());
        Glide.with(context).load(country_modelList.get(position).getFlag()).into(imageView);
        return view;
    }

    @Override
    public int getCount()
    {
        return country_modelListFiltered.size();
    }
    @Override
    public Country_Model getItem(int position)
    {
      return country_modelListFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public Filter getFilter()
    {
       Filter filter = new Filter()
       {
           @Override
           protected FilterResults performFiltering(CharSequence constraint) {
              FilterResults filterResults = new FilterResults();
              if(constraint==null || constraint.length()==0)
              {
                  filterResults.count  = country_modelList.size();
                  filterResults.values = country_modelList;
              }
              else
              {
                  List<Country_Model> resultModel = new ArrayList<>();
                  String searchStr = constraint.toString().toLowerCase();

                  for(Country_Model itemsModel:country_modelList)
                  {
                      if(itemsModel.getCountries().toLowerCase().contains(searchStr))
                      {
                          resultModel.add(itemsModel);
                      }
                      filterResults.count   = resultModel.size();
                      filterResults.values = resultModel;
                  }
              }
               return filterResults;
           }

           @Override
           protected void publishResults(CharSequence constraint, FilterResults results) {
               country_modelListFiltered = (List<Country_Model>) results.values;
               Countries.country_modelList = (List<Country_Model>) results.values;
               notifyDataSetChanged();

           }
       };
        return filter;
    }
}
