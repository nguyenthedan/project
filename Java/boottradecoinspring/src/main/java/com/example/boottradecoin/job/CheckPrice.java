package com.example.boottradecoin.job;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TimerTask;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.TimeInForce;
import com.binance.api.client.domain.account.NewOrder;
import com.binance.api.client.domain.account.NewOrderResponse;
import com.binance.api.client.domain.account.Order;
import com.binance.api.client.domain.account.request.CancelOrderRequest;
import com.binance.api.client.domain.account.request.OrderRequest;
import com.binance.api.client.domain.market.TickerPrice;
import com.binance.api.client.exception.BinanceApiException;
import com.example.boottradecoin.model.Detail;

public class CheckPrice extends TimerTask {

	BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
	BinanceApiRestClient client = factory.newRestClient();

	@Override
	public void run() {
		List<TickerPrice> allPrices = client.getAllPrices();
		Map<String, BigDecimal> mapPrices = new HashMap<>();
		for (TickerPrice ticker : allPrices) {
			mapPrices.put(ticker.getSymbol(), new BigDecimal(ticker.getPrice()));
		}

		for (Detail coin : CheckPriceManager.listCoinCheck) {
			BigDecimal currentPrice = mapPrices.get(coin.getSymbol());
			if (currentPrice.compareTo(coin.getPriceAbove()) > 0) {
				// bán có lãi
				try {
					// hủy lệnh bán cắt lỗ
					List<Order> openOrders = client.getOpenOrders(new OrderRequest(coin.getSymbol()));
					client.cancelOrder(new CancelOrderRequest(coin.getSymbol(), openOrders.get(0).getOrderId()));

					// đặt lệnh bán lãi
					NewOrderResponse newOrderResponse = client.newOrder(NewOrder.limitBuy(coin.getSymbol(), TimeInForce.GTC,
							String.valueOf(coin.getAmount()), String.valueOf(coin.getPriceAbove())));
					System.out.println(newOrderResponse);
				} catch (BinanceApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			// else if(currentPrice.compareTo(coin.getPriceBelow()) < 0) {
			// // bán cắt lỗ
			//
			// }
		}

		// CheckPriceManager.listCoinCheck.forEach(coin -> {
		// priceMap.get(coin.getSymbol());
		// });
	}

}
