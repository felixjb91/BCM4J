package publisher;

import broker.ManagementI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import message.MessageFilterI;

public class PusblisherOutboundPort2 extends AbstractOutboundPort implements ManagementI {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PusblisherOutboundPort2(ComponentI owner) throws Exception {
		super(ManagementI.class, owner);
		assert	owner instanceof Pusblisher ;
	}

	public PusblisherOutboundPort2(String uri, ComponentI owner) 
		throws Exception {
		super(uri, ManagementI.class, owner);
		assert	owner instanceof Pusblisher ;
	}
	
	@Override
	public void createTopic(String topic) {
		((ManagementI)this.connector).createTopic(topic);
		
	}

	@Override
	public void createTopics(String[] topics) {
		((ManagementI)this.connector).createTopics(topics);
		
	}

	@Override
	public void destroyTopic(String topic) {
		((ManagementI)this.connector).destroyTopic(topic);
	}

	@Override
	public boolean isTopic(String topic) {
		return ((ManagementI)this.connector).isTopic(topic);
	}

	@Override
	public String[] getTopics() {
		return ((ManagementI)this.connector).getTopics();
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) {
		((ManagementI)this.connector).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String[] topic, String inboundPortUri) {
		((ManagementI)this.connector).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) {
		((ManagementI)this.connector).subscribe(topic, filter, inboundPortUri);
		
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) {
		((ManagementI)this.connector).modifyFilter(topic, newFilter, inboundPortUri);
		
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) {
		((ManagementI)this.connector).unsubscribe(topic, inboundPortUri);
		
	}

}
