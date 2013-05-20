package com.projekt.pogodynka;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONException;
import org.json.JSONObject;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

public class getWeather {

	private static String WEATHER_URL = "http://api.wunderground.com/api/c9d15b10ff3ed303/conditions/lang:PL/q/";
	// http://api.wunderground.com/api/c9d15b10ff3ed303/conditions/q/CA/San_Francisco.json
	public String weather;
	public String temperature_string;
	public Bitmap weather_icon;
	public String icon_url;
	public String odczuwalnaTemp;
	public Boolean pobrano = false;

	public getWeather(String city, String panstwo) {

		if (city == null)
			city = "Lublin";
		city = city.replaceAll(" ", "_");
		// construct post URL
		// final String GET_WEATHER_URL = WEATHER_URL + panstwo + "/" + city +
		// ".json";
		final String GET_WEATHER_URL = WEATHER_URL + "Poland/" + city + ".json";
		new Thread(new Runnable() {
			public void run() {
				String request = GET_WEATHER_URL;
				HttpResponse rp = null;
				JSONObject jObject = null;
				try {
					rp = (new DefaultHttpClient())
							.execute(new HttpPost(request));
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				if (rp != null
						&& rp.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					Log.i("Get Weather", "Success");
					HttpEntity entity = rp.getEntity();
					InputStream is = null;
					try {
						is = entity.getContent();
					} catch (IllegalStateException e2) {
						e2.printStackTrace();
					} catch (IOException e2) {
						e2.printStackTrace();
					} catch (NullPointerException n1) {
						n1.printStackTrace();
					}
					final char[] buffer = new char[0x10000];
					StringBuilder out = new StringBuilder();
					Reader in = null;
					String json_string_response = null;
					try {
						in = new InputStreamReader(is, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						e.printStackTrace();
					} catch (NullPointerException n1) {
						n1.printStackTrace();
					}
					int read = 0;
					do {
						try {
							read = in.read(buffer, 0, buffer.length);
						} catch (IOException e) {
							e.printStackTrace();
						} catch (NullPointerException n1) {
							n1.printStackTrace();
						}
						if (read > 0) {
							out.append(buffer, 0, read);
						}
					} while (read >= 0);

					try {
						is.close();
						json_string_response = out.toString();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						jObject = new JSONObject(json_string_response);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					try {
						/*
						 * Here is where I try to get the data from the json for
						 * specific keys but it says no value found
						 */
						JSONObject current_observation = jObject
								.getJSONObject("current_observation");
						weather = current_observation.getString("weather");
						temperature_string = current_observation
								.getString("temperature_string");
						icon_url = current_observation.getString("icon_url");
						weather_icon = get_weather_icon(icon_url);
						odczuwalnaTemp = current_observation
								.getString("feelslike_string");
						pobrano = true;

					} catch (JSONException e4) {
						// TODO Auto-generated catch block
						e4.printStackTrace();
					}
				} else {
					String response = rp.toString().toString();
					Log.e("Get Weather2", response);
				}
				
			/*	if(pobrano==false)
				{
					weather = "Brak po³¹czenia z internetem";
					
				}*/

			}
		}).start();
	}

	public static Bitmap get_weather_icon(String url) {
		Bitmap bitmap = null;
		InputStream in = null;
		BufferedOutputStream out = null;
		bitmap = downloadImage(url);
		// try {
		//
		// // bitmap = BitmapFactory.decodeStream((InputStream) new
		// // URL(url).getContent());
		//
		//
		// } catch (MalformedURLException e) {
		// e.printStackTrace();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }

		return bitmap;

	}

	public static Bitmap getBitmapFromURL(String src) {
		try {
			URL url = new URL(src);
			HttpURLConnection connection = (HttpURLConnection) url
					.openConnection();
			connection.setDoInput(true);
			connection.connect();
			InputStream input = connection.getInputStream();
			Bitmap myBitmap = BitmapFactory.decodeStream(input);
			return myBitmap;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	private static Bitmap downloadImage(String url) {
		Bitmap bitmap = null;
		InputStream stream = null;
		BitmapFactory.Options bmOptions = new BitmapFactory.Options();
		bmOptions.inSampleSize = 1;

		try {
			stream = getHttpConnection(url);
			bitmap = BitmapFactory.decodeStream(stream, null, bmOptions);
			stream.close();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		return bitmap;
	}

	private static InputStream getHttpConnection(String urlString)
			throws IOException {
		InputStream stream = null;
		URL url = new URL(urlString);
		URLConnection connection = url.openConnection();

		try {
			HttpURLConnection httpConnection = (HttpURLConnection) connection;
			httpConnection.setRequestMethod("GET");
			httpConnection.connect();

			if (httpConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				stream = httpConnection.getInputStream();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return stream;
	}

}