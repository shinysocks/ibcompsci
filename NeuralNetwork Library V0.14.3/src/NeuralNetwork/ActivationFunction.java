package NeuralNetwork;

/*
 * Activation Functions are a vital part of building a well-functioning neural
 * network. If you're new to the concept, or want to see an example of this
 * class in action, see the "DefaultActFunctions" class for an in-depth explanation
 * and to see the built-in activation functions. Also see the user-manual if it
 * is available at the time you are viewing this.
 *
 * This class is used to create activation functions compatible with this
 * library. It allows users(like you!) to add any functions I missed or to
 * experiment with making your own! I encourage you to explore and see what's
 * possible and what works best.
 */
public abstract class ActivationFunction {
	// built in variables to allow parameters to be defined at creation. Feel free
	// to add more to your classes if you need more that 3!
	protected float alpha, beta, sigma;

	// Default constructor. Your activation functions can change the inputs, and if
	// you aren't using one of the parameters, simply set it to 0 in your super()
	// call.
	public ActivationFunction(double alpha, double beta, double sigma) {
		this.alpha = (float) alpha;
		this.beta = (float) beta;
		this.sigma = (float) sigma;
	}

	// Function called on the output of each layer by the Network class.
	// Performs the activation function on each node's output given in the inputs
	// array
	float[] activateLayer(float[] inputs) {

		for (short i = 0; i < inputs.length; i++) {
			inputs[i] = activate(inputs[i]);
		}

		return inputs;
	}

	// using colt
//	public static float[] activateLayer(float[] inputs) {
//        DoubleFunction activateFunc = new DoubleFunction() {
//            @Override
//            public double apply(double z) {
//                return activate((float) z);
//            }
//        };
//        cern.colt.map.OpenFloatArrayAdapter inputsAdapter = new cern.colt.map.OpenFloatArrayAdapter(inputs);
//        inputsAdapter.assign(activateFunc);
//        return inputs;
//    }

	// Defines the math performed on the output of each node in the activateLayer()
	// function above
	abstract float activate(float z);
}
