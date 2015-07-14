/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.test.expection;

import java.util.Calendar;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.service.ApiService;
import net.mercadobitcoin.tradeapi.test.base.AbstractBaseApiTest;
import net.mercadobitcoin.tradeapi.to.Order.CoinPair;
import net.mercadobitcoin.util.TimestampInterval;

import org.junit.BeforeClass;
import org.junit.Test;

public class ApiExceptionFlowTest extends AbstractBaseApiTest {

	private static ApiService api;
	
	@BeforeClass
	public static void init() throws MercadoBitcoinException {
		api = new ApiService();
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testTradesIniInvalid() throws MercadoBitcoinException {
		api.tradeList(CoinPair.BTC_BRL, null);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testTradesIniFinInvalid1() throws MercadoBitcoinException {
		TimestampInterval interval = new TimestampInterval(Calendar.getInstance().getTime(), null);
		api.tradeList(CoinPair.BTC_BRL, interval);
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testTradesIniFinInvalid2() throws MercadoBitcoinException {
		Calendar toDate = Calendar.getInstance();
		toDate.add(Calendar.YEAR, -1);
		TimestampInterval interval = new TimestampInterval(Calendar.getInstance().getTime(), toDate.getTime());
		api.tradeList(CoinPair.BTC_BRL, interval);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testLitecoinTradesIniInvalid() throws MercadoBitcoinException {
		api.tradeList(CoinPair.LTC_BRL, -1 * TIMESTAMP_TEST_VALUE);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testLitecoinTradesFinInvalid() throws MercadoBitcoinException {
		TimestampInterval interval = new TimestampInterval(TIMESTAMP_TEST_VALUE, -1 * TIMESTAMP_TEST_VALUE);
		api.tradeList(CoinPair.LTC_BRL, interval);
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testLitecoinTradesIniFinInvalid1() throws MercadoBitcoinException {
		TimestampInterval interval = new TimestampInterval(-1 * TIMESTAMP_TEST_VALUE, -1 * TIMESTAMP_TEST_VALUE);
		api.tradeList(CoinPair.LTC_BRL, interval);
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testLitecoinTradesIniFinInvalid2() throws MercadoBitcoinException {
		TimestampInterval interval = new TimestampInterval(TIMESTAMP_TEST_VALUE + 1, TIMESTAMP_TEST_VALUE);
		api.tradeList(CoinPair.LTC_BRL, interval);
	}

}