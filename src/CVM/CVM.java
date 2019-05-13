package CVM;

import broker.impl.BrokerImpl;
import connectors.ManagementConnector;
import connectors.PublicationsConnector;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import publisher.impl.Publisher;

public class CVM extends AbstractCVM {

	protected static final String URI1 = "iportP" ; // Broker's offered port for PublicationI
	protected static final String URI2 = "oportR" ; // Broker's required port for ReceptionI
	protected static final String URI3 = "iportM" ; // Broker's offered port for ManagementI
	
	protected static final String URI4 = "oportP" ; // Publisher's required port for PublicationI
	protected static final String URI5 = "oportM" ; // Publisher's required port for ManagementI
	
	protected static final String URI6 = "iportR" ; // Subscriber's offered port for ReceptionI
	protected static final String URI7 = "oportM" ; // Subscriber's required port for ManagementI

	
	public CVM() throws Exception {
		super();
	}

	@Override
	public void deploy() throws Exception {
		
		BrokerImpl b = new BrokerImpl(URI1,URI2,URI3) ;
		Publisher p = new Publisher(URI4,URI5) ;
		//Subscriber s = new Subscriber(URI6,URI7) ;
		
		p.toggleTracing() ;
		b.toggleTracing() ;
		//s.toggleTracing() ;
		
		/*b.doPortConnection(
			URI2, URI6,
			ReceptionsConnector.class.getCanonicalName()); */
		p.doPortConnection(
			URI4, URI1,
			PublicationsConnector.class.getCanonicalName());
		p.doPortConnection(
			URI5, URI3,
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
