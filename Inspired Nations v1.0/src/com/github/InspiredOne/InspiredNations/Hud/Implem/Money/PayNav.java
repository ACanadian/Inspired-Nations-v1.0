package com.github.InspiredOne.InspiredNations.Hud.Implem.Money;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress.PickNavGeneral;
import com.github.InspiredOne.InspiredNations.ToolBox.Payable;

public class PayNav extends PickNavGeneral {

	Payable accounts;
	/**
	 * 
	 * @param PDI
	 * @param accounts
	 * @param back	the menu to return to after doing all of the payment stuff.
	 */
	public PayNav(PlayerData PDI, Payable accounts, Menu back) {
		super(PDI, back);
		this.accounts = accounts;
	}

	@Override
	public String getPreOptionText() {
		return "";
	}

	@Override
	public String getHeader() {
		return "Pay";
	}

	@Override
	public String getGovOptionText() {
		return "Pay Government";
	}

	@Override
	public String getPlayerOptionText() {
		return "Pay Player";
	}

	@Override
	public Menu getGovMenu() {
		return new PickGovToPay(PDI, accounts, this, this);
	}

	@Override
	public Menu getPlayerMenu() {
		return new PayPlayer(PDI, accounts, previous);
	}

}
