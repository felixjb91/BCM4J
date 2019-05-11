package broker;

import fr.sorbonne_u.components.AbstractComponent;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import message.MessageFilterI;

public class ManagementInboundPort 
	   extends AbstractInboundPort
	   implements ManagementI
{
	private static final long serialVersionUID = 1L;

	public ManagementInboundPort(ComponentI owner, String uri) throws Exception {
		super(uri, ManagementI.class, owner);
	}
	
	public ManagementInboundPort(ComponentI owner) throws Exception {
		super(ManagementI.class, owner);
	}
	
	@Override
	public void createTopic(String topic) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((BrokerImpl)this.getOwner()).createTopic(topic);
						return null;
					}
				}
		);
	}
	
	@Override
	public void createTopics(String[] topics) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((BrokerImpl)this.getOwner()).createTopics(topics);
						return null;
					}
				}
		);
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Void>() {
					@Override
					public Void call() throws Exception {
						((BrokerImpl)this.getOwner()).destroyTopic(topic);
						return null;
					}
				}
		);
		
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		return this.owner.handleRequestSync(
				new AbstractComponent.AbstractService<Boolean>() {
					@Override
					public Boolean call() throws Exception {
						return ((BrokerImpl)this.getOwner()).isTopic(topic);
					}
				}
		);
	}

	@Override
	public String[] getTopics() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribe(String[] topic, String inboundPortUri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) {
		// TODO Auto-generated method stub
		
	}

}
