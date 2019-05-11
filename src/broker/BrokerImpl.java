package broker;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;

@OfferedInterfaces(offered = {ManagementI.class})
public class BrokerImpl extends AbstractComponent {

	public BrokerImpl(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
	}
	
	public void createTopic(String topic) throws Exception {
		
	}
	public void createTopics(String[] topics) throws Exception {
		
	}
	
	public void destroyTopic(String topic) throws Exception {
		
	}
	public boolean isTopic(String topic) throws Exception {
		return false;
	}
	
	public String[] getTopics() throws Exception {
		return null;
	}
	

}
