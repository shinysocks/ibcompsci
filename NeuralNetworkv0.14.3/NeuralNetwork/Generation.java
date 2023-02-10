package NeuralNetwork;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

/*
 * This is the Generation class, and it's the built-in driver for your network!
 * Of course, like all parts of this library, feel free to build your own, but
 * if you're not up to it, this is a powerful tool for your network.
 * 
 * To create your generation, simply instantiate the class, and give it an array
 * of your agent class with the size you want your generation to be, provide an
 * instance of your agent with all of the Network functions you want, and call
 * the nextGeneration() function and watch your creations learn!
 */
public class Generation<T extends Network<T>> {
	private final short numIns, numOuts, numBestSaved;
	private final LearnFunction learnFunction;
	private T[] networks;
	private int genNum;

	// generate new generation with given object and object array
	// default learnType
	// should be main constructor used for beginners
	public Generation(T[] networks, T obj, int sampleSize) {
		if (sampleSize > networks.length) {
			throw new IllegalArgumentException(
					"\nGeneration Construction Exception | at Generation(<child of Network>[], <child of Network>, byte): "
							+ "Number of best agents saved per Generation must not be larger than Generation size"
							+ "\nSize given: " + networks.length + "\tSample size given: " + sampleSize);
		}
		numBestSaved = (short) sampleSize;
		this.numIns = obj.getNumIns();
		this.numOuts = obj.getNumOuts();
		this.learnFunction = new DefaultLearnFunctions.Clone();
		this.networks = networks.clone();
		this.setGenNum((short) 0);

		for (int i = 0; i < networks.length; i++) {
			getNetworks()[i] = obj.child();
		}
	}

	// generate new generation with given object and object array
	// specified learnType
	// should be main constructor used
	public Generation(T[] networks, T obj, int sampleSize, LearnFunction learnFunction) {
		if (sampleSize > networks.length) {
			throw new IllegalArgumentException(
					"\nGeneration Construction Exception | at Generation(<child of Network>[], <child of Network>, byte, LearnType): "
							+ "Number of best agents saved per Generation must not be larger than Generation size"
							+ "\nSize given: " + networks.length + "\tSample size given: " + sampleSize);
		}
		numBestSaved = (short) sampleSize;
		this.numIns = obj.getNumIns();
		this.numOuts = obj.getNumOuts();
		this.learnFunction = learnFunction;
		this.networks = networks.clone();
		this.setGenNum(0);

		for (short i = 0; i < networks.length; i++) {
			getNetworks()[i] = obj.child();
		}
	}

	// new generation from already-generated Network array
	// default learnType
	public Generation(T[] networks, int sampleSize) {
		if (sampleSize > networks.length) {
			throw new IllegalArgumentException(
					"\nGeneration Construction Exception | at Generation(<child of Network>[], byte): "
							+ "Number of best agents saved per Generation must not be larger than Generation size"
							+ "\nSize given: " + networks.length + "\tSample size given: " + sampleSize);
		}
		if (!Network.networksCompatible(networks)) {
			throw new IllegalArgumentException(
					"\nGeneration Construction Exception | at Generation(<child of Network>[], byte): "
							+ "the number of inputs and outputs of Networks in a Generation's Network array "
							+ "must match number of inputs and outputs of each other Network in array");
		}
		numBestSaved = (short) sampleSize;
		this.numIns = networks[0].getNumIns();
		this.numOuts = networks[0].getNumOuts();
		this.learnFunction = new DefaultLearnFunctions.Clone();
		this.networks = networks;
		this.setGenNum(0);
	}

