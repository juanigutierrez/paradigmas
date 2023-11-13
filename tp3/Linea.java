package linea;

import java.util.ArrayList;
import java.util.stream.IntStream;

public class Linea {
    public static String IndexOutOfBounds = "Index out of bounds";
    private String StateInfo;
    int width;
    int PhantomColumns;
    int height;
    ModeManager gameModeType;
    TurnManager turn;
    static ArrayList<ArrayList<String>> Board = new ArrayList<>();
    static Boolean finished;

    public Linea(int width, int heigth, char gameStyleType) {
        this.width = width;
        this.height = heigth;
        this.gameModeType = ModeManager.getestilo(gameStyleType);
        finished = false;
        StateInfo = "" ;
        this.turn = new RedTurn();
        this.PhantomColumns = (heigth - 1);
        this.Board = createTablero(width + (PhantomColumns * 2));
    }

    public ArrayList<ArrayList<String>> createTablero(int base) {
        return IntStream.range(0, base)
                .collect(ArrayList::new, (tablero, i) -> tablero.add(new ArrayList<>()), ArrayList::addAll);
    }

    public boolean checkTalesDirectionMatches(int x, int y) {
        return IntStream.range(0, Board.size())
                .anyMatch(i -> IntStream.range(0, Board.get(i).size())
                        .anyMatch(j -> checkConsecutiveColors(i, j, x, y, Board.get(i)))
                );
    }

    private boolean checkConsecutiveColors(int i, int j, int x, int y, ArrayList<String> row) {
        return IntStream.range(0, 4)
                .allMatch(k -> {
                    int rowIdx = i + k * x;
                    int colIdx = j + k * y;
                    return rowIdx >= 0 && rowIdx < Board.size() &&
                            colIdx >= 0 && colIdx < Board.get(rowIdx).size() &&
                            GetElementInPosition(rowIdx, colIdx).equals(getTurn());
                });
    }

    public void playInMe(int x, String color) {
        Board.get(x + PhantomColumns).add(color);
    }

    public void playRedAt(int x) {
        if ((Board.get(x + PhantomColumns).size()) < height && x < width) {
            turn.playRedAt(this, x);
            finished = gameModeType.finished(this);
            StateInfo=GetStateInfo();
            setTurn();
        } else {
            throw new RuntimeException(IndexOutOfBounds);
        }
    }

    public void playBlueAt(int x) {
        if ((Board.get(x + PhantomColumns).size() < height && x < width)) {
            turn.playBlueAt(this, x);
            finished = gameModeType.finished(this);
            StateInfo=GetStateInfo();
            setTurn();
        } else {
            throw new RuntimeException(IndexOutOfBounds);
        }
    }

    public boolean draw() {
        return allColumnsAreFull(Board);
    }
    private String GetStateInfo() {
        if (finished) {
            return "Win " + getTurn();
        }
        if (draw()) {
            return "Draw";
        }
        return "";
    }
    public String getTurn() {
        return turn.getTurno();
    }

    public void setTurn() {
        turn = turn.nextTurno();
    }

    public static boolean isFinished() {
        return finished;
    }

    public boolean allColumnsAreFull(ArrayList<ArrayList<String>> tablero) {
        return tablero.stream()
                .allMatch(column -> column.size() == height);
    }

    public String GetElementInPosition(int x, int y) {
        if (Board.get(x).size() <= y) {
            return " ";
        }
        return Board.get(x).get(y);
    }

    public String show() {
        return IntStream.range(0, height)
                .mapToObj(i -> IntStream.range(PhantomColumns, width + PhantomColumns)
                        .mapToObj(j -> GetElementInPosition(j, height - 1 - i))
                        .reduce("|", (a, b) -> a + b + "|"))
                .reduce("", (a, b) -> a + b + "\n") + StateInfo;

    }
}


