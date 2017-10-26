package com.drk3931.earthtracker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Array;

public class Renderer {
	
	
	
	private PerspectiveCamera cam;
    private CameraInputController camController;
    private ModelBatch modelBatch;

    private Environment environment;
    
    
    private Array<ModelInstance> drawableEntities;
    private Array<Vector3> needles;
    
    public Renderer() {
		
    	needles = new Array<Vector3>();
    	drawableEntities = new Array<ModelInstance>();
        
        modelBatch = new ModelBatch();
      
        /*environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.4f, 0.4f, 0.4f, 1f));
        environment.add(new DirectionalLight().set(0.8f, 0.8f, 0.0f, 1f, 1.0f, 1.0f));*/

        cam = new PerspectiveCamera(25, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(0f, 0f, 4f);
        cam.lookAt(0,0,0);
        cam.near = 0.01f;
        cam.far = 200;
        cam.update();

        camController = new CameraInputController(cam);
        Gdx.input.setInputProcessor(camController);
    	
    	
    	
    	
    	
    	
	}
    
    
    public void render(float delta)
    {
    	 
        camController.update();
       
       
        

        Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

       
   
        
        modelBatch.begin(cam);
     
        for(ModelInstance m: drawableEntities)
        {
        	modelBatch.render(m);
        }
        
        
        
        
        modelBatch.end();
    	
       
        
       
        
    }
    
    
    public void addToRenderer(ModelInstance m)
    {
    	drawableEntities.add(m);
    	
    }
    
    
  
    
    public void addToRenderer(Array<ModelInstance> instances)
    {
    	drawableEntities.addAll(instances);
    }
    
   

	public Array<Vector3> getNeedles() {
		// TODO Auto-generated method stub
		return needles;
	}
    
    

}
