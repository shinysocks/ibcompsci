package NeuralNetwork;

/*
 * LearnFunctions determine the way neural networks learn how to solve the
 * problem they're given. They're mostly self-explanatory, but you can look
 * below to see the explanations for some simple ones. Just like
 * ActivationFunctions, feel free to write and try your own! Mine probably
 * aren't perfect or the best ones for your project, so once you get comfortable
 * with the library, try experimenting.
 *
 * Learning functions greatly effect a network's learning performance, but it
 * can be hard to know which function will work best for your network, so try
 * several and see how it goes!
 *
 *
 *
 * If no LearnFunction is given on initialization, Network will default to
 * Clone.
 */
public class DefaultLearnFunctions {
	public static enum LearnType {
		CLONE, PARENT, CROSSOVER;
	}

	/*
	 * The Clone learn function is the most basic and easiest to understand. Simply,
	 * it takes in one network as input, and for each Node's weight, as well as it's
	 * bias and parameters for any LearningActivationFunctions, it will mutate it by
	 * a given mutation rate and size. Mutation rate is the probability for any
	 * given weight to mutate, and the mutation size is the maximum percent change
	 * of the value of the given parameter(weight, bias, etc.)
	 *
	 * This function allows for more diversity of solutions your network can try, as
	 * it doesn't combine networks, so multiple different solutions can move on to
	 * the next generation. It can also, however result in your network
	 *
	 * FINISH DESCRIPTION
	 */
	public static class Clone extends LearnFunction {
		public static final String learnType = "Clone";
		public static final LearnType type = DefaultLearnFunctions.LearnType.CLONE;

		@Override
		<T extends Network<T>> T networkLearn(@SuppressWarnings("unchecked") T... parents) {
			final Layer[] newLayers = new Layer[parents[0].getNumLayers() + 2];
			final T child = parents[0].child();

			if (parents[0].getActivationFunction() instanceof LearningActivationFunction) {
				child.setActivationFunction(actFunctionLearn(parents[0].getMuteRate(), parents[0].getMuteSize(),
						parents[0].getActivationFunction()));
			}

			if (parents[0].getOutputActivationFunction() instanceof LearningActivationFunction) {
				child.setOutputActivationFunction(actFunctionLearn(parents[0].getMuteRate(), parents[0].getMuteSize(),
						parents[0].getOutputActivationFunction()));
			}

			for (byte layerIndex = 0; layerIndex < newLayers.length; layerIndex++) {
				newLayers[layerIndex] = layerLearn(parents[0].getMuteRate(), parents[0].getMuteSize(),
						parents[0].getLayers()[layerIndex]);
			}

			child.setWeights(newLayers);
			return child;
		}

		@Override
		Layer layerLearn(float muteRate, float muteSize, Layer... parents) {
			final Node[] newLayer = new Node[parents[0].getNumNodes()];

			for (int i = 0; i < newLayer.length; i++) {
				newLayer[i] = nodeLearn(muteRate, muteSize, parents[0].getNodes()[i]);
			}

			return new Layer(newLayer);
		}

		@Override
		Node nodeLearn(float muteRate, float muteSize, Node... parents) {
			final float[] newWeights = new float[parents[0].getNumIns()];
			final double newBias = Node.mutate(parents[0].getBias(), muteRate, muteSize * 0.65f);

			for (int i = 0; i < parents[0].getNumIns(); i++) {
				newWeights[i] = Node.mutate(parents[0].getWeights()[i], muteRate, muteSize);
			}

			return new Node(newWeights, newBias);
		}

		@Override
		LearningActivationFunction actFunctionLearn(float muteRate, float muteSize,
				LearningActivationFunction... parents) {
			final LearningActivationFunction newActFunction = parents[0].newInstance();
			final LearningActivationFunction.LearningVariable[] newLearningVars = new LearningActivationFunction.LearningVariable[parents[0]
					.getLearningVars().length];

			newActFunction.setAllVars(parents[0].getAllVars());

			for (byte i = 0; i < parents[0].getLearningVars().length; i++) {
				newLearningVars[i] = new LearningActivationFunction.LearningVariable(0,
						parents[0].getLearningVars()[i].getMin(), parents[0].getLearningVars()[i].getMax());

				while (!newLearningVars[i].setValue(
						Node.mutate(parents[0].getLearningVars()[i].getValue(), muteRate, muteSize * 0.65f))) {
				}
			}

			newActFunction.setLearningVars(newLearningVars);

			return newActFunction;
		}

		@Override
		public String toString() {
			return type.toString();
		}
	}

