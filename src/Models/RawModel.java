package Models;

public class RawModel {

	int vaoID;
	int vertexCount;
	
	public RawModel(int v, int vc) {
		vaoID = v;
		vertexCount = vc;
	}

	public int getVaoID() {
		return vaoID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

}
