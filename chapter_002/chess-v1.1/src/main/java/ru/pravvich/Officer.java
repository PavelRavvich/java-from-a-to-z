package ru.pravvich;

public class Officer extends Figure {

    public Officer(Cell position, String color) {
        super(position, color);
    }

    // проверяет идет ли фигура ровно по диаганали
    private boolean check(Cell position) {
        boolean result = false;
        int checkX = position.getX() - this.position.getX();
        int checkY = position.getY() - this.position.getY();
        if (checkX < 0) checkX *= -1;
        if (checkY < 0) checkY *= -1;
        if (checkX == checkY) result = true;
        return result;
    }

    @Override
    public void move(Cell position) {
        try {
            if (this.check(position)) {
                moveRightDown(position);
            }
        } catch (ImposableMoveException ex) {
            System.out.println(ex);
        }
    }

    private void moveRightDown(Cell position) throws ImposableMoveException {
        //check road
        boolean checkRoad = true;
        for (int i = this.position.getY() + 1, j = this.position.getX() + 1;
             i < position.getY() && j < position.getX(); i++, j++) {
            if (Board.desc[i][j] != null) {
                checkRoad = false;
            }
        }
        System.out.println(checkRoad);

        if (checkRoad && Board.desc[position.getY()][position.getX()] == null) {
            Board.desc[position.getY()][position.getX()] = new Officer(new Cell(position.getY(), position.getX()),this.color);
            Board.desc[this.position.getY()][this.position.getX()] = null;
        } else if (Board.desc[position.getY()][position.getX()] != null &&
                    !(this.color.equals(Board.desc[position.getY()][position.getX()].color))) {
            Board.desc[position.getY()][position.getX()] = new Officer(new Cell(position.getY(), position.getX()),this.color);
            Board.desc[this.position.getY()][this.position.getX()] = null;
        } else {
            throw new ImposableMoveException("Invalid move!");
        }
    }
}