	// new generation from already-generated Network array
	// specified learnType
	public Generation(T[] networks, int sampleSize, LearnFunction learnFunction) {
		if (sampleSize > networks.length) {
			throw new IllegalArgumentException(
					"\nGeneration Construction Exception | at Generation(<child of Network>[], byte, LearnType): "
							+ "Number of best agents saved per Generation must not be larger than Generation size"
							+ "\nSize given: " + networks.length + "\tSample size given: " + sampleSize);
		}
		if (!Network.networksCompatible(networks)) {
			throw new IllegalArgumentException(
					"\nGeneration Construction Exception | at Generation(<child of Network>[], byte, LearnType): "
							+ "the number of inputs and outputs of Networks in a Generation's Network array "
							+ "must match number of inputs and outputs of each other Network in array");
		}
		numBestSaved = (short) sampleSize;
		this.numIns = networks[0].getNumIns();
		this.numOuts = networks[0].getNumOuts();
		this.learnFunction = learnFunction;
		this.networks = networks;
		this.setGenNum(0);
	}

	// get output array for each agent
	public float[][] getOutput() {
		final float[][] outputs = new float[getSize()][getNumOuts()];

		for (short i = 0; i < getSize(); i++) {
			outputs[i] = getNetworks()[i].getOutput();
		}

		return outputs;
	}

	public void setFitnesses() {
		for (final T network : getNetworks()) {
			network.setFitnessVar();
		}
	}

	// get best agents for learn function
	public T[] getBest() {
		final T[] best = Arrays.copyOfRange(getNetworks(), 0, getNumBestSaved());
		final ArrayList<Short> worst = new ArrayList<Short>();
		// set fitness variable to value given by user-defined fitness function to avoid
		// calling function repeatedly
		setFitnesses();

		for (final T network : getNetworks()) {
			if (!Arrays.asList(best).contains(network)) {
				// find the worst performers in best array
				worst.clear();
				worst.add((short) 0);
				for (short replacementIndex = 0; replacementIndex < getNumBestSaved(); replacementIndex++) {
					if (best[replacementIndex].getFitness() < best[worst.get(0)].getFitness()) {
						worst.clear();
						worst.add(replacementIndex);
					} else if (best[replacementIndex].getFitness() == best[worst.get(0)].getFitness()
							&& !worst.contains(replacementIndex)) {
						worst.add(replacementIndex);
					}
				}

				// if current network is better than worst in best array, replace worst
				if (best[worst.get(0)].getFitness() < network.getFitness()) {
					for (final Short i : worst) {
						best[i] = network;
					}
				}
			} // end if not already in array
		} // end for loop
		return best;
	}

	// get best n agents
	public T[] getBest(int numBest) {
		final T[] best = Arrays.copyOfRange(getNetworks(), 0, numBest);
		final ArrayList<Short> worst = new ArrayList<Short>();
		// set fitness variable to value given by user-defined fitness function to avoid
		// calling function repeatedly
		setFitnesses();

		for (final T network : getNetworks()) {
			if (!Arrays.asList(best).contains(network)) {
				// find the worst performers in best array
				worst.clear();
				worst.add((short) 0);
				for (short replacementIndex = 0; replacementIndex < numBest; replacementIndex++) {
					if (best[replacementIndex].getFitness() < best[worst.get(0)].getFitness()) {
						worst.clear();
						worst.add(replacementIndex);
					} else if (best[replacementIndex].getFitness() == best[worst.get(0)].getFitness()
							&& !worst.contains(replacementIndex)) {
						worst.add(replacementIndex);
					}
				}

				// if current network is better than worst in best array, replace worst
				if (best[worst.get(0)].getFitness() < network.getFitness()) {
					for (final Short i : worst) {
						best[i] = network;
					}
				}
			} // end if not already in array
		} // end for loop
		return best;
	}

	public void setNetworks(T[] networks) {
		if (!Network.networksCompatible(networks)) {
			throw new IllegalArgumentException(
					"\nNetwork Set Exception | at Generation.setNetworks(<child of Network>[]): "
							+ "the number of inputs and outputs of Networks in a Generation's Network array "
							+ "must match number of inputs and outputs of each other Network in array");
		}
		if (networks[0].getNumIns() != numIns) {
			throw new IllegalArgumentException(
					"\\nNetwork Set Exception | at Generation.setNetworks(<child of Network>[]): "
							+ "Generations that take " + numIns + " inputs cannot accept a new network that takes "
							+ networks[0].getNumIns() + " inputs");
		}
		if (networks[0].getNumIns() != numIns) {
			throw new IllegalArgumentException(
					"\\nNetwork Set Exception | at Generation.setNetworks(<child of Network>[]): "
							+ "Generation that gives" + numOuts + " outputs cannot accept new networks that give "
							+ networks[0].getNumOuts() + " outputs");
		}

		this.networks = networks;
	}

