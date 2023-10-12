package Render;
import org.lwjgl.LWJGLException;
import org.lwjgl.input.Keyboard;
import org.lwjgl.opengl.ContextAttribs;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.PixelFormat;

import Project.MainModel;

public class DisplayManager {
	
	public static final int WIDTH = 1000;
	public static final int HEIGHT = 720;
	public static final int FPS_CAP = 20;

	public static void createDisplay() {
		
		ContextAttribs attrib = new ContextAttribs(3, 2)
				.withForwardCompatible(true)
				.withProfileCore(true);
		
		try {
			Display.setDisplayMode(new DisplayMode(WIDTH, HEIGHT));
			Display.create(new PixelFormat(), attrib);
			Display.setTitle("Project");
			Display.setFullscreen(true);
			GL11.glViewport(0, 0, Display.getWidth(), Display.getHeight());
		} catch (LWJGLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void updateDisplay() {
		Display.sync(FPS_CAP);
		Display.update();
		while(Keyboard.next()) {
			if (Keyboard.isKeyDown(Keyboard.KEY_ESCAPE)) {
				closeDisplay();
			}
		}
	}
	
	public static void closeDisplay() {
		MainModel.loader1.cleanup();
		MainModel.shader1.cleanUp();
		Display.destroy();
		System.exit(0);
	}

}
