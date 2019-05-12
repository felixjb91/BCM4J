package connector;

import fr.sorbonne_u.components.connectors.AbstractConnector;
import message.MessageI;
import publisher.PublicationI;

public class PusblisherConnector extends AbstractConnector implements PublicationI {

	@Override
	public void publish(MessageI m, String topic) throws Exception {
		((PublicationI)this.requiring).publish(m, topic);
		
	}

	@Override
	public void publish(MessageI m, String[] topics) throws Exception {
		((PublicationI)this.requiring).publish(m, topics);
		
	}

	@Override
	public void publish(MessageI[] ms, String topic) throws Exception {
		((PublicationI)this.requiring).publish(ms, topic);
		
	}

	@Override
	public void publish(MessageI[] ms, String[] topics) throws Exception {
		((PublicationI)this.requiring).publish(ms, topics);
		
	}

}
