package oop;

import java.util.ArrayList;
import java.util.List;
public class GameHelper {
    public List<Integer> moveAndMergeEqual(List<Integer> list) {

        ArrayList<Integer> listNotNull = new ArrayList<>();

        for (Integer integer : list) {
            if (integer != null) {
                listNotNull.add(integer);
            }
        }

        for (int i = 1; i < listNotNull.size(); i++) {
            if (listNotNull.get(i).equals(listNotNull.get(i - 1))) {
                int temp = 0;
                temp = listNotNull.get(i - 1) * 2;
                listNotNull.set(i - 1, temp);
                listNotNull.set(i, 0);
            }
        }

        listNotNull.removeIf(nextValue -> nextValue.equals(0));

        int countNull = list.size() - listNotNull.size();
        for (int i = 0; i < countNull; i++) {
            listNotNull.add(null);
        }
        return listNotNull;
    }
}