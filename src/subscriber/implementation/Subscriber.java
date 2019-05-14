package subscriber.implementation;

import bcm.extend.AbstractComponent;
import bcm.extend.Environement;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import message.MessageI;
import publisher.ports.ManagementOutboundPort;
import subscriber.interfaces.ReceptionI;
import subscriber.ports.ReceptionInboundPort;
import broker.interfaces.ManagementI;

@RequiredInterfaces(required = {ManagementI.class})
@OfferedInterfaces(offered = {ReceptionI.class})
public class Subscriber 
	   extends AbstractComponent
{ 

	private static int i  = 0;
	private String componenetName;
	
	private ManagementOutboundPort managementOutboundPort;
	private ReceptionInboundPort recepetionInboundPort;
	
	public Subscriber(String managementOutboundPortUri, String recepetionInboundPortUri) throws Exception {
		this(1, 0, managementOutboundPortUri, recepetionInboundPortUri);
	}
					
	public Subscriber(int nbThreads, int nbSchedulableThreads, String managementOutboundPortUri,
							String recepetionInboundPortUri) throws Exception {
		
		super(nbThreads, nbSchedulableThreads);
		assert managementOutboundPortUri != null;
		assert recepetionInboundPortUri != null;
		
		this.componenetName = String.format("subscriber %d", ++i);
		
		this.managementOutboundPort = new ManagementOutboundPort(managementOutboundPortUri, this);
		this.recepetionInboundPort = new ReceptionInboundPort(recepetionInboundPortUri, this);
		
		this.addPort(managementOutboundPort);
		this.addPort(recepetionInboundPort);
		
		this.managementOutboundPort.publishPort();
		this.recepetionInboundPort.publishPort();
		
		this.tracer.setTitle("subscriber component");
	}
	
	public void acceptMessage(MessageI m) throws Exception {
		Environement.logInfo(
					String.format("%s received %s", this.componenetName, m.toString())
				);
	}

	public void acceptMessages(MessageI[] ms) throws Exception {
		for(MessageI m : ms) {
			this.acceptMessage(m);
		}
	}
	
	@Override
	public void shutdown() throws ComponentShutdownException {
		
		try {
			this.managementOutboundPort.unpublishPort();
			this.recepetionInboundPort.unpublishPort();
		} 
		catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		
		super.shutdown();
	}
	
	@Override
	public void shutdownNow() throws ComponentShutdownException {
		
		try {
			this.managementOutboundPort.unpublishPort();
			this.recepetionInboundPort.unpublishPort();
		}
		catch(Exception e) {
			throw new ComponentShutdownException(e);
		}
		
		super.shutdownNow();
	}

}