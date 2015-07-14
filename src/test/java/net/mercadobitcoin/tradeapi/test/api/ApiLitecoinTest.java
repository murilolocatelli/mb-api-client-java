/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.test.api;

import static org.junit.Assert.assertNotNull;
import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.service.ApiService;
import net.mercadobitcoin.tradeapi.test.base.AbstractBaseApiTest;
import net.mercadobitcoin.tradeapi.to.Order.CoinPair;
import net.mercadobitcoin.tradeapi.to.Orderbook;
import net.mercadobitcoin.util.TimestampInterval;

import org.junit.BeforeClass;
import org.junit.Test;

public class ApiLitecoinTest extends AbstractBaseApiTest {

	private static ApiService api;
	
	@BeforeClass
	public static void init() throws MercadoBitcoinException {
		api = new ApiService();
	}
	
	@Test
	public void testLitecoinTicker() throws MercadoBitcoinException {
		api.ticker24h(CoinPair.LTC_BRL);
		api.tickerOfToday(CoinPair.LTC_BRL);
	}
	
	@Test
	public void testLitecoinOrderbooks() throws MercadoBitcoinException {
		Orderbook litecoinOrderbook = api.orderbook(CoinPair.LTC_BRL);
		assertNotNull(litecoinOrderbook);
	}

	@Test
	public void testLitecoinTrades() throws MercadoBitcoinException {
		api.tradeList(CoinPair.LTC_BRL);
	}
	
	@Test
	public void testLitecoinTradesIni() throws MercadoBitcoinException {
		api.tradeList(CoinPair.LTC_BRL, 1434136539);
	}
	
	@Test
	public void testLitecoinTradesIniFin() throws MercadoBitcoinException {
		TimestampInterval interval = new TimestampInterval(1434136539, 1434136930);
		api.tradeList(CoinPair.LTC_BRL, interval);
	}

}