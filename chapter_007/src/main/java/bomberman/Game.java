package bomberman;

import bomberman.gameBoard.Board;
import bomberman.gameBoard.GameBoard;
import bomberman.user.Monster;
import bomberman.user.Unit;
import bomberman.user.Walker;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by pavel on 11.04.17.
 */
public class GameConditions {
    private final List<Thread> monsters;
    private final AtomicReference<Board> board;
    private final ExecutorService executor;
    private final int TARGET_AMOUNT_MONSTERS;
    private final AtomicBoolean endGame;
    private final CyclicBarrier BARRIER;

    private Runnable startGame;
    private final Walker walker;

    public GameConditions(final int heightBoard,
                          final int widthBoard,
                          final int amountMonsters,
                          final Walker walker) {
        this.walker = walker;
        this.board = new AtomicReference<>(new GameBoard(heightBoard, widthBoard));
        this.monsters = new CopyOnWriteArrayList<>();
        this.executor = Executors.newFixedThreadPool(amountMonsters);
        this.TARGET_AMOUNT_MONSTERS = amountMonsters;
        this.endGame = new AtomicBoolean(false);
        this.BARRIER = new CyclicBarrier(this.TARGET_AMOUNT_MONSTERS, this.startGame());
    }



    public void initMonsters(final int startPosX, final int startPosY) {
        for (int i = 0; i < this.TARGET_AMOUNT_MONSTERS; i++) {

            final Unit monster = new Monster(
                    this.board,
                    new AtomicInteger(startPosX),
                    new AtomicInteger(startPosY),
                    this.BARRIER);

            final Thread monsterThread = new Thread(monster);
            this.executor.execute(monsterThread);
            this.monsters.add(monsterThread);
        }
    }

    private void checkingEndGame()  {
        final Thread checkEndGame = new Thread(new Runnable() {
            @Override
            public void run() {
                while (!endGame.get()) {
                    try {
                        Thread.sleep(1500);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    int i = 0;
                    for (Thread monster: getMonsters()) {
                        if (monster.isAlive()) {
                            i++;
                        }
                    }

                    if (i == monsters.size()) endGame.set(true);
                }
            }
        });
        checkEndGame.setDaemon(true);
        checkEndGame.start();
    }

    private List<Thread> getMonsters() {
        synchronized (this.monsters) {
            return new ArrayList<>(this.monsters);
        }
    }

    private Runnable startGame() {
        return this.startGame = new Runnable() {
            @Override
            public void run() {
                // TODO: 12.04.17 start
            }
        };
    }
}
