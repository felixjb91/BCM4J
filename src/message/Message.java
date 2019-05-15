package message;

import java.io.Serializable;

public class Message implements MessageI,Serializable  {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	String uri;
	TimeStamp timestamp;
	Properties properties;
	
	public Message(String uri, TimeStamp timestamp, Properties properties) {
		this.uri = uri;
		this.timestamp = timestamp;
		this.properties = properties;
	}
	
	
	@Override
	public String getURI() throws Exception {
		return uri;
	}

	@Override
	public TimeStamp getTimeStamp() throws Exception {
		return timestamp;
	}

	@Override
	public Properties getProperties() throws Exception {
		return properties;
	}

	@Override
	public Serializable getPlayload() throws Exception {
		return null;
	}
	
	
}
