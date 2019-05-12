package broker.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bcm.extend.AbstractComponent;
import broker.interfaces.ManagementI;
import broker.interfaces.PublicationI;

import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import message.MessageFilterI;
import message.MessageI;

@OfferedInterfaces(offered = {ManagementI.class, PublicationI.class})
public class BrokerImpl extends AbstractComponent {
	
	private Map<String, List<String>> subscriptions;
	private List<String> topics;
	
	
	public BrokerImpl(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
		subscriptions = new HashMap<>();
		topics = new ArrayList<>();
	}
	
	public void createTopic(String topic) throws Exception {
		if(!isTopic(topic)) {
			topics.add(topic);
		}
	}
	
	public void createTopics(String[] topics) throws Exception {
		for(String topic : topics) {
			createTopic(topic);
		}
	}
	
	public void destroyTopic(String topic) throws Exception {
		topics.remove(topic);
	}
	
	public boolean isTopic(String topic) throws Exception {
		return topics.contains(topic);
	}
	
	public String[] getTopics() throws Exception {
		return topics.toArray(new String[topics.size()]);
	}
	
	public void subscribe(String topic, String inboundPortUri) throws Exception {
		if(!subscriptions.containsKey(inboundPortUri)) {
			subscriptions.put(topic, new ArrayList<>());
		}
		if(!isTopic(topic)) {
			this.createTopic(topic);
		}
		subscriptions.get(topic).add(inboundPortUri);
	}
	
	public void subscribe(String[] topics, String inboundPortUri) throws Exception {
		for(String topic : topics) {
			subscribe(topic, inboundPortUri);
		}
	}
	
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		
	}
	
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception {
		
	}
	
	public void unsubscribe(String topic, String inboundPortUri) throws Exception {
		subscriptions.get(topic).remove(inboundPortUri);
	}
	
	public void publish(MessageI m, String topic) throws Exception {
		if(isTopic(topic)) {
			
		}
		
	}
	public void publish(MessageI m, String[] topics) throws Exception {
		
	}
	public void publish(MessageI[] ms, String topic)  throws Exception {
		
	}
	public void publish(MessageI[] ms, String[] topics)  throws Exception {
		
	}	

}
