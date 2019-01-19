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
import net.mercadobitcoin.tradeapi.to.Operation;
import net.mercadobitcoin.tradeapi.to.Order.CoinPair;
import net.mercadobitcoin.tradeapi.to.Ticker;
import net.mercadobitcoin.util.TimestampInterval;

import org.junit.BeforeClass;
import org.junit.Test;

public class ApiBitcoinTest extends AbstractBaseApiTest {

	private static ApiService api;
	
	@BeforeClass
	public static void init() throws MercadoBitcoinException {
		api = new ApiService();
	}
	
	@Test
	public void testTicker() throws MercadoBitcoinException {
		Ticker tickerOfTheDay = api.tickerOfToday(CoinPair.BRLBTC);
		assertNotNull(tickerOfTheDay.getBuy());
		assertNotNull(tickerOfTheDay.getDate());
		assertNotNull(tickerOfTheDay.getHigh());
		assertNotNull(tickerOfTheDay.getLast());
		assertNotNull(tickerOfTheDay.getLow());
		assertNotNull(tickerOfTheDay.getSell());
		assertNotNull(tickerOfTheDay.getVol());

		Ticker ticker = api.ticker24h(CoinPair.BRLBTC);
		assertNotNull(ticker);
	}
	
	@Test
	public void testOrderbook() throws MercadoBitcoinException {
		assertNotNull(api.orderbook(CoinPair.BRLBTC));
	}

	@Test
	public void testTrades() throws MercadoBitcoinException {
		Operation[] operationList = api.tradeList(CoinPair.BRLBTC);
		assertNotNull(operationList);
	}
	
	@Test
	public void tradesListByTid() throws MercadoBitcoinException {
		Operation[] operationList = api.tradeList(CoinPair.BRLBTC);
		assertNotNull(operationList);
		Operation lastOperation = operationList[operationList.length - 1];

		Operation[] operationListById = api.tradeList(CoinPair.BRLBTC, lastOperation.getTid() - 1);
		assertNotNull(operationListById);
	}
	
	@Test
	public void testTradesIni() throws MercadoBitcoinException {
		Operation[] operationList = api.tradeList(CoinPair.BRLBTC);
		assertNotNull(operationList);
		Operation lastOperation = operationList[operationList.length - 1];

		TimestampInterval interval = new TimestampInterval(lastOperation.getDate());
		Operation[] operationListByInitialTimestamp = api.tradeList(CoinPair.BRLBTC, interval);
		assertNotNull(operationListByInitialTimestamp);
	}
	
	@Test
	public void testTradesIniFin() throws MercadoBitcoinException {
		Operation[] operationList = api.tradeList(CoinPair.BRLBTC);
		assertNotNull(operationList);
		Operation tenBehindOperation = operationList[operationList.length - 10];
		Operation lastOperation = operationList[operationList.length - 1];

		TimestampInterval interval = new TimestampInterval(tenBehindOperation.getDate(), lastOperation.getDate());
		Operation[] tradesByIntervalTimestamp = api.tradeList(CoinPair.BRLBTC, interval);
		assertNotNull(tradesByIntervalTimestamp);
	}

}