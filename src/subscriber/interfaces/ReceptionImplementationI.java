package subscriber.interfaces;

import message.MessageI;

public interface ReceptionImplementationI {
	
	public void acceptMessage(MessageI m) throws Exception;
	public void acceptMessages(MessageI[] ms) throws Exception;

}