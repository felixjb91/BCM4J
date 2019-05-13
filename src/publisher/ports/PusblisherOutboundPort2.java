package publisher.ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.ManagementI;
import message.MessageFilterI;
import publisher.impl.Publisher;

public class PusblisherOutboundPort2 extends AbstractOutboundPort implements ManagementI {

	private static final long serialVersionUID = 1L;

	public PusblisherOutboundPort2(ComponentI owner) throws Exception {
		super(ManagementI.class, owner);
		assert	owner instanceof Publisher ;
	}

	public PusblisherOutboundPort2(String uri, ComponentI owner) 
		throws Exception {
		super(uri, ManagementI.class, owner);
		assert	owner instanceof Publisher ;
	}
	
	@Override
	public void createTopic(String topic) throws Exception {
		((ManagementI)this.connector).createTopic(topic);
		
	}

	@Override
	public void createTopics(String[] topics) throws Exception {
		((ManagementI)this.connector).createTopics(topics);
		
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		((ManagementI)this.connector).destroyTopic(topic);
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		return ((ManagementI)this.connector).isTopic(topic);
	}

	@Override
	public String[] getTopics() throws Exception {
		return ((ManagementI)this.connector).getTopics();
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) throws Exception {
		((ManagementI)this.connector).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String[] topic, String inboundPortUri) throws Exception {
		((ManagementI)this.connector).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		((ManagementI)this.connector).subscribe(topic, filter, inboundPortUri);
		
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception {
		((ManagementI)this.connector).modifyFilter(topic, newFilter, inboundPortUri);
		
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) throws Exception {
		((ManagementI)this.connector).unsubscribe(topic, inboundPortUri);
		
	}

}