	/*
	 * The Parent function is also quite easy. It is essentially the same as the
	 * Clone LearnFunction, except it take two parents, and for each value to
	 * mutate, it will either chose one parent's value or the other and mutate that,
	 * or average the two parent's value and mutate that. For example, Nodes'
	 * weights will be averaged between the two parent's weights and then, if chosen
	 * to mutate, mutated, while biases and parameters for
	 * LearningActivationFunctions will be chosen from either parent 1 or 2 and then
	 * that value will be mutated.
	 *
	 * FINISH DESCRIPTION
	 */
	public static class Parent extends LearnFunction {
		public static final String learnType = "Parent";
		public static final LearnType type = DefaultLearnFunctions.LearnType.PARENT;

		@Override
		<T extends Network<T>> T networkLearn(@SuppressWarnings("unchecked") T... parents) {
			final Layer[] newLayers = new Layer[parents[0].getNumLayers() + 2];
			final T child = parents[1].child();

			if (parents[1].getActivationFunction() instanceof LearningActivationFunction) {
				child.setActivationFunction(actFunctionLearn(parents[0].getMuteRate(), parents[0].getMuteSize(),
						parents[1].getActivationFunction(), parents[1].getActivationFunction()));
			}

			if (parents[1].getOutputActivationFunction() instanceof LearningActivationFunction) {
				child.setOutputActivationFunction(actFunctionLearn(parents[0].getMuteRate(), parents[0].getMuteSize(),
						parents[1].getOutputActivationFunction(), parents[1].getOutputActivationFunction()));
			}

			for (byte i = 0; i < newLayers.length; i++) {
				newLayers[i] = layerLearn(parents[0].getMuteRate(), parents[0].getMuteSize(), parents[0].getLayers()[i],
						parents[1].getLayers()[i]);
			}

			child.setWeights(newLayers);
			return child;
		}

		@Override
		Layer layerLearn(float muteRate, float muteSize, Layer... parents) {
			final Node[] newLayer = new Node[parents[0].getNumNodes()];

			for (int i = 0; i < newLayer.length; i++) {
				newLayer[i] = nodeLearn(muteRate, muteSize, parents[0].getNodes()[i], parents[1].getNodes()[i]);
			}

			return new Layer(newLayer);
		}

		@Override
		Node nodeLearn(float muteRate, float muteSize, Node... parents) {
			final float[] newWeights = new float[parents[0].getNumIns()];
			final float newBias = Node.mutate((parents[0].getBias() + parents[0].getBias()) / 2f, muteRate,
					muteSize * 0.65f);

			for (int i = 0; i < parents[0].getNumIns(); i++) {
				newWeights[i] = Node.mutate(
						Math.random() > 0.5 ? parents[0].getWeights()[i] : parents[1].getWeights()[i], muteRate,
						muteSize);
			}

			return new Node(newWeights, newBias);
		}

		@Override
		LearningActivationFunction actFunctionLearn(float muteRate, float muteSize,
				LearningActivationFunction... parents) {
			if (!parents[0].getClass().equals(parents[1].getClass())) {
				throw new IllegalArgumentException(
						"\nActivation Function Learn Exception | at Learn.actFunctionParentLearn(<Child of LearningActivationFunction>, <Child of LearningActivationFunction>): "
								+ "Parents using LearningActivationFunctions must use the same LearningActivationFunction to create a child");
			}
			final LearningActivationFunction newActFunction = parents[0].newInstance();
			final LearningActivationFunction.LearningVariable[] newLearningVars = new LearningActivationFunction.LearningVariable[parents[0]
					.getLearningVars().length];

			newActFunction.setAllVars(parents[0].getAllVars());

			for (byte i = 0; i < parents[0].getLearningVars().length; i++) {
				newLearningVars[i] = new LearningActivationFunction.LearningVariable(0,
						parents[0].getLearningVars()[i].getMin(), parents[0].getLearningVars()[i].getMax());

				while (!newLearningVars[i]
						.setValue(Node.mutate(Math.random() > 0.5 ? parents[0].getLearningVars()[i].getValue()
								: parents[1].getLearningVars()[i].getValue(), muteRate, muteSize * 0.65f))) {
				}
			}

			newActFunction.setLearningVars(newLearningVars);

			return newActFunction;
		}

		@Override
		public String toString() {
			return type.toString();
		}

	}

	public static class Crossover extends LearnFunction {
		public static final String learnType = "Crossover";
		public static final LearnType type = DefaultLearnFunctions.LearnType.CROSSOVER;

		@Override
		<T extends Network<T>> T networkLearn(@SuppressWarnings("unchecked") T... parents) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Layer layerLearn(float muteRate, float muteSize, Layer... parents) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		Node nodeLearn(float muteRate, float muteSize, Node... parents) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		LearningActivationFunction actFunctionLearn(float muteRate, float muteSize,
				LearningActivationFunction... parents) {
			// TODO Auto-generated method stub
			return null;
		}

		@Override
		public String toString() {
			return type.toString();
		}
	}
}
