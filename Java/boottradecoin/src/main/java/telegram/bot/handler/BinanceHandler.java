package telegram.bot.handler;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.repeatSecondlyForever;
import static org.quartz.TriggerBuilder.newTrigger;

import java.math.BigDecimal;
import java.util.Date;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.telegram.abilitybots.api.bot.AbilityBot;
import org.telegram.abilitybots.api.objects.Ability;
import org.telegram.abilitybots.api.objects.Locality;
import org.telegram.abilitybots.api.objects.Privacy;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.binance.api.client.BinanceApiClientFactory;
import com.binance.api.client.BinanceApiRestClient;

import telegram.bot.Constant;

public class BinanceHandler extends AbilityBot {

    private static final String LOGTAG = "BinanceHandler";

    private BigDecimal pricePercent; // mặc định là 1.5%
    // private int timeInterval; // mặc định là 2p
    private Scheduler scheduler;

    // private List<String> listNotifycation;

    public BinanceHandler() {
        super(Constant.BOT_TOKEN, "nguyenthedanbot");
        this.pricePercent = new BigDecimal("1.5");
        // this.timeInterval = 120;
    }

    @Override
    public int creatorId() {
        return 881994; // Your ID here
    }

    public Ability saysHelloWorld() {
        return Ability.builder().name("hello") // Name and command (/hello)
                .info("Says hello world!") // giải thích về command đó
                .privacy(Privacy.PUBLIC) // Choose from Privacy Class (Public,
                                         // Admin, Creator)
                .locality(Locality.ALL) // Choose from Locality enum Class
                                        // (User, Group, PUBLIC)
                .input(0) // Arguments required for command (0 for ignore)
                .action(ctx -> {
                    /*
                     * ctx has the following main fields that you can utilize: -
                     * ctx.update() -> the actual Telegram update from the basic
                     * API - ctx.user() -> the user behind the update -
                     * ctx.firstArg()/secondArg()/thirdArg() -> quick accessors
                     * for message arguments (if any) - ctx.arguments() -> all
                     * arguments - ctx.chatId() -> the chat where the update has
                     * emerged NOTE that chat ID and user are fetched no matter
                     * what the update carries. If the update does not have a
                     * message, but it has a callback query, the chatId and user
                     * will be fetched from that query.
                     */
                    // Custom sender implementation
                    SendMessage sendMessage = new SendMessage(ctx.chatId(), "Hello");
                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    /**
     * Help command
     *
     * @return
     */
    public Ability helpCommand() {
        return Ability.builder().name("help").info("Help").privacy(Privacy.PUBLIC).locality(Locality.ALL).input(0)
                .action(ctx -> {
                    SendMessage sendMessage = new SendMessage(ctx.chatId(), "Help");
                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    /**
     * Setting command
     *
     * @return
     */
    public Ability settingCommand() {
        return Ability.builder().name("setting").info("Setting bot").privacy(Privacy.PUBLIC).locality(Locality.ALL)
                .input(0).action(ctx -> {
                    SendMessage sendMessage = new SendMessage(ctx.chatId(), "Setting");
                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    /**
     * Start command
     *
     * @return
     */
    public Ability startCommand() {
        return Ability.builder().name("start").info("Start việc check coin").privacy(Privacy.PUBLIC)
                .locality(Locality.ALL).input(0).action(ctx -> {
                    SendMessage sendMessage = null;
                    if (this.scheduler == null) {
                        // this.startBinaceAlertTimers(ctx.update().getMessage());
                        try {
                            this.scheduler = StdSchedulerFactory.getDefaultScheduler();
                            this.scheduler.start();

                            JobDataMap jobGetCoinDataMap = new JobDataMap();
                            // jobGetCoinDataMap.put("client", this.client);
                            // jobGetCoinDataMap.put("listTicker",
                            // this.listTicker);
                            jobGetCoinDataMap.put("sender", this.sender);
                            jobGetCoinDataMap.put("chatId", ctx.chatId());
                            JobDetail jobGetCoin = newJob(GetCoinJob.class).usingJobData(jobGetCoinDataMap)
                                    .withIdentity(new JobKey("jobGetCoin", "group1")).build();
                            Trigger triggerGetCoin = newTrigger().withIdentity("triggerGetCoin", "group1").startNow()
                                    .withSchedule(repeatSecondlyForever(120)).build();

                            this.scheduler.scheduleJob(jobGetCoin, triggerGetCoin);
                        } catch (SchedulerException e) {
                            sendMessage = new SendMessage(ctx.chatId(), "Error when start job");
                            e.printStackTrace();
                        }
                        sendMessage = new SendMessage(ctx.chatId(), "Start check coin");

                    } else {
                        sendMessage = new SendMessage(ctx.chatId(), "Bot is running");
                    }

                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    /**
     * Stop command
     *
     * @return
     */
    public Ability stopCommand() {
        return Ability.builder().name("stop").info("Stop việc check coin").privacy(Privacy.PUBLIC)
                .locality(Locality.ALL).input(0).action(ctx -> {
                    SendMessage sendMessage = new SendMessage(ctx.chatId(), "Success");
                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    /**
     * changePricePercent command
     *
     * @return
     */
    public Ability changePricePercentCommand() {
        return Ability.builder().name("changePricePercent").info("thay đổi tỉ lệ tăng của coin (%)")
                .privacy(Privacy.PUBLIC).locality(Locality.ALL).input(1).action(ctx -> {
                    this.pricePercent = new BigDecimal(ctx.firstArg());
                    SendMessage sendMessage = new SendMessage(ctx.chatId(), "DONE");
                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    /**
     * changeTimeInterval command
     *
     * @return
     */
    public Ability changeTimeIntervalCommand() {
        return Ability.builder().name("changeTimeInterval")
                .info("thay đổi khoảng thời gian giữa các lần kiểm tra giá coin, tính theo giây")
                .privacy(Privacy.PUBLIC).locality(Locality.ALL).input(1).action(ctx -> {
                    // this.timeInterval = Integer.valueOf(ctx.firstArg());
                    SendMessage sendMessage = new SendMessage(ctx.chatId(), "DONE");
                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

    public Ability checkTimeCommand() {
        return Ability.builder().name("checkTime").info("Check time server").privacy(Privacy.PUBLIC)
                .locality(Locality.ALL).input(0).action(ctx -> {
                    BinanceApiClientFactory factory = BinanceApiClientFactory.newInstance("API-KEY", "SECRET");
                    BinanceApiRestClient client = factory.newRestClient();
                    long binaceServerTime = client.getServerTime();
                    long currentServerTime = (new Date()).getTime();
                    SendMessage sendMessage = new SendMessage(ctx.chatId(),
                            String.valueOf(currentServerTime - binaceServerTime));
                    try {
                        this.sender.execute(sendMessage);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }).build();
    }

}
