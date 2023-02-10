package NeuralNetwork;

import NeuralNetwork.LearningActivationFunction.LearningVariable;

/*
 * Activation Functions are used to increase the complexity of the learning a neural network is capable of.
 * Since neural networks work by multiplying an input to a node my a number, then averaging the outputs of
 * many nodes to get the input for the next node, they simply pass the output of each node through a linear
 * function. This means any network could be simplified to a linear function, which of course could not solve
 * complex problems. To combat this, the output of each node is ran through a non-linear function before being
 * passed to the next layer. This function is called an activation function, and they are used by the neurons
 * in our brains as well! There are many many kinds of activation functions, all suited to different
 * applications. I recommend you play around and see what works best for your network!
 *
 * Looking up graphs of the functions can be very helpful if you're new to neural networks.
 *
 * If network is not given an Activation Function, it will default to a linear function
 */
/*
 * So far I've implemented the following activation functions: Linear,
 * learningLinear, Sigmoid, LearningSigmoid, TanH, LearningTanH, BinaryStep,
 * ReLU, LearningReLU, LeakyReLU, LearningLeakyReLU, SinRELU, LearningSinReLU,
 * ELU, LearningELU, SELU, LearningSELU, Swish, LearningSwish, Softplus,
 * LearningSoftPlus, and Softmax!
 */
public class DefaultActFunctions {
	/*
	 * Gaussian: This activation function is defined as f(x) = e^(-x^2) and is a
	 * smooth function that can be used for certain types of regression problems.
	 *
	 * GELU: The Gaussian Error Linear Unit (GELU) is a relatively new activation
	 * function that was introduced in a paper by Google Research in 2020. It is
	 * defined as f(x) = x * cdf(x) where cdf is the cumulative distribution
	 * function of a normal distribution. GELU has been shown to work well in
	 * language models and has been used in several state-of-the-art models.
	 *
	 * Mish: Mish is a novel activation function that was proposed in 2019, which
	 * aims to smooth the standard ReLU and make it computationally more efficient.
	 *
	 * SoftExponential: The soft exponential activation function is an extension of
	 * the exponential activation function, which can have negative values and has a
	 * smooth transition from negative to positive values.
	 *
	 * SoftSign: The soft sign activation function is a smooth version of the sign
	 * activation function, which can have negative values and has a smooth
	 * transition from negative to positive values.
	 */
	/*
	 * Many of the activation functions in the second half are untested. I encourage
	 * experienced users to review my code and experiment to ensure they are working
	 * properly.
	 */
//    Gaussian: This function outputs random numbers from a Gaussian distribution. It can be useful when you want the network to generate new data, such as in a Generative Adversarial Network (GAN).

	public static class Linear extends ActivationFunction {
		/*
		 * Performs a basic linear f(x) = mx + b formula to input, where m = alpha and b
		 * = beta. Defaults to f(x) = x
		 */
		public Linear(double alpha, double beta) {
			super(alpha, beta, 0);
		}

		public Linear(double alpha) {
			super(alpha, 0, 0);
		}

		public Linear() {
			super(1, 0, 0);
		}

		@Override
		float activate(float z) {
			return z * alpha + beta;
		}

	}

