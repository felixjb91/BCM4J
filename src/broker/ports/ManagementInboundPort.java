package broker.ports;

import broker.impl.BrokerImpl;
import fr.sorbonne_u.components.ComponentI;
import fr.sorbonne_u.components.ports.AbstractInboundPort;
import interfaces.ManagementI;
import message.MessageFilterI;

public class ManagementInboundPort 
	   extends AbstractInboundPort
	   implements ManagementI
{
	private static final long serialVersionUID = 1L;

	public ManagementInboundPort(String uri, ComponentI owner) throws Exception {
		super(uri, ManagementI.class, owner);
		assert owner instanceof BrokerImpl;
	}
	
	public ManagementInboundPort(ComponentI owner) throws Exception {
		super(ManagementI.class, owner);
		assert owner instanceof BrokerImpl;
	}
	
	private BrokerImpl owner() {
		return (BrokerImpl) owner;
	}
	
	
	@Override
	public void createTopic(String topic) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() ->  this.owner().createTopic(topic)	
			);
	}
	
	@Override
	public void createTopics(String[] topics) throws Exception {

		this.owner()
			.handleRequestAsync(
				() -> this.owner().createTopics(topics)
			);		
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().destroyTopic(topic)
			);
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		
		return this.owner()
				   .handleRequestSync(
						   () -> this.owner().isTopic(topic)
					);		
	}

	@Override
	public String[] getTopics() throws Exception {
		
		return this.owner()
				   .handleRequestSync(
						   () -> this.owner().getTopics()
					);	
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().subscribe(topic, inboundPortUri)
			);
	}

	@Override
	public void subscribe(String[] topics, String inboundPortUri) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().subscribe(topics, inboundPortUri)
			);
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().subscribe(topic, filter, inboundPortUri)
			);
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().modifyFilter(topic, newFilter, inboundPortUri)
			);
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) throws Exception {
		
		this.owner()
			.handleRequestAsync(
				() -> this.owner().unsubscribe(topic, inboundPortUri)
			);
	}
}