package com.livequake.disastersafetyalert;

import java.util.ArrayList;
import java.util.HashMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class DisasterFragment extends Fragment{

	// All static variables
	static final String earthquakeURL = "http://earthquake.usgs.gov/eqcenter/catalogs/1day-M2.5.xml";
	static final String historicURL = "http://ryansdoherty.com/HISTORIC-DISASTER.xml";	//canadian data stored here
	// XML node keys
	static final String KEY_ENTRY = "entry";
	static final String KEY_TITLE = "title"; // parent node
	static final String KEY_LOCATION = "georss:point";
	// Hashmap keys
	static final String KEY_DATE = "date";
	static final String KEY_CATEGORY = "category";
	static final String KEY_INFO = "info";
	
	private static Context ctx;
	private ListView list_view;
	private DisasterListAdapter adapter;
	
	public boolean isOnline() {
	    ConnectivityManager cm =
	        (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
	    NetworkInfo netInfo = cm.getActiveNetworkInfo();
	    if (netInfo != null && netInfo.isConnectedOrConnecting()) {
	        return true;
	    }
	    return false;
	}
    
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_disaster, container, false);
		ctx = this.getActivity().getApplicationContext();
		
		ArrayList<HashMap<String, String>> DEventList = new ArrayList<HashMap<String, String>>();

		if(isOnline()) {
		XMLParser parser = new XMLParser();
		String xml = parser.getXmlFromUrl(historicURL); // getting XML from URL
		Document doc = parser.getDomElement(xml); // getting DOM element
		
		NodeList nl = doc.getElementsByTagName("Event");//KEY_ENTRY);
		// looping through all nodes
		for (int i = 1; i < nl.getLength(); i++) {
			// creating new HashMap
			HashMap<String, String> map = new HashMap<String, String>();
			Element e = (Element) nl.item(i);
			
			/* Disaster Feed
			//clean up date
			String cleanDate = parser.getValue(e, "updated").replace("T", "\n").substring(0, 16);
					
			// adding each child node to HashMap key => value
			map.put(KEY_TITLE, parser.getValue(e, KEY_TITLE));
			map.put(KEY_DATE, cleanDate);
			map.put(KEY_LOCATION, parser.getValue(e, KEY_LOCATION)); */
			
			/* Canadian open data set */
			
			/* Clean up date info */
			String startDate = parser.getValue(e, "EVENT_START_DATE");
			String endDate = parser.getValue(e, "EVENT_END_DATE");
			String cleanDate = "";
			if(startDate.equals(endDate)) {
				cleanDate = startDate;
			}
			else {
				cleanDate = startDate + "\n-" + endDate;
			}
			
			/* Add to map */
			map.put(KEY_TITLE, parser.getValue(e, "EVENT_APP"));
			map.put(KEY_DATE, cleanDate);
			map.put(KEY_LOCATION, parser.getValue(e, "PLACE"));
			map.put(KEY_CATEGORY, parser.getValue(e, "CATEGORY_RSS"));
			map.put(KEY_INFO, parser.getValue(e, "COMMENTS"));
			map.put(KEY_LOCATION, parser.getValue(e, "PLACE"));
			
			/* adding Hashmap to ArrayList */
			DEventList.add(map);
		}
		
		//list
		list_view=(ListView) view.findViewById(R.id.place_d_list);
		
		// Getting adapter by passing xml data ArrayList
        adapter=new DisasterListAdapter(getActivity(), DEventList);        
        list_view.setAdapter(adapter);
        
        /* Click event for single list row 
         * Open dialog and show info for disaster
         */
        list_view.setOnItemClickListener(new OnItemClickListener() {
        	@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        		String info = DisasterListAdapter.data.get(position).get(KEY_INFO);
        		String selected = DisasterListAdapter.data.get(position).get(KEY_TITLE);
        		
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				builder.setMessage(info)
			       .setTitle(selected);
				builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User clicked OK button
			           }
			       });
				builder.setNegativeButton("Close", new DialogInterface.OnClickListener() {
			           public void onClick(DialogInterface dialog, int id) {
			               // User cancelled the dialog
			           }
			       });
				builder.show();
				
			}
		});
		}
		else {
			Toast t = Toast.makeText(ctx, "Connect to the internet to get an up-to-date list of disasters",
					 Toast.LENGTH_LONG);
			t.show();
		}
        return view;
	}
	
	public String getName() {
		return "Places";
	}
}
