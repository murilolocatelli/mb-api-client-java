package net.mercadobitcoin.tradeapi.test.expection;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.service.TradeApiService;
import net.mercadobitcoin.tradeapi.test.base.AbstractBaseApiTest;
import net.mercadobitcoin.tradeapi.test.base.UserInfo;
import net.mercadobitcoin.tradeapi.to.Order;
import net.mercadobitcoin.tradeapi.to.Order.CoinPair;
import net.mercadobitcoin.tradeapi.to.OrderFilter;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

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
		OrderFilter filter = new OrderFilter(CoinPair.BRLBTC);
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
	
	/*@Test(expected = MercadoBitcoinException.class)
	public void testWithdrawalBitcoinLimitExceeded() throws MercadoBitcoinException {
		tapi.withdrawalBitcoin("", Order.BITCOIN_24H_WITHDRAWAL_LIMIT.add(BigDecimal.ONE));
	}*/
	
	@Test(expected = MercadoBitcoinException.class)
	public void testSellBtcLowValue() throws MercadoBitcoinException {
		tapi.placeSellOrder(CoinPair.BRLBTC, Order.MINIMUM_VOLUME, EXTREMELY_LOW_PRICE);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testSellBtcInsuficientFunds() throws MercadoBitcoinException {
		Order sellOrder = tapi.placeSellOrder(CoinPair.BRLBTC, BITCOIN_HUGE_VOLUME, HIGH_PRICE);
		assertNotNull(sellOrder);
	}

	@Test(expected = MercadoBitcoinException.class)
	public void testBuyBtcInsuficientFunds() throws MercadoBitcoinException {
		tapi.placeBuyOrder(CoinPair.BRLBTC, BITCOIN_HUGE_VOLUME, HIGH_PRICE);
	}

}