package oop;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class Board<K,V> {

    int weigh;
    int height;
    Map<K,V> board = new HashMap<>();

    public Board(int weigh, int height) {
        this.weigh = weigh;
        this.height = height;
    }

    abstract public void fillBoard(List<V> list);

    abstract public List<K> availableSpace();

    abstract public void addItem(K key, V value);

    abstract public K getKey(int i, int j);

    abstract public V getValue(K key);

    abstract public List<K> getColumn(int j);

    abstract public List<K> getRow(int i);

    abstract public boolean hasValue(V value);

    abstract public List<V> getValues(List<K> keys);

}