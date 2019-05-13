package broker.ports;


import broker.impl.BrokerImpl;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractOutboundPort;
import message.MessageI;
import subscriber.ReceptionI;


public class ReceptionOutboundPort 
extends AbstractOutboundPort 
implements ReceptionI {

	private static final long serialVersionUID = 1L;

	public ReceptionOutboundPort(ComponentI owner) throws Exception {
		super(ReceptionI.class, owner);
		assert	owner instanceof BrokerImpl ;
	}

	public ReceptionOutboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ReceptionI.class, owner);
		assert	owner instanceof BrokerImpl ;
	}

	@Override
	public void acceptMessage(MessageI m) throws Exception {
		((ReceptionI) this.connector).acceptMessage(m);
	}

	@Override
	public void acceptMessages(MessageI[] m) throws Exception {
		((ReceptionI) this.connector).acceptMessages(m);
	}

}