package TreIRad;

public class Game {
    private Markings[][] board;
    public Game(int boardSize) {
        initialise(boardSize);
    }
    public Game() {
        initialise(3);
    }

    private void initialise(int boardSize) {
        this.board = new Markings[boardSize][boardSize];
        for(int x = 0; x < boardSize; x++) {
            for(int y = 0; y < boardSize; y++) {
                this.board[x][y] = Markings.None;
            }
        }
    }

    public boolean over() {
        int boardSize = this.board.length;
        boolean done = false;
        boolean filled = false;

        /* Check row and column */
        for(int column = 0; column < boardSize && !done; column++) {
            done = this.board[column][0] != Markings.None;
            for(int row = 0; row < boardSize - 1 && done; row++) {
                done = this.board[column][row] == this.board[column][row+1];
            }
        }
        for(int row = 0; row < boardSize; row++) {
            done = this.board[0][row] != Markings.None;
            for(int column = 0; column < boardSize -1 && done; column++) {
                done = this.board[column][row] == this.board[column + 1][row];
            }
        }
        if(!done) {
            done = this.board[0][0] != Markings.None;
            for (int diagonal = 0; diagonal < boardSize - 1 && done; diagonal++) {
                done = this.board[diagonal][diagonal] == this.board[diagonal + 1][diagonal + 1];
            }
        }

        /* Board filled with no winner */
        if(!done) {
            filled = true;
            for (Markings[] markings : this.board) {
                for (int row = 0; row < boardSize; row++) {
                    filled = markings[row] != Markings.None;
                    if (!filled) break;
                }
                if (!filled) break;
            }
        }
        return done || filled;
    }
    public boolean set(int x, int y, Markings marking) {
        boolean success = (this.board[x][y] == Markings.None);
        if(success) {
            this.board[x][y] = marking;
        }
        return success;
    }

    @Override
    public String toString() {
        StringBuilder board = new StringBuilder();
        int boardSize = this.board.length;
        for (Markings[] markings : this.board) {
            for (int y = 0; y < boardSize; y++) {
                char c = ' ';
                if (markings[y] == Markings.X) {
                    c = 'X';
                } else if (markings[y] == Markings.O) {
                    c = 'O';
                }
                board.append("[").append(c).append("]");
            }
            board.append("\n");
        }
        return board.toString();
    }
}
