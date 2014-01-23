package com.github.InspiredOne.InspiredNations.Regions.Implem;

import org.bukkit.Chunk;
import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
import com.github.InspiredOne.InspiredNations.Hud.Menu;
import com.github.InspiredOne.InspiredNations.Hud.Implem.ClaimLand.ClaimChunkoid;
import com.github.InspiredOne.InspiredNations.Regions.CummulativeRegion;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;
import com.github.InspiredOne.InspiredNations.ToolBox.Point3D;

public class Chunkoid extends CummulativeRegion {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4821199291297874395L;
	private static final String typeName = "Chunkoid";
	private static final String description = "";
	
	@Override
	public String getTypeName() {
		return typeName;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public Menu getClaimMenu(PlayerData PDI, Menu previous, InspiredGov gov) {
		return new ClaimChunkoid(PDI, previous, gov);
	}
	
	public void addChunk(Chunk chunk) {
		Point2D position = new Point2D(chunk);
		addChunk(position);
	}
	
	public void addChunk(Point2D position) {
		ChunkRegion chunk = new ChunkRegion(position);
		addChunk(chunk);
	}
	
	public void addChunk(ChunkRegion region) {
		if(!this.getRegions().contains(region)) {
			this.getRegions().add(region);	
		}
	}

	@Override
	public boolean contains(Point3D location) {
		Location spot = location.getLocation();
		return this.getRegions().contains(new ChunkRegion(spot.getChunk()));
	}
}
