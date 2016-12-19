package ru.pravvich;

import ru.pravvich.figures.*;

class Board {
    static Figure[][] desc = new Figure[8][8];

    private void add(Figure figure) {
        if (figure instanceof Officer) {
            desc[figure.getPosition().getY()][figure.getPosition().getX()] =
                    new Officer(figure.getPosition(), figure.getColor());
        }
    }

    void move(Cell position, Cell newPosition) throws ImposableMoveException {
        boolean noFriendFire = this.noFriendFire(position, newPosition);
        boolean existenceFigure = this.existenceFigure(position);
        Cell[] cells = desc[position.getY()][position.getX()].move(newPosition);
        boolean checkRoad = this.checkRoad(cells);
        // если все ок записывает фигуру в новую позицию, а в старую записывает null
        if (existenceFigure && checkRoad && noFriendFire) {
            desc[newPosition.getY()][newPosition.getX()] = desc[position.getY()][position.getX()];
            desc[position.getY()][position.getX()] = null;
        } else {
            throw new ImposableMoveException("Error move");
        }
    }

    // проверяет существует ли фигура
    private boolean existenceFigure(Cell position) throws ImposableMoveException {
        if (desc[position.getY()][position.getX()] != null) {
            return true;
        } else {
            throw new ImposableMoveException("Error move");
        }
    }

    // проверяет нет ли припядствий на пути. так как сама фигура в путь входит начинаем обход с i = 1
    private boolean checkRoad(Cell[] addressRoad) throws ImposableMoveException {
        for (int i = 1; i != addressRoad.length; i++) {
            if (desc[addressRoad[i].getY()][addressRoad[i].getX()] != null) {
                throw new ImposableMoveException("Error move");
            }
        }
        return true;
    }

    // проверяет не совпадает ли цвет объекта в точке назначения с цветом движущейся фигуры
    private boolean noFriendFire(Cell position, Cell newPosition) {
        if (desc[newPosition.getY()][newPosition.getX()] != null) {
            String colorStart = desc[position.getY()][position.getX()].getColor();
            String colorFinish = desc[newPosition.getY()][newPosition.getX()].getColor();
            if (colorStart.equals(colorFinish)) {
                return false;
            } else {
                return true;
            }
        }
        return true;
    }
}
