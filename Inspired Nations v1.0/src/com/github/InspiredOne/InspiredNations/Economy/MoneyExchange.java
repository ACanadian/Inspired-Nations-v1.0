package com.github.InspiredOne.InspiredNations.Economy;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.HashMap;

import com.github.InspiredOne.InspiredNations.InspiredNations;

public class MoneyExchange implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3674609233229292913L;
	private MathContext mcup = new MathContext(5, RoundingMode.UP);
	private MathContext mcdown = new MathContext(5, RoundingMode.HALF_EVEN);

	private HashMap<Currency, BigDecimal> Exchange = new HashMap<Currency, BigDecimal>();
	
	public void registerCurrency(Currency currency, BigDecimal diamondValue) {
		
		BigDecimal amount = new BigDecimal(InspiredNations.plugin.getConfig().getString("exchange_multiplyer"));
		
		Exchange.put(currency, amount);
	}
	
	public final BigDecimal getValue(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal valueAmount = Exchange.get(valueType);
		BigDecimal monAmount = Exchange.get(monType);

		BigDecimal monSum = monAmount.add(mon);
		BigDecimal division = valueAmount.divide(monSum, mcup);
		BigDecimal output = mon.multiply(division);
		//BigDecimal output = mon.multiply(Exchange.get(valueType).divide(Exchange.get(monType).add(mon), mcdown));
		
		//Remove this when you figure it out
		if(monType == valueType) {
			System.out.println("The value of funds to be released from the exchanger: " + mon.toString());
			return mon;
		}
		
		return output;
	}
	
	public final BigDecimal exchange(BigDecimal mon, Currency monType, Currency valueType) {
		BigDecimal output = this.getValue(mon, monType, valueType);
		Exchange.put(monType, Exchange.get(monType).add(mon));
		Exchange.put(valueType, Exchange.get(valueType).subtract(output));
		return this.getValue(mon, monType, valueType);
	}
	
	@SuppressWarnings("unchecked")
	public final HashMap<Currency, BigDecimal> getExchangeMap() {
		return (HashMap<Currency, BigDecimal>) this.Exchange.clone();
	}
	
}