package subscriber;

import message.MessageI;

public interface ReceptionImplementationI {
	
	public void acceptMessage(MessageI m);
	public void acceptMessages(MessageI[] ms);

}