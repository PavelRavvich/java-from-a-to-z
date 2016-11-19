package ru.pravvich.figures;

import static ru.pravvich.Board.*;

public class Pawn extends Figure {

    public int getCounterMoves() {
        return counterMoves;
    }

    private int counterMoves = 1;

    public Pawn(String color, int id, int y, int x){
        this.color = color;
        this.id = id;
        this.positionY = y;
        this.positionX = x;
    }

    @Override
    public void move(int[] newPosition) {
        try {
            /**
             * @see OfficerTest#whenWhitePawnFirstMoveOnTwoCellThenMoveOnTwoCell()
             */
            if (this.color.equals("white") && this.counterMoves == 1 && // 1 ход на 2 клетки вверх белая
                    (this.positionX == newPosition[1] + 2) &&
                    this.positionY == newPosition[0] ) {
                this.firstMoveWhiteUp(newPosition);
                /**
                 * @see OfficerTest#whenBlackPawnFirstMoveOnTwoCellThenMoveOnTwoCell()
                 */
            } else if (this.color.equals("black") && this.counterMoves == 1 && // 1 ход на 2 клетки вниз черная
                    this.positionX == (newPosition[1] -2) &&
                    this.positionY == newPosition[0]) {
                firstMoveBlackDown(newPosition);
                /**
                 * @see OfficerTest#whenWhitePawnGoUpThenWorkSwapOnlyMoveUp()
                 */
            } else if ((this.positionX == newPosition[1] + 1) && // белая пешка на 1 клетку вверх
                    this.color.equals("white") && this.positionY == newPosition[0] &&
                    desc[newPosition[0]][newPosition[1]] instanceof Place) {
                moveSwap(newPosition);
                /**
                 * @see OfficerTest#whenBlackPawnGoUpThenWorkSwapOnlyMoveUp()
                 */
            } else if ((this.positionX == newPosition[1] - 1) && // черная пешка на 1 клетку вниз
                    this.color.equals("black") && this.positionY == newPosition[0] &&
                    desc[newPosition[0]][newPosition[1]] instanceof Place) {
                moveSwap(newPosition);
                /**
                 * @see OfficerTest#whenWhitePawnEatBlackFigureInRightThenBlackFigureGoInDeathFigure()
                 */
            } else if (!"white".equals(desc[newPosition[0]][newPosition[1]].getColor()) && // белые едят вправо вверх
                    !(desc[newPosition[0]][newPosition[1]] instanceof Place) &&
                    this.positionY == (newPosition[0] - 1) &&
                    this.positionX == (newPosition[1] + 1)) {
                deathFigures.add(desc[newPosition[0]][newPosition[1]]);
                this.moveSwap(newPosition);
                /**
                 * @see OfficerTest#whenWhitePawnEatBlackFigureInLeftThenBlackFigureGoInDeathFigure()
                 */
            } else if (!"white".equals(desc[newPosition[0]][newPosition[1]].getColor()) && // белые едят влево вверх
                    !(desc[newPosition[0]][newPosition[1]] instanceof Place) &&
                    this.positionY == (newPosition[0] + 1) &&
                    this.positionX == (newPosition[1] + 1)) {
                deathFigures.add(desc[newPosition[0]][newPosition[1]]);
                this.moveSwap(newPosition);
                /**
                 * @see OfficerTest#whenBlackPawnEatWhiteFigureInRightThenWhiteFigureGoInDeathFigure()
                 */
            } else if (!"black".equals(desc[newPosition[0]][newPosition[1]].getColor()) && // черные едят вправо вниз
                    !(desc[newPosition[0]][newPosition[1]] instanceof Place) &&
                    this.positionY == (newPosition[0] - 1) &&
                    this.positionX == (newPosition[1] - 1)) {
                deathFigures.add(desc[newPosition[0]][newPosition[1]]);
                this.moveSwap(newPosition);
                /**
                 * @see OfficerTest#whenBlackPawnEatWhiteFigureInLeftThenWhiteFigureGoInDeathFigure()
                 */
            } else if (!"black".equals(desc[newPosition[0]][newPosition[1]].getColor()) && // черные едят влево вниз
                    !(desc[newPosition[0]][newPosition[1]] instanceof Place) &&
                    this.positionY == (newPosition[0] + 1) &&
                    this.positionX == (newPosition[1] - 1)) {
                deathFigures.add(desc[newPosition[0]][newPosition[1]]);
                this.moveSwap(newPosition);
            } else {
                throw new ArrayIndexOutOfBoundsException();
            }
        } catch (ArrayIndexOutOfBoundsException ex) {
            System.err.println("Invalid move.");
        }

    }
    
    private void firstMoveWhiteUp(int[] newPosition) throws ArrayIndexOutOfBoundsException {
            // промежеточная клетка свободна, целевая клетка свободна,
        if (desc[this.positionY][this.positionX - 1] instanceof Place &&
                desc[this.positionY][this.positionX - 2] instanceof Place) {
            this.moveSwap(newPosition);
        }
    }

    private void firstMoveBlackDown(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        if (desc[this.positionY][this.positionX + 1] instanceof Place &&
                desc[this.positionY][this.positionX + 2] instanceof Place) {
            this.moveSwap(newPosition);
        }
    }

    private void moveSwap(int[] newPosition) throws ArrayIndexOutOfBoundsException {
        desc[newPosition[0]][newPosition[1]] = desc[this.positionY][this.positionX];
        desc[this.positionY][this.positionX] = new Place(this.positionY,this.positionX);
        this.positionY = newPosition[0];
        this.positionX = newPosition[1];
        this.counterMoves++;
    }
}