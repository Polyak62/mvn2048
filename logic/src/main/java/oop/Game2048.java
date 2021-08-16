package oop;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Game2048 implements Game {
    public static final int GAME_SIZE = 4;
    Board<Key, Integer> board = new SquareBoard<>(GAME_SIZE);
    GameHelper helper = new GameHelper();
    Random random = new Random();


    @Override
    public void init() {
        List<Integer> listBoard = new ArrayList<>();
        for (int i = 0; i < board.weigh * board.height; i++) {
            listBoard.add(null);
        }
        board.fillBoard(listBoard);
        try {
            addItem();
            addItem();
        } catch (NotEnoughSpace notEnoughSpace) {
            notEnoughSpace.printStackTrace();
        }

    }


    @Override
    public boolean canMove() {
        if (!board.availableSpace().isEmpty()) {
            return true;
        } else {
            for (int a = 0; a < board.weigh; a++) {
                for (int b = 0; b < board.height - 1; b++) {
                    int temp = board.getValue(new Key(a, b));
                    int next = board.getValue(new Key(a + 1, b));
                    if (temp == next) {
                        return true;
                    }
                }
            }

        }
        return false;
    }

    @Override
    public boolean move(Direction direction) {
        boolean result = false;
        if (canMove()) {
            int count = 0;
            List<Key> listKey = new ArrayList<>();

            for (int a = 0; a < GAME_SIZE; a++) {
                switch (direction) {
                    case LEFT:
                        listKey = board.getRow(a);
                        result = true;
                        break;

                    case RIGHT:
                        listKey = board.getRow(a);
                        Collections.reverse(listKey);
                        result = true;
                        break;

                    case UP:
                        listKey = board.getColumn(a);
                        result = true;
                        break;

                    case DOWN:
                        listKey = board.getColumn(a);
                        Collections.reverse(listKey);
                        result = true;
                        break;
                }

                List<Integer> listValues = new ArrayList();
                for (Key key : listKey) {
                    listValues.add(board.getValue(key));
                }

                List<Integer>  listMerge = helper.moveAndMergeEqual(listValues);
                if (!listMerge.equals(listValues)) {
                    for (int i = 0; i < GAME_SIZE; i++) {
                        board.addItem(listKey.get(i), listMerge.get(i));
                    }
                    count++;
                }
            }
            if (count > 0) {
                try {
                    addItem();
                } catch (NotEnoughSpace notEnoughSpace) {
                    notEnoughSpace.printStackTrace();
                }
            }
        }
        return result;
    }


    @Override
    public void addItem() throws NotEnoughSpace {

        if (board.availableSpace().isEmpty()) {
            throw new NotEnoughSpace();
        }
        List<Key> listNullValues = board.availableSpace();
//        int rand = (int) (Math.random() * (listNullValues.size() + 1)); // (0;listNullValues.size()]
//        board.addItem(listNullValues.get(rand), 2);
        Integer value = random.nextInt(9) == 9 ? 4 : 2;
        int index = listNullValues.size() == 1 ? 0 : random.nextInt(listNullValues.size() - 1);
        Key key = listNullValues.get(index);
        board.addItem(key, value);

    }

    @Override
    public Board getGameBoard() {
        return board;
    }

    @Override
    public boolean hasWin() {
        return board.hasValue(2048);
    }

}

