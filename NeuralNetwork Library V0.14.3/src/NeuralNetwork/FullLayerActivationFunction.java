package NeuralNetwork;

import NeuralNetwork.DefaultActFunctions.Linear;

/*
 * The FullLayerActivationFunction class allows an activation function to not
 * only run the output of each node in a layer through a normal activation
 * function, but then also take those values and apply another function on the
 * array of layer outputs relative to other outputs.
 * 
 * This can be helpful in scenarios where outputs of a network should be scaled
 * to values in a set range in order to create probability ranges for potential
 * cases, such as identifying shapes in an image, where the outputs should all
 * be scaled from 0 - 1 to create confidence percentages as to what the image
 * shows.
 * 
 * These kinds of activation functions are seen most often in 
 * 
 * There are not many built in FullLayerActivationFunctions, however as with
 * other kinds of ActivationFunctions, I encourage you to define your own!
 */
public abstract class FullLayerActivationFunction extends ActivationFunction {
	// FullLayerActivationFunctions take in a normal ActivationFunction to apply to
	// all node outputs as would normally be seen in layer output.
	protected ActivationFunction activationFunction;

	public FullLayerActivationFunction(ActivationFunction actFunction) {
		super(0, 0, 0);
		activationFunction = actFunction;
	}

	public FullLayerActivationFunction() {
		super(0, 0, 0);
		activationFunction = new Linear();
	}

	@Override
	// This is the activation function called on each node output before the layer
	// activation is performed. Defaults to f(x) = x
	public float activate(float z) {
		return activationFunction.activate(z);
	}

	@Override
	// Function called on the output of each layer by the Network class
	float[] activateLayer(float[] inputs) {
		return activate(super.activateLayer(inputs));
	}

	// This is the function actually called on the layer outputs by the Layer class'
	// fullLayerActivationOutput method.
	abstract protected float[] activate(float[] inputs);
}
