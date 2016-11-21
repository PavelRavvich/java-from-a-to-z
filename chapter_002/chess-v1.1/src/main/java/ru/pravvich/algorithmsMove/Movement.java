package ru.pravvich.algorithmsMove;

import ru.pravvich.*;
import ru.pravvich.figures.*;

public abstract class Movement {
    abstract void move(Figure figure, Cell position) throws ImposableMoveException;
}
