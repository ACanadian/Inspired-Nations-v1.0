package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;

public abstract class PickNavGeneral extends PassByOptionMenu {

	protected Menu previous;
	
	public PickNavGeneral(PlayerData PDI, Menu previous) {
		super(PDI);
		this.previous = previous;
	}

	@Override
	public Menu getPreviousMenu() {
		return previous;
	}

	@Override
	public final void init() {
		this.options.add(new PromptOption(this, this.getPlayerOptionText(), this.getPlayerMenu()));
		if(!InspiredNations.global.getData().getAllSubGovsAndFacilitiesJustBelow().isEmpty()) {
			this.options.add(new PromptOption(this, this.getGovOptionText(), this.getGovMenu()));
		}
	}
	/**
	 * Gets the text to be used for the government option
	 * @return
	 */
	public abstract String getGovOptionText();
	/**
	 * Gets the text to be used for the player option
	 * @return
	 */
	public abstract String getPlayerOptionText();
	/**
	 * gets the menu to be used for the government option
	 * @return
	 */
	public abstract Menu getGovMenu();
	/**
	 * gets the menu to be used for the Player option
	 * @return
	 */
	public abstract Menu getPlayerMenu();

}