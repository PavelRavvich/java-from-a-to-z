package bomberman.units;

import bomberman.gameBoard.Board;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pavel on 11.04.17.
 *
 */
public class Monster extends Gamer {
    private final CyclicBarrier BARRIER;

    public Monster(final AtomicReference<Board> board,
                   final AtomicInteger startPosX,
                   final AtomicInteger startPosY,
                   final CyclicBarrier BARRIER) {

        super(board, startPosX, startPosY);
        this.BARRIER = BARRIER;
    }

    @Override
    public void run() {
        try {
            this.BARRIER.await();
        } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
        }

        new MonsterAutomaticMoveAlgorithm().go();
    }

    @Override
    protected boolean pointNotClear(final int futureX, final int futureY) {
        int waitTime = 5000;
        while (this.board.get().getBoard()[futureY][futureX] != null) {
            if (waitTime == 0) return true;
            try {
                wait(250);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            waitTime -= 250;
        }
        return false;
    }

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

