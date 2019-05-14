package broker;


import org.junit.Before;
import org.junit.Test;

import broker.implementation.Broker;
import broker.interfaces.ManagementI;
import broker.interfaces.PublicationI;
import subscriber.implementation.Subscriber;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Map;
import java.util.Set;


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
		Broker broker = new Broker(1, 0, null, null, null);
		String topic = "Hello";
		String inboundPortUri = "inboundPort";
		broker.subscribe(topic, inboundPortUri);
		/*Subscriber subscriber = new Subscriber(inboundPortUri,null);
		Map<String,Set<Subscriber>> subscribers = {topic, subscriber};
		assertArrayEquals(subscribers, broker.getSubscriber());*/
		
	}
	
	@Test
	public void modifyFilterTest() throws Exception {
	
	}
	
	@Test
	public void unsubscribeTest() throws Exception {
			
	}
	
	@Test
	public void publish() throws Exception {
		
	}

}
