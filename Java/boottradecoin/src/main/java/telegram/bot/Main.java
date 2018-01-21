package telegram.bot;

import org.apache.log4j.Logger;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import telegram.bot.handler.BinanceHandler;
public class Main {
    private static final Logger log = Logger.getLogger(Main.class);

    public static void main(String[] args) {
    	// System.out.println("bot start");
        log.info("bot start");

        // Initialize Api Context
        ApiContextInitializer.init();

        // Instantiate Telegram Bots API
        TelegramBotsApi botsApi = new TelegramBotsApi();

        // Register our bot
        try {
            botsApi.registerBot(new BinanceHandler());
        } catch (TelegramApiException e) {
            log.info(e.getMessage());
        }
    }
}
