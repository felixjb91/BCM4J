package connectors;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import message.MessageI;
import subscriber.interfaces.ReceptionI;

public class ReceptionsConnector 
	   extends AbstractConnector 
	   implements ReceptionI 
{

	@Override
	public void acceptMessage(MessageI m) throws Exception {
		((ReceptionI) this.offering).acceptMessage(m);
	}

	@Override
	public void acceptMessages(MessageI[] m) throws Exception {
		((ReceptionI) this.offering).acceptMessages(m);
	}

}
