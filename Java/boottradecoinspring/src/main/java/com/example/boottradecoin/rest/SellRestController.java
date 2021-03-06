package com.example.boottradecoin.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.boottradecoin.job.CheckPriceManager;
import com.example.boottradecoin.model.Detail;
import com.example.boottradecoin.model.FormSell;

@RestController
@RequestMapping("/api")
public class SellRestController {
	@RequestMapping(value = "/sell", method = RequestMethod.POST)
	public ResponseEntity<FormSell> addCoin(@RequestBody FormSell formSell) {
		Detail coin = new Detail();
		coin.setSymbol(formSell.getPair());
		coin.setPriceAbove(formSell.getPriceAbove());
		coin.setPriceBelow(formSell.getPriceBelow());
		coin.setAmount(formSell.getAmount());
		CheckPriceManager.listCoinCheck.add(coin);
		return ResponseEntity.ok(formSell);
	}
}
