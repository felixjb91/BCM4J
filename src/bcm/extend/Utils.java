package bcm.extend;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Utils {
	
	public static <K, V> void addOnMap(Map<K, Set<V>> map, K key, V value) {
		if(!map.containsKey(key)) {
			map.put(key, new HashSet<>());
		}
		map.get(key).add(value);
	}
	
	public static <V> V getOnSet(Set<V> values, V v) {
		
		if(values.contains(v)) {
			Iterator<V> iterator = values.iterator();
			while(iterator.hasNext()) {
				V e = iterator.next();
				if(e.equals(v)) {
					return e;
				}
			}
		}
		
		return null;
	}

}