package publisher.ports;

import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import interfaces.PublicationI;
import message.MessageI;
import publisher.impl.Publisher;


public class PusblisherOutboundPort1 extends AbstractOutboundPort implements PublicationI {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PusblisherOutboundPort1(ComponentI owner) throws Exception {
		super(PublicationI.class, owner);
		assert	owner instanceof Publisher ;
	}

	public PusblisherOutboundPort1(String uri, ComponentI owner) 
		throws Exception {
		super(uri, PublicationI.class, owner);
		assert	owner instanceof Publisher ;
	}

	@Override
	public void publish(MessageI m, String topic) throws Exception {
		((PublicationI)this.connector).publish(m, topic); 

	}

	@Override
	public void publish(MessageI m, String[] topics) throws Exception {
		((PublicationI)this.connector).publish(m, topics);

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
