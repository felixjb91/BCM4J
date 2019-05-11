package broker;

import message.MessageFilterI;

public interface SubscriptionImplementationI {
	
	public void subscribe(String topic, String inboundPortUri);
	public void subscribe(String[] topic, String inboundPortUri);
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri);
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri);
	public void unsubscribe(String topic, String inboundPortUri);

}
