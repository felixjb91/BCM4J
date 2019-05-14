package bcm.extend;

public class Environement {
	
	private static Logger logger;
	
	public Environement(boolean log) {
		logger = new Logger(log);
	}

	public static void logInfo(String message) {
		logger.logInfo(message);
	}
	
	public static void logWarnning(String message) {
		logger.logWarnning(message);
	}
}

