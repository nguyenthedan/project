import React, { Component } from 'react';
import { Image, Text } from 'react-native';

import Icons from './TabbarIcons'
import Color from './AsosColors'
import { Router, Scene } from 'react-native-router-flux';

import Search from './containers/Search';
import Profile from './containers/Profile';
import Favourites from './containers/Favourites';
import Homepage from './containers/Homepage';


export default class AppRouter extends Component {

    tabIcon(imageURI) {
        return (
            <Image style={{ width: 40, height: 40, resizeMode: 'center', tintColor: Color.lightText }} source={{ uri: imageURI }} />
        );
    }
    render() {
        return (
            <Router>
                <Scene key="root">
                    <Scene key="tabbar" tabs={true} default="search_tab" tabBarStyle={{ backgroundColor: Color.lightBackground, borderTopColor: Color.extraLightBackground, borderTopWidth: 1 }}>
                        <Scene key="homepage_tab" icon={() => this.tabIcon(Icons.homeIcon)} >
                            <Scene key="homepage" component={Homepage} title="Homepage" hideNavBar={true} />


                        </Scene>

                        <Scene key="search_tab" title="" intial={true} icon={() => this.tabIcon(Icons.searchIcon)} hideNavBar={true}>
                            <Scene key="search" component={Search} title="Search" />
                        </Scene>

                        <Scene key="bag_tab" title="" icon={() => this.tabIcon(Icons.bagIcon)} hideNavBar={true}>
                            <Scene key="bag" component={Profile} title="Basket" />
                        </Scene>

                        <Scene key="favourites_tab" title="" icon={() => this.tabIcon(Icons.favorites)} hideNavBar={true}>
                            <Scene key="favourites" component={Favourites} title="Favourites" />
                        </Scene>

                        <Scene key="profile_tab" title="" icon={() => this.tabIcon(Icons.userIcon)} hideNavBar={true}>
                            <Scene key="profile" component={Profile} title="Profile" />
                        </Scene>
                    </Scene>
                </Scene>
            </Router>
        );
    }
}