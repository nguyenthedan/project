package com.example.boottradecoin.model;

import java.math.BigDecimal;

public class FormSell {
	private String pair;
    private BigDecimal priceAbove;
    private BigDecimal priceBelow;
    private BigDecimal amount;
	public String getPair() {
		return pair;
	}
	public BigDecimal getPriceAbove() {
		return priceAbove;
	}
	public void setPriceAbove(BigDecimal priceAbove) {
		this.priceAbove = priceAbove;
	}
	public BigDecimal getPriceBelow() {
		return priceBelow;
	}
	public void setPriceBelow(BigDecimal priceBelow) {
		this.priceBelow = priceBelow;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}
}
