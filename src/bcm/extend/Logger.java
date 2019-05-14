package bcm.extend;

public class Logger {
	
	private boolean log;
	
	public Logger(boolean log) {
		this.log = log;
	}
	
	public void logInfo(String message) {
		if(log) {
			System.out.println("info : " + message);
		}
		
	}
	
	public void logWarnning(String message) {
		if(log) {
			System.out.println("warnning : " + message);
		}
	}
}