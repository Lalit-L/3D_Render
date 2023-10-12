package Project;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;

import org.lwjgl.opengl.Display;
import org.lwjgl.util.vector.Vector3f;

import Entities.Camera;
import Entities.CubeEntity;
import Models.RawModel;
import Render.DisplayManager;
import Render.Loader;
import Render.MasterRenderer;
import Shaders.StaticShader;

public class MainModel {

	public static Loader loader1 = null;
	public static StaticShader shader1 = null;
	static List<CubeEntity> entityList = new ArrayList<CubeEntity>();
	
	public static void main(String[] args) {
		DisplayManager.createDisplay();
		Loader loader = new Loader();
		loader1 = loader;
		StaticShader shader = new StaticShader();
		shader1 = shader;
		MasterRenderer renderer = new MasterRenderer(shader);
		float[] vertices = {
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,0.5f,-0.5f,		
				
				-0.5f,0.5f,0.5f,	
				-0.5f,-0.5f,0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				0.5f,0.5f,-0.5f,	
				0.5f,-0.5f,-0.5f,	
				0.5f,-0.5f,0.5f,	
				0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,-0.5f,	
				-0.5f,-0.5f,-0.5f,	
				-0.5f,-0.5f,0.5f,	
				-0.5f,0.5f,0.5f,
				
				-0.5f,0.5f,0.5f,
				-0.5f,0.5f,-0.5f,
				0.5f,0.5f,-0.5f,
				0.5f,0.5f,0.5f,
				
				-0.5f,-0.5f,0.5f,
				-0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,-0.5f,
				0.5f,-0.5f,0.5f
		};
		/*float[] vertices2 =  {
				-0.7f, 0.3f, -0.2f,
				-0.7f, -0.7f, -0.2f,
				0.3f, -0.7f, -0.2f,
				0.3f, 0.3f, -0.2f,
		};*/
		int[] indices = { 
				0,1,3,	
				3,1,2,	
				4,5,7,
				7,5,6,
				8,9,11,
				11,9,10,
				12,13,15,
				15,13,14,	
				16,17,19,
				19,17,18,
				20,21,23,
				23,21,22
		};
		//int[] indices2 = {0, 1, 2, 2, 3, 0};
		RawModel model = loader.loadToVAO(vertices, indices);
		//CubeEntity myEntity = new CubeEntity(model, new Vector3f(0, 0, -1), 0, 0, 0, 1);
		//entityList.add(myEntity);
		Camera camera = new Camera(new Vector3f(0, 0, 0), 0, 0, 0);
		
		
		/*Color black = new Color(0, 0, 0);
		int bRGB = black.getRGB();
		JFileChooser chooser = new JFileChooser();
		chooser.setMultiSelectionEnabled(true);
		chooser.showSaveDialog(null);
		File[] files = chooser.getSelectedFiles();
		try {
			for (File f : files) {
				BufferedImage myPic = ImageIO.read(f);
				for (int x = 0; x < myPic.getWidth(); x++) {
					for(int y = 0; y < myPic.getWidth(); y++) {
						if (myPic.getRGB(x, y) == bRGB) {
							System.out.println("hi");
						}
					}
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		new Thread(new Runnable() {public void run() {
			/*for (int x = (int) camera.getPosition().x - 10; x < camera.getPosition().x+10; x++) {
				for (int z = (int) camera.getPosition().z - 10; z < camera.getPosition().z+10; z++) {
					entityList.add(new CubeEntity(model, new Vector3f(x, 0, z), 0, 0, 0, 1));
				}
			}*/
			
			Color black = new Color(0, 0, 0);
			int bRGB = black.getRGB();
			JFileChooser chooser = new JFileChooser();
			chooser.setMultiSelectionEnabled(true);
			chooser.showSaveDialog(null);
			File[] files = chooser.getSelectedFiles();
			try {
				int curFile = 1;
				for (File f : files) {
					BufferedImage myPic = ImageIO.read(f);
					int minW = -myPic.getWidth()/2;
					int maxW = myPic.getWidth()/2;
					int minH = -myPic.getHeight()/2;
					int maxH = myPic.getHeight()/2;
					for (int x = 0; x < myPic.getWidth(); x++) {
						for(int y = 0; y < myPic.getWidth(); y++) {
							if (myPic.getRGB(x, y) == bRGB) {
								
								/*if(x < myPic.getWidth()/2) {
									if (y < myPic.getHeight()/2) {
										int curX = x-myPic.getWidth()/2;
										int curY = y-myPic.getHeight()/2;
									}
									else {
										int curX = x-myPic.getWidth()/2;
										int curY = 
									}
								}*/
								
								
								entityList.add(new CubeEntity(model, new Vector3f(x, y, curFile), 0, 0, 0, 1));
							}
						}
					}
					curFile += 1;
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}}).start();
		
		//RawModel model2 = loader.loadToVAO(vertices2, indices2);
		while(!Display.isCloseRequested()) {
			camera.move();
			renderer.prepare();
			
			//entity.increasePosition(0, 0, -0.01f);
			//entity.increaseScale(-0.001f);
			//entity.increaseRotation(0, 0, 0.1f);
			
			shader.start();
			shader.loadViewMatrix(camera);
			//renderer.render(model2);
			for (CubeEntity entity: entityList) {
				renderer.render(entity, shader);
			}
			shader.stop();
			DisplayManager.updateDisplay();
		}
		DisplayManager.closeDisplay();
	}

}
