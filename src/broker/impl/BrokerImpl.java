package broker.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import bcm.extend.AbstractComponent;
import broker.ports.ManagementInboundPort;
import broker.ports.PublicationInboundPort;
import broker.ports.ReceptionOutboundPort;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import interfaces.ManagementI;
import interfaces.PublicationI;
import message.MessageFilterI;
import message.MessageI;

import static bcm.extend.Utils.addOnMap;
import static bcm.extend.Utils.getOnSet;;

@OfferedInterfaces(offered = {ManagementI.class, PublicationI.class})
public class BrokerImpl extends AbstractComponent {
	
	private Map<String, Set<Subscriber>> subscriptions;
	private Map<String, Set<MessageI>> messages;
	private Set<String> topics;
	private ManagementInboundPort mangeInPort;
	private ReceptionOutboundPort recOutPort;
	private PublicationInboundPort pubInPort;
	
	
	public BrokerImpl(String pubInPortUri, String recOutPortUri, String mangeInPortUri) throws Exception
	{
		super(1, 0);
		
		this.mangeInPort = new ManagementInboundPort(mangeInPortUri, this);
		this.recOutPort = new ReceptionOutboundPort(recOutPortUri, this);
		this.pubInPort = new PublicationInboundPort(pubInPortUri, this);
		
		this.addPort(mangeInPort);
		this.addPort(recOutPort);
		this.addPort(pubInPort);
		
		this.mangeInPort.publishPort();
		this.recOutPort.publishPort();
		this.pubInPort.publishPort();
		
		subscriptions = new HashMap<>();
		messages = new HashMap<>();
		topics = new HashSet<>();
	}
	
	public void createTopic(String topic) throws Exception {
		topics.add(topic);
		System.out.print("create : ");
		for(String t: topics) System.out.print(t+" ");
		System.out.println();
	}
	
	public void createTopics(String[] topics) throws Exception {
		for(String topic : topics) {
			createTopic(topic);
		}
	}
	
	public void destroyTopic(String topic) throws Exception {
		topics.remove(topic);
		System.out.print("destroy : ");
		for(String t: topics) System.out.print(t+" ");
		System.out.println();
	}
	
	public boolean isTopic(String topic) throws Exception {
		return topics.contains(topic);
	}
	
	public String[] getTopics() throws Exception {
		return topics.toArray(new String[topics.size()]);
	}
	
	public void subscribe(String topic, String inboundPortUri) throws Exception {
		this.subscribe(topic, null, inboundPortUri);
	}
	
	public void subscribe(String[] topics, String inboundPortUri) throws Exception {
		for(String topic : topics) {
			subscribe(topic, inboundPortUri);
		}
	}
	
	public void subscribe(String topic, MessageFilterI filter, String inboundPortUri) throws Exception {
		if(isTopic(topic)) {
			addOnMap(subscriptions, topic, new Subscriber(inboundPortUri, filter));
		}
		else {
			String msg = String.format("you can not subscribe to %s because it does not exist", topic);
			throw new Exception(msg);
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
		
		System.out.print("publish : ");
		System.out.println("publish Broker -> message: "+m.toString()+" , topic: "+topic);
		for(String t: topics) System.out.print(t+" ");
		System.out.println();
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
		
		for(MessageI m : ms) {
			for(String topic : topics) {
				publish(m, topic);
			}
		}
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