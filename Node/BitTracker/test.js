var firebase = require('firebase');
var config = {
    apiKey: "AIzaSyA99QXxZE5vAVm9Ltft6skmYxXHqMRtKTQ",
    authDomain: "node-firebase-7e2bb.firebaseapp.com",
    databaseURL: "https://node-firebase-7e2bb.firebaseio.com",
    projectId: "node-firebase-7e2bb",
    storageBucket: "node-firebase-7e2bb.appspot.com",
    messagingSenderId: "770034031219"
};
firebase.initializeApp(config);

var database = firebase.database();
var coinRef = database.ref('/Coin');
coinRef.push('BTC');