package com.example.boottradecoin.job;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.queue.CircularFifoQueue;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.TickerPrice;

public class Test {
	public static void main(String[] args) {

		BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
		BinanceApiRestClient client = factory.newRestClient();
		List<TickerPrice> allPrices = client.getAllPrices();
		Map<String, BigDecimal> mapPrices = new HashMap<>();
		for (TickerPrice ticker : allPrices) {
			mapPrices.put(ticker.getSymbol(), new BigDecimal(ticker.getPrice()));
		}
	}
}
