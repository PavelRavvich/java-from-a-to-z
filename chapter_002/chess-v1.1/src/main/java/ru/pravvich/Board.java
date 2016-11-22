package ru.pravvich;

import ru.pravvich.figures.*;

public class Board {
    public static Figure[][] desc = new Figure[8][8];

    private void add(Figure figure) {
        if (figure instanceof Officer) {
            desc[figure.getPosition().getY()][figure.getPosition().getX()] =
                    new Officer(figure.getPosition(), figure.getColor());
        }
    }

    public void move(Cell position, Cell newPosition) throws ImposableMoveException {

        boolean existence = false;
        if (!(desc[position.getY()][position.getX()] instanceof Place)) {
            existence = true;
        }

        boolean noFriendFire = false; //color
        String colorStart = desc[position.getY()][position.getX()].getColor();
        String colorFinish = desc[newPosition.getY()][newPosition.getX()].getColor();
        if (!colorStart.equals(colorFinish)) {
            noFriendFire = true;
        }

        boolean goodRoad = false;
        if (desc[position.getY()][position.getX()].move(newPosition).length != 0) {
            goodRoad = true;
        }

        if (existence && goodRoad && noFriendFire) {
            desc[newPosition.getY()][newPosition.getX()] = desc[position.getY()][position.getX()];
            desc[position.getY()][position.getX()] = new Place(new Cell(position.getY(),position.getX()),"");
        } else {
            throw new ImposableMoveException("Error move");
        }
    }
}
