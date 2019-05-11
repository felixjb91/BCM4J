package publisher;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.annotations.RequiredInterfaces;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.exceptions.ComponentStartException;
import fr.sorbonne_u.components.ports.PortI;


@RequiredInterfaces(required = {PublicationI.class})
public class Pusblisher extends AbstractComponent {
	
	protected PusblisherOutboundPort1 uriGetterPort1 ;
	protected PusblisherOutboundPort2 uriGetterPort2 ;
	protected int counter1 ;
	protected int counter2 ;

	public Pusblisher(String message, String outboundPortURI1, String outboundPortURI2) throws Exception {
		super(message, 1, 0);
		// create the port that exposes the required interface
		this.uriGetterPort1 = new PusblisherOutboundPort1(outboundPortURI1, this);
		this.uriGetterPort2 = new PusblisherOutboundPort2(outboundPortURI2, this);
		// add the port to the set of ports of the component
		this.addPort(this.uriGetterPort1);
		this.addPort(this.uriGetterPort2);
		// publish the port (an outbound port is always local)
		this.uriGetterPort1.localPublishPort();
		this.uriGetterPort2.localPublishPort();
		this.counter1 = 0 ;
		this.counter2 = 0 ;

		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir"));
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home"));
		}
		this.tracer.setTitle("Pusblisher");
		this.tracer.setRelativePosition(1, 1);
	}
	
	@Override
	public void	start() throws ComponentStartException
	{
		this.logMessage("starting provider component.") ;
		super.start();
	}
	
	@Override
	public void	finalise() throws Exception
	{
		this.logMessage("stopping pusblish component.") ;
		// This is the place where to clean up resources, such as
		// disconnecting and unpublishing ports that will be destroyed
		// when shutting down.
		// In static architectures like in this example, ports can also
		// be disconnected by the finalise method of the component
		// virtual machine.
		this.uriGetterPort1.unpublishPort() ;
		this.doPortDisconnection(this.uriGetterPort1.getPortURI());

		// This called at the end to make the component internal
		// state move to the finalised state.
		super.finalise();
	}
	
	@Override
	public void	shutdownNow() throws ComponentShutdownException
	{
		try {
			PortI[] p = this.findPortsFromInterface(PublicationI.class);
			p[0].unpublishPort() ;
		} catch (Exception e) {
			throw new ComponentShutdownException(e);
		}
		super.shutdownNow();
	}
	
	
}
