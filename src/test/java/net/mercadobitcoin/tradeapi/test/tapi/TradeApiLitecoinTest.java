/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.test.tapi;

import static org.junit.Assert.assertNotNull;

import java.util.List;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.service.TradeApiService;
import net.mercadobitcoin.tradeapi.test.base.AbstractBaseApiTest;
import net.mercadobitcoin.tradeapi.test.base.UserInfo;
import net.mercadobitcoin.tradeapi.to.Order;
import net.mercadobitcoin.tradeapi.to.Order.CoinPair;
import net.mercadobitcoin.tradeapi.to.Order.OrderStatus;
import net.mercadobitcoin.tradeapi.to.OrderFilter;

import org.junit.BeforeClass;
import org.junit.Test;

public class TradeApiLitecoinTest extends AbstractBaseApiTest {

	private static TradeApiService tapi;
	
	@BeforeClass
	public static void init() throws MercadoBitcoinException {
		tapi = new TradeApiService(UserInfo.MY_TAPI_CODE, UserInfo.MY_TAPI_KEY);
	}

	@Test
	public void testListOrdersAllFilters() throws MercadoBitcoinException {
		OrderFilter filter = new OrderFilter(CoinPair.BRLLTC);
		filter.setPair(CoinPair.BRLLTC);
		filter.setType(2);
		filter.setStatus(OrderStatus.CANCELED);
		filter.setFromId((long) 219323);
		filter.setEndId((long) 2195243); 
		filter.setSince((long) 1432667542);
		filter.setEnd((long) 1432178802);

		List<Order> orderList = tapi.listOrders(filter);
		assertNotNull(orderList);
	}
	
	@Test
	public void testListOrdersAllFiltersNull() throws MercadoBitcoinException {
		OrderFilter filter = new OrderFilter(CoinPair.BRLLTC);
		filter.setPair(null);
		filter.setType(null);
		filter.setStatus(null);
		filter.setFromId(null);
		filter.setEndId(null); 
		filter.setSince(null);
		filter.setEnd(null);

		List<Order> orderList = tapi.listOrders(filter);
		assertNotNull(orderList);
	}

}