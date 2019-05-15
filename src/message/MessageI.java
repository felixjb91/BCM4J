package message;

import java.io.Serializable;

public interface MessageI extends Serializable {
	
	public String getURI()throws Exception;
	public TimeStamp getTimeStamp() throws Exception;
	public Properties getProperties() throws Exception;
	public Serializable getPlayload() throws Exception;

}
