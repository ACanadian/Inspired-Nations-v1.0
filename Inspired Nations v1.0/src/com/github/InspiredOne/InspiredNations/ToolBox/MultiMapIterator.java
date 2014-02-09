package com.github.InspiredOne.InspiredNations.ToolBox;

import java.util.HashSet;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MultiMapIterator<T> implements Iterator<T> {

	private MultiMap <?, T> map;
	private Iterator<HashSet<T>> HashIter;
	private Iterator<T> govIter;
	private T value;
	public MultiMapIterator(MultiMap<?, T> map) {
		this.map = map;
		HashIter = map.values().iterator();
		if(HashIter.hasNext()) {
			govIter = HashIter.next().iterator();
		}
		if(govIter.hasNext()) {
			value = govIter.next();
		}
	}

	@Override
	public boolean hasNext() {
		if (govIter.hasNext()) {
			return true;
		}
		else if(HashIter.hasNext()) {
			govIter = HashIter.next().iterator();
			return this.hasNext();
		}
		else {
			return false;
		}
	}

	@Override
	public T next() {
		if(!this.hasNext()) {
			throw new NoSuchElementException();
		}
		else {
			value = govIter.next();
			return value;
		}
	}

	@Override
	public void remove() {
		map.remove(value);
	}

}
