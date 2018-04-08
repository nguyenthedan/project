package com.example.boottradecoin.model;

import java.math.BigDecimal;

public class FormSell {
	private String pair;
    private BigDecimal sellPriceAbove;
    private BigDecimal sellPriceBelow;
    private BigDecimal amount;
	public String getPair() {
		return pair;
	}
	public void setPair(String pair) {
		this.pair = pair;
	}
	public BigDecimal getSellPriceAbove() {
		return sellPriceAbove;
	}
	public void setSellPriceAbove(BigDecimal sellPriceAbove) {
		this.sellPriceAbove = sellPriceAbove;
	}
	public BigDecimal getSellPriceBelow() {
		return sellPriceBelow;
	}
	public void setSellPriceBelow(BigDecimal sellPriceBelow) {
		this.sellPriceBelow = sellPriceBelow;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
}
