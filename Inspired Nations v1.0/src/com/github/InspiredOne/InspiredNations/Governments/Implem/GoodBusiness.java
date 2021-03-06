package com.github.InspiredOne.InspiredNations.Governments.Implem;

import java.util.ArrayList;
import java.util.List;

import com.github.InspiredOne.InspiredNations.Governments.Facility;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Regions.Implem.GoodBusinessLand;


public class GoodBusiness extends Business {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8118583887730062434L;
	private static final String typeName = "Good Business";
	
	public GoodBusiness() {
		super();
		this.setRegion(new GoodBusinessLand());
	}
	
	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public List<Class<? extends InspiredGov>> getSelfGovs() {
		List<Class<? extends InspiredGov>> output = new ArrayList<Class<? extends InspiredGov>>();
		output.add(this.getClass());
		return output;
		
	}
	
	@Override
	public List<Class<? extends Facility>> getGovFacilities() {
		List<Class<? extends Facility>> output = new ArrayList<Class<? extends Facility>>();
		output.add(ChestShop.class);
		return output;
	}
	
	@Override
	public Class<? extends InspiredGov> getGeneralGovType() {
		return Business.class;
	}

}
