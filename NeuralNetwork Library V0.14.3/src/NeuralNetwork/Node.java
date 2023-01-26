package NeuralNetwork;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Random;

/*
 * unlike the rest of the network classes, Node works as more of a placeholder or reference.
 * A Node object's only purpose is to hold the weights and give output from given inputs
 * Most
 */
public class Node implements Serializable {
	private static final long serialVersionUID = 9001300250076712972L;
	private final float[] weights;
	private final float bias;

	public Node(int numIns) {
		bias = (float) ((Math.random() * 2 - 1) / numIns);
		weights = generateWeights(numIns);
	}

	public Node(int numIns, float bias) {
		this.bias = bias;
		weights = generateWeights(numIns);
	}

	public Node(int numIns, double bias) {
		this.bias = (float) bias;
		weights = generateWeights(numIns);
	}

	public Node(float[] weights) {
		bias = (float) ((Math.random() * 2 - 1) / weights.length);
		this.weights = weights;
	}

	public Node(float[] weights, float bias) {
		this.bias = bias;
		this.weights = weights;
	}

	public Node(float[] weights, double bias) {
		this.bias = (float) bias;
		this.weights = weights;
	}

	// weights should start able to be both positive or negative, but must average
	// to 1 so outputs trend towards inputs and not infinity
	private float[] generateWeights(int numIns) {
		float[] weights = new float[numIns];

		for (short i = 0; i < numIns; i++) {
			weights[i] = (float) (Math.random() * 4 - 1);
		}

		return weights;
	}

	private float[] generateWeights(int numIns, Random rand) {
		float[] weights = new float[numIns];

		for (short i = 0; i < numIns; i++) {
			weights[i] = (float) (rand.nextDouble() * 4 - 1);
		}

		return weights;
	}

	// used to mutate both weights and biases
	static float mutate(float num, float muteRate, float muteSize) {
		Random rand = new Random();
		return (float) (rand.nextDouble() <= muteRate ? rand.nextDouble(num - muteSize / 2, num + muteSize / 2) : num);
	}

	// mutate both weights and biases using Random class so a seed can be given
	static float mutate(float num, float muteRate, float muteSize, Random rand) {
		return (float) (rand.nextDouble() <= muteRate ? rand.nextDouble(num - muteSize / 2, num + muteSize / 2) : num);
	}

	public float output(float[] inputs) {
		float sum = 0;

		for (short i = 0; i < getNumIns(); i++) {
			sum += getWeights()[i] * inputs[i];
		}

		return sum / getNumIns() + getBias();
	}

	public Node generateChild(float muteRate, float muteSize) {
		final float[] newWeights = new float[getNumIns()];

		for (short i = 0; i < getNumIns(); i++) {
			newWeights[i] = mutate(getWeights()[i], muteRate, muteSize);
		}

		return new Node(newWeights, mutate(getBias(), muteRate, muteSize * 0.65f));
	}

	@Override
	public String toString() {
		double sum = 0;

		for (final double n : getWeights()) {
			sum += n;
		}

		return "Node " + "Weight av: " + sum / getNumIns() + ", Bias: " + getBias() + ", "
				+ Arrays.toString(getWeights());
	}

	public short getNumIns() {
		return (short) getWeights().length;
	}

	public float[] getWeights() {
		return weights;
	}

	public float getBias() {
		return bias;
	}
}
