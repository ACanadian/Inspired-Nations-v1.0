package com.github.InspiredOne.InspiredNations.ToolBox;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.Location;

import com.github.InspiredOne.InspiredNations.Debug;
import com.github.InspiredOne.InspiredNations.InspiredNations;
import com.github.InspiredOne.InspiredNations.PlayerData;
import com.github.InspiredOne.InspiredNations.Exceptions.PlayerOfflineException;
import com.github.InspiredOne.InspiredNations.Governments.InspiredGov;
/**
 * Includes Text Style and color enums and a fully generalized ascii Map drawer.
 * @author Jedidiah Phillips
 *
 */
public class Tools {
	
	InspiredNations plugin;
	
	public Tools(InspiredNations instance) {
		this.plugin = instance;
	}
	
	public static List<InspiredGov> getGovsThatContain(Location loc) {
		List<InspiredGov> output = new ArrayList<InspiredGov>();
		
		for(InspiredGov gov:InspiredNations.regiondata) {
			if(gov.contains(loc)) {
				output.add(gov);
				Debug.print(gov.getName());
			}
		}
		return output;
	}
	
	public static <T> T getInstance(Class<T> gov) {
		try {
			return gov.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
			return null;
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			return null;
		}
	}
	/**
	 *  A method to cut off decimals greater than the hundredth place;
	 * @param input
	 * @return
	 */
	public static BigDecimal cut(BigDecimal input) {
		return input.divide(BigDecimal.ONE, 2, BigDecimal.ROUND_DOWN);
	}
	public static <T extends Nameable> List<T> filter(String key, List<T> fulllist) {
		List<T> output = new ArrayList<T>();
		
		for(T test:fulllist) {
			if(test.getName().toLowerCase().contains(key.toLowerCase())) {
				output.add(test);
			}
		}
		return output;
	}
	
	public enum TextColor {
		/**
		 * Used exclusively for the menu header.
		 */
		HEADER(ChatColor.BLUE + "" + ChatColor.BOLD),
		/**
		 * Subheader
		 */
		SUBHEADER(ChatColor.YELLOW + "" + ChatColor.ITALIC + "" + ChatColor.BOLD),
		/**
		 * Used to say what the proceeding value is.
		 */
		LABEL(ChatColor.RED + ""),
		/**
		 * Anything that is calculated information displayed for the viewer 
		 */
		VALUE(ChatColor.GOLD + ""),
		/**
		 * Additional text describing the value
		 */
		VALUEDESCRI(ChatColor.RED + ""),
		/**
		 * Used for the divider between sections of the HUD
		 */
		DIVIDER(ChatColor.DARK_AQUA + ""),
		
		/**
		 * The color of the options
		 */
		OPTION(ChatColor.DARK_GREEN + ""),
		/**
		 * The color of the number correlating to the option
		 */
		OPTIONNUMBER(ChatColor.YELLOW + ""),
		/**
		 * Used for text that describes an option
		 */
		OPTIONDESCRIP(ChatColor.GRAY + ""),
		/**
		 * Color to be used for an unavailable option
		 */
		UNAVAILABLE(ChatColor.DARK_GRAY + ""),
		/**
		 * Color of text next to unavailable option describing why it's unavailable
		 */
		UNAVAILREASON(ChatColor.GRAY + ""),
		/**
		 * Used to give instructions
		 */
		INSTRUCTION(ChatColor.YELLOW + ""),
		/**
		 * Used for error messages through out the plugin
		 */
		ERROR(ChatColor.RED + ""),
		/**
		 * Used for alert messages through out the plugin
		 */
		ALERT(ChatColor.YELLOW + ""),
		/**
		 * Used for all units mentioned in the HUD
		 */
		UNIT(ChatColor.YELLOW + ""),
		/**
		 * Used for the line of text below the HUD
		 */
		ENDINSTRU(ChatColor.AQUA + "");
		
		private String color;
		
        private TextColor(String color) {
                this.color = color;
        }
        @Override
        public String toString() {
        	return color;
        }
	}
	

	
	// A method to simply repeat a string
	public static String repeat(String entry, int multiple) {
		String temp = "";
		for (int i = 0; i < multiple; i++) {
			temp = temp.concat(entry);
		}
		return temp;
	}
	
