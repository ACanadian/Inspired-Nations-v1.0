package com.github.InspiredOne.InspiredNations;


public class Debug {

	public static boolean report = true;
	
	public static void print(String msg) {
		InspiredNations.plugin.logger.info( msg);
	} 
	public static void print(Object msg) {
		InspiredNations.plugin.logger.info(msg.toString());
	}
}
