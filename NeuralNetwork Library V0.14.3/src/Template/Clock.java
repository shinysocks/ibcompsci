package Template;

public class Clock {
	private static double frameStartTime, milisPerFrame;
	private static double[] lastFrames = new double[10];
	private static int counter;

	public static void setFps(double f) {
		// If not called, you can still call the tick() method in the run loop to allow
		// the getFps() method to work without actually delaying frames at all. also
		// using -1 works the same way. Makes it easy to find max runspeed of a program.
		final double fps = f;
		counter = 0;
		milisPerFrame = 1000 / fps;
		frameStartTime = System.currentTimeMillis();
	}

	public static void setClock() {
		lastFrames[counter] = System.currentTimeMillis() - frameStartTime;
		counter = counter == lastFrames.length - 1 ? 0 : counter + 1;
		frameStartTime = System.currentTimeMillis();
	}

	public static void delayFrame() {
		while (System.currentTimeMillis() < frameStartTime + milisPerFrame) {
			// delay between frames if time since last frame less than minimum number of
			// milliseconds per frame
		}
	}

	public static void tick() {
		delayFrame();
		setClock();
	}

	public static double getFps() {
		double sum = 0;
		for (final double frame : lastFrames) {
			sum += frame;
		}
		return 1000.0 / (sum / lastFrames.length);
	}
}