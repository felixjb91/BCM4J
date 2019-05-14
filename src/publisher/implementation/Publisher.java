package publisher.implementation;

import broker.interfaces.ManagementI;
import broker.interfaces.ManagementImplementationI;
import broker.interfaces.PublicationI;
import broker.interfaces.PublicationsImplementationI;
import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.examples.chm.connectors.MapReadingConnector;
import fr.sorbonne_u.components.examples.chm.connectors.MapWritingConnector;
import fr.sorbonne_u.components.examples.chm.interfaces.MapReading;
import fr.sorbonne_u.components.examples.chm.interfaces.MapWriting;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import fr.sorbonne_u.components.reflection.connectors.ReflectionConnector;
import fr.sorbonne_u.components.reflection.ports.ReflectionOutboundPort;
import message.Message;
import message.MessageI;
import publisher.ports.PublicationOutbountPort;
import publisher.ports.ManagementOutboundPort;


public class Publisher 
extends AbstractComponent 
implements PublicationsImplementationI, ManagementImplementationI {
	
	protected PublicationOutbountPort publicationOutboundPort;
	protected ManagementOutboundPort managementOutboundPort;
	protected final String	chmReflectionIBPUri ;
	
	public Publisher(String chmReflectionIBPUri) throws Exception {
		
		super(1, 5);
		
		assert	chmReflectionIBPUri != null ;
		this.chmReflectionIBPUri = chmReflectionIBPUri ;
		
		// create the port that exposes the required interface
		this.publicationOutboundPort = new PublicationOutbountPort(publicationOutboundPortUri, this);
		this.managementOutboundPort = new ManagementOutboundPort(managementOutboundPortUri, this);
		// add the port to the set of ports of the component
		this.addPort(this.publicationOutboundPort);
		this.addPort(this.managementOutboundPort);

		this.publicationOutboundPort.publishPort();
		this.managementOutboundPort.publishPort();

		if (AbstractCVM.isDistributed) {
			this.executionLog.setDirectory(System.getProperty("user.dir"));
		} else {
			this.executionLog.setDirectory(System.getProperty("user.home"));
		}
		this.tracer.setTitle("Pusblisher");
		this.tracer.setRelativePosition(1, 1);
	}

	@Override
	public void publish(MessageI m, String topic) throws Exception {
		this.publicationOutboundPort.publish(m,topic);
	}

	@Override
	public void publish(MessageI m, String[] topics) throws Exception {
		this.publicationOutboundPort.publish(m,topics);

	}

	@Override
	public void publish(MessageI[] m, String topic) throws Exception {
		this.publicationOutboundPort.publish(m,topic);

	}

	@Override
	public void publish(MessageI[] m, String[] topics) throws Exception {
		this.publicationOutboundPort.publish(m,topics);
	}

	@Override
	public void createTopic(String topic) throws Exception {
		this.managementOutboundPort.createTopic(topic);	
	}

	@Override
	public void createTopics(String[] topics) throws Exception {
		this.managementOutboundPort.createTopics(topics);
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		this.managementOutboundPort.destroyTopic(topic);
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		return this.managementOutboundPort.isTopic(topic);
	}

	@Override
	public String[] getTopics() throws Exception {
		return this.managementOutboundPort.getTopics();
	}

	
	@Override
	public void execute() throws Exception {
				
		super.execute() ;

		ReflectionOutboundPort rop = new ReflectionOutboundPort(this) ;
		this.addPort(rop) ;
		rop.publishPort() ;

		this.doPortConnection(rop.getPortURI(),
							  chmReflectionIBPUri,
							  ReflectionConnector.class.getCanonicalName());
		String[] publiIBPURI =
				rop.findInboundPortURIsFromInterface(PublicationI.class) ;
		assert	publiIBPURI != null && publiIBPURI.length == 1 ;
		this.doPortConnection(
				this.publicationOutboundPort.getPortURI(),
				publiIBPURI[0],
				MapReadingConnector.class.getCanonicalName()) ;

		String[] manageIBPURI =
				rop.findInboundPortURIsFromInterface(ManagementI.class) ;
		assert	manageIBPURI != null && manageIBPURI.length == 1 ;
		this.doPortConnection(
				this.managementOutboundPort.getPortURI(),
				manageIBPURI[0],
				MapWritingConnector.class.getCanonicalName()) ;

		this.doPortDisconnection(rop.getPortURI()) ;
		rop.unpublishPort() ;
		rop.destroyPort() ;

		String[] lesTopics = {"tpoic1", "tpoic2", "tpoic3"};
		String[] lesTopics2 = {"tpoic3", "tpoic4"};
		createTopics(lesTopics);
		createTopics(lesTopics2);
		Thread.sleep(1000L);
		publish(new Message("hello World"), lesTopics);
		//destroyTopic("topic1");
		//destroyTopic("topic4");
	}
	
	
	@Override
	public void shutdown() throws ComponentShutdownException {
		try {
			this.publicationOutboundPort.unpublishPort();
			this.managementOutboundPort.unpublishPort();
		} catch (Exception e) {
			throw new ComponentShutdownException(e) ;
		}
		super.shutdown();
	}
	
	
}
