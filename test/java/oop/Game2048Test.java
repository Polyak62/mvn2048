package oop;


import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Game2048Test {

    @Spy
    private GameHelper helper;

    @Mock
    private SquareBoard<Integer> board;

    @InjectMocks
    private Game2048 instance;



    @Test
    void init(){
        doNothing().when(board).fillBoard(anyList());
     // when(board.availableSpace().thenReturn(List.of(new Key(3,1)));
        doReturn(List.of(new Key(1,1))).when(board).availableSpace();
        instance.init();
        verify(board).fillBoard(anyList());
    }
}
