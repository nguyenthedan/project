package telegram.bot.handler;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.telegram.abilitybots.api.sender.MessageSender;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;
import com.binance.api.client.domain.market.Candlestick;
import com.binance.api.client.domain.market.CandlestickInterval;
import com.binance.api.client.domain.market.TickerPrice;

public class GetCoinJob implements Job {

    private BinanceApiClientFactory factory;
    private BinanceApiRestClient client;
    private List<String> listTicker;
    private int count;

    public GetCoinJob() {
        this.factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
        this.client = this.factory.newRestClient();
    }

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        // this.client = (BinanceApiRestClient)
        // context.getJobDetail().getJobDataMap().get("client");
        // this.listTicker = (List<String>)
        // context.getJobDetail().getJobDataMap().get("listTicker");
        // this.listTicker.clear();
        this.listTicker = new ArrayList<>();

        if (this.count == 0) {
            this.getCoin();
            this.count++;
        }
        if (this.count == 10) {
            this.count = 0;
        }

        System.out.println("Start check price");
        StringBuilder coinBuilder = new StringBuilder();
        coinBuilder.append("Coin: ");
        this.listTicker.forEach(ticker -> {
            // System.out.println(ticker);
            List<Candlestick> candlesticks = this.client.getCandlestickBars(ticker, CandlestickInterval.ONE_MINUTE,
                    new Integer(10), null, null);
            BigDecimal changPer6 = BinanceUtil.perChange(candlesticks, 6);
            // BigDecimal changPer8 =
            // BinanceUtil.perChange(candlesticks, 8);
            if (changPer6.compareTo(new BigDecimal("1.5")) > 0) {
                // BinanceCoin tempCoin = new BinanceCoin();
                // tempCoin.setCoinName(ticker.getCoinName());
                // tempCoin.setOpenPrice(candlesticks.getOpen());
                // tempCoin.setClosePrice(candlesticks.getClose());
                // tempCoin.setPerChange(perChange.toString());
                // tempCoin.setTime(candlesticks.getOpenTime());
                // // System.out.println(tempCoin);
                // tempListTicker.add(tempCoin);
                coinBuilder.append(ticker + " " + changPer6);
                coinBuilder.append("\n");
                // System.out.println(ticker + " " + changPer6);
                // System.out.println(candlesticks);
            }
            // if (changPer8.compareTo(new BigDecimal(2)) > 0) {
            // // System.out.println("Change 8: " + candlesticks);
            // }
        });

        SendMessage sendMessage = new SendMessage((Long) context.getJobDetail().getJobDataMap().get("chatId"),
                coinBuilder.toString());
        MessageSender sender = (MessageSender) context.getJobDetail().getJobDataMap().get("sender");

        try {
            sender.execute(sendMessage);
        } catch (TelegramApiException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    private void getCoin() {
        List<TickerPrice> listPrices = this.client.getAllPrices();
        listPrices.forEach(ticker -> {
            Candlestick candlesticks = this.client
                    .getCandlestickBars(ticker.getSymbol(), CandlestickInterval.TWO_HOURLY, new Integer(1), null, null)
                    .get(0);
            BigDecimal change = BinanceUtil.perChange(candlesticks);
            if (change.compareTo(BigDecimal.ZERO) > 0 && ticker.getSymbol().contains("BTC")) {
                this.listTicker.add(ticker.getSymbol());
            }
        });
    }

}
