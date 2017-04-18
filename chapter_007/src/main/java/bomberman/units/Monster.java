package bomberman.units;

import bomberman.gameBoard.Board;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pavel on 11.04.17.
 * Determines specific for monsters attributes.
 */
public class Monster extends Member {
    /**
     * Link on barrier for monster. When all monsters run then game begin.
     */
    private final CyclicBarrier BARRIER;

    /**
     * Default constructor.
     * @param board game's board.
     * @param startPosX position on board by 'Y'.
     * @param startPosY position on board by 'X'.
     * @param BARRIER link on barrier.
     */
    public Monster(final AtomicReference<Board> board,
                   final AtomicInteger startPosY,
                   final AtomicInteger startPosX,
                   final CyclicBarrier BARRIER) {

        super(board, startPosY, startPosX);
        this.BARRIER = BARRIER;
    }

    @Override
    public void run() {
        //wait while start all monsters.
        try {
            this.BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        new MonsterAutomaticMoveAlgorithm().go();
    }

    /**
     * Check Is the cell occupied.
     * @param futureX position checking cell by X.
     * @param futureY position checking cell by Y.
     * @return if the cell occupied then unit try 5 seconds.
     * If 5 seconds cell is occupied return true.
     * Else - false.
     */
    @Override
    protected boolean pointNotClear(final int futureX, final int futureY) {
        int waitTime = 5000;
        while (this.board.get().getBoard()[futureY][futureX] != null &&
                !Thread.currentThread().isInterrupted()) {

            if (waitTime == 0) return true;
            try {
                Thread.sleep(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTime -= 250;
        }
        return false;
    }

    /**
     * Algorithm determines automatic moves of monsters.
     */
    final private class MonsterAutomaticMoveAlgorithm {
        private final Random random;

        private MonsterAutomaticMoveAlgorithm() {
            this.random = new Random();
        }

        private void go() {
            int lastRote = 0;
            while (!Thread.currentThread().isInterrupted() && unitIsLive.get()) {
                int currentRote = this.random.nextInt(4) + 1;
                while (lastRote == currentRote) {
                    currentRote = this.random.nextInt(4) + 1;
                }
                lastRote = currentRote;
                if (currentRote == 1) loopMoveRight();
                if (currentRote == 2) loopMoveDown();
                if (currentRote == 3) loopMoveLeft();
                if (currentRote == 4) loopMoveUp();
            }
        }

        private void loopMoveRight() {
            boolean right = true;
            while (right) {
                right = moveRight();
            }
        }

        private void loopMoveLeft() {
            boolean left = true;
            while (left) {
                left = moveLeft();
            }
        }

        private void loopMoveUp() {
            boolean up = true;
            while (up) {
                up = moveLeft();
            }
        }

        private void loopMoveDown() {
            boolean down = true;
            while (down) {
                down = moveLeft();
            }
        }
    }
}