	/**
	 * Draws a ascii map for the player to view. The map shows the gov
	 * and the gov below that. The gov is encoded with a color while the
	 * subgov is encoded with a symbol.
	 * @param plugin	the <code>InspiredNations</code> plugin
	 * @param PDI		the <code>PlayerData</code> of the player using the map
	 * @param res		the length of one pixel side in blocks
	 * @param gov		the highest government that will be shown on the map
	 * @return			the <code>String</code> that includes the map
	 */
	public static String drawMap(PlayerData PDI, int res, int tier, int size) {
		
		String output = "";
		Location location = null;
		try {
			location = PDI.getPlayer().getLocation();
		} catch (PlayerOfflineException e) {
			e.printStackTrace();
		}

		
		HashMap<InspiredGov, ChatColor> superGov = new HashMap<InspiredGov, ChatColor>();
		HashMap<InspiredGov, String> subGov = new HashMap<InspiredGov, String>();
		
		int superIter = 0;// for iterating through superGov color choices
		ChatColor[] superColors = {ChatColor.GREEN, ChatColor.RED, ChatColor.DARK_BLUE, ChatColor.DARK_GREEN, ChatColor.DARK_RED, ChatColor.GOLD, ChatColor.WHITE,
				ChatColor.AQUA, ChatColor.YELLOW, ChatColor.DARK_AQUA, ChatColor.DARK_PURPLE, ChatColor.LIGHT_PURPLE};
		int subIter = 0;// for iterating through subGov Char choices
		String[] subChars = {"#", "$", "%", "&", "S", "~", "=", "+", "B", "T", "G"};

		ChatColor colorInit = ChatColor.DARK_GRAY;
		String characterInit = "/";
		ChatColor selfcolor = ChatColor.GRAY;
		String selfchar = ">";
		
		int above = size;
		int below = size + 1;
		
		//int[] remove = {160*above+240,160*above+77,160*above-80,160*above+83};
		
		for(int z = -above;z < below; z++) {
			Location loctest = location.clone();
			loctest.setZ(location.getBlockZ() + z*res);
			for (int x = -26; x < 27; x++) {
				String character = characterInit;
				ChatColor color = colorInit;
				loctest.setX(location.getBlockX() + x*res);
				
				//Check if self
				if(x == 0 && z == 0) {
					color = selfcolor;
				}
				
				//Loop through the superGovs to see if any of them contain loctest
				for(InspiredGov govtest:InspiredNations.regiondata) {
					if(govtest.contains(loctest) && govtest.getTier()==tier) {
						if(!superGov.containsKey(govtest)) {
							superGov.put(govtest, superColors[superIter]);
							superIter++;
							if(superIter == superColors.length) {
								superIter = 0;
							}
						}
						//Color is known, so append that to map string
						color = superGov.get(govtest);
						
						//Loop through the subGovs to see if any of them contain loctest
						for(InspiredGov subtest: govtest.getAllSubGovsAndFacilitiesJustBelow()) {
							if(subtest.contains(loctest)) {
								if(!subGov.containsKey(subtest)) {
									subGov.put(subtest, subChars[subIter]);
									subIter++;
									if(subIter == subChars.length) {
										subIter = 0;
									}
								}
								character = subGov.get(subtest);
								break;
							}
						}
						break;
					}
				}
				//Check if self
				if(x == 0 && z == 0) {
					//character = selfchar;
					if ((-45 < location.getYaw() && 45 >= location.getYaw()) || (315 < location.getYaw() && 360 >= location.getYaw())
							|| (-360 < location.getYaw() && -315 >= location.getYaw())) {
						character = "V";
					}
					if ((45 < location.getYaw() && 135 >= location.getYaw()) || (-315 < location.getYaw() && -225 >= location.getYaw())) {
						character = "<";
					} 
					if ((135 < location.getYaw() && 225 >= location.getYaw()) || (-225 < location.getYaw() && -135 >= location.getYaw())) {
						character = "^";

					}
					if ((225 < location.getYaw() && 315 >= location.getYaw()) || (-135 < location.getYaw() && -45 >= location.getYaw())) {
						character = ">";
					}
					selfchar = character;
				}
				output = output.concat(color + character);
			}
			output = output.concat("\n");
		}
/*		
		// Direction Icon
		if ((-45 < location.getYaw() && 45 >= location.getYaw()) || (315 < location.getYaw() && 360 >= location.getYaw())
				|| (-360 < location.getYaw() && -315 >= location.getYaw())) {
			output = output.substring(0, remove[0]).concat("`|`").concat(output.substring(remove[0] + 1));
		}
		if ((45 < location.getYaw() && 135 >= location.getYaw()) || (-315 < location.getYaw() && -225 >= location.getYaw())) {
			output = output.substring(0, remove[1]).concat("-").concat(output.substring(remove[1] + 1));
		} 
		if ((135 < location.getYaw() && 225 >= location.getYaw()) || (-225 < location.getYaw() && -135 >= location.getYaw())) {
			output = output.substring(0, remove[2]).concat(",|,").concat(output.substring(remove[2] + 1));

		}
		if ((225 < location.getYaw() && 315 >= location.getYaw()) || (-135 < location.getYaw() && -45 >= location.getYaw())) {
			output = output.substring(0, remove[3]).concat("-").concat(output.substring(remove[3] + 1));
		}
*/
		//Key
		for(InspiredGov key:superGov.keySet()) {
			output = output.concat(superGov.get(key) + key.getName() + ", ");
		}
		for(InspiredGov key:subGov.keySet()) {
			output = output.concat(superGov.get(key.getSuperGovObj()) + subGov.get(key) + "=" + key.getName() + ", ");
		}
		output = output.concat(ChatColor.DARK_GRAY + "/" + ChatColor.GRAY + "= Unclaimed Land, "+ selfchar +" = You.\n");
		
		return output;
		
	}
}
