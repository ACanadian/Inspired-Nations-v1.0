package com.github.InspiredOne.InspiredNations.ToolBox;
/**
 * 
 * @author Jedidiah Phillips
 *
 * @param <E> The type of data to be extracted
 */
public interface Datable<E> {
	public E getData();
	public void setData(E data);
}
