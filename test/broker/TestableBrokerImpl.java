package broker;

import broker.impl.BrokerImpl;
import broker.interfaces.ManagementI;
import broker.interfaces.PublicationI;
import message.MessageFilterI;
import message.MessageI;

public class TestableBrokerImpl implements ManagementI, PublicationI {
	
	private BrokerImpl broker = new BrokerImpl(1, 0, "mInPort", "rOutPort", "pInPort");
	
	public TestableBrokerImpl() throws Exception {
		broker = new BrokerImpl(1, 0, "mInPort", "rOutPort", "pInPort");
	}

	@Override
	public void createTopic(String topic) throws Exception {
		broker.createTopic(topic);
	}

	@Override
	public void createTopics(String[] topics) throws Exception {
		broker.createTopics(topics);
	}

	@Override
	public void destroyTopic(String topic) throws Exception {
		broker.destroyTopic(topic);
	}

	@Override
	public boolean isTopic(String topic) throws Exception {
		return broker.isTopic(topic);
	}

	@Override
	public void subscribe(String topic, String inboundPortUri) throws Exception {
		broker.subscribe(topic, inboundPortUri);
	}

	@Override
	public void subscribe(String[] topics, String inboundPortUri) throws Exception {
		broker.subscribe(topics, inboundPortUri);
	}

	@Override
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		broker.subscribe(topic, filter, inboundPortUri);
	}

	@Override
	public void unsubscribe(String topic, String inboundPortUri) throws Exception {
		broker.unsubscribe(topic, inboundPortUri);
	}

	@Override
	public void publish(MessageI m, String topic) throws Exception {
		broker.publish(m, topic);
	}

	@Override
	public void publish(MessageI m, String[] topics) throws Exception {
		broker.publish(m, topics);
	}

	@Override
	public void publish(MessageI[] ms, String topic) throws Exception {
		broker.publish(ms, topic);
	}

	@Override
	public void publish(MessageI[] ms, String[] topics) throws Exception {
		broker.publish(ms, topics);
	}

	@Override
	public String[] getTopics() throws Exception {
		return broker.getTopics();
	}

	@Override
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception {
		broker.modifyFilter(topic, newFilter, inboundPortUri);
	}


}
