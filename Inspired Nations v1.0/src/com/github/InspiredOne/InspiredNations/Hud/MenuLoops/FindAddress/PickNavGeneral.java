package com.github.InspiredOne.InspiredNations.Hud.MenuLoops.FindAddress;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PassByOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.ToolBox.PlayerID;

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
	public abstract PickPlayerGeneral getPlayerMenu();

	@Override
	public void addOptions() {
		Debug.print("Inside addOptions 1");
		TabSelectOptionMenu<PlayerID> playermenu = this.getPlayerMenu();
		Debug.print("Inside addOptions 2");
		if (!playermenu.getTabOptions().isEmpty()){
			Debug.print("Inside addOptions 3");
			this.options.add(new PromptOption(this, this.getPlayerOptionText(), this.getPlayerMenu()));
			Debug.print("Inside addOptions 4");
		}
		Debug.print("Inside addOptions 5");
		if(!InspiredNations.global.getData().getAllSubGovsAndFacilitiesJustBelow().isEmpty()) {
			Debug.print("Inside addOptions 6");
			this.options.add(new PromptOption(this, this.getGovOptionText(), this.getGovMenu()));
			Debug.print("Inside addOptions 7");
		}
		Debug.print("Inside addOptions 1");
	}



}
