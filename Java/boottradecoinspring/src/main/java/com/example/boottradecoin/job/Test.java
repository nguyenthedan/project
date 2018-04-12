package com.example.boottradecoin.job;

import java.math.BigDecimal;

import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceOrder;
import com.webcerebrium.binance.datatype.BinanceOrderPlacement;
import com.webcerebrium.binance.datatype.BinanceOrderSide;
import com.webcerebrium.binance.datatype.BinanceOrderType;
import com.webcerebrium.binance.datatype.BinanceSymbol;

public class Test {
	public static void main(String[] args) {

		BinanceApi binanceApi = new BinanceApi();
		
		try {
			System.out.println(binanceApi.time().get("serverTime").getAsString());
			
			BinanceSymbol symbol = new BinanceSymbol("TRXBTC");
			// hủy lệnh bán cắt lỗ
			BinanceOrder order = binanceApi.allOrders(symbol).get(0);
			binanceApi.deleteOrder(order);

			// đặt lệnh bán lãi
			BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.SELL);
			placement.setType(BinanceOrderType.LIMIT);
			placement.setPrice(BigDecimal.ONE);
			placement.setQuantity(BigDecimal.ONE);
			BinanceOrder result = binanceApi.getOrderById(symbol, binanceApi.createOrder(placement).get("orderId").getAsLong());
			System.out.println(result);
		} catch (BinanceApiException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
