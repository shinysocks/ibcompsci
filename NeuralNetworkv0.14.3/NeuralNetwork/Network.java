package NeuralNetwork;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/*
 * The Network class is the backbone of the library. It holds the information
 * about the state of the network, as well as the chosen ActivationFunction, the
 * mutation rate and size, and more. To interact with the library, extend this
 * class to the class you want to learn, and create the required functions.
 */
public abstract class Network<T extends Network<T>> {
	// For info on Activation Functions, see the ActivationFunction and
	// DefaultActivationFuctions classes. Or see the user manual if it is available
	// at the time you're reading this.
	private ActivationFunction activationFunction, outputActivationFunction;

	// The probability for each weight to mutate, and the maximum percent change of
	// the mutation.
	private final float muteRate, muteSize;

	// holds fitness so it doesn't need to be calculated many times when learning.
	private float fitness = 0;

	private Layer[] layers;

	// create new network with all values specified and no activation function
	public Network(int numIns, int numOuts, int numLayers, int numNodes, double muteRate, double muteSize) {
		if (0 > muteRate || 1 < muteRate) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(int, int, int, int, double, double): "
							+ "Network mutation rate must be a float between 0 and 1, inclusive\ngiven: " + muteRate);
		}
		if (muteSize < 0) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(int, int, int, int, double, double): "
							+ "Network mutation size must be positive\ngiven: " + muteSize);
		}
		this.muteRate = (float) muteRate;
		this.muteSize = (float) muteSize;
		this.activationFunction = new DefaultActFunctions.Linear();
		this.outputActivationFunction = new DefaultActFunctions.Linear();
		this.layers = generateNetworkArray(numIns, numOuts, numLayers, numNodes);
	}

	// create new network with all values specified
	public Network(int numIns, int numOuts, int numLayers, int numNodes, double muteRate, double muteSize,
			ActivationFunction activationFunction) {
		if (0 > muteRate || 1 < muteRate) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(int, int, int, int, double, double, ActivationFunction): "
							+ "Network mutation rate must be a float between 0 and 1, inclusive\ngiven: " + muteRate);
		}
		if (muteSize < 0) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(int, int, int, int, double, double, ActivationFunction): "
							+ "Network mutation size must be positive\ngiven: " + muteSize);
		}
		this.muteRate = (float) muteRate;
		this.muteSize = (float) muteSize;
		this.activationFunction = activationFunction;
		this.outputActivationFunction = activationFunction;
		this.layers = generateNetworkArray(numIns, numOuts, numLayers, numNodes);
	}

	// create new network with all values specified and a secondary activation
	// function
	public Network(int numIns, int numOuts, int numLayers, int numNodes, double muteRate, double muteSize,
			ActivationFunction activationFunction, ActivationFunction outputActivationFunction) {
		if (0 > muteRate || 1 < muteRate) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(int, int, int, int, double, double, ActivationFunction): "
							+ "Network mutation rate must be a float between 0 and 1, inclusive\ngiven: " + muteRate);
		}
		if (muteSize < 0) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(int, int, int, int, double, double, ActivationFunction): "
							+ "Network mutation size must be positive\ngiven: " + muteSize);
		}
		this.muteRate = (float) muteRate;
		this.muteSize = (float) muteSize;
		this.activationFunction = activationFunction;
		this.outputActivationFunction = outputActivationFunction;
		this.layers = generateNetworkArray(numIns, numOuts, numLayers, numNodes);
	}

	// create new network with values defined from given layers with no set
	// activationType
	public Network(Layer[] layers, double muteRate, double muteSize) {
		if (0 > muteRate || 1 < muteRate) {
			throw new IllegalArgumentException("\nNetwork Constructor Exception | at Network(Layer[], double, double): "
					+ "Network mutation rate must be a double between 0 and 1, inclusive\ngiven: " + muteRate);
		}
		if (muteSize < 0) {
			throw new IllegalArgumentException("\nNetwork Constructor Exception | at Network(Layer[], double, double):"
					+ "Network mutation size must be positive\ngiven: " + muteSize);
		}
		this.layers = layers;
		this.muteRate = (float) muteRate;
		this.muteSize = (float) muteSize;
		this.activationFunction = new DefaultActFunctions.Linear();
		this.outputActivationFunction = new DefaultActFunctions.Linear();
	}

	// create new network with values defined from given layers
	public Network(Layer[] layers, double muteRate, double muteSize, ActivationFunction activationFunction) {
		if (0 > muteRate || 1 < muteRate) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(Layer[], double, double, ActivationFunction): "
							+ "Network mutation rate must be a double between 0 and 1, inclusive\ngiven: " + muteRate);
		}
		if (muteSize < 0) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(Layer[], double, double, ActivationFunction):"
							+ "Network mutation size must be positive\ngiven: " + muteSize);
		}
		this.layers = layers;
		this.muteRate = (float) muteRate;
		this.muteSize = (float) muteSize;
		this.activationFunction = activationFunction;
		this.outputActivationFunction = activationFunction;
	}

	// create new network with values defined from given layers and a defined
	// activation function and output later activation function
	public Network(Layer[] layers, double muteRate, double muteSize, ActivationFunction activationFunction,
			ActivationFunction outputActivationFunction) {
		if (0 > muteRate || 1 < muteRate) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(Layer[], double, double, ActivationFunction, ActivationFunction): "
							+ "Network mutation rate must be a float between 0 and 1, inclusive\ngiven: " + muteRate);
		}
		if (muteSize < 0) {
			throw new IllegalArgumentException(
					"\nNetwork Constructor Exception | at Network(Layer[], double, double, ActivationFunction, ActivationFunction):"
							+ "Network mutation size must be positive\ngiven: " + muteSize);
		}
		this.layers = layers;
		this.muteRate = (float) muteRate;
		this.muteSize = (float) muteSize;
		this.activationFunction = activationFunction;
		this.outputActivationFunction = outputActivationFunction;
	}

	// generate new layers array with given size
	private static Layer[] generateNetworkArray(int numIns, int numOuts, int numLayers, int numNodes) {
		Layer[] layers;
		layers = new Layer[numLayers + 2];
		layers[0] = new Layer(numIns, numNodes);

		for (byte i = 1; i < layers.length - 1; i++) {
			layers[i] = new Layer(numNodes);
		}

		layers[layers.length - 1] = new Layer(numNodes, numOuts);

		return layers;
	}

	// generate new layers array of same size as given network
	// <?> given bc static method so needs to work with any class that is a child of
	// Network, not just one given
	private static Layer[] generateNetworkArray(Network<?> network) {
		final Layer[] layers = new Layer[network.getNumLayers() + 2];

		layers[0] = new Layer(network.getNumIns(), network.getNumNodes());
		for (byte i = 1; i < layers.length - 1; i++) {
			layers[i] = new Layer(network.getNumNodes());
		}
		layers[layers.length - 1] = new Layer(network.getNumNodes(), network.getNumOuts());

		return layers;
	}

	public static boolean networksCompatible(Network<?>[] networks) {
		for (short i = 1; i < networks.length; i++) {
			if (networks[i].getNumIns() != networks[i - 1].getNumIns()
					|| networks[i].getNumOuts() != networks[i - 1].getNumOuts()) {
				return false;
			}
		}
		return true;
	}

	public static boolean networksMatch(Network<?>[] networks) {
		for (short i = 1; i < networks.length; i++) {
			if (networks[i].getNumIns() != networks[i - 1].getNumIns()
					|| networks[i].getNumOuts() != networks[i - 1].getNumOuts()
					|| networks[i].getNumLayers() != networks[i - 1].getNumLayers()
					|| networks[i].getNumNodes() != networks[i - 1].getNumNodes()) {
				return false;
			}
		}
		return true;
	}

	public static boolean networksMatch(Network<?> network1, Network<?> network2) {
		if (network1.getNumIns() != network2.getNumIns() || network1.getNumOuts() != network2.getNumOuts()
				|| network1.getNumLayers() != network2.getNumLayers()
				|| network1.getNumNodes() != network2.getNumNodes()) {
			return false;
		}
		return true;
	}

	// return new null layers array of same size as network
	public Layer[] generateEmptyChildArray() {
		final Layer[] layers = new Layer[getNumLayers() + 2];

		layers[0] = new Layer(getNumIns(), getNumNodes());
		for (byte i = 1; i < layers.length - 1; i++) {
			layers[i] = new Layer(getNumNodes());
		}
		layers[layers.length - 1] = new Layer(getNumNodes(), getNumOuts());

		return layers;
	}

	// return new random layers array of same size as network
	public Layer[] generateBlankChildArray() {
		return generateNetworkArray(this);
	}

	// return new Network of same size with randomized weights
	public T generateBlankChild() {
		final T newNetwork = child();
		newNetwork.setWeights(generateNetworkArray(this));
		return newNetwork;
	}

	// return new Network with same weights as this
	public T copy() {
		final T newNetwork = child();
		newNetwork.setWeights(getLayers());
		return newNetwork;
	}

	// get output from inputs from user-defined getInputs function with
	// ActivationFunctions defined at initialization
	public float[] getOutput() {
		float[] tempInputs = getInput();
		if (tempInputs.length != getNumIns()) {
			throw new IllegalArgumentException(
					"\nNetwork Input Exception | at Network.getOutput(): Network that takes  " + getNumIns()
							+ " inputs cannot accept input array of length " + tempInputs.length);
		}

		for (byte i = 0; i < getLayers().length - 1; i++) {
			tempInputs = getActivationFunction().activateLayer(getLayers()[i].output(tempInputs));
		}

		return getOutputActivationFunction().activateLayer(getLayers()[getLayers().length - 1].output(tempInputs));
	}

	// get output from inputs from user-defined getInputs function with given
	// activation function
	public float[] getOutput(ActivationFunction activationFunction) {
		float[] tempInputs = getInput();
		if (tempInputs.length != getNumIns()) {
			throw new IllegalArgumentException(
					"\nNetwork Input Exception | at Network.getOutput(ActivationType): Network that takes  "
							+ getNumIns() + " inputs cannot accept input array of length " + tempInputs.length);
		}

		for (final Layer layer : getLayers()) {
			tempInputs = getActivationFunction().activateLayer(layer.output(tempInputs));
		}

		return tempInputs;
	}

	// get output from given inputs with ActivationFunctions defined at
	// initialization
	public float[] getOutput(float[] inputs) {
		if (inputs.length != getNumIns()) {
			throw new IllegalArgumentException(
					"\nNetwork Input Exception | at Network.getOutput(float[]): Network that takes  " + getNumIns()
							+ " inputs cannot accept input array of length " + inputs.length);
		}
		float[] tempInputs = inputs.clone();

		for (byte i = 0; i < getLayers().length - 1; i++) {
			tempInputs = getActivationFunction().activateLayer(getLayers()[i].output(tempInputs));
		}

		return getOutputActivationFunction().activateLayer(getLayers()[getLayers().length - 1].output(tempInputs));
	}

	// get output from given inputs and activation function
	public float[] getOutput(float[] inputs, ActivationFunction activationFunction) {
		if (inputs.length != getNumIns()) {
			throw new IllegalArgumentException(
					"\nNetwork Input Exception | at Network.getOutput(float[], ActivationType): Network that takes  "
							+ getNumIns() + " inputs cannot accept input array of length " + inputs.length);
		}
		float[] tempInputs = inputs.clone();

		for (final Layer layer : getLayers()) {
			tempInputs = getActivationFunction().activateLayer(layer.output(tempInputs));
		}

		return tempInputs;
	}

	// set weights to be match given Network's
	public void setWeights(Network<T> network) {
		if (network.getNumIns() != getNumIns()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Network): Networks that take " + getNumIns()
							+ " inputs cannot accept a new network that takes " + network.getNumIns() + " inputs");
		}
		if (network.getNumNodes() != getNumNodes()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Network): Networks with " + getNumNodes()
							+ " nodes per hidden layer cannot accept a new network with " + network.getNumNodes()
							+ " nodes per hidden layer");
		}
		if (network.getNumLayers() != getNumLayers()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Network): Networks with " + getNumLayers()
							+ " hidden layers cannot accept a new network with " + network.getNumLayers()
							+ " hidden layers");
		}
		if (network.getNumOuts() != getNumOuts()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Network): Networks that give " + getNumOuts()
							+ " outputs cannot accept a new network that gives " + network.getNumOuts() + " outputs");
		}

		layers = network.getLayers();
	}

	// take new layers
	public void setWeights(Layer[] layers) {
		if (layers[0].getNumIns() != getNumIns()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Layer[]): Networks that take " + getNumIns()
							+ " inputs cannot accept a new layer array that takes " + layers[0].getNumIns()
							+ " inputs");
		}
		if (layers[0].getNumNodes() != getNumNodes()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Layer[]): Networks with " + getNumNodes()
							+ " nodes per hidden layer cannot accept a new layer array with " + layers[0].getNumNodes()
							+ " nodes per hidden layer");
		}
		if (layers.length - 2 != getNumLayers()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Layer[]): Networks with " + getNumLayers()
							+ " hidden layers cannot accept a new layer array with " + (layers.length - 2)
							+ " hidden layers");
		}
		if (layers[layers.length - 1].getNumNodes() != getNumOuts()) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Network.setWeights(Layer[]): Networks that give " + getNumOuts()
							+ " outputs cannot accept a new layer array that gives "
							+ layers[layers.length - 1].getNumNodes() + " outputs");
		}

		this.layers = layers;
	}

	// write layer array to file
	public boolean toFile(String fileName) {
		try {
			final ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName));
			out.writeObject(this.getLayers());
			out.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		if (new File(fileName).exists()) {
			return true;
		}
		return false;
	}

	// read & return layer array from file
	public static Layer[] readFile(String fileName) {
		try {
			final ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName));
			final Layer[] layersIn = (Layer[]) in.readObject();
			in.close();
			return layersIn;
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
		return null;
	}

	// set weights from data from file
	public void setFromFile(String fileName) {
		setWeights(readFile(fileName));
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder("numIns: ").append(getNumIns()).append(", numOuts: ")
				.append(getNumOuts()).append(", hiddenLayers: ").append(getNumLayers()).append(", totalLayers: ")
				.append(getLayers().length).append(", nodes per hiddenLayer: ").append(getNumNodes()).append(", \t{\n");

		for (byte i = 0; i < getNumLayers() + 2; i++) {
			str.append("\t\tLayer ").append(i).append(": ").append(getLayers()[i]).append(",\n");
			;
		}

		return str.append("\t}").toString();
	}

	public abstract float[] getInput(); // get input based on the environment made by user

	public abstract float fitness(); // Scoring networks based on performance

	public abstract T child(); // NAME BETTER MAYBE COPY()??

	public final Layer[] getLayers() {
		return layers;
	}

	public final short getNumIns() {
		return getLayers()[0].getNumIns();
	}

	public final short getNumOuts() {
		return getLayers()[layers.length - 1].getNumNodes();
	}

	public final short getNumNodes() {
		return layers[0].getNumNodes();
	}

	public final byte getNumLayers() {
		return (byte) (layers.length - 2);
	}

	public final float getMuteRate() {
		return muteRate;
	}

	public final float getMuteSize() {
		return muteSize;
	}

	@SuppressWarnings("unchecked")
	public final <ActType extends ActivationFunction> ActType getActivationFunction() {
		return (ActType) activationFunction;
	}

	@SuppressWarnings("unchecked")
	public final <ActType extends ActivationFunction> ActType getOutputActivationFunction() {
		return (ActType) outputActivationFunction;
	}

	public void setActivationFunction(ActivationFunction actFunction) {
		this.activationFunction = actFunction;
	}

	public void setOutputActivationFunction(ActivationFunction actFunction) {
		outputActivationFunction = actFunction;
	}

	public void setFitness(float fitness) {
		this.fitness = fitness;
	}

	public void setFitnessVar() {
		this.fitness = fitness();
	}

	public float getFitness() {
		return fitness;
	}
}