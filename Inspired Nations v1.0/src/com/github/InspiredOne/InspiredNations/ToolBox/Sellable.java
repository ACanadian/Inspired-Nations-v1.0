package com.github.InspiredOne.InspiredNations.ToolBox;


public interface Sellable {

	/**
	 * Handles the physical transfer of the item ownership. For items with multiple
	 * owners (i.e. governments or accounts) all other owners are removed and the 
	 * new owner is added making him/her the sole owner. For items, they are placed
	 * in the inventory of the buyer. If there is no space, then they are ejected
	 * on the ground around the buyer.
	 * @param player
	 */
	public void transferOwnership(PlayerID playerTo);
	
}