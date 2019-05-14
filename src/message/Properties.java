package message;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class Properties implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Map<String,Object> Prop = new HashMap<>();
	
//setter
	
	public void putProp(String name, boolean v) {
		this.Prop.put(name, v);
	}
	
	public void putProp(String name, byte v) {
		this.Prop.put(name, v);
	}
	
	public void putProp(String name, char v) {
		this.Prop.put(name, v);
	} 
	
	public void putProp(String name, double v) {
		this.Prop.put(name, v);
	}
	  
	public void putProp(String name, float v) {
		this.Prop.put(name, v);
	}
	 
	public void putProp(String name, int v) {
		this.Prop.put(name, v);
	}
	
	public void putProp(String name, long v) {
		this.Prop.put(name, v);
	}
	
	public void putProp(String name, short v) {
		this.Prop.put(name, v);
	}
	
	public void putProp(String name, String v) {
		this.Prop.put(name, v);
	}

// getters

	public boolean isBooleanProp(String name) {
		return (boolean) this.Prop.get(name); 
	}

	public Byte getByteProp(String name) {
		return (Byte) this.Prop.get(name);
	}

	public char getCharPtop(String name) {
		return (char) this.Prop.get(name);
	}

	public double getDoubleProp(String name) {
		return (double) this.Prop.get(name);
	}

	public float getFloatProp(String name) {
		return (float) this.Prop.get(name);
	}

	public int getIntProp(String name) {
		return (int) this.Prop.get(name);
	}

	public long getLongProp(String name) {
		return (long) this.Prop.get(name);
	}

	public short getShortProp(String name) {
		return (short) this.Prop.get(name);
	}

	public String getStringProp(String name) {
		return (String) this.Prop.get(name);
	}

}
