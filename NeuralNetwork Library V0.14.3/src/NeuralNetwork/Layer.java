package NeuralNetwork;

import java.io.Serializable;

/*
 * Layers represent the layers of nodes in the network. They hold an array of
 * nodes and provide several functions for manipulating them.
 */
public class Layer implements Serializable {
	private static final long serialVersionUID = -6061419000603469908L;
	private final Node[] nodes;

	// generate new random layer with different number of inputs and nodes
	public Layer(int numIns, int numNodes) {
		nodes = generateWeights(numIns, numNodes);
	}

	// generate new random layer with same number of inputs and nodes, simplifies
	// hidden layer construction
	public Layer(int numNodes) {
		nodes = generateWeights(numNodes, numNodes);
	}

	// new layer with given node array
	public Layer(Node[] nodes) {
		this.nodes = nodes;
	}

	// generate new nodes array with randomized weights
	private Node[] generateWeights(int numIns, int numNodes) {
		Node[] nodes = new Node[numNodes];

		for (int i = 0; i < numNodes; i++) {
			nodes[i] = new Node(numIns);
		}

		return nodes;
	}

	// get the output of the layer from given input
	public float[] output(float[] inputs) {
		if (inputs.length != getNumIns()) {
			throw new IllegalArgumentException("Layer Input Exception | at Layer.output(float[]): Layer that takes  "
					+ getNumIns() + " inputs cannot take input array of length " + inputs.length);
		}
		final float[] outputs = new float[getNumNodes()];

		for (short i = 0; i < getNumNodes(); i++) {
			outputs[i] = getNodes()[i].output(inputs);
		}

		return outputs;
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder("NumIns: ").append(getNumIns()).append(", ").append("NumNodes: ")
				.append(getNumNodes()).append("\t{\n");
		for (short i = 0; i < getNumNodes(); i++) {
			str.append("\t\t\tNode ").append(i).append(": ").append(getNodes()[i]).append(", \n");
		}
		return str.append("\t\t}").toString();
	}

	public final Node[] getNodes() {
		return nodes;
	}

	public final short getNumIns() {
		return getNodes()[0].getNumIns();
	}

	public final short getNumNodes() {
		return (short) getNodes().length;
	}
}