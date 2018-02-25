import React, { Component } from 'react';
import { Text, View, StyleSheet } from 'react-native';
import { TabNavigator } from 'react-navigation';
import { Constants } from 'expo';
import Home from './screens/HomeScreen';
import Setting from './screens/SettingScreen';

// or any pure javascript modules available in npm
import { Card } from 'react-native-elements'; // 0.18.5

export default TabNavigator({
  home: { screen: Home },
  setting: { screen: Setting }
});

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    paddingTop: Constants.statusBarHeight,
    backgroundColor: '#ecf0f1',
  },
  paragraph: {
    margin: 24,
    fontSize: 18,
    fontWeight: 'bold',
    textAlign: 'center',
    color: '#34495e',
  },
});
