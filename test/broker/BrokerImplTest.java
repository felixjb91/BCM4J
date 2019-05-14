package broker;

public class BrokerImplTest extends AbstractBrokerTest {

	@Override
	public void setUp() throws Exception {
		TestableBrokerImpl brokerImpl = new TestableBrokerImpl();
		managementProvider = brokerImpl;
		publicationProvider = brokerImpl;
	}

}
