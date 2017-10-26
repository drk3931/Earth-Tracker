package com.drk3931.earthtracker;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Environment;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.model.NodePart;
import com.badlogic.gdx.graphics.g3d.shaders.DefaultShader;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.DefaultTextureBinder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Array;

public class MainScreen implements Screen {

	
	public Model model;
	
	private PerspectiveCamera cam;
	private ModelBatch modelBatch;
	
    public CameraInputController camController;
    
    
    private Shader shader;
    
    //Renderable is the smallest renderable part, which is passed through the rendering pipeline.
    private Renderable renderable;
    public RenderContext renderContext;
	
	public MainScreen() {
		
				initCam();

			 ModelBuilder modelBuilder = new ModelBuilder();
		       model = modelBuilder.createSphere(2f, 2f, 2f, 25, 25, 
		         new Material(),
		         Usage.Position | Usage.Normal | Usage.TextureCoordinates);
		         //Remember a vertex in OpenGL can have several attributes. Color, position, texture, etc.
		       
		       
		       
		       NodePart blockPart = model.nodes.get(0).parts.get(0);

		       renderable = new Renderable();
		       blockPart.setRenderable(renderable);
		       renderable.environment = null;
		       
		       //?
		       renderable.worldTransform.idt();

		       renderContext = new RenderContext(new DefaultTextureBinder(DefaultTextureBinder.WEIGHTED, 1));
		       
		       String vert = Gdx.files.internal("createshader/data/test.vertex.glsl").readString();
		       String frag = Gdx.files.internal("createshader/data/test.fragment.glsl").readString();
		       
		       System.out.println(vert);
		       
		       shader = new DefaultShader(renderable, new DefaultShader.Config(vert, frag));
		       shader.init();
		       
		       
		       
	}
	
	
	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	
	
	@Override
	public void render(float delta) {
		
		camController.update();
		
		
		Gdx.gl.glViewport(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

        renderContext.begin();
        shader.begin(cam, renderContext);
        shader.render(renderable);
        shader.end();
        renderContext.end();
  
		
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		modelBatch.dispose();
        model.dispose();	
	}
	
	private void initCam()
	{
		cam = new PerspectiveCamera(90, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        //defining the frustrum. 
		cam.position.set(0f, 0, 10);
        cam.lookAt(0,0,0);
        cam.near = 1f;
        cam.far = 300;
        cam.update();
        
     
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
	}
	
}
