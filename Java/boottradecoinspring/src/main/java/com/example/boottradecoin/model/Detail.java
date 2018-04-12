package com.example.boottradecoin.model;

import java.math.BigDecimal;

public class Detail {
    private String symbol;
    private BigDecimal currentPrice;
    private BigDecimal priceAbove;
    private BigDecimal priceBelow;
    private BigDecimal amount;

    public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public BigDecimal getCurrentPrice() {
		return currentPrice;
	}

	public void setCurrentPrice(BigDecimal currentPrice) {
		this.currentPrice = currentPrice;
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

	@Override
    public boolean equals(Object obj) {
        return ((Detail) obj).getSymbol().equals(this.symbol);
    }

    @Override
    public int hashCode() {
        return this.symbol.hashCode();
    }

//    @Override
//    public String toString(){
//        return "{"+getId()+","+getName()+"}";
//    }

}
