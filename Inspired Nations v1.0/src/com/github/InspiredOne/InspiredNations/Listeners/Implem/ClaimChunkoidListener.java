package com.github.InspiredOne.InspiredNations.Listeners.Implem;

import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerMoveEvent;

import com.github.InspiredOne.InspiredNations.Listeners.InspiredListener;
import com.github.InspiredOne.InspiredNations.ToolBox.Point2D;

public class ClaimChunkoidListener extends InspiredListener {

	public ClaimChunkoidListener(ClaimChunkoidManager manager) {
		super(manager);
	}
	
	@EventHandler
	public void onPlayerMove(PlayerMoveEvent event) {
		if(this.getPlayerData().getPlayer() != event.getPlayer()) {
			return;
		}
		Point2D from = new Point2D(event.getFrom().getChunk());
		Point2D to = new Point2D(event.getTo().getChunk());
		if(!from.equals(to)) {
			((ClaimChunkoidManager) manager).setPosition(to);
			this.manager.Update();
		}
	}

}
