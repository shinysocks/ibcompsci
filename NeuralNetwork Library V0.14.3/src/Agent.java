import NeuralNetwork.Network;

public class Agent extends Network{
	public int x, y, direc;

	public Agent() {
		super(4, 3, 1, 10, 0.3, 0.2);
		x = 10;
		y = 50;
		direc = 0;
	}

	@Override
	public float[] getInput() {
		return new float[]{x, y, direc};
	}

	@Override
	public float fitness() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Network child() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
