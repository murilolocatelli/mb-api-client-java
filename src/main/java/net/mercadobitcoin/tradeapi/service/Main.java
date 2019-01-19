package net.mercadobitcoin.tradeapi.service;

import net.mercadobitcoin.common.exception.MercadoBitcoinException;
import net.mercadobitcoin.tradeapi.to.AccountBalance;
import net.mercadobitcoin.tradeapi.to.Operation;
import net.mercadobitcoin.tradeapi.to.Order;
import net.mercadobitcoin.tradeapi.to.TapiOrderbook;
import net.mercadobitcoin.tradeapi.to.Ticker;

public class Main {

    public static void main(String[] args) throws MercadoBitcoinException {

        TradeApiService tradeApiService = new TradeApiService(
                "76c40dd3c1d8d0dffe80315796ca664a7afcefe1e7ebed574970e1d0a616e66a",
                "3dea59a284ae8d4abc110bb7a57ee5a7");

        ApiService apiService = new ApiService();

        // Get Account Info
//        final AccountBalance accountBalance = tradeApiService.getAccountInfo();
//
//        System.out.println(accountBalance);

        // Place Buy Order
//        final Order order = tradeApiService.placeBuyOrder(Order.CoinPair.BRLXRP, "1", "1.1");
//
//        System.out.println(order);

        // Place Sell Order
//        final Order order = tradeApiService.placeSellOrder(Order.CoinPair.BRLXRP, "58.58407065", "1.69");
//
//        System.out.println(order);

        // List Order Book
//        final TapiOrderbook tapiOrderbook = tradeApiService.listOrderBook(Order.CoinPair.BRLXRP);
//
//        System.out.println(tapiOrderbook);

        // Cancel Order
//        final Order order = new Order(Order.CoinPair.BRLBTC, 55662679L);
//
//        final Order orderReturned = tradeApiService.cancelOrder(order);
//
//        System.out.println(orderReturned);

        // Trade
        final Operation[] operations = apiService.tradeList(Order.CoinPair.BRLXRP);

        System.out.println(operations[0]);

        // Ticker
//        final Ticker ticker = apiService.ticker24h(Order.CoinPair.BRLXRP);
//
//        System.out.println(ticker.getLast());
    }

}
