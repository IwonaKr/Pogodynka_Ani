package com.projekt.pogodynka;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class Main extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void doPogody(View view) {
		Intent intent = new Intent(this, Weather.class);
		EditText editText = (EditText) findViewById(R.id.lokacjaET);
		String message = editText.getText().toString();
		// getWeather gw = new getWeather(message, "Poland");
		intent.putExtra("Lokacja", message);
		// intent
		startActivity(intent);
	}

	public void doSportow(View view) {
		Intent i = new Intent(getApplicationContext(), Sporty.class);
		// i.putExtra("PersonID", personID);
		startActivity(i);
	}

}
