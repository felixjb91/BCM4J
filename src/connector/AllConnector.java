package connector;

import broker.ManagementI;
import fr.sorbonne_u.components.connectors.AbstractConnector;
import message.MessageFilterI;

public class AllConnector extends AbstractConnector implements ManagementI {

	@Override
	public void createTopic(String topic) {
		((ManagementI)this.offering).createTopic(topic);
		
	}

	@Override
	public void createTopics(String[] topics) {
		((ManagementI)this.offering).createTopics(topics);		
	}

	@Override
	public void destroyTopic(String topic) {
		((ManagementI)this.offering).destroyTopic(topic);
		
	}

	@Override
	public boolean isTopic(String topic) {
		return ((ManagementI)this.offering).isTopic(topic);
	}

	@Override
	public String[] getTopics() {
		return ((ManagementI)this.offering).getTopics();
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) {
		((ManagementI)this.offering).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String[] topic, String inboundPortUri) {
		((ManagementI)this.offering).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) {
		((ManagementI)this.offering).subscribe(topic, filter, inboundPortUri);
		
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) {
		((ManagementI)this.offering).modifyFilter(topic, newFilter, inboundPortUri);
		
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) {
		((ManagementI)this.offering).unsubscribe(topic, inboundPortUri);
		
	}

}
