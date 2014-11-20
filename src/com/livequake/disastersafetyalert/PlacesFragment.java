package com.livequake.disastersafetyalert;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class PlacesFragment extends Fragment implements OnClickListener {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_places, container, false);
		
		/* Add buttons */
		Button bAP = (Button) view.findViewById(R.id.AddPlaces);
		bAP.setOnClickListener(this);
		return view;
	}
	@Override
	public void onClick(View v) {
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setMessage("To be implemented")
	       .setTitle("Add a location");
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
	public String getName() {
		return "Places";
	}
}
