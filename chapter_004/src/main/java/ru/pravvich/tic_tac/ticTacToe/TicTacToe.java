package ru.pravvich.tic_tac.ticTacToe;

import ru.pravvich.tic_tac.game.*;
import ru.pravvich.tic_tac.input.Dialog;
import ru.pravvich.tic_tac.input.Input;
import ru.pravvich.tic_tac.users.Subject;

import java.util.ArrayList;

public class TicTacToe implements TicTacToeStart {
    // хранит партии которые можно будет сыграть в процессе игры
    private ArrayList<Play> games = new ArrayList<>();
    // хранит объекты игроков которые победили
    private ArrayList<Subject> winners = new ArrayList<>();
    // единственный победитель набравший 5 побед
    private String winner;

    // for test
    void setGames(ArrayList games) {
        this.games = games;
    }

    // инициализирует объеты партий
    public void createGames(Input input) {
        for (int i = 0; i < 100; i++) {
            this.games.add(
                    new Game(
                            new Dialog(input)));
        }
    }

    // возвращает имя победителя
    @Override
    public String getWinner() {
        return this.winner;
    }

    // зацикливает игры до 5 побед
    @Override
    public void start() {
        int count = 0;
        while (checkWinner().equals("")) {
            Play game = this.games.get(count);
            game.choiceSide();
            game.loopMove();
            Subject winner = game.initWinner();
            if (!winner.getName().equals("nobody")) {
                this.winners.add(winner);
            }
            count++;
        }
        this.winner = checkWinner();
    }

    // проверяет кто победил 5 раз и возвращает имя победителя
    private String checkWinner() {
        int user = 0, bot = 0;
        for (Subject winner : winners) {
            if ("user".equals(winner.getName())) {
                user++;
                if (user == 5) return "user";
            }

            if ("bot".equals(winner.getName())) {
                bot++;
                if (bot == 5) return "bot";
            }
        }
        return "";
    }
}
