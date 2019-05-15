package message;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import com.sun.xml.internal.ws.api.message.Message;

public abstract class AbstractMessageTest {

	protected MessageI messageProvider;
	protected MessageFilterI messageFilterProvider;
	
	@Before
	public abstract void setUp()throws Exception;
	
	@Test
	public void getUriTest() {
		String uri = "test";
		this.messageProvider = new Message(uri);
		assertEquals(uri,this.messageProvider.getURI());
	}
	
	@Test
	public void getTimeStampTest() {
		TimeStamp timestamp = new TimeStamp(10,"10janvier2018");
		this.messageProvider = new Message(timestamp);
		assertEquals(timestamp,this.messageProvider.getTimeStamp());
	}
	
	@Test
	public void getPropertiesTest() {
		Properties properties = new Properties();
		properties.putProp("nombre", 10);
		this.messageProvider = new Message(properties);
		assertEquals(properties.getIntProp("nombre"),this.messageProvider.getProperties().getIntProp("nombre"));
	}
}
