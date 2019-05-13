package publisher.ports;

import broker.interfaces.PublicationI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import message.MessageI;
import publisher.impl.PublisherImpl;


public class PublicationOutbountPort extends AbstractOutboundPort implements PublicationI {
	
	private static final long serialVersionUID = 1L;

	public PublicationOutbountPort(ComponentI owner) throws Exception {
		super(PublicationI.class, owner);
		assert	owner instanceof PublisherImpl ;
	}

	public PublicationOutbountPort(String uri, ComponentI owner) 
		throws Exception {
		super(uri, PublicationI.class, owner);
		assert	owner instanceof PublisherImpl ;
	}

	@Override
	public void publish(MessageI m, String topic) throws Exception {
		((PublicationI) this.connector).publish(m, topic); 

	}

	@Override
	public void publish(MessageI m, String[] topics) throws Exception {
		((PublicationI) this.connector).publish(m, topics);

	}

	@Override
	public void publish(MessageI[] ms, String topic) throws Exception {
		((PublicationI) this.connector).publish(ms, topic);

	}

	@Override
	public void publish(MessageI[] ms, String[] topics) throws Exception {
		((PublicationI) this.connector).publish(ms, topics);

	}

}
