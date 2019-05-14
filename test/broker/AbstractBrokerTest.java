package broker;


import org.junit.Before;
import org.junit.Test;

import broker.impl.BrokerImpl;
import broker.interfaces.ManagementI;
import broker.interfaces.PublicationI;
import message.MessageFilterI;
import message.MessageI;


import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

public abstract class AbstractBrokerTest {
	
	
	protected ManagementI managementProvider;
	protected PublicationI publicationProvider;
	
	@Before
	public abstract void setUp() throws Exception;
	
	@Test
	public void createTopicTest() throws Exception {
		this.managementProvider.createTopic("Hacking");
		String[] topics = {"Hacking"};
		assertArrayEquals(topics, this.managementProvider.getTopics());
	}
	
	@Test 
	public void getTopicsTest() throws Exception {
		this.managementProvider.createTopic("Hacking");
		String[] topics = {"Hacking"};
		assertArrayEquals(topics, this.managementProvider.getTopics());
	}
	
	@Test
	public void destroyTopicTest() throws Exception {
		this.managementProvider.createTopic("Hacking");
		String[] topics = this.managementProvider.getTopics();
		this.managementProvider.destroyTopic("Hacking");
		assertFalse(Arrays.asList(topics).contains("Hacking"));		
	}
	
	@Test
	public void isTopicTest() throws Exception {
		this.managementProvider.createTopic("Hacking");
		assertTrue(this.managementProvider.isTopic("Hacking"));
	}
	
	@Test
	public void subscribeTest() throws Exception {
	
	}
	
	@Test
	public void modifyFilterTest() throws Exception {
	
	}
	
	@Test
	public void unsubscribeTest() throws Exception {
			
	}
	
	@Test
	public void publishTest() throws Exception {
		
	}
		

}
