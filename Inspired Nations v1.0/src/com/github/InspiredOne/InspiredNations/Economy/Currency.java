package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import com.github.InspiredOne.InspiredNations.ToolBox.Nameable;

public class Currency implements Serializable, Nameable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2855995345401677901L;
	private String name = "Coin";
	public static final Currency DEFAULT = new Currency("Coin");

	public Currency(String name) {

	}

	@Override
	public String getName() {
		return name;
	}
	@Override
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
    public int hashCode() {
        return new HashCodeBuilder(17, 31). // two randomly chosen prime numbers
            // if deriving: appendSuper(super.hashCode()).
            append(name).
            toHashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (obj == this)
            return true;
        if (!(obj instanceof Currency))
            return false;

        Currency rhs = (Currency) obj;
        return new EqualsBuilder().
            // if deriving: appendSuper(super.equals(obj)).
            append(name, rhs.getName()).
            isEquals();
    }
	
}
