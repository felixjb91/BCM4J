package connectors;


import fr.sorbonne_u.components.connectors.AbstractConnector;
import interfaces.ManagementI;
import message.MessageFilterI;

public class ManagementConnector 
	   extends AbstractConnector
	   implements ManagementI
{

	@Override
	public void createTopic(String topic) throws Exception {
		((ManagementI) this.offering).createTopic(topic) ;
	}

	@Override
	public void createTopics(String[] topics) throws Exception {
		((ManagementI) this.offering).createTopics(topics) ;		
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		((ManagementI) this.offering).destroyTopic(topic);
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		return ((ManagementI) this.offering).isTopic(topic);
	}

	@Override
	public String[] getTopics() throws Exception {
		return ((ManagementI) this.offering).getTopics();
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) throws Exception {
		((ManagementI) this.offering).subscribe(topic, inboundPortUri) ;
	}

	@Override
	public void subscribe(String[] topic, String inboundPortUri) throws Exception {
		((ManagementI) this.offering).subscribe(topic,inboundPortUri) ;
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		((ManagementI) this.offering).subscribe(topic,filter,inboundPortUri) ;
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception {
		((ManagementI) this.offering).modifyFilter(topic,newFilter,inboundPortUri) ;
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) throws Exception {
		((ManagementI) this.offering).unsubscribe(topic,inboundPortUri) ;
	}

}
