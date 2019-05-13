package broker.impl;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import bcm.extend.AbstractComponent;
import broker.interfaces.ManagementI;
import broker.interfaces.PublicationI;
import broker.ports.ManagementInboundPort;
import broker.ports.PublicationInboundPort;
import broker.ports.ReceptionOutboundPort;
import fr.sorbonne_u.components.annotations.OfferedInterfaces;
import fr.sorbonne_u.components.exceptions.ComponentShutdownException;
import message.MessageFilterI;
import message.MessageI;

import static bcm.extend.Utils.addOnMap;
import static bcm.extend.Utils.getOnSet;;

@OfferedInterfaces(offered = {ManagementI.class, PublicationI.class})
public class BrokerImpl extends AbstractComponent {
	
	private Map<String, Set<Subscriber>> subscriptions;
	private Set<String> topics;
	private ManagementInboundPort managementInboundPort;
	private ReceptionOutboundPort receptionOutboundPort;
	private PublicationInboundPort publicationInboundPort;
	
	public BrokerImpl(String mangeInPortUri, String recOutPortUri, String pubInPortUri) throws Exception
	{
		this(1, 0, mangeInPortUri,recOutPortUri, pubInPortUri);
	}
	
	public BrokerImpl(int nbThreads, int nbSchedulableThreads, String managementInboundPortUri,
						String receptionOutboundPortUri, String publicationInboundPortUri) throws Exception
	{
		super(nbThreads, nbSchedulableThreads);
		
		assert managementInboundPortUri != null;
		assert receptionOutboundPortUri != null;
		assert publicationInboundPortUri != null;
		
		this.managementInboundPort = new ManagementInboundPort(managementInboundPortUri, this);
		this.receptionOutboundPort = new ReceptionOutboundPort(receptionOutboundPortUri, this);
		this.publicationInboundPort = new PublicationInboundPort(publicationInboundPortUri, this);
		
		this.addPort(managementInboundPort);
		this.addPort(receptionOutboundPort);
		this.addPort(publicationInboundPort);
		
		this.managementInboundPort.publishPort();
		this.receptionOutboundPort.publishPort();
		this.publicationInboundPort.publishPort();
		
		this.subscriptions = new HashMap<>();
		this.topics = new HashSet<>();
		
		this.tracer.setTitle("broker component");
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
			subscriptions.get(topic)
						 .parallelStream()
						 .filter(s -> s.filterMessage(m))
						 .forEach(s -> {});
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
		
		for(MessageI m : ms) {
			for(String topic : topics) {
				publish(m, topic);
			}
		}
	}
	
	@Override
	public void finalise() throws Exception {
		
		this.doPortDisconnection(this.managementInboundPort.getPortURI());
		this.doPortDisconnection(this.publicationInboundPort.getPortURI());
		this.doPortDisconnection(this.receptionOutboundPort.getPortURI());
		
		super.finalise();
	}
	
	
	@Override
	public void shutdown() throws ComponentShutdownException {
		
		try {
			this.managementInboundPort.unpublishPort();
			this.publicationInboundPort.unpublishPort();
			this.receptionOutboundPort.unpublishPort();
		}
		catch(Exception e) {
			throw new ComponentShutdownException(e);
		}
		
		super.shutdown();
	}
	
	@Override
	public void shutdownNow() throws ComponentShutdownException
	{
		try {
			this.managementInboundPort.unpublishPort();
			this.publicationInboundPort.unpublishPort();
			this.receptionOutboundPort.unpublishPort();
		}
		catch(Exception e) {
			throw new ComponentShutdownException(e);
		}
		
		super.shutdownNow();
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
	
	public boolean filterMessage(MessageI m) {
		return this.getFilter().isPresent() | this.getFilter().get().filter(m);
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