package com.example.boottradecoin.job;

import java.math.BigDecimal;
import java.util.Map;
import java.util.TimerTask;

import com.example.boottradecoin.model.Detail;
import com.webcerebrium.binance.api.BinanceApi;
import com.webcerebrium.binance.api.BinanceApiException;
import com.webcerebrium.binance.datatype.BinanceOrder;
import com.webcerebrium.binance.datatype.BinanceOrderPlacement;
import com.webcerebrium.binance.datatype.BinanceOrderSide;
import com.webcerebrium.binance.datatype.BinanceOrderType;
import com.webcerebrium.binance.datatype.BinanceSymbol;

public class CheckPrice extends TimerTask {

	BinanceApi binanceApi = new BinanceApi();
    @Override
    public void run() {
    	Map<String, BigDecimal> priceMap = null;
		try {
			priceMap = binanceApi.pricesMap();
		} catch (BinanceApiException e) {
			e.printStackTrace();
		}
		
		for(Detail coin : CheckPriceManager.listCoinCheck) {
			BigDecimal currentPrice = priceMap.get(coin.getSymbol());
			if(currentPrice.compareTo(coin.getPriceAbove()) > 0) {
				// bán có lãi
				try {
					BinanceSymbol symbol = new BinanceSymbol(coin.getSymbol());
					// hủy lệnh bán cắt lỗ
					BinanceOrder order = binanceApi.allOrders(symbol).get(0);
					binanceApi.deleteOrder(order);
					
					// đặt lệnh bán lãi
					BinanceOrderPlacement placement = new BinanceOrderPlacement(symbol, BinanceOrderSide.SELL);
					placement.setType(BinanceOrderType.LIMIT);
					placement.setPrice(coin.getPriceAbove());
					placement.setQuantity(coin.getAmount());
					binanceApi.createOrder(placement);
				} catch (BinanceApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			} 
//			else if(currentPrice.compareTo(coin.getPriceBelow()) < 0) {
//				// bán cắt lỗ
//				
//			}
		}
    	
//    	CheckPriceManager.listCoinCheck.forEach(coin -> {
//    			priceMap.get(coin.getSymbol());
//    	});
    }

}
