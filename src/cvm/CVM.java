package cvm;

import broker.implementation.Broker;
import connectors.ManagementConnector;
import connectors.PublicationsConnector;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import publisher.implementation.Publisher;

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
		
		Broker b = new Broker(BROKER_MAN_IN, BROKER_REC_OUT, BROKER_PUB_IN);
		Publisher p = new Publisher(PUBLISHER_PUB_OUT ,PUBLISHER_MAN_OUT) ;
		//Subscriber s = new Subscriber(URI6,URI7) ;
		
		p.toggleTracing() ;
		b.toggleTracing() ;
		//s.toggleTracing() ;
		
		/*b.doPortConnection(
			URI2, URI6,
			ReceptionsConnector.class.getCanonicalName()); */
		p.doPortConnection(
			PUBLISHER_PUB_OUT, BROKER_PUB_IN,
			PublicationsConnector.class.getCanonicalName());
		p.doPortConnection(
			PUBLISHER_MAN_OUT, BROKER_MAN_IN,
			ManagementConnector.class.getCanonicalName());
		/*s.doPortConnection(
			URI7, URI3,
			ManagementConnector.class.getCanonicalName());
		 */
		super.deploy();
	}

	public static void main(String[] args) {
		try {
			CVM cvm = new CVM() ;
			cvm.startStandardLifeCycle(2000L) ;
			Thread.sleep(10000L);
			System.exit(0);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
