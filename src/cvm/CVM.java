package cvm;

import broker.implementation.Broker;
import connectors.ManagementConnector;
import connectors.PublicationsConnector;
import connectors.ReceptionsConnector;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import publisher.implementation.Publisher;
import subscriber.implementation.Subscriber;

public class CVM extends AbstractCVM {

	protected static final String BROKER_PUB_IN = "BROKER_PUBLICATION_IN_PORT_URI";
	protected static final String BROKER_REC_OUT = "BROKER_RECEPTION_OUT_PORT_URI";
	protected static final String BROKER_MAN_IN = "BROKER_MANAGEMENT_IN_PORT_URI";
	
	protected static final String PUBLISHER_PUB_OUT = "PUBLISHER_PUBLICATION_OUT_PORT_URI";
	protected static final String PUBLISHER_MAN_OUT = "PUBLISHER_MANAGEMENT_OUT_PORT_URI";
	
	protected static final String SUBSCRIBER_REC_IN = "SUBSCRIBER_RECEPTION_IN_PORT_URI";
	protected static final String SUBSCRIBER_MAN_OUT = "SUBSCRIBER_MANAGEMENT_OUT_PORT_URI";

	
	public CVM() throws Exception {
		super();
	}

	@Override
	public void deploy() throws Exception {
		
		Broker b = new Broker(BROKER_PUB_IN, BROKER_MAN_IN);
		Publisher p = new Publisher(PUBLISHER_PUB_OUT ,PUBLISHER_MAN_OUT) ;
		Subscriber s = new Subscriber(SUBSCRIBER_MAN_OUT, SUBSCRIBER_REC_IN);
		
		b.addReceptionOutboundPort(BROKER_REC_OUT, SUBSCRIBER_REC_IN);
		
		p.toggleTracing() ;
		b.toggleTracing() ;
		s.toggleTracing() ;
		
		b.doPortConnection(
			BROKER_REC_OUT, SUBSCRIBER_REC_IN,
			ReceptionsConnector.class.getCanonicalName());
		
		p.doPortConnection(
			PUBLISHER_PUB_OUT, BROKER_PUB_IN,
			PublicationsConnector.class.getCanonicalName());
		
		p.doPortConnection(
			PUBLISHER_MAN_OUT, BROKER_MAN_IN,
			ManagementConnector.class.getCanonicalName());
		
		s.doPortConnection(
			SUBSCRIBER_MAN_OUT, BROKER_MAN_IN,
			ManagementConnector.class.getCanonicalName());
		
		super.deploy();
	}

	public static void main(String[] args) {
		try {
			CVM cvm = new CVM() ;
			cvm.startStandardLifeCycle(20000L) ;
			Thread.sleep(10000L);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
