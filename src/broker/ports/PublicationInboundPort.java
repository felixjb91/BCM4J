package broker.ports;

import broker.implementation.Broker;
import broker.interfaces.PublicationI;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import message.MessageI;


public class PublicationInboundPort 
	   extends AbstractInboundPort 
	   implements PublicationI 
{

	private static final long serialVersionUID = 1L;

	public PublicationInboundPort(ComponentI owner) throws Exception {
		super(PublicationI.class, owner);
		assert owner instanceof Broker ;
	}

	public PublicationInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, PublicationI.class, owner);
		assert owner instanceof Broker;
	}
	
	private Broker owner() {
		return (Broker) owner;
	}

	@Override
	public void publish(MessageI m, String topic) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().publish(m, topic)
			);
	}

	@Override
	public void publish(MessageI m, String[] topics) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().publish(m, topics)
			);
	}

	@Override
	public void publish(MessageI[] ms, String topic) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().publish(ms, topic)
			);
	}

	@Override
	public void publish(MessageI[] ms, String[] topics) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().publish(ms, topics)
			);
	}

}
