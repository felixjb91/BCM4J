package broker;

import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ManagementI extends ManagementImplementationI,
									 SubscriptionImplementationI,
									 OfferedI,
									 RequiredI 
{

}