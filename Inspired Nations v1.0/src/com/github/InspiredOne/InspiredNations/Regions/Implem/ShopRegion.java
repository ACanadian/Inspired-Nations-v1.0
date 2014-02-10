package com.github.InspiredOne.InspiredNations.Regions.Implem;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.NonCummulativeRegion;
import com.github.InspiredOne.InspiredNations.Regions.Region;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class ShopRegion extends Region {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2195353839150455099L;

	Point3D one;
	Point3D two;

	@Override
	public String getTypeName() {
		return "Shop";
	}

	@Override
	public String getDescription() {
		return "A facility to sell items from";
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		return null;
	}

	@Override
	public boolean IsIn(NonCummulativeRegion region) {
		return this.IsIn((Region) region);
	}

	@Override
	public boolean IsIn(CummulativeRegion<?> region) {
		return this.IsIn((Region) region);
	}

	@Override
	public int volume() {
		int output = 0;
		if(one != null) {
			output++;
		}
		if(two != null) {
			output ++;
		}
		return output;
	}

	@Override
	public boolean contains(Point3D location) {
		if(one != null) {
			if(one.equals(location)) {
				return true;
			}
		}
		if(two != null) {
			if(two.equals(location)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean Intersects(NonCummulativeRegion region) {
		return Intersects((Region) region);
	}

	@Override
	public boolean Intersects(CummulativeRegion<?> region) {
		return Intersects((Region) region);
	}

	@Override
	public boolean IsIn(Region region) {
		if(one == null) {
			if(two == null) {
				return true;
			}
			else {
				return region.contains(two);
			}
		}
		else {
			if(two ==null) {
				return region.contains(one);
			}
			else {
				return(region.contains(one) && region.contains(two));
			}
		}
	}

	@Override
	public boolean Intersects(Region region) {
		if(one != null) {
			if(region.contains(one)) return true;
		}
		if(two != null) {
			if(region.contains(two)) return true;
		}
		return false;
	}

	@Override
	protected boolean instantiated() {
		if(one != null || two != null) {
			return true;
		}
		else {
			return false;
		}
	}

}