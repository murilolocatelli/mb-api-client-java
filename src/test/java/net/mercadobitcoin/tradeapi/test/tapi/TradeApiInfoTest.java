/**
 * under the MIT License (MIT)
 * Copyright (c) 2015 Mercado Bitcoin Servicos Digitais Ltda.
 * @see more details in /LICENSE.txt
 */

package net.mercadobitcoin.tradeapi.test.tapi;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.service.TradeApiService;
import net.mercadobitcoin.tradeapi.test.base.AbstractBaseApiTest;
import net.mercadobitcoin.tradeapi.test.base.UserInfo;
import net.mercadobitcoin.tradeapi.to.AccountBalance;
import org.junit.BeforeClass;
import org.junit.Test;

public class TradeApiInfoTest extends AbstractBaseApiTest {

	private static TradeApiService tapi;
	
	@BeforeClass
	public static void init() throws MercadoBitcoinException {
		tapi = new TradeApiService(UserInfo.MY_TAPI_CODE, UserInfo.MY_TAPI_KEY);
	}

	@Test
	public void testGetInfo() throws MercadoBitcoinException {
		AccountBalance info = tapi.getAccountInfo();
//		assertNotNull(info.getFunds());
//		assertNotNull(info.getFunds().getBrl());
//		assertNotNull(info.getFunds().getBtc());
//		assertNotNull(info.getFunds().getLtc());
//		assertNotNull(info.getFunds().getBrlWithOpenOrders());
//		assertNotNull(info.getFunds().getBtcWithOpenOrders());
//		assertNotNull(info.getFunds().getLtcWithOpenOrders());
	}

}