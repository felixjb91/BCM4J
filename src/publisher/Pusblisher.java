package publisher;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;

@RequiredInterfaces(required = {PublicationI.class})
public class Pusblisher extends AbstractComponent {
	
	protected PusblisherOutboundPort1 uriGetterPort1 ;
	protected PusblisherOutboundPort2 uriGetterPort2 ;

	public Pusblisher(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
		// TODO Auto-generated constructor stub
	}

}
