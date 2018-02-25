var listCoin = require('./ListCoin.json');
console.log(listCoin.list[0]);
const binance = require('node-binance-api');
const TelegramBot = require('node-telegram-bot-api');

const token = '506454250:AAHGn4R57z5hSa50-fCkYwvD3s1_D4B0lxY';

const bot = new TelegramBot(token, { polling: true });

var chatId;

var listTracking = [];

binance.options({
  APIKEY: '<key>',
  APISECRET: '<secret>',
  useServerTime: true, // If you get timestamp errors, synchronize to server time at startup
  test: true // If you want to use sandbox mode where orders are simulated
});

binance.prices((error, ticker) => {
  console.log("prices()", ticker);
  
  Object.keys(ticker).forEach(function(key) {
    if(key.includes("BTC")){
      var item = {};
      console.log(key);
      console.log(ticker[key]);
      item.ticker = key;
      item.priceStart = ticker[key];
      listTracking.push();
    }
    
  });
});

bot.onText(/\/start/, (msg, match) => {
  chatId = msg.chat.id;

  bot.sendMessage(chatId, "start");
});

setInterval(function () {
  //listCoinMaxChange();
  if (typeof chatId != 'undefined') {
    binance.prices((error, ticker) => {
      console.log("prices()", ticker);
      console.log("Price of BTC: ", ticker.BTCUSDT);
      
    });

    bot.sendMessage(chatId, "test");
  }
}, 5 * 1000);

function listCoinMaxChange() {
  for (var i = 0; i < listCoin.list.length; i++) {
    binance.candlesticks(listCoin.list[i], "15m", (error, ticks, symbol) => {
      let last_tick = ticks[ticks.length - 1];
      let [time, open, high, low, close, volume, closeTime, assetVolume, trades, buyBaseVolume, buyAssetVolume, ignored] = last_tick;
      var change = (high - open) / open * 100;
      if (change > 1) {
        console.log(symbol + " change " + change);
      }
    }, { limit: 1 });

  }
}