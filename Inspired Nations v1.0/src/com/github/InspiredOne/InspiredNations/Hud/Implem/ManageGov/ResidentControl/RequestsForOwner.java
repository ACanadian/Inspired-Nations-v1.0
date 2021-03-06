package com.github.InspiredOne.InspiredNations.Hud.Implem.ManageGov.ResidentControl;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.OwnerGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.PromptOption;
import com.github.InspiredOne.InspiredNations.Hud.TabSelectOptionMenu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.IgnoreOfferOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.JoinOwnerGovOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.JoinSubjectGovOption;
import com.github.InspiredOne.InspiredNations.Hud.Implem.Player.PlayerID;

public class RequestsForOwner extends TabSelectOptionMenu<PlayerID> {

	OwnerGov gov;
	Menu previous;
	
	public RequestsForOwner(PlayerData PDI, Menu previous, OwnerGov gov) {
		super(PDI);
		this.gov = gov;
		this.previous = previous;
	}

	@Override
	public Menu getPreviousPrompt() {
		return new ManageCitizens(PDI, previous, gov);
	}

	@Override
	public String postTabListPreOptionsText() {
		return "";
	}

	@Override
	public String getHeader() {
		return gov.getOwnerPositionName() + " Requests";
	}

	@Override
	public void addTabOptions() {
		for(PlayerID player:this.gov.getOwnerRequests()) {
			this.taboptions.add(player);
		}
	}

	@Override
	public void addOptions() {
		if(taboptions.size() != 0) {
			this.options.add(new IgnoreOfferOption(this, "Ignore Request", this.getData(), gov.getOwnerRequests()));
			this.options.add(new JoinOwnerGovOption(this, "Accept Request", this.gov, this.getData()));
		}
		this.options.add(new PromptOption(new RequestsForOwner(PDI, previous, gov), gov.getOwnerPositionName() + " Offers",
				new OffersForOwner(PDI, previous, gov)));
	}

	@Override
	public void addActionManagers() {

	}

}
