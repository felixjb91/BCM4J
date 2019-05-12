package broker.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import bcm.extend.AbstractComponent;
import broker.interfaces.ManagementI;
import broker.interfaces.PublicationI;

import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import message.MessageFilterI;
import message.MessageI;

import static bcm.extend.Utils.addOnMap;
import static bcm.extend.Utils.getOnSet;;

@OfferedInterfaces(offered = {ManagementI.class, PublicationI.class})
public class BrokerImpl extends AbstractComponent {
	
	private Map<String, Set<Subscriber>> subscriptions;
	private Map<String, Set<MessageI>> messages;
	private Set<String> topics;
	
	
	public BrokerImpl(int nbThreads, int nbSchedulableThreads) {
		super(nbThreads, nbSchedulableThreads);
		subscriptions = new HashMap<>();
		messages = new HashMap<>();
		topics = new HashSet<>();
	}
	
	public void createTopic(String topic) throws Exception {
		topics.add(topic);
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
		if(isTopic(topic)) {
			addOnMap(subscriptions, topic, new Subscriber(inboundPortUri));
		}
	}
	
	public void subscribe(String[] topics, String inboundPortUri) throws Exception {
		for(String topic : topics) {
			subscribe(topic, inboundPortUri);
		}
	}
	
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		if(isTopic(topic)) {
			addOnMap(subscriptions, topic, new Subscriber(inboundPortUri));
		}
	}
	
	public void modifyFilter(String topic, MessageFilterI newFilter, String inboundPortUri) throws Exception {
		if(isTopic(topic) && subscriptions.containsKey(topic)) {
			Subscriber subscriber = getOnSet(subscriptions.get(topic), new Subscriber(inboundPortUri));
			subscriber.setFilter(newFilter);
		}
	}
	
	public void unsubscribe(String topic, String inboundPortUri) throws Exception {
		if(isTopic(topic) && subscriptions.containsKey(topic)) {
			subscriptions.get(topic)
						 .remove(new Subscriber(inboundPortUri));
		}
	}
	
	public void publish(MessageI m, String topic) throws Exception {
		if(isTopic(topic)) {
			addOnMap(messages, topic, m);
		}
		
	}
	public void publish(MessageI m, String[] topics) throws Exception {
		
		for(String topic : topics) {
			publish(m, topic);
		}
		
	}
	public void publish(MessageI[] ms, String topic)  throws Exception {
		
		for(MessageI m : ms) {
			publish(m, topic);
		}
		
	}
	public void publish(MessageI[] ms, String[] topics)  throws Exception {
		
	}
	


}

class Subscriber {
	
	private String subscriber;
	private Optional<MessageFilterI> filter;
	
	public Subscriber(String subscriber) {
		this.subscriber = subscriber;
		this.filter = Optional.empty();
	}
	
	public Subscriber(String subscriber, MessageFilterI filter) {
		this.subscriber = subscriber;
		this.filter = Optional.ofNullable(filter);
	}
	
	public void setFilter(MessageFilterI filter) {
		this.filter = Optional.ofNullable(filter);
	}
	
	public String getSubscriber() {
		return subscriber;
	}
	
	public Optional<MessageFilterI> getFilter() {
		return filter;
	}
	
	@Override
	public boolean equals(Object o) {
		if(o == null) return false;
		if(!(o instanceof Subscriber)) return false;
		Subscriber sub = (Subscriber) o;
		if(!(sub.subscriber.equals(this.subscriber))) {
			return false;
		}
		return true;
	}
}