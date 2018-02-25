import React, {Component} from 'react';
import {StyleSheet, View, Text} from 'react-native';
import Color from '../AsosColors';

class Profile extends Component {
    render() {
        return (
            <View style={styles.container}>
                <Text>
                    "Profile goes here"
                </Text>
            </View>
        );
    }
}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        justifyContent: 'center',
        alignItems: 'center',
        backgroundColor: Color.orange
    }
});

export default Profile;