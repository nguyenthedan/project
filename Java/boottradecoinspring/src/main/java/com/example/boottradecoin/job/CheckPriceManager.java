package com.example.boottradecoin.job;

import java.util.HashSet;
import java.util.Set;
import java.util.Timer;
import com.example.boottradecoin.model.Detail;

public class CheckPriceManager {
    public static Set<Detail> listCoinCheck = new HashSet<Detail>();

    private CheckPrice checkPriceJob;
    private Timer timer;
//    timer.scheduleAtFixedRate(task1, 0, 10 * 1000);

    public void addOrder(String symbol) {
        this.checkPriceJob = new CheckPrice();
        this.timer = new Timer();
        // TODO: setting
        this.timer.scheduleAtFixedRate(this.checkPriceJob, 0, 10 * 1000);
    }

    public void removeOrder(String symbol) {
        this.checkPriceJob = new CheckPrice();
        this.timer = new Timer();
        // TODO: setting
        this.timer.scheduleAtFixedRate(this.checkPriceJob, 0, 10 * 1000);
    }

    public void updateOrder(String symbol) {
        this.checkPriceJob = new CheckPrice();
        this.timer = new Timer();
        // TODO: setting
        this.timer.scheduleAtFixedRate(this.checkPriceJob, 0, 10 * 1000);
    }
}
