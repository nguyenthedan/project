import React from 'react';
import { View, ListView, StyleSheet, Text, Image } from 'react-native';
import Row from './Row';
import data from './demoData';

class ListViewDemo extends React.Component {
  constructor(props) {
    super(props);
    const ds = new ListView.DataSource({ rowHasChanged: (r1, r2) => r1 !== r2 });
    this.state = {
      dataSource: ds.cloneWithRows(data),
    };
  }

  taoHang(props) {
    return (
      <View style={styles.container}>
        <Image source={{ uri: props.picture.large }} style={styles.photo} />
        <Text style={styles.text}>
          {`${props.name.first} ${props.name.last}`}
        </Text>
      </View>
    );
  }

  render() {
    return (
      <ListView
        dataSource={this.state.dataSource}
        renderRow={this.taoHang}
      />
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 12,
    flexDirection: 'row',
    alignItems: 'center',
  },
  text: {
    marginLeft: 12,
    fontSize: 16,
  },
  photo: {
    height: 40,
    width: 40,
    borderRadius: 20,
  },
});

export default ListViewDemo;