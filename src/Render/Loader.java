package Render;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import org.lwjgl.BufferUtils;
import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL15;
import org.lwjgl.opengl.GL20;
import org.lwjgl.opengl.GL30;

import Models.RawModel;

public class Loader {
	
	public static List<Integer> vao = new ArrayList<Integer>();
	public static List<Integer> vbo = new ArrayList<Integer>();

	public RawModel loadToVAO(float[] vertices, int[] indices) {
		int vaoID = createVAO();
		storeDataInAL(vertices, 0, 3);
		bindIndicesBuffer(indices);
		GL30.glBindVertexArray(0);
		RawModel m = new RawModel(vaoID, indices.length);
		return m;
	}
	
	private FloatBuffer storeData(float[] data) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(data.length);
		buffer.put(data);
		buffer.flip();
		return buffer;
	}
	
	private int createVAO() {
		int vaoID = GL30.glGenVertexArrays();
		vao.add(vaoID);
		GL30.glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void bindIndicesBuffer(int[] indices) {
		int vboID = GL15.glGenBuffers();
		vbo.add(vboID);
		GL15.glBindBuffer(GL15.GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer intBuf = BufferUtils.createIntBuffer(indices.length);
		intBuf.put(indices);
		intBuf.flip();
		GL15.glBufferData(GL15.GL_ELEMENT_ARRAY_BUFFER, intBuf, GL15.GL_STATIC_DRAW);
	}
	
	private void storeDataInAL(float[] data, int attN, int dim) {
		int vboID = GL15.glGenBuffers();
		vbo.add(vboID);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = storeData(data);
		GL15.glBufferData(GL15.GL_ARRAY_BUFFER, buffer, GL15.GL_STATIC_DRAW);
		GL20.glVertexAttribPointer(attN, dim, GL11.GL_FLOAT, false, 0, 0);
		GL15.glBindBuffer(GL15.GL_ARRAY_BUFFER, 0);
	}
	
	public void cleanup() {
		for (int v : vao) {
			GL30.glDeleteVertexArrays(v);
		}
		for (int v : vbo) {
			GL15.glDeleteBuffers(v);
		}
	}
	
}
