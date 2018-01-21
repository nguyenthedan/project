package telegram.bot.handler;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

import com.binance.api.client.domain.market.Candlestick;

public class BinanceUtil {
    public static BigDecimal perChange(List<Candlestick> candelstick, int index) {
        BigDecimal open = new BigDecimal(candelstick.get(0).getOpen());
        BigDecimal close = new BigDecimal(candelstick.get(index).getClose());
        return close.subtract(open).multiply(new BigDecimal(100)).divide(open, 6, RoundingMode.HALF_UP);
    }

    public static BigDecimal perChange(Candlestick candelstick) {
        BigDecimal open = new BigDecimal(candelstick.getOpen());
        BigDecimal close = new BigDecimal(candelstick.getClose());
        return close.subtract(open).multiply(new BigDecimal(100)).divide(open, 6, RoundingMode.HALF_UP);
    }
}
