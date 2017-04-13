package bomberman.user;

import bomberman.gameBoard.Board;
import bomberman.user.Gamer;

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

        this.goMonsterAutomaticMove();
    }

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

    private void goMonsterAutomaticMove() {
        while (gameMustGoOn()) {
            boolean left = true;
            while (left && gameMustGoOn()) {
                left = moveLeft();
            }

            boolean right = true;
            while (right && gameMustGoOn()) {
                right = moveRight();
            }
        }

        // TODO: 12.04.17 use all move type.
    }

    private boolean gameMustGoOn() {
        return !Thread.currentThread().isInterrupted() && unitIsLive.get();
    }
}