	public void nextGeneration() {
		setNetworks(getNextGenerationArray());
		incrementGenNum();
	}

	public void nextGeneration(LearnFunction learnFunction) {
		setNetworks(getNextGenerationArray(learnFunction));
		incrementGenNum();
	}

	@SuppressWarnings("unchecked")
	public T[] getNextGenerationArray() {
		final T[] nextGen = networks.clone(), best = getBest();
		final Random rand = new Random();
		T network2 = null;
		short i = 0;

		// set first few agents of next generation with best from previous
		for (final T network : best) {
			if (network != null) {
				nextGen[i] = network.copy();
				i++;
			}
		}

		// fill rest of new generation
		while (i < getSize()) {
			for (final T network : best) {
				if (i == getSize()) {
					break;
				}
				if (network != null) {
					while (network2 == null) {
						network2 = best[rand.nextInt(getNumBestSaved())];
					}
					nextGen[i] = getLearnFunction().networkLearn(network, network2);
					network2 = null;
					i++;
				}
			}
		}

		return nextGen;
	}

	@SuppressWarnings("unchecked")
	public T[] getNextGenerationArray(LearnFunction learnFunction) {
		final T[] nextGen = networks.clone(), best = getBest();
		final Random rand = new Random();
		T network2 = null;
		short i = 0;

		// set first few agents of final next generation with final best from previous
		for (final T network : best) {
			if (network != null) {
				nextGen[i] = network.copy();
				i++;
			}
		}

		// fill rest of new generation
		while (i < getSize()) {
			for (final T network : best) {
				if (i == getSize()) {
					break;
				}
				if (network != null) {
					while (network2 != null && network2 != network) {
						network2 = best[rand.nextInt(getNumBestSaved())];
					}
					nextGen[i] = learnFunction.networkLearn(network, network2); // MAKE THIS STATIC AGAIN?? IS
					// FASTER??
					network2 = null;
					i++;
				}
			}
		}

		return nextGen;
	}

	public Generation<T> getNextGeneration() {
		return new Generation<T>(getNextGenerationArray(learnFunction), getNumBestSaved());
	}

	public Generation<T> getNextGeneration(LearnFunction learnFunction) {
		return new Generation<T>(getNextGenerationArray(learnFunction), getNumBestSaved());
	}

	public void fill(T obj) {
		Arrays.fill(getNetworks(), obj);
	}

	public final float avScore() {
		float sum = 0;

		for (final T network : getNetworks()) {
			sum += network.fitness();
		}

		return sum / getNetworks().length;
	}

	public T bestOfGen() {
		return getBest(1)[0];
	}

	@Override
	public String toString() {
		final StringBuilder str = new StringBuilder("Generation:  Size: ").append(getSize())
				.append(", defaut sample size for next gen: ").append(getNumBestSaved()).append(", LearnType: ")
				.append(getLearnFunction()).append(", current generation: ").append(getGenNum()).append(", \t{\n");

		for (byte i = 0; i < getSize(); i++) {
			str.append("\tNetwork ").append(i).append(": ").append(getNetworks()[i]).append(",\n");
		}

		return str.append("}").toString();
	}

	public final int getSize() {
		return getNetworks().length;
	}

	public final short getNumIns() {
		return numIns;
	}

	public final short getNumOuts() {
		return numOuts;
	}

	public final short getNumBestSaved() {
		return numBestSaved;
	}

	public final LearnFunction getLearnFunction() {
		return learnFunction;
	}

	public final T[] getNetworks() {
		return networks;
	}

	public final int getGenNum() {
		return genNum;
	}

	public void setGenNum(int genNum) {
		this.genNum = (short) genNum;
	}

	public final void incrementGenNum() {
		setGenNum(getGenNum() + 1);
	}
}