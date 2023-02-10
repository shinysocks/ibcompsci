package NeuralNetwork;

public abstract class LearnFunction {
	public String LearnType;

	abstract <T extends Network<T>> T networkLearn(@SuppressWarnings("unchecked") T... parents);

	abstract Layer layerLearn(float muteRate, float muteSize, Layer... parents);

	abstract Node nodeLearn(float muteRate, float muteSize, Node... parents);

	abstract LearningActivationFunction actFunctionLearn(float muteRate, float muteSize,
			LearningActivationFunction... parents);

	@Override
	public String toString() {
		return LearnType;
	}
}
