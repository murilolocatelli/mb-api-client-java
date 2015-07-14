/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.test.tapi;


import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.util.List;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.service.TradeApiService;
import net.mercadobitcoin.tradeapi.test.base.AbstractBaseApiTest;
import net.mercadobitcoin.tradeapi.test.base.UserInfo;
import net.mercadobitcoin.tradeapi.to.Order;
import net.mercadobitcoin.tradeapi.to.Order.CoinPair;
import net.mercadobitcoin.tradeapi.to.Order.OrderStatus;
import net.mercadobitcoin.tradeapi.to.Order.OrderType;
import net.mercadobitcoin.tradeapi.to.OrderFilter;
import net.mercadobitcoin.tradeapi.to.Withdrawal;

import org.junit.BeforeClass;
import org.junit.Test;

public class TradeApiBitcoinTest extends AbstractBaseApiTest {

	private static TradeApiService tapi;
	
	@BeforeClass
	public static void init() throws MercadoBitcoinException {
		tapi = new TradeApiService(UserInfo.MY_TAPI_CODE, UserInfo.MY_TAPI_KEY);
	}
	
	@Test
	public void testListAllOrders() throws MercadoBitcoinException {
		OrderFilter filter = new OrderFilter(CoinPair.BTC_BRL);
		
		List<Order> orderList = tapi.listOrders(filter);
		assertNotNull(orderList);
	}

	@Test
	public void testListBuyOrders() throws MercadoBitcoinException { 
		OrderFilter filter = new OrderFilter(CoinPair.BTC_BRL);
		filter.setType(OrderType.BUY);
		tapi.listOrders(filter);
	}
	
	@Test
	public void testListOpenOrders() throws MercadoBitcoinException { 
		OrderFilter filter = new OrderFilter(CoinPair.BTC_BRL);
		filter.setStatus(OrderStatus.ACTIVE);
		tapi.listOrders(filter);
	}

	@Test
	public void testListCompletedOrders() throws MercadoBitcoinException { 
		OrderFilter filter = new OrderFilter(CoinPair.BTC_BRL);
		filter.setStatus(OrderStatus.COMPLETED);
		tapi.listOrders(filter);
	}

	@Test
	public void testListOrdersByTid() throws MercadoBitcoinException {
		Order order = tapi.createBuyOrder(CoinPair.BTC_BRL, Order.MINIMUM_VOLUME, LOW_PRICE);
		assertNotNull(order);

		OrderFilter filter = new OrderFilter(CoinPair.BTC_BRL);
		filter.setStatus(OrderStatus.ACTIVE);
		List<Order> orderList = tapi.listOrders(filter);
		assertNotNull(orderList);
		assertTrue(orderList.size() > 0);
		
		filter.setFromId(orderList.get(0).getOrderId());
		orderList = tapi.listOrders(filter);
		assertNotNull(orderList);
	}
	
	@Test
	public void testBuyBtc() throws MercadoBitcoinException {
		Order order = tapi.createBuyOrder(CoinPair.BTC_BRL, Order.MINIMUM_VOLUME, LOW_PRICE);
		assertNotNull(order);
	}

	@Test
	public void testCancelOrder() throws MercadoBitcoinException {
		Order order = tapi.createBuyOrder(CoinPair.BTC_BRL, Order.MINIMUM_VOLUME, LOW_PRICE);
		assertNotNull(order);
		Order cancelledOrder = tapi.cancelOrder(order);
		assertNotNull(cancelledOrder);
		assertEquals(cancelledOrder.getStatus(), OrderStatus.CANCELED.getValue());
	}
	
	@Test
	public void testBuyBitcoin() throws MercadoBitcoinException {
		Order buyOrder = tapi.createBuyOrder(CoinPair.BTC_BRL, Order.MINIMUM_VOLUME, LOW_PRICE);
		assertNotNull(buyOrder);
	}

	@Test
	public void testSellBitcoin() throws MercadoBitcoinException {
		Order sellOrder = tapi.createSellOrder(CoinPair.BTC_BRL, Order.MINIMUM_VOLUME, LOW_PRICE);
		assertNotNull(sellOrder);
	}
	
	@Test
	public void testWithdrawalBitcoin() throws MercadoBitcoinException {
		Withdrawal withdrawal = tapi.withdrawalBitcoin(UserInfo.MY_TRUSTED_BITCOIN_WITHDRAWAL_ADDRESS,
						new BigDecimal(Order.MINIMUM_VOLUME));
		assertNotNull(withdrawal);
	}

}