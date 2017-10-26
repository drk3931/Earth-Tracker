package com.drk3931.earthtracker;


import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class FinalScreen implements Screen
{


  
    public ModelInstance space = ETracker.assets.getSpaceModelInstance();
    public ModelInstance globe = ETracker.assets.getGlobeModelInstance();
    
   
    
    private Renderer renderer;
    
    private LocationDataPuller dataPuller;
    
    public FinalScreen() {
    		
    		needles = new Array<ModelInstance>();
    		renderer=  new Renderer();
    		renderer.addToRenderer(space);
    		renderer.addToRenderer(globe);
    	
	    		dataPuller = new LocationDataPuller(this);
    		
    		
	    		dataPuller.pullData();
    		
    		

    		
    	
    		renderer.addToRenderer(needles);
    		
    }
    
    private Array<ModelInstance> needles;

    
	ModelBuilder modelBuilder = new ModelBuilder();

    
    @Override
    public void render (float delta) {
    	

	
    	
    	
    	 for(ModelInstance m : needles)
    	 {
    		 m.transform.rotate(0, 1, 0, 5*delta);
    	 }
    	 globe.transform.rotate(0, 1, 0, 5*delta);
    	
    	
    	renderer.render(delta);
    }



    
    
    
    
    
	
	
	public  Vector3 LongitudeLatitudeToCoordinate(float longitude, float latitude, float temperatureFahrenheit)
	{

		
		
		
		longitude*=-1;
		longitude+=90;
		
		 float rho = 1 + temperatureFahrenheit/100;   
		 float phi = latitude;
		 float theta = longitude;
		
	     float   x = MathUtils.cosDeg(phi) * MathUtils.cosDeg(theta) * rho ;
		 
	     
	     float   z = MathUtils.cosDeg(phi) * MathUtils.sinDeg(theta) * rho;
		 
		 
		 float   y = MathUtils.sinDeg(phi) * rho ;
    	
		

		
		 
		
		return new Vector3(x,y,z);
	}
	
	
	
	
	Color needleColor = new Color();

	public void addNeedle(Vector3 needle, float temperatureFarenheit)
    {
		
		
		modelBuilder.begin();
		
		MeshPartBuilder meshBuilder;
		
		if(temperatureFarenheit > 90){
		needleColor.set(Color.RED);
		}
		
		if(temperatureFarenheit < 90 && temperatureFarenheit > 80){
			needleColor.set(Color.ORANGE);
		}
		
		
		if(temperatureFarenheit < 80 && temperatureFarenheit > 70){
			needleColor.set(Color.YELLOW);
		}
		
		if(temperatureFarenheit > 60 && temperatureFarenheit < 70){
			needleColor.set(Color.GREEN);
		}
		
		if(temperatureFarenheit < 60 && temperatureFarenheit > 32){
			needleColor.set(Color.BLUE);
		}
		
		if(temperatureFarenheit < 32){
			needleColor.set(Color.WHITE);
		}
		

		Material m = new Material(ColorAttribute.createDiffuse(needleColor));
		meshBuilder = modelBuilder.part("line", GL20.GL_LINES, Usage.Position,m);
	
		
		
	
		meshBuilder.line(new Vector3(0,0,0), needle);
		
		
		Model model = modelBuilder.end();
		
		needles.add(new ModelInstance(model));


    	
    }

	

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Override
    public void resume () {
    }

    @Override
    public void resize (int width, int height) {
    }

    @Override
    public void pause () {
    }

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
		



	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
}


