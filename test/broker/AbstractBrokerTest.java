package broker;


import org.junit.Before;
import org.junit.Test;

import broker.interfaces.ManagementI;
import broker.interfaces.PublicationI;

import static org.junit.Assert.assertArrayEquals;


public abstract class AbstractBrokerTest {
	
	
	protected ManagementI managementProvider;
	protected PublicationI publicationProvider;
	
	@Before
	public abstract void setUp() throws Exception;
	
	@Test
	public void createTopicTest() {
		
	}
	
	@Test 
	public void getTopicsTest() throws Exception {
		this.managementProvider.createTopic("Hacking");
		String[] topics = {"Hacking"};
		assertArrayEquals(topics, this.managementProvider.getTopics());
	}

}
