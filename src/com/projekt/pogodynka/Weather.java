package com.projekt.pogodynka;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class Weather extends Activity {
	private ShowWeatherData dataBitmap;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_weather);
		Intent intent = getIntent();
		Bundle extras = getIntent().getExtras();
		if (extras != null) {
			String myParam = extras.getString("Lokacja");
			TextView tv = (TextView) this.findViewById(R.id.lokacja2TB);
			tv.setText("Pogoda w " + myParam);
			getWeather gw = new getWeather(myParam, "Poland");
			Bitmap bm = null;
			bm = gw.weather_icon;
			dataBitmap = new ShowWeatherData(gw);
			// ImageView mImageView = (ImageView) findViewById(R.id.imageView1);
			// if(gw.weather_icon!=null){
			// mImageView.setImageBitmap(gw.weather_icon);
			// }else{}
			int i = 0;
			TextView tw = (TextView) this.findViewById(R.id.pogodaTV);
			TextView tvTEMP = (TextView) this.findViewById(R.id.tempTV);
			TextView tvOdTemp = (TextView) this.findViewById(R.id.tempOdczTV);
			do {
				i++;
				tw.setText("Hahaa! " + gw.weather);
			} while (gw.pobrano == false);
			tw.setText(gw.weather);
			tvTEMP.setText(gw.temperature_string);
			tvOdTemp.setText(gw.odczuwalnaTemp);
			if (gw.weather_icon != null) {
				ImageView img = (ImageView) findViewById(R.id.img);
				img.setImageBitmap(gw.weather_icon);
			}

		} else {
			TextView tw = (TextView) this.findViewById(R.id.pogodaTV);
			tw.setText("Haha, nie wysz³o");
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.weather, menu);
		return true;
	}

	public void doSportow(View view){
		Intent intent = new Intent(this, Sporty.class);
		startActivity(intent);
		
		//startActivity(new Intent(this, Sporty.class));
		
		//Intent intent = new Intent();
        //intent.setClass(Weather.this, Sporty.class);
        //startActivityForResult(intent, 0); 
        
      
		
	}
	
	public void doUbran(View view){
		Intent intent = new Intent(this, Ubranie.class);
		startActivity(intent);
		
		
	}
}
