package com.projekt.pogodynka;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Sporty extends ListActivity {

	/*
	 * @Override protected void onCreate(Bundle savedInstanceState) {
	 * super.onCreate(savedInstanceState);
	 * setContentView(R.layout.activity_sporty); }
	 */

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sporty, menu);
		return true;
	}

	String listArray[] = { "Spacer", "Bieganie", "P³ywanie", "Spanie" };

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sporty);

		ArrayAdapter<String> array = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, listArray);
		setListAdapter(array);
		ListView listView = getListView();
		listView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplicationContext(),
						((TextView) view).getText(), Toast.LENGTH_SHORT).show();
			}
		});

	}

	// ImageView imgView=(ImageView)findViewById(R.id.image1);

	// protected void onListItemClick(ListView l, View v, int position, long id)
	// {
	// super.onListItemClick(l, v, position, id);
	// Toast.makeText(getBaseContext(),
	// l.getItemAtPosition(position).toString(), Toast.LENGTH_SHORT).show();
	// imgView.setImageBitmap(BitmapFactory.decodeResource(this.getResources(),R.drawable.manatee14));

	// }

}
