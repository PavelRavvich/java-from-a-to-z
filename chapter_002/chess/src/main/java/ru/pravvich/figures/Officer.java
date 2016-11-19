package ru.pravvich.figures;

import java.util.Arrays;

import static ru.pravvich.Board.*;

public class Officer extends Figure {

    public Officer(String color, int id, int y,int x) {
        this.color = color;
        this.id = id;
        this.positionX = x;
        this.positionY = y;
    }

    @Override
    public void move(int[] newPosition) {
        int[] position = {this.positionY, this.positionX};
        try {
            if (Arrays.equals(position, newPosition)) throw new IllegalArgumentException();
            if (newPosition[0] > position[0] && newPosition[1] < position[1]) {
                this.moveRightUp(newPosition);
            } else if (newPosition[0] < position[0] && newPosition[1] < position[1]) {
                this.moveLeftUp(newPosition);
            } else if (newPosition[0] > position[0] && newPosition[1] > position[1]) {
                this.moveRightDown(newPosition);
            } else if (newPosition[0] < position[0] && newPosition[1] > position[1]) {
                this.moveLeftDown(newPosition);
            } else {
                throw new IllegalArgumentException();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Such place on a board doesn't exist!");
        } catch (IllegalArgumentException ex) {
            System.err.println("It is impossible!");
        }
    }

    private void moveLeftDown(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i != 8; i++) {
            if (newPosition[0] == (this.positionY - i) && newPosition[1] == (this.positionX + i) &&
                    desc[(this.positionY - i)][(this.positionX + i)] instanceof Place) {
                Figure tempFigure = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                desc[newPosition[0]][newPosition[1]] = tempFigure;
                break;
            } else if (newPosition[0] == (this.positionY - i) && newPosition[1] == (this.positionX + i) &&
                    !(desc[(this.positionY - i)][(this.positionX + i)] instanceof Place) &&
                    !(desc[(this.positionY - i)][(this.positionX + i)].getColor().equals(
                            desc[this.positionY][this.positionX].getColor()))) {
                deathFigures[indexDeath++] = desc[(this.positionY - i)][(this.positionX + i)];
                desc[(this.positionY - i)][(this.positionX + i)] = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                break;
            }
        }
    }

    private void moveLeftUp(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i != 8; i++) {
            if (newPosition[0] == (this.positionY - i) && newPosition[1] == (this.positionX - i) &&
                    desc[(this.positionY - i)][(this.positionX - i)] instanceof Place) {
                Figure tempFigure = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                desc[newPosition[0]][newPosition[1]] = tempFigure;
                break;
            } else if (newPosition[0] == (this.positionY - i) && newPosition[1] == (this.positionX - i) &&
                    !(desc[(this.positionY - i)][(this.positionX - i)] instanceof Place) &&
                    !(desc[(this.positionY - i)][(this.positionX - i)].getColor().equals(
                            desc[this.positionY][this.positionX].getColor()))) {
                deathFigures[indexDeath++] = desc[(this.positionY - i)][(this.positionX - i)];
                desc[(this.positionY - i)][(this.positionX - i)] = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                break;
            }
        }
    }

    private void moveRightDown(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i != 8; i++) {
            if (newPosition[0] == (this.positionY + i) && newPosition[1] == (this.positionX + i) &&
                    desc[(this.positionY + i)][(this.positionX + i)] instanceof Place) {
                Figure tempFigure = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                desc[newPosition[0]][newPosition[1]] = tempFigure;
                break;
            } else if (newPosition[0] == (this.positionY + i) && newPosition[1] == (this.positionX + i) &&
                    !(desc[(this.positionY + i)][(this.positionX + i)] instanceof Place) &&
                    !(desc[(this.positionY + i)][(this.positionX + i)].getColor().equals(
                            desc[this.positionY][this.positionX].getColor()))) {
                deathFigures[indexDeath++] = desc[(this.positionY + i)][(this.positionX + i)];
                desc[(this.positionY + i)][(this.positionX + i)] = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                break;
            }
        }
    }

    private void moveRightUp(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        for (int i = 1; i != 8; i++) {
            if (newPosition[0] == (this.positionY + i) && newPosition[1] == (this.positionX - i) &&
                    desc[(this.positionY + i)][(this.positionX - i)] instanceof Place) {
                Figure tempFigure = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                desc[newPosition[0]][newPosition[1]] = tempFigure;
                break;
            } else if (newPosition[0] == (this.positionY + i) && newPosition[1] == (this.positionX - i) &&
                    !(desc[(this.positionY + i)][(this.positionX - i)] instanceof Place) &&
                    !(desc[(this.positionY + i)][(this.positionX - i)].getColor().equals(
                            desc[this.positionY][this.positionX].getColor()))) {
                deathFigures[indexDeath++] = desc[(this.positionY + i)][(this.positionX - i)];
                desc[(this.positionY + i)][(this.positionX - i)] = desc[this.positionY][this.positionX];
                desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
                break;
            }
        }
    }
}
