package broker.interfaces;

import message.MessageFilterI;

public interface SubscriptionImplementationI {
	
	public void subscribe(String topic, String inboundPortUri) throws Exception;
	public void subscribe(String[] topics, String inboundPortUri) throws Exception;
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception;
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception;
	public void unsubscribe(String topic, String inboundPortUri) throws Exception;

}
