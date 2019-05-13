package message;

import java.io.Serializable;


public class Message implements MessageI {
	
	private static int i = 0;
	private static final long serialVersionUID = 1L;
	
	private String URI;
	private TimeStamp timeStamp;
	private Properties properties;
	private Serializable content;
	
	
	public Message(Serializable obj) {
		i++;
		this.URI = String.format("Message n°%d", i);
		this.timeStamp = new TimeStamp();
		this.properties = new Properties();
		this.content = obj;
	}
	
	@Override
	public String getURI() {
		return URI;
	}

	@Override
	public TimeStamp getTimeStamp() {
		return timeStamp;
	}

	@Override
	public Properties getProperties() {
		return properties;
	}

	@Override
	public Serializable getPayload() {
		return content;
	}
	
	@Override
	public String toString() {
		return String.format("message(uri:%s, content:%s)",
							  this.URI,
							  this.content.toString());
	}
	
	@Override
	public boolean equals(Object o) {
		
		if(o == null) return false;
		if(!(o instanceof MessageI)) return false;
		MessageI m = (MessageI) o;
		if(m.getURI().equals(this.getURI())) return true;
		
		return false;
	}
	
	@Override
	public int hashCode() {
		return this.getURI().hashCode();
	}

}
