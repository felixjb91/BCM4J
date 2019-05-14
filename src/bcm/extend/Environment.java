package bcm.extend;

public class Environment {
	
	private static Logger logger;
	
	public Environment(boolean log) {
		logger = new Logger(log);
	}

	public static void logInfo(String message) {
		logger.logInfo(message);
	}
	
	public static void logWarnning(String message) {
		logger.logWarnning(message);
	}
}

