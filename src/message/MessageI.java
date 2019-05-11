package message;

import java.io.Serializable;

public interface MessageI extends Serializable {
	
	public String getURI();
	public TimeStamp getTimeStamp();
	public Properties getProperties();
	public Serializable getPayload();

}
