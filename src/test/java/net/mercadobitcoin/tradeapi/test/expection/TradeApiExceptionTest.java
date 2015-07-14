/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.test.expection;

import static org.junit.Assert.assertNotNull;

import java.math.BigDecimal;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.service.TradeApiService;
import net.mercadobitcoin.tradeapi.test.base.AbstractBaseApiTest;
import net.mercadobitcoin.tradeapi.test.base.UserInfo;
import net.mercadobitcoin.tradeapi.to.Order;
import net.mercadobitcoin.tradeapi.to.Order.CoinPair;
import net.mercadobitcoin.tradeapi.to.OrderFilter;

import org.junit.BeforeClass;
import org.junit.Test;

public class TradeApiExceptionTest extends AbstractBaseApiTest {

	private static TradeApiService tapi;
	
	@BeforeClass
	public static void init() throws MercadoBitcoinException {
		tapi = new TradeApiService(UserInfo.MY_TAPI_CODE, UserInfo.MY_TAPI_KEY);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testNullKey() throws MercadoBitcoinException {
		tapi.getAccountInfo();
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testInvalidKey() throws MercadoBitcoinException {
		tapi.getAccountInfo();
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testNullCode() throws MercadoBitcoinException {
		tapi.getAccountInfo();
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testInvalidCode() throws MercadoBitcoinException {
		tapi.getAccountInfo();
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testListOrdersInvalidFilter() throws MercadoBitcoinException { 
		OrderFilter filter = new OrderFilter(CoinPair.BTC_BRL);
		filter.setFromId((long) -219323);
		tapi.listOrders(filter);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testListNullFilter() throws MercadoBitcoinException { 
		OrderFilter filter = null;
		tapi.listOrders(filter);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testCancelNull() throws MercadoBitcoinException {
		tapi.cancelOrder(null);
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testWithdrawalBitcoinLimitExceeded() throws MercadoBitcoinException {
		tapi.withdrawalBitcoin("", Order.BITCOIN_24H_WITHDRAWAL_LIMIT.add(BigDecimal.ONE));
	}
	
	@Test(expected = MercadoBitcoinException.class)
	public void testSellBtcLowValue() throws MercadoBitcoinException {
		tapi.createSellOrder(CoinPair.BTC_BRL, Order.MINIMUM_VOLUME, EXTREMELY_LOW_PRICE);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testSellBtcInsuficientFunds() throws MercadoBitcoinException {
		Order sellOrder = tapi.createSellOrder(CoinPair.BTC_BRL, BITCOIN_HUGE_VOLUME, HIGH_PRICE);
		assertNotNull(sellOrder);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testBuyBtcInsuficientFunds() throws MercadoBitcoinException {
		tapi.createBuyOrder(CoinPair.BTC_BRL, BITCOIN_HUGE_VOLUME, HIGH_PRICE);
	}

}