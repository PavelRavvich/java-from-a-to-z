package ru.pravvich.figures;

import static ru.pravvich.Board.*;

public class Rook extends Figure{

    public Rook(String color, int id, int y, int x) {
        this.color = color;
        this.id = id;
        this.positionX = x;
        this.positionY = y;
    }

    @Override
    public void move(int[] newPosition) {
        try {
            if (this.positionY == newPosition[0] && this.positionX > newPosition[1]) {
                this.moveUp(newPosition);
            } else if (this.positionX == newPosition[1] && this.positionY < newPosition[1]) {
                this.moveRight(newPosition);
            } else if (this.positionX == newPosition[1] && this.positionY > newPosition[0]) {
                this.moveLeft(newPosition);
            } else if (this.positionY == newPosition[0] && this.positionX < newPosition[1]) {
                this.moveDown(newPosition);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Invalid move.");
        }
    }

    /**
     * @see RookTest#whenNewPositionInThenRookGoDownAndOldPositionInstancePlace()
     * @see RookTest#whenNewPositionInThenRookGoDownAndKillAndOldPositionInstancePlace() test kill
     */
    private  void moveDown(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        boolean checkRoad = true;
        for (int i = (this.positionX + 1); i < newPosition[1]; i++) { // проверяем ячейки до цели, не включительно
            if (!(desc[this.positionY][i] instanceof Place)) {
                checkRoad = false;
                break;
            }
        }
        this.checkEnemy(newPosition,checkRoad);
    }

    /**
     * @see RookTest#whenNewPositionInThenRookGoRightAndOldPositionInstancePlace()
     * @see RookTest#whenNewPositionInThenRookGoRightAndOldPositionInstancePlaceAndKill()
     */
    private void moveRight(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        boolean checkRoad = true;
        for (int i = (this.positionY + 1); i < newPosition[0]; i++) {
            if (!(desc[i][this.positionX] instanceof Place)) {
                checkRoad = false;
                break;
            }
        }
        this.checkEnemy(newPosition,checkRoad);
    }

    /**
     * @see RookTest#whenNewPositionInThenRookGoInNewPositionAndOldPositionInstancePlace()
     * @see RookTest#whenNewPositionInThenRookGoInNewPositionAndOldPositionInstancePlaceAndKillEnemy()
     */
    private void moveUp(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        boolean checkRoad = true;
        for (int i = (this.positionX - 1); i > newPosition[1]; i--) {
            if (!(desc[this.positionY][i] instanceof Place)) {
                checkRoad = false;
                break;
            }
        }
        this.checkEnemy(newPosition,checkRoad);
    }

    /**
     * @see RookTest#whenNewPositionInThenRookGoLeftAndOldPositionInstancePlace()
     * @see RookTest#whenNewPositionInThenRookGoLeftAndOldPositionInstancePlaceAndKillEnemy()
     */
    private void moveLeft(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        boolean checkRoad = true;
        for (int i = (this.positionY - 1); i > newPosition[0]; i--) {
            if (!(desc[i][this.positionX] instanceof Place)) {
                checkRoad = false;
                break;
            }
        }
        this.checkEnemy(newPosition,checkRoad);
    }

    private void swap(int[] newPosition, Boolean checkRoad) throws ArrayIndexOutOfBoundsException {
        if (checkRoad) {
            desc[newPosition[0]][newPosition[1]] = desc[this.positionY][this.positionX];
            desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
            this.positionY = newPosition[0];
            this.positionX = newPosition[1];
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    private void checkEnemy(int[] newPosition, Boolean checkRoad) {
        if (!(desc[newPosition[0]][newPosition[1]] instanceof Place) && checkRoad && // проверка целевой ячейки
                !(this.color.equals(desc[newPosition[0]][newPosition[1]].getColor()))) {
            deathFigures.add(desc[newPosition[0]][newPosition[1]]);
            this.swap(newPosition,checkRoad);
        } else if (desc[newPosition[0]][newPosition[1]] instanceof Place && checkRoad) {
            this.swap(newPosition,checkRoad);
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }
}