package interfaces;

import fr.sorbonne_u.components.interfaces.OfferedI;
import fr.sorbonne_u.components.interfaces.RequiredI;

public interface ManagementI extends OfferedI,
									 RequiredI,
									 ManagementImplementationI,
									 SubscriptionImplementationI
{

}