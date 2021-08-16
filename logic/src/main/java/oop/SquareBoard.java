package oop;

import java.util.*;
import java.util.stream.Collectors;


public class SquareBoard<V> extends Board<Key, V> {
    private int size;

    public SquareBoard(int size) {
        super(size, size);
        this.size = size;
    }

    @Override
    public void fillBoard(List<V> list) throws RuntimeException {
        if (list.size() > size * size || list.size() < size *size){
            throw new RuntimeException();
        }
        for (int c = 0; c < list.size(); c++) {
            int j = c % weigh;
            int i = c / height;
            Key key = new Key(i, j);
            board.put(key, list.get(c));
        }
    }

    @Override
    //Возвращает все ключи, значение которых null
    public List<Key> availableSpace() {
        return board.entrySet().stream().filter(entry -> entry.getValue() == null).map(Map.Entry::getKey).collect(Collectors.toList());
//        ArrayList listValuesNull = new ArrayList();
//        for (Key key : board.keySet()) {
//            if (board.get(key) == null) {
//                listValuesNull.add(key);
//            }
//        }
//        return listValuesNull;
    }

    @Override
    public void addItem(Key key, V value) {
        board.put(key, value);
    }


    @Override
    public Key getKey(int i, int j) {
        for (Key key : board.keySet()) {
            if (key.getI() == i && key.getJ() == j) {
                return key;
            }
        }
        return null;
    }

    @Override
    public V getValue(Key key) {
        return board.get(key);
    }

    @Override
    public List<Key> getColumn(int j) {
//        ArrayList<Key> listReturn = new ArrayList<>();
//        ArrayList<Key> list = new ArrayList<>(board.keySet());
//        list.sort(Comparator.comparingInt(Key::getJ));
//        for (Key key : list) {
//            if (key.getJ() == j) {
//                listReturn.add(key);
//            }
//        }
//        return listReturn;
        List<Key> column = new ArrayList<>();
        for (int i = 0; i < height; i++) {
            column.add(getKey(i, j));
        }
        return column;
    }

    @Override
    public List<Key> getRow(int i) {
//        ArrayList<Key> listReturn = new ArrayList<>();
//        ArrayList<Key> list = new ArrayList<>(board.keySet());
//        list.sort(Comparator.comparingInt(Key::getI));
//        for (Key key : list) {
//            if (key.getI() == i) {
//                listReturn.add(key);
//            }
//        }
//        return listReturn;

        List<Key> row = new ArrayList<>();
        for (int j = 0; j < weigh; j++) {
            row.add(getKey(i, j));
        }
        return row;
    }

    @Override
    public boolean hasValue(V value) {
        for (Key key : board.keySet()) {
            if (board.get(key) == value) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<V> getValues(List<Key> keys) {
        List<V> list = new ArrayList<>();
        for (Key key : keys) {
            list.add(board.get(key));
        }
        return list;
    }
}