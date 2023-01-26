package NeuralNetwork;

/*
 * Input modifiers are optional functions for organizing input data, and can
 * help to provide clearer inputs to your network. They should be called on the
 * input array before returning in your agent's getInputs() function.
 * 
 * They are not always necessary, but are useful tools to keep in mind in case
 * you're having trouble with your network understanding inputs.
 * 
 * KEEP IN MIND: These functions are not necessarily part of neural network
 * theory, and are simply tools to help you organize data more effectively. They
 * should not be a fallback response for fixing issues with your network, and
 * will likely not solve any major behavior problems.
 */
public class DefaultInputModifiers {
	/*
	 * Set inputs to range 0 - 1, on a linear scale of highest value to lowest of
	 * base inputs
	 * 
	 * Can help with scaling data to a size that makes sense for a network and is
	 * within a reasonable range for input values that are dependent on each other.
	 * 
	 * See Desmos graph of function here:
	 * https://www.desmos.com/calculator/dsw6iki4m6
	 * 
	 * Warning: using a scale function will cause each input to be changed by the
	 * values of other inputs. Only should be used when all inputs are related.
	 */
	public static double[] scale(double[] inputs) {
		double tempMax = -1, tempMin = 2;

		for (final double input : inputs) {
			tempMax = tempMax < input ? input : tempMax;
			tempMin = tempMin > input ? input : tempMin;
		}

		for (byte i = 0; i < inputs.length; i++) {
			inputs[i] = (inputs[i] - tempMin) / (tempMax - tempMin);
		}

		return inputs;
	}

	// set inputs to range 0 - 1, on an exponential scale of highest value to lowest
	// of base inputs
	public static double[] scale(double[] inputs, double exponent) {
		double tempMax = -1, tempMin = 2;

		for (final double input : inputs) {
			tempMax = tempMax < input ? input : tempMax;
			tempMin = tempMin > input ? input : tempMin;
		}

		for (byte i = 0; i < inputs.length; i++) {
			inputs[i] = Math.pow((inputs[i] - tempMin) / (tempMax - tempMin), exponent);
		}

		return inputs;
	}

	// set inputs to range rangeMin - rangeMax, on a linear scale of highest value
	// to lowest of base inputs
	public static double[] scale(double[] inputs, double rangeMin, double rangeMax) {
		double tempMax = -1, tempMin = 2;

		for (final double input : inputs) {
			tempMax = tempMax < input ? input : tempMax;
			tempMin = tempMin > input ? input : tempMin;
		}

		for (byte i = 0; i < inputs.length; i++) {
			inputs[i] = (rangeMax - rangeMin) * (inputs[i] - tempMin) / (tempMax - tempMin);
		}

		return inputs;
	}

	// set inputs to range rangeMin - rangeMax, on an exponential scale of highest
	// value to lowest of base inputs
	public static double[] scale(double[] inputs, double rangeMin, double rangeMax, double exponent) {
		double tempMax = -1, tempMin = 2;

		for (final double input : inputs) {
			tempMax = tempMax < input ? input : tempMax;
			tempMin = tempMin > input ? input : tempMin;
		}

		for (byte i = 0; i < inputs.length; i++) {
			inputs[i] = Math.pow((rangeMax - rangeMin) * (inputs[i] - tempMin) / (tempMax - tempMin), exponent);
		}

		return inputs;
	}
}
