import React, {Component} from 'react';
import {StyleSheet, View, TextInput, Text, ListView, Image, Dimensions, TouchableHighlight} from 'react-native';
import Color from '../AsosColors';

const {width, height} = Dimensions.get('window');


const ds = new ListView.DataSource({rowHasChanged: (r1, r2) => r1 !== r2});

class Search extends Component {

  

    render() {
        return (
            <Text>ntd</Text>
        );
    }
}

export default Search;