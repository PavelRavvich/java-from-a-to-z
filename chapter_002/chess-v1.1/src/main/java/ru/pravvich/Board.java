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
        boolean existenceFigure = this.existenceFigure(position, newPosition);
        boolean checkRoad = this.checkRoad(desc[position.getY()][position.getX()].move(newPosition));
        boolean noFriendFire = this.noFriendFire(position, newPosition);
        // если все ок записывает фигуру в новую позицию, а в старую записывает Place
        if (existenceFigure && checkRoad && noFriendFire) {
            desc[newPosition.getY()][newPosition.getX()] = desc[position.getY()][position.getX()];
            desc[position.getY()][position.getX()] = new Place(new Cell(position.getY(),position.getX()),"");
        } else {
            throw new ImposableMoveException("Error move");
        }
    }

    // проверяет существует ли фигура
    public boolean existenceFigure(Cell position, Cell newPosition) {
        boolean result = false;
        if (!(desc[position.getY()][position.getX()] instanceof Place)) {
            result = true;
        }
        return result;
    }

    // проверяет нет ли припядствий на пути
    private boolean checkRoad(Cell[] addressRoad) {
        boolean result = true;
        for (int i = 0; i != addressRoad.length; i++) {
            if (!(desc[addressRoad[i].getY()][addressRoad[i].getX()] instanceof Place)) {
                result = false;
            }
        }
        return result;
    }

    // проверяет не совпадает ли цвет объекта в точке назначения с цветом движущейся фигуры
    private boolean noFriendFire(Cell position, Cell newPosition) {
        boolean noFriendFire = false; //check color
        String colorStart = desc[position.getY()][position.getX()].getColor();
        String colorFinish = desc[newPosition.getY()][newPosition.getX()].getColor();
        if (!colorStart.equals(colorFinish)) {
            noFriendFire = true;
        }
        return noFriendFire;
    }
}
