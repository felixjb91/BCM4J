package publisher.ports;

import broker.interfaces.ManagementI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import message.MessageFilterI;

public class ManagementOutboundPort extends AbstractOutboundPort implements ManagementI {

	private static final long serialVersionUID = 1L;

	public ManagementOutboundPort(ComponentI owner) throws Exception {
		super(ManagementI.class, owner);
		assert	owner instanceof ManagementI;
	}

	public ManagementOutboundPort(String uri, ComponentI owner) 
		throws Exception {
		super(uri, ManagementI.class, owner);
		assert	owner instanceof ManagementI;
	}
	
	@Override
	public void createTopic(String topic) throws Exception {
		((ManagementI) this.connector).createTopic(topic);
		
	}

	@Override
	public void createTopics(String[] topics) throws Exception {
		((ManagementI) this.connector).createTopics(topics);
		
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		((ManagementI) this.connector).destroyTopic(topic);
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		return ((ManagementI) this.connector).isTopic(topic);
	}

	@Override
	public String[] getTopics() throws Exception {
		return ((ManagementI) this.connector).getTopics();
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) throws Exception {
		((ManagementI) this.connector).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String[] topic, String inboundPortUri) throws Exception {
		((ManagementI) this.connector).subscribe(topic, inboundPortUri);
		
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		((ManagementI) this.connector).subscribe(topic, filter, inboundPortUri);
		
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception {
		((ManagementI) this.connector).modifyFilter(topic, newFilter, inboundPortUri);
		
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) throws Exception {
		((ManagementI) this.connector).unsubscribe(topic, inboundPortUri);
		
	}

}