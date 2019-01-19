/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.to;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import com.eclipsesource.json.JsonObject;

/**
 * Contains the balances of:
 * - <b>BRL</b> : Brazilian Real;
 * - <b>BTC</b> : Bitcoin;
 * - <b>LTC</b> : Litecoin.
 */
public class Funds implements Serializable {

	private static final long serialVersionUID = -6033277213248384326L;

	private BigDecimal brl;
	private BigDecimal btc;
	private BigDecimal ltc;
	private BigDecimal brlWithOpenOrders;
	private BigDecimal btcWithOpenOrders;
	private BigDecimal ltcWithOpenOrders;

	/**
	 * Constructor based on JSON response.
	 * 
	 * @param jsonObject Trade API JSON response
	 */
	public Funds(JsonObject jsonObject) {
		this.brl = new BigDecimal(jsonObject.get("brl").asString()).setScale(6, RoundingMode.FLOOR);
		this.btc = new BigDecimal(jsonObject.get("btc").asString()).setScale(6, RoundingMode.FLOOR);
		this.ltc = new BigDecimal(jsonObject.get("ltc").asString()).setScale(6, RoundingMode.FLOOR);
		this.brlWithOpenOrders = new BigDecimal(jsonObject.get("brl_with_open_orders").asString()).setScale(6, RoundingMode.FLOOR);
		this.btcWithOpenOrders = new BigDecimal(jsonObject.get("btc_with_open_orders").asString()).setScale(6, RoundingMode.FLOOR);
		this.ltcWithOpenOrders = new BigDecimal(jsonObject.get("ltc_with_open_orders").asString()).setScale(6, RoundingMode.FLOOR);
	}

	public BigDecimal getBrl() {
		return brl;
	}

	public BigDecimal getBtc() {
		return btc;
	}

	public BigDecimal getLtc() {
		return ltc;
	}
	
	public BigDecimal getBrlWithOpenOrders() {
		return brlWithOpenOrders;
	}
	
	public BigDecimal getBtcWithOpenOrders() {
		return btcWithOpenOrders;
	}
	
	public BigDecimal getLtcWithOpenOrders() {
		return ltcWithOpenOrders;
	}

	@Override
	public String toString() {
		return "Funds [BRL=" + brl + ", BTC=" + btc + ", LTC=" + ltc
				 		+ ", BRL_ALL=" + brlWithOpenOrders + ", BTC_ALL=" + btcWithOpenOrders
				 		+ ", LTC_ALL=" + ltcWithOpenOrders + "]";
	}

}