	public static class LearningLinear extends LearningActivationFunction {
		/*
		 * Performs a basic linear f(x) = mx + b formula to input that can learn the
		 * best values for it's parameters, where m = alpha and b = beta. Defaults to
		 * f(x) = x
		 */
		public LearningLinear(double alpha, double beta) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(beta) });
		}

		public LearningLinear(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(0) });
		}

		public LearningLinear() {
			super(new LearningVariable[] { new LearningVariable(1), new LearningVariable(0) });
		}

		@Override
		float activate(float z) {
			return z * learningVars[0].getValue() + learningVars[1].getValue();
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningLinear();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue(), learningVars[1].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class Sigmoid extends ActivationFunction {
		/*
		 * A sigmoid function is a logistic function that locks values between 0 - 1 and
		 * slowly approaches them. Good for approximately binary outputs and commonly
		 * used as output function. Has great issue with disappearing gradient problem,
		 * meaning if a number gets too small, it will stay a very very small and cannot
		 * grow.
		 *
		 * alpha( > 1, default 10): changes point where gradient virtually disappears.
		 * abs(log_alpha(0.005)/sigma) = distance in either direction from beta before
		 * gradient ≈ 0.
		 *
		 * beta(default 0): changes center of the function on the x axis. If 1 should be
		 * the turning point on your graph(i.e. inputs above 1 are greater than 0.5 and
		 * numbers under are less than), then beta should equal 1. Commonly assigned to
		 * 0.5.
		 *
		 * sigma( > 1, default 1): drastically changes gradient similarly to alpha.
		 * Recommended to view a graph of your function to evaluate behavior before
		 * changing
		 */
		public Sigmoid(double alpha, double beta, double sigma) {
			super(alpha, beta, sigma);
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Sigmoid(double, double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
			if (sigma <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Sigmoid(double, double, double): "
								+ "sigma must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public Sigmoid(double alpha, double beta) {
			super(alpha, beta, 1);
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Sigmoid(double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public Sigmoid(double alpha) {
			super(alpha, 0, 1);
			if (alpha <= 1) {
				throw new IllegalArgumentException("\nActivation Function Constructor Exception | at Sigmoid(double): "
						+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public Sigmoid() {
			super(10, 0, 1);
		}

		@Override
		public float activate(float z) {
			return (float) (1 / (1 + Math.pow(alpha, -(sigma * (z - beta)))));
		}
	}

	public static class LearningSigmoid extends LearningActivationFunction {
		/*
		 * A sigmoid function that is able to learn the optimal values for it's
		 * parameters
		 *
		 * A sigmoid function is a logistic function that locks values between 0 - 1 and
		 * slowly approaches them. Good for approximately binary outputs and commonly
		 * used as output function. Has great issue with disappearing gradient problem,
		 * meaning if a number gets too small, it will stay a very very small and cannot
		 * grow.
		 *
		 * learningVariable1( > 1, default 10): changes point where gradient virtually
		 * disappears. abs(log_alpha(0.005)/sigma) = distance in either direction from
		 * beta before gradient ≈ 0.
		 *
		 * learningVariable2(default 0): changes center of the function on the x axis.
		 * If 1 should be the turning point on your graph(i.e. inputs above 1 are
		 * greater than 0.5 and numbers under are less than), then beta should equal 1.
		 * Commonly assigned to 0.5.
		 *
		 * learningVariable3( > 1, default 1): drastically changes gradient similarly to
		 * alpha. Recommended to view a graph of your function to evaluate behavior
		 * before changing
		 */
		public LearningSigmoid(double alpha, double beta, double sigma) {
			super(new LearningVariable[] { new LearningVariable(alpha, 1f), new LearningVariable(beta),
					new LearningVariable(sigma, 0f) });
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Sigmoid(double, double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
			if (sigma <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Sigmoid(double, double, double): "
								+ "sigma must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public LearningSigmoid(double alpha, double beta) {
			super(new LearningVariable[] { new LearningVariable(alpha, 1f), new LearningVariable(beta),
					new LearningVariable(1, 0f) });
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Sigmoid(double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public LearningSigmoid(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha, 1f), new LearningVariable(0),
					new LearningVariable(1, 0f) });
			if (alpha <= 1) {
				throw new IllegalArgumentException("\nActivation Function Constructor Exception | at Sigmoid(double): "
						+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public LearningSigmoid() {
			super(new LearningVariable[] { new LearningVariable(10, 1f), new LearningVariable(0),
					new LearningVariable(1, 0f) });
		}

		@Override
		public float activate(float z) {
			return (float) (1 / (1 + Math.pow(learningVars[0].getValue(),
					-(learningVars[2].getValue() * (z - learningVars[2].getValue())))));
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningSigmoid();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue(), learningVars[1].getValue(), learningVars[2].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class Tanh extends ActivationFunction {
		/*
		 * TanH(hyperbolic tangent) is similar to the sigmoid function except that it's
		 * range is centered around 0, meaning outputs are locked between -1 - 1
		 *
		 * alpha( > 1, default e): changes point where gradient virtually disappears.
		 *
		 * beta(default 0): changes center of range(moves graph up and down)
		 *
		 * sigma(default 1): changes range(i.e. 1 = {-1 <= y <= 1}, 2 = {-2 <= y <= 2}
		 */
		public Tanh(double alpha, double beta, double sigma) {
			super(alpha, beta, sigma);
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Tanh(double, double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public Tanh(double alpha, double beta) {
			super(alpha, beta, 1);
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Tanh(double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public Tanh(double alpha) {
			super(alpha, 0, 1);
			if (alpha <= 1) {
				throw new IllegalArgumentException("\nActivation Function Constructor Exception | at Tanh(double): "
						+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public Tanh() {
			super(Math.E, 0, 1);
		}

		@Override
		public float activate(float z) {
			return (float) (sigma
					* ((Math.pow(alpha, z) - Math.pow(alpha, -z)) / (Math.pow(alpha, z) + Math.pow(alpha, -z))) + beta);
		}
	}

	public static class LearningTanh extends LearningActivationFunction {
		/*
		 * A TanH(hyperbolic tangent) function that is able to learn the optimal values
		 * for it's parameters
		 *
		 * A TanH function is similar to the sigmoid function except that it's range is
		 * centered around 0, meaning outputs are locked between -1 - 1
		 *
		 * learningVariable1( > 1, default e): changes point where gradient virtually
		 * disappears.
		 *
		 * learningVariable2(default 0): changes center of range(moves graph up and
		 * down)
		 *
		 * learningVariable3(default 1): changes range(i.e. 1 = {-1 <= y <= 1}, 2 = {-2
		 * <= y <= 2}
		 */
		public LearningTanh(double alpha, double beta, double sigma) {
			super(new LearningVariable[] { new LearningVariable(alpha, 1f), new LearningVariable(beta),
					new LearningVariable(sigma) });
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Tanh(double, double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public LearningTanh(double alpha, double beta) {
			super(new LearningVariable[] { new LearningVariable(alpha, 1f), new LearningVariable(beta),
					new LearningVariable(1) });
			if (alpha <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at Tanh(double, double): "
								+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public LearningTanh(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha, 1f), new LearningVariable(0),
					new LearningVariable(1) });
			if (alpha <= 1) {
				throw new IllegalArgumentException("\nActivation Function Constructor Exception | at Tanh(double): "
						+ "alpha must have a value greater than 1\ngiven: " + alpha);
			}
		}

		public LearningTanh() {
			super(new LearningVariable[] { new LearningVariable(Math.E, 1f), new LearningVariable(0),
					new LearningVariable(1) });
		}

		@Override
		public float activate(float z) {
			return (float) (learningVars[2].getValue()
					* ((Math.pow(learningVars[0].getValue(), z) - Math.pow(learningVars[0].getValue(), -z))
							/ (Math.pow(learningVars[0].getValue(), z) + Math.pow(learningVars[0].getValue(), -z)))
					+ learningVars[1].getValue());
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningTanh();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue(), learningVars[1].getValue(), learningVars[2].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class BinaryStep extends ActivationFunction {
		/*
		 * Locks output to 2 values. Can be thought of as a node being on or off.
		 *
		 * alpha(default 0.5): cutoff for value being 0 or 1.
		 */
		public BinaryStep(double alpha) {
			super(alpha, 0, 0);
		}

		public BinaryStep() {
			super(0.5, 0, 0);
		}

		@Override
		public float activate(float z) {
			return z < alpha ? 0f : 1f;
		}
	}

	public static class Relu extends ActivationFunction {
		/*
		 * ReLU(rectified linear unit) is a linear function that locks the output to 0
		 * if the input is below 0. Not recommended for use in an output layer
		 *
		 * Alpha(default 1): gradient of the linear part of the function
		 */
		public Relu(double alpha) {
			super(alpha, 0, 0);
		}

		public Relu() {
			super(1, 0, 0);
		}

		@Override
		public float activate(float z) {
			return alpha * Math.max(0, z);
		}
	}

	public static class LearningRelu extends LearningActivationFunction {
		/*
		 * An ReLU(rectified linear unit) function that is able to learn the optimal
		 * values for it's parameters
		 *
		 * An ReLU function is a linear function that locks the output to 0 if the input
		 * is below 0. Not recommended for use in an output layer
		 *
		 * learningVariable1(default 1): gradient of the linear part of the function
		 */
		public LearningRelu(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha) });
		}

		public LearningRelu() {
			super(new LearningVariable[] { new LearningVariable(1) });
		}

		@Override
		public float activate(float z) {
			return learningVars[0].getValue() * Math.max(0, z);
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningRelu();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class LeakyRelu extends ActivationFunction {
		/*
		 * A Leaky ReLU(rectified linear unit) is an ReLU function that allows for some
		 * negative values by applying a small gradient to negative inputs instead of
		 * locking the output of the function to 0 when input < 0.
		 *
		 * alpha(default 1): gradient of the positive portion of the graph
		 *
		 * beta(default 0.1): gradient of the negative portion of the graph
		 */
		public LeakyRelu(double alpha, double beta) {
			super(alpha, beta, 0);
		}

		public LeakyRelu(double alpha) {
			super(alpha, 0.1, 0);
		}

		public LeakyRelu() {
			super(1, 0.1, 0);
		}

		@Override
		public float activate(float z) {
			return Math.max(z * alpha, z * beta);
		}
	}

	public static class LearningLeakyRelu extends LearningActivationFunction {
		/*
		 * A Leaky ReLU(rectified linear unit) function that is able to learn the
		 * optimal values for it's parameters
		 *
		 * A Leaky ReLU is an ReLU function that allows for some negative values by
		 * applying a small gradient to negative inputs instead of locking the output of
		 * the function to 0 when input < 0.
		 *
		 * learningVariable1(learning, default 1): gradient of the positive portion of
		 * the function
		 *
		 * learningVariable2(learning, default 0.1): gradient of the negative portion of
		 * the function
		 */
		public LearningLeakyRelu(double alpha, double beta) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(beta) });
		}

		public LearningLeakyRelu(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(0.1) });
		}

		public LearningLeakyRelu() {
			super(new LearningVariable[] { new LearningVariable(1), new LearningVariable(0.1) });
		}

		@Override
		public float activate(float z) {
			return Math.max(z * alpha, z * beta);
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningLeakyRelu();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue(), learningVars[1].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class SinRelu extends ActivationFunction {
		/*
		 * Sin ReLU(rectified linear unit) is a linear function that transitions to a
		 * sin function when it reaches 0
		 *
		 * differentiable everywhere, can output negative values, periodic
		 *
		 * alpha(default 1): gradient of the linear part of the function, and the
		 * amplitude of the sin portion.
		 */
		public SinRelu(double alpha) {
			super(alpha, 0, 0);
		}

		public SinRelu() {
			super(1, 0, 0);
		}

		@Override
		public float activate(float z) {
			return (float) (alpha * Math.max(Math.sin(z), z));
		}

	}

	public static class LearningSinRelu extends LearningActivationFunction {
		/*
		 * A sin ReLU(rectified linear unit) function that is able to learn the optimal
		 * values for it's parameters
		 *
		 * A sin ReLU function is a linear function that transitions to a sin function
		 * when it reaches 0
		 *
		 * differentiable everywhere, can output negative values, periodic
		 *
		 * learningVariable1(default 1): gradient of the linear part of the function,
		 * and the amplitude of the sin portion.
		 */
		public LearningSinRelu(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha) });
		}

		public LearningSinRelu() {
			super(new LearningVariable[] { new LearningVariable(1) });
		}

		@Override
		public float activate(float z) {
			return (float) (learningVars[0].getValue() * Math.max(Math.sin(z), z));
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningSinRelu();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class Elu extends ActivationFunction {
		/*
		 * An ELU(Exponential Linear Unit) is essentially Leaky ReLU function that
		 * instead of locking inputs below 0 to a very small gradient, locks them to an
		 * exponential function. Essentially smoothly curves from a positive linear
		 * function to a very slightly negative exponential function.
		 *
		 * alpha(default 1): gradient of positive portion of the function
		 *
		 * beta(default e): change in gradient of negative(exponential) portion of the
		 * function
		 */
		public Elu(double alpha, double beta) {
			super(alpha, beta, 0);
		}

		public Elu(double alpha) {
			super(alpha, Math.E, 0);
		}

		public Elu() {
			super(1, Math.E, 0);
		}

		@Override
		public float activate(float z) {
			return (float) (z > 0 ? z * alpha : Math.pow(beta, z) - 1);
		}
	}

	public static class LearningElu extends LearningActivationFunction {
		/*
		 * An ELU(Exponential Linear Unit) function that is able to learn the optimal
		 * values for it's parameters
		 *
		 * An ELU is essentially Leaky ReLU function that instead of locking inputs
		 * below 0 to a very small gradient, locks them to an exponential function.
		 * Essentially smoothly curves from a positive linear function to a very
		 * slightly negative exponential function.
		 *
		 * learningVariable1(learning, default 1): gradient of positive portion of the
		 * function
		 *
		 * learningVariable2(learning, default e): change in gradient of
		 * negative(exponential) portion of the function
		 */
		public LearningElu(double alpha, double beta) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(beta) });
		}

		public LearningElu(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(Math.E) });
		}

		public LearningElu() {
			super(new LearningVariable[] { new LearningVariable(1), new LearningVariable(Math.E) });
		}

		@Override
		public float activate(float z) {
			return (float) (z > 0 ? z * learningVars[0].getValue() : Math.pow(learningVars[1].getValue(), z) - 1);
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningElu();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue(), learningVars[1].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class Selu extends ActivationFunction {
		/*
		 * SELU(Scaled Exponential Linear Unit) functions are essentially an ELU
		 * function multiplied by a value. They allow for scaling of data between layers
		 * for more control of the values of the data exchanged through the network.
		 *
		 * alpha(default 1.67326324): gradient of positive portion of the function
		 *
		 * beta( > 1, default 1.05070098): scale of the function
		 */
		public Selu(double alpha, double beta) {
			super(alpha, beta, 0);
			if (beta <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Selu(double, double): "
								+ "beta must have a value greater than 1\ngiven: " + beta);
			}
		}

		public Selu(double alpha) {
			super(alpha, 1.05070098, 0);
		}

		public Selu() {
			super(1.67326324, 1.05070098, 0);
		}

		@Override
		float activate(float z) {
			if (z > 0) {
				return z * beta;
			}
			return (float) (beta * alpha * (Math.exp(z) - 1));

		}

	}

	public static class LearningSelu extends LearningActivationFunction {
		/*
		 * An SELU(Scaled Exponential Linear Unit) function that is able to learn the
		 * optimal values for it's parameters.
		 *
		 * SELU functions are essentially an ELU function multiplied by a value. They
		 * allow for scaling of data between layers for more control of the values of
		 * the data exchanged through the network.
		 *
		 * learningVariable1(default 1.67326324): gradient of positive portion of the
		 * function
		 *
		 * learningVariable2( > 1, default 1.05070098): scale of the function
		 */
		public LearningSelu(double alpha, double beta) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(beta, 1f) });
			if (beta <= 1) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Selu(double, double): "
								+ "beta must have a value greater than 1\ngiven: " + beta);
			}
		}

		public LearningSelu(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha), new LearningVariable(1.05070098, 1f) });
		}

		public LearningSelu() {
			super(new LearningVariable[] { new LearningVariable(1.67326324), new LearningVariable(1.05070098, 1f) });
		}

		@Override
		float activate(float z) {
			if (z > 0) {
				return z * learningVars[1].getValue();
			}
			return (float) (learningVars[1].getValue() * learningVars[0].getValue() * (Math.exp(z) - 1));

		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningSelu();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue(), learningVars[1].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}

	}

	public static class Swish extends ActivationFunction {
		/*
		 * A function that increases semi-linearly above 0, dips slightly negative for
		 * low negative inputs, then tens towards 0 as inputs become more negative.
		 *
		 * alpha(default e): higher numbers result in less negative outputs, and
		 * increase the speed at which it tends towards 0.
		 *
		 * beta(default 1): increases how negative outputs can be, and the gradient of
		 * the positive portion of the function
		 */
		public Swish(double alpha) {
			super(alpha, 0, 0);
		}

		public Swish() {
			super((float) Math.E, 0, 0);
		}

		@Override
		public float activate(float z) {
			return (float) (z / (1 + Math.pow(alpha, -z)));
		}
	}

	public static class LearningSwish extends LearningActivationFunction {
		/*
		 * A swish function that that is able to learn the optimal values for it's
		 * parameters
		 *
		 * Swish functions increase semi-linearly above 0, dips slightly negative for
		 * low negative inputs, then tens towards 0 as inputs become more negative.
		 *
		 * learningVariable1(default e): higher numbers result in less negative outputs,
		 * and increase the speed at which it tends towards 0.
		 *
		 * learningVariable2(default 1): increases how negative outputs can be, and the
		 * gradient of the positive portion of the function
		 */
		public LearningSwish(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha) });
		}

		public LearningSwish() {
			super(new LearningVariable[] { new LearningVariable(Math.E) });
		}

		@Override
		public float activate(float z) {
			return (float) (z / (1 + Math.pow(learningVars[0].getValue(), -z)));
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningSwish();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	public static class Softplus extends ActivationFunction {
		/*
		 * Similar to the ReLU function, Softplus is close to linear for positive
		 * inputs, although it's y-intercept is > 0, and it curves quickly towards 0 for
		 * negative inputs.
		 *
		 * differentiable everywhere, which can be useful for backpropagation. Output
		 * always positive, approaches zero as input becomes more negative, approaches
		 * infinity as input becomes more positive.
		 *
		 * alpha( > 0, default e): changes the slope of the linear part of the line
		 * without changing the y-intercept
		 *
		 * beta( > 0, default 1): changes the slope of the linear part of the line as
		 * well as the y-intercept
		 */
		public Softplus(double alpha, double beta) {
			super(alpha, beta, 0);
			if (alpha <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Softplus(double, double): "
								+ "alpha must have a value greater than 0\ngiven: " + alpha);
			}
			if (beta <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Softplus(double, double): "
								+ "beta must have a value greater than 0\ngiven: " + beta);
			}
		}

		public Softplus(double alpha) {
			super(alpha, 1, 0);
			if (alpha <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Softplus(double, double): "
								+ "alpha must have a value greater than 0\ngiven: " + alpha);
			}
		}

		public Softplus() {
			super(Math.E, 1, 0);
		}

		@Override
		float activate(float z) {
			return (float) (Math.log1p(Math.exp(alpha * z)) * beta);
		}
	}

	public static class LearningSoftplus extends LearningActivationFunction {
		/*
		 * A softplus function that that is able to learn the optimal values for it's
		 * parameters
		 *
		 * Similar to the ReLU function, Softplus is close to linear for positive
		 * inputs, although it's y-intercept is > 0, and it curves quickly towards 0 for
		 * negative inputs.
		 *
		 * differentiable everywhere, which can be useful for backpropagation. Output
		 * always positive, approaches zero as input becomes more negative, approaches
		 * infinity as input becomes more positive.
		 *
		 * learningVariable1( > 0, default e): changes the slope of the linear part of
		 * the line without changing the y-intercept
		 *
		 * learningVariable2( > 0, default 1): changes the slope of the linear part of
		 * the line as well as the y-intercept
		 */
		public LearningSoftplus(double alpha, double beta) {
			super(new LearningVariable[] { new LearningVariable(alpha, 0f), new LearningVariable(beta, 0f) });
			if (alpha <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Softplus(double, double): "
								+ "alpha must have a value greater than 0\ngiven: " + alpha);
			}
			if (beta <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Softplus(double, double): "
								+ "beta must have a value greater than 0\ngiven: " + beta);
			}
		}

		public LearningSoftplus(double alpha) {
			super(new LearningVariable[] { new LearningVariable(alpha, 0f), new LearningVariable(1, 0f) });
			if (alpha <= 0) {
				throw new IllegalArgumentException(
						"\nActivation Function Constructor Exception | at ActivationType.Softplus(double, double): "
								+ "alpha must have a value greater than 0\ngiven: " + alpha);
			}
		}

		public LearningSoftplus() {
			super(new LearningVariable[] { new LearningVariable(Math.E, 0f), new LearningVariable(1, 0f) });
		}

		@Override
		float activate(float z) {
			return (float) (Math.log1p(Math.exp(learningVars[0].getValue() * z)) * learningVars[0].getValue());
		}

		@Override
		public LearningActivationFunction newInstance() {
			return new LearningSoftplus();
		}

		@Override
		public float[] getAllVars() {
			return new float[] { learningVars[0].getValue(), learningVars[1].getValue() };
		}

		@Override
		public void setAllVars(float[] newVars) {
		}
	}

	// FINISH DESCRIPTION
	public static class Softmax extends FullLayerActivationFunction {
		/*
		 * "typically used for multi-class classification problems, as it produces a
		 * probability distribution over the classes, making it easy to select the most
		 * likely class. it is differentiable everywhere, so it can be used in
		 * backpropagation based optimization algorithms, and it is also bounded, so it
		 * can avoid some issues that can arise with other unbounded activation
		 * functions."
		 */
		public Softmax(ActivationFunction actFunction) {
			super(actFunction);
		}

		public Softmax() {
			super();
		}

		@Override
		protected float[] activate(float[] inputs) {
			float sum = 0;
			final float[] output = new float[inputs.length];

			// System.out.println(Arrays.toString(inputs));

			for (short i = 0; i < inputs.length; i++) {
				output[i] = (float) Math.exp(inputs[i]);
				sum += output[i];
			}

			for (short i = 0; i < output.length; i++) {
				output[i] = output[i] / sum;
			}

			return output;
		}

//		import com.github.fommil.netlib.BLAS;
//
//		public float[] softmax(float[] inputs) {
//		    final float[] output = new float[inputs.length];
//		    BLAS.getInstance().saxpy(inputs.length, 1.0f, inputs, 1, output, 1);
//		    final float sum = BLAS.getInstance().sasum(inputs.length, output, 1);
//		    BLAS.getInstance().sscal(inputs.length, 1.0f / sum, output, 1);
//		    return output;
//		}

	}

//	public static class Glu extends ActivationFunction {
	/*
	 * A GLU(Gated Linear Unit) is a variation of the ReLU activation function that
	 * uses a gate to control the amount of information that flows through a neuron.
	 * This can help to prevent the vanishing gradient problem and improve the
	 * stability of the network.
	 */
//		public Glu(double alpha) {
//			super(alpha, 0, 0);
//		}
//
//		public Glu() {
//			super(0.5, 0, 0);
//		}
//
//		@Override
//		public float activate(float z) {
//			return (float) (1 / (1 + Math.exp(-alpha * z)) * alpha);
//		}
//	}
}