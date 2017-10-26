package com.drk3931.earthtracker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;

public class Assets {
	
	
	private AssetManager assetManager;
	
	private  ModelInstance space;
	private  ModelInstance globe;
	
	
	public AssetManager getAssets()
	{
		return assetManager;
	}
	
	public Assets() {
		assetManager = new AssetManager();
		
		
		switch(Gdx.app.getType())
		{
		
		case Desktop  : assetManager.load("mobile_low_res/globe.g3db", Model.class);
						assetManager.load("mobile_low_res/spacesphere.obj", Model.class);	
						
						assetManager.finishLoading();

						  //interesting  bug  here. Try calling ETracker.assets.getAssets().get(...);
						  //It will be null because the assets class has not been fully made. 
						  
					      space = new ModelInstance(getAssets().get("mobile_low_res/spacesphere.obj", Model.class));
					      globe = new ModelInstance(getAssets().get("mobile_low_res/globe.g3db",Model.class));  break;
					
						
						
		
		case Android  : assetManager.load("high_res/spacesphere.obj", Model.class);
						assetManager.load("high_res/globe.g3db", Model.class);

						assetManager.finishLoading();

						 //interesting  bug  here. Try calling ETracker.assets.getAssets().get(...);
						 //It will be null because the assets class has not been fully made. 
						  
						
			            space = new ModelInstance(getAssets().get("high_res/spacesphere.obj", Model.class));
			            globe = new ModelInstance(getAssets().get("high_res/globe.g3db",Model.class));  break;
		}
		
		
		
		
		

		
	}
	
	public ModelInstance getSpaceModelInstance()
	{
		return space;
	}
	
	

	public ModelInstance getGlobeModelInstance()
	{
		return globe;
	}
	
	
	
	
	
	

}
