package bomberman;

import bomberman.gameBoard.Board;
import bomberman.gameBoard.GameBoard;
import bomberman.units.Gamer;
import bomberman.units.Monster;
import bomberman.units.Unit;

import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pavel on 11.04.17.
 * Determines start statement game.
 */
public class Game implements Round {
    /**
     * Board for game.
     */
    private final AtomicReference<Board> board;
    /**
     * Amount monsters for game.
     */
    private final int TARGET_AMOUNT_MONSTERS;
    /**
     * Barrier will falls only when all monsters call run().
     */
    private final CyclicBarrier BARRIER;
    /**
     * Thread save monster's threads for regular checking end game.
     */
    private final List<Thread> monsters;
    /**
     * Default value this flag is false.
     * When all monsters will death or user will die, flag will be true.
     */
    private final AtomicBoolean endGame;
    /**
     * Thread pool start all monster thread. Only monsters thread.
     */
    private final ExecutorService executorOfMonsters;
    private final Unit user;

    /**
     * Default constructor.
     * @param widthBoard is 'X'.
     * @param heightBoard is 'Y'.
     * @param amountMonsters which will be in current game.
     */
    public Game(final int widthBoard,
                final int heightBoard,
                final int amountMonsters) {

        this.TARGET_AMOUNT_MONSTERS = amountMonsters;

        //User waits until runs all monsters.
        this.BARRIER = new CyclicBarrier(
                amountMonsters, this.user = this.createUserRunnable());

        //Size determines by amount monsters.
        this.executorOfMonsters = Executors.newFixedThreadPool(amountMonsters);

        this.monsters = new CopyOnWriteArrayList<>();
        //Flag for ending game.
        this.endGame = new AtomicBoolean(false);
        //Init game's board.
        this.board = new AtomicReference<>(
                new GameBoard(
                        heightBoard,
                        widthBoard
                )
        );
    }

    /**
     * Create all monsters and send them in executor service.
     */
    @Override
    public void initMonsters() {
        for (int i = 0; i < this.TARGET_AMOUNT_MONSTERS; i++) {
            final int[] position = this.getPositionForNextMonster();
            final int y = position[0];
            final int x = position[1];

            final Unit monster = new Monster(
                    this.board,
                    new AtomicInteger(y),
                    new AtomicInteger(x),
                    this.BARRIER);

            this.board.get().getBoard()[y][x] = monster;
            final Thread t = new Thread(monster);
            this.executorOfMonsters.execute(t);
            this.monsters.add(t);
        }

        this.loopCheckingEndGame();
    }

    /**
     * Generate position [y][x] for addition monster on desk.
     * @return {y, x}.
     */
    private int[] getPositionForNextMonster() {
        for (int i = 0; i < board.get().getBoard().length; i++) {
            for (int j = 0; j < board.get().getBoard()[i].length; j++) {
                if (board.get().getBoard()[i][j] == null) {
                    return new int[] {i, j};
                }
            }
        }

        throw new IllegalArgumentException("Too much monsters for desk size!");
    }

    /**
     * Create cline user object, determines start position of user on board.
     * @return Runnable User object.
     */
    private Unit createUserRunnable() {
        final int startGamerX = (this.board.get().getBoard()[0].length - 1) / 2;
        final int startGamerY = (this.board.get().getBoard().length - 1);
        return new Gamer(this.board,
                new AtomicInteger(startGamerX),
                new AtomicInteger(startGamerY));
    }



    /**
     * Initialize and run loop for check game over.
     */
    private void loopCheckingEndGame() {
        final TimerTask loopCheck = new FollowerOfEndGame();
        final Timer timer = new Timer();
        timer.schedule(loopCheck, 5_000, 1_000);
    }

    /**
     * Thread which look for end game.
     * When all monsters is die, or hero is die, then game over.
     */
    private final class FollowerOfEndGame extends TimerTask {
        @Override
        public void run() {
            int countOfDieMonsters = 0;
            for (Thread monster: monsters) {
                if (!monster.isAlive()) {
                    countOfDieMonsters++;
                }
            }

            if (user.isDie().get() || countOfDieMonsters == monsters.size()) {
                endGame.set(true);
                this.cancel();
            }
        }
    }
}
