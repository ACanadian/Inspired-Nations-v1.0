package com.github.InspiredOne.InspiredNations.Hud.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.GovFactory;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Governments.Implem.Country;
import com.github.InspiredOne.InspiredNations.Hud.InputMenu;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Listeners.Implem.MapManager;
import com.github.InspiredOne.InspiredNations.ToolBox.MenuTools.MenuError;

public class Map extends InputMenu {

	private MapManager<Map> manager;
	Class<? extends InspiredGov> selection = Country.class;
	
	public Map(PlayerData PDI) {
		super(PDI);
	}

	@Override
	public String getHeader() {
		return "Map 1:" + (int) Math.pow(2, manager.zoom);
	}

	@Override
	public Menu getPreviousMenu() {
		return new MainHud(PDI);
	}

	@Override
	public boolean getPassBy() {
		return false;
	}

	@Override
	public Menu getPassTo() {
		return null;
	}

	@Override
	public void actionResponse() {
	}

	@Override
	public Menu nextMenu() {
		this.setError(MenuError.NOT_AN_OPTION());
		return this.getSelfPersist();
	}

	@Override
	public String validate(String input) {
		
		for(String test:this.getTabOptions()) {
			if(test.equalsIgnoreCase(input)) {
				for(Class<? extends InspiredGov> govType:InspiredNations.regiondata.keySet()) {
					InspiredGov gov = GovFactory.getGovInstance(govType);
					if(input.equalsIgnoreCase(gov.getTypeName())) {
						this.selection = govType;
						return MenuError.NO_ERROR();
					}
				}
			}
		}

		return MenuError.NOT_AN_OPTION();
	}

	@Override
	public void useInput(String input) {
		this.Update();
	}
	
	@Override
	public String getInstructions() {
		return manager.drawMap(selection, 6);
	}

	@Override
	public void addTabOptions() {
		// Trying to use some kind of tab complete thing for selecting the scope of the map?
		for(Class<? extends InspiredGov> govType:InspiredNations.regiondata.keySet()) {
			InspiredGov gov = GovFactory.getGovInstance(govType);
			if(gov.getSubGovs().size() !=0) {
				tabOptions.add(gov.getTypeName());
			}
		}
		tabOptions.clear();  // this looks counter productive. Fill the list then clear it?
		
	}

	@Override
	public void addActionManagers() {
		Debug.print("Inside addManagers");
		manager = new MapManager<Map>(this);
		managers.add(manager);
		
	}
}
