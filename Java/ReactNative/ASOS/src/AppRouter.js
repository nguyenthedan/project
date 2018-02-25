import React, {Component} from 'react';
import {Image, Text} from 'react-native';

import Search from './containers/Search';

import Icons from './TabbarIcons'
import Color from './AsosColors'
import { Router, Scene } from 'react-native-router-flux';




export default class AppRouter extends Component {

 tabIcon(imageURI){
  return (
    <Image style={{width:40, height:40, resizeMode:'center', tintColor:Color.lightText}} source={{uri:imageURI}} />
  );
}
    render() {
        return (
            <Router>
                <Scene key="root"> 
                    <Scene key="tabbar" tabs={true}  default="search_tab" tabBarStyle={{backgroundColor: Color.lightBackground, borderTopColor:Color.extraLightBackground, borderTopWidth:1}}>
                        {/*Tab and it's scenes */}
                        
                        
                        <Scene key = "search_tab" title="" intial={true} icon={() => this.tabIcon(Icons.searchIcon)} hideNavBar={true}>
                            <Scene key="search" component={Search} title="Search" />
                        </Scene>


                    </Scene>        
                </Scene>
            </Router>
        );
    }    
}