package com.drk3931.earthtracker;

import java.awt.Color;
import java.awt.FlowLayout;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Application.ApplicationType;

public class ETracker extends Game {
	
	
	public static Assets assets;

	@Override
	public void create () {
		

			
		
		if(Gdx.app.getType() == ApplicationType.Desktop)
		{
			
			JFrame statChooser = new JFrame();
			statChooser.setVisible(true);
			statChooser.setSize(240,480);
			statChooser.setTitle("Plot Data");
			statChooser.setLayout(new FlowLayout());
			
			statChooser.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
			statChooser.setBackground(Color.BLUE);
			
			JButton button = new JButton();
			button.setText("Global Temperatures");
			button.setSize(240, 240);
			
			statChooser.add(button);
			
		}
		
		
		
		
		assets = new Assets();
		setScreen(new FinalScreen());
			
	}

	
}
