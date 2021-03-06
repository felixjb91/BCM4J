package message;

import java.io.Serializable;
import java.sql.Timestamp;

public class TimeStamp implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private long time;
	private String timestamper;
	
	public TimeStamp(long time, String timestamper) {
		this.time = time;
		this.timestamper = timestamper;
	}
	
	public TimeStamp() {
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		this.time = timestamp.getTime();
		this.timestamper = timestamp.toString();
	}
	
	public boolean isInitialised() {
		return (this.time != 0)  && (this.timestamper != null);
	}
	
	public long getTime() {
		return time;
	}
	
	public void setTime(long time) {
		this.time = time;
	}
	
	public String getTimestamper() {
		return timestamper;
	}
	
	public void setTimestamper(String timestamper) {
		this.timestamper = timestamper;
	}

}
