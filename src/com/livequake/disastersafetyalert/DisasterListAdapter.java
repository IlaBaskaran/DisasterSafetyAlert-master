package com.livequake.disastersafetyalert;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import android.content.Context;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DisasterListAdapter extends BaseAdapter {
    
    private FragmentActivity activity;
    public static ArrayList<HashMap<String, String>> data;
    private static LayoutInflater inflater=null;
    public Map<String, Integer> categoryMap = new HashMap<String, Integer>();
    
    /* accepts a list of full disaster list */
    public DisasterListAdapter(FragmentActivity a, ArrayList<HashMap<String, String>> d) {
    	categoryMap.put("Crime", R.drawable.crime_icon);
    	categoryMap.put("Natural", R.drawable.nature_icon);
    	categoryMap.put("Transit", R.drawable.transit_icon);
    	categoryMap.put("Health", R.drawable.health_icon);
    	categoryMap.put("General", R.drawable.general_icon); 	
    	
    	/* Get Info*/
        activity = a;
        data=d;
        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return data.size();
    }

    public Object getItem(int position) {
        return position;
    }

    public long getItemId(int position) {
        return position;
    }
    
    public View getView(int position, View convertView, ViewGroup parent) {
        View vi=convertView;
        if(convertView==null) {
            vi = inflater.inflate(R.layout.list_row, null);
        }
        
        /* Get relevant ids */
        TextView date = (TextView)vi.findViewById(R.id.date); // date
        TextView title = (TextView)vi.findViewById(R.id.title); //title
        TextView location = (TextView)vi.findViewById(R.id.location); // location
        ImageView image = (ImageView)vi.findViewById(R.id.list_image);

        HashMap<String, String> d_list = new HashMap<String, String>();
        d_list = data.get(position);

        /* Setting all values in listview */
        date.setText(d_list.get(DisasterFragment.KEY_DATE));
        title.setText(d_list.get(DisasterFragment.KEY_TITLE));
        location.setText(d_list.get(DisasterFragment.KEY_LOCATION));
        
        String cat = d_list.get(DisasterFragment.KEY_CATEGORY);
        if (cat.equals("Crime")) {
    		image.setImageResource(categoryMap.get("Crime"));
        }
        else if (cat.equals("Natural")) {
    		image.setImageResource(categoryMap.get("Natural"));
        }
        else if (cat.equals("Transit")) {
    		image.setImageResource(categoryMap.get("Transit"));
        }
        else if (cat.equals("Health")) {
    		image.setImageResource(categoryMap.get("Health"));
        }
        else if (cat.equals("General")) {
    		image.setImageResource(categoryMap.get("General"));
        }
        
        return vi;
    }
}