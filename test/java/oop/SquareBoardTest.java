package oop;

import org.junit.jupiter.api.Test;

import java.awt.*;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SquareBoardTest {

    private Board<RenderingHints.Key, Integer> instance = new SquareBoard(2);

    @Test
    void fillBoard() {
        assertDoesNotThrow(()->instance.fillBoard(List.of(1,2,3,4)));
    }

    @Test
    void fillBoardWithException(){
        assertThrows(RuntimeException.class, ()->instance.fillBoard(List.of(1,2,3)));
    }



}