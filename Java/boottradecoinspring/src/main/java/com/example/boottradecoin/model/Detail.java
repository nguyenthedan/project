package com.example.boottradecoin.model;

import java.math.BigDecimal;

public class Detail {
    private String symbol;
    private BigDecimal currentPrice;
    private BigDecimal sellPriceAbove;
    private BigDecimal sellPriceBelow;
    private BigDecimal buyPriceAbove;
    private BigDecimal buyPriceBelow;

    public String getSymbol() {
        return this.symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getCurrentPrice() {
        return this.currentPrice;
    }

    public void setCurrentPrice(BigDecimal currentPrice) {
        this.currentPrice = currentPrice;
    }

    public BigDecimal getSellPriceAbove() {
        return this.sellPriceAbove;
    }

    public void setSellPriceAbove(BigDecimal sellPriceAbove) {
        this.sellPriceAbove = sellPriceAbove;
    }

    public BigDecimal getSellPriceBelow() {
        return this.sellPriceBelow;
    }

    public void setSellPriceBelow(BigDecimal sellPriceBelow) {
        this.sellPriceBelow = sellPriceBelow;
    }

    public BigDecimal getBuyPriceAbove() {
        return this.buyPriceAbove;
    }

    public void setBuyPriceAbove(BigDecimal buyPriceAbove) {
        this.buyPriceAbove = buyPriceAbove;
    }

    public BigDecimal getBuyPriceBelow() {
        return this.buyPriceBelow;
    }

    public void setBuyPriceBelow(BigDecimal buyPriceBelow) {
        this.buyPriceBelow = buyPriceBelow;
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
