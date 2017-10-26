package com.drk3931.earthtracker;

import java.io.InputStream;
import java.net.URL;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class LocationDataPuller {

	FinalScreen screenRef;

	
	private Array<DataPackage> cityPackages;
	
	private class DataPackage
	{
		
		public String name;
		public float temperatureFahrenheit;
		public float longitude;
		public float latitude;
		
		
		
	}
	
	String[] cities;
	
	public LocationDataPuller(FinalScreen screen) {

		this.screenRef = screen;
		cityPackages = new Array<LocationDataPuller.DataPackage>();
		
		 cities = Gdx.files.internal("cities.txt").readString().split("\n");
		
		

		}

	


	public void pullData()
	{

		
		
		
		for (String s : cities) {

			try {

				cityPackages.add(new DataPackage());

				URL point = new URL(" http://api.openweathermap.org/data/2.5/weather?q="+ s);

				InputStream is = point.openStream();

				JsonValue json = new JsonReader().parse(is);
				
				
				JsonValue val = json.get("main");
				JsonValue val2 = json.get("coord");
				JsonValue val3 = json.get("name");
				
			
				cityPackages.peek().name = val3.asString() ;
				
				

				for (JsonValue v : val2.iterator()) {

					if (v.name.equals("lon")) {

						cityPackages.peek().longitude = v.asFloat() ;
					}

					if (v.name.equals("lat")) {

						cityPackages.peek().latitude = v.asFloat();


					}

				}

				for (JsonValue v : val.iterator()) {
					if (v.name.equals("temp")) {

						// Its in Kelvin. Converting to Farenheit
						float f = ((v.asFloat() - 273.15f) * 1.8f) + 32;
						
						cityPackages.peek().temperatureFahrenheit = f;


					}
				}
				
				
				Vector3 needlePoint = screenRef.LongitudeLatitudeToCoordinate(cityPackages.peek().longitude, cityPackages.peek().latitude, cityPackages.peek().temperatureFahrenheit);
				screenRef.addNeedle(needlePoint, cityPackages.peek().temperatureFahrenheit);

			}

			
			
			
			
			
			catch (Exception e) {System.out.println(e);}
			
		}

	}
	
	}
	


