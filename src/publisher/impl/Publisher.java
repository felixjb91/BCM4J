package publisher.impl;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.cvm.AbstractCVM;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import interfaces.ManagementImplementationI;
import interfaces.PublicationsImplementationI;
import message.Message;
import message.MessageI;
import publisher.ports.PusblisherOutboundPort1;
import publisher.ports.PusblisherOutboundPort2;


public class Publisher 
extends AbstractComponent 
implements PublicationsImplementationI, ManagementImplementationI {
	
	protected PusblisherOutboundPort1 uriGetterPort1 ;
	protected PusblisherOutboundPort2 uriGetterPort2 ;

	public Publisher(String outboundPortURI1, String outboundPortURI2) throws Exception {
		super(1, 0);
		// create the port that exposes the required interface
		this.uriGetterPort1 = new PusblisherOutboundPort1(outboundPortURI1, this);
		this.uriGetterPort2 = new PusblisherOutboundPort2(outboundPortURI2, this);
		// add the port to the set of ports of the component
		this.addPort(this.uriGetterPort1);
		this.addPort(this.uriGetterPort2);

		this.uriGetterPort1.publishPort();
		this.uriGetterPort2.publishPort();

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
		this.uriGetterPort1.publish(m,topic);
	}

	@Override
	public void publish(MessageI m, String[] topics) throws Exception {
		this.uriGetterPort1.publish(m,topics);

	}

	@Override
	public void publish(MessageI[] m, String topic) throws Exception {
		this.uriGetterPort1.publish(m,topic);

	}

	@Override
	public void publish(MessageI[] m, String[] topics) throws Exception {
		this.uriGetterPort1.publish(m,topics);
	}

	@Override
	public void createTopic(String topic) throws Exception {
		this.uriGetterPort2.createTopic(topic);	
	}

	@Override
	public void createTopics(String[] topics) throws Exception {
		this.uriGetterPort2.createTopics(topics);
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		this.uriGetterPort2.destroyTopic(topic);
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		return this.uriGetterPort2.isTopic(topic);
	}

	@Override
	public String[] getTopics() throws Exception {
		return this.uriGetterPort2.getTopics();
	}

	
	@Override
	public void execute() throws Exception {
		super.execute();
		String[] lesTopics = {"tpoic1", "tpoic2", "tpoic3"};
		String[] lesTopics2 = {"tpoic3", "tpoic4"};
		createTopics(lesTopics);
		publish(new Message("hello World"), lesTopics);
		createTopics(lesTopics2);
		destroyTopic("topic1");
		destroyTopic("topic4");
	}
	
	@Override
	public void	finalise() throws Exception
	{		
		this.doPortDisconnection(this.uriGetterPort1.getPortURI());
		this.doPortDisconnection(this.uriGetterPort2.getPortURI());
		
		super.finalise();
	}
	
	@Override
	public void shutdown() throws ComponentShutdownException {
		try {
			this.uriGetterPort1.unpublishPort();
			this.uriGetterPort2.unpublishPort();
		} catch (Exception e) {
			throw new ComponentShutdownException(e) ;
		}
		super.shutdown();
	}
	
	
}
