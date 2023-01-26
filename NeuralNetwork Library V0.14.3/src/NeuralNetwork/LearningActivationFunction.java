package NeuralNetwork;

/*
 * This class allows activation functions to be accessed and manipulated by the
 * Learn class. Children of this class are able to use "LearningVariables" that
 * can be adjusted by the network during training to find the best value for
 * your implementation.
 *
 *
 * WARNING: This functionality is in-development and may not always work as
 * expected. Although I guess that's true for most of this library :)
 */
public abstract class LearningActivationFunction extends ActivationFunction {
	// Ontop of 3 default variables, LearningActivationFunctions holds an array
	// of LearningVariables to allow parameters that learn as well as ones that keep
	// their values after definition.
	protected LearningVariable[] learningVars;

	public LearningActivationFunction(double alpha, double beta, double sigma, LearningVariable[] learningVars) {
		super(alpha, beta, sigma);
		this.learningVars = learningVars;
	}

	public LearningActivationFunction(double alpha, double beta, LearningVariable[] learningVars) {
		super(alpha, beta, 0);
		this.learningVars = learningVars;
	}

	public LearningActivationFunction(double alpha, LearningVariable[] learningVars) {
		super(alpha, 0, 0);
		this.learningVars = learningVars;
	}

	public LearningActivationFunction(LearningVariable[] learningVars) {
		super(0, 0, 0);
		this.learningVars = learningVars;
	}

	// LearningActivationFunctions require a "newInstance" function for the learn
	// function.
	public abstract LearningActivationFunction newInstance();

	// Should return all variables defined, both ones that learn and don't.
	public abstract float[] getAllVars();

	// Should take in output from getAllVars method and set instance variables to
	// their value.
	// Can be blank if there are no non-learning parameters
	public abstract void setAllVars(float[] newVars);

	public LearningVariable[] getLearningVars() {
		return learningVars;
	}

	public void setLearningVars(LearningVariable[] learningVars) {
		this.learningVars = learningVars;
	}

	/*
	 * The LearningVariable class holds the values of the parameters for the
	 * LearningActivationFunction to change in order to learn. They also hold the
	 * max and min bounds for the variable to limit possible values the learning can
	 * set. If min or max are set to null, there will be no limit implemented.
	 */
	protected static class LearningVariable {
		protected float value, startValue;
		protected final Float min, max;

		protected LearningVariable(float value, Float min, Float max) {
			this.value = value;
			startValue = value;
			this.min = min;
			this.max = max;
		}

		protected LearningVariable(float value, Float min) {
			this.value = value;
			startValue = value;
			this.min = min;
			max = null;
		}

		protected LearningVariable(float value) {
			this.value = value;
			startValue = value;
			min = null;
			max = null;
		}

		protected LearningVariable(double value, Float min, Float max) {
			this.value = (float) value;
			startValue = (float) value;
			this.min = (float) min;
			this.max = (float) max;
		}

		protected LearningVariable(double value, Float min) {
			this.value = (float) value;
			startValue = (float) value;
			this.min = (float) min;
			max = null;
		}

		protected LearningVariable(double value) {
			this.value = (float) value;
			startValue = (float) value;
			min = null;
			max = null;
		}

		// Sets new value if value given is within range. Returns true if successful.
		public boolean setValue(float newValue) {
			if ((min == null || newValue > min) && (max == null || newValue < max)) {
				value = newValue;
				return true;
			}
			return false;
		}

		public float getValue() {
			return value;
		}

		public float getStartValue() {
			return startValue;
		}

		public Float getMin() {
			return min;
		}

		public Float getMax() {
			return max;
		}
	} // end LearningVariable class
}
