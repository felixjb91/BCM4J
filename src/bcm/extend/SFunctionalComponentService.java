package bcm.extend;


@FunctionalInterface
public interface SFunctionalComponentService<T> {
	
	public T service() throws Exception;

}