package com.example.project1;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class singlelistadap extends ArrayAdapter<String> {

    private final String[] Dates ;
    private final Activity context;
    private final String[] Web;
    private final Integer[] Id;

    public singlelistadap(Activity context, String[]Web, Integer[]Id, String[] Dates)
    {
        super(context,R.layout.admin_single_element,Web);
        this.context=context;
        this.Web=Web;
        this.Id=Id;
        this.Dates=Dates;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        TextView text,date;
        ImageView image;
        LayoutInflater inflater;
        inflater=context.getLayoutInflater();
        View Rowelement=inflater.inflate(R.layout.admin_single_element,parent,false);
        text=( TextView )Rowelement.findViewById(R.id.single_element_text);
        text.setText(Web[position]);
        image=( ImageView )Rowelement.findViewById(R.id.single_element_image);
        image.setImageResource(Id[position]);
        date=( TextView )Rowelement.findViewById(R.id.single_element_date);
        date.setText(Dates[position]);
        return Rowelement;
    }
}