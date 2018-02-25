function getAllCoin() {

}

function addCoin(coin){
    var database = firebase.database();
    var coinRef = database.ref('/coin');
    coinRef.push('BTC');
}