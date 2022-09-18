import TreIRad.Game;
import TreIRad.Markings;

import java.util.Scanner;

public class TreIRad {
    public static void main(String[] args) {
        Game game = new Game();
        Scanner scanner = new Scanner(System.in);

        /* turn == true infers player X. */
        boolean turn = true;
        Markings player;
        boolean successful;
        int x, y;

        int[] positions = new int[2];
        String positionInput;
        boolean parsedOk;

        while(!game.over()) {
            System.out.println(game);
            if(turn) {
                player = Markings.X;
            } else {
                player = Markings.O;
            }

            do {
                /* Print and get  */
                System.out.println("Player " + player + "'s turn:");
                positionInput = scanner.nextLine();
                try {
                    positions = parseInput(positionInput);
                    parsedOk = true;
                } catch (Exception e) {
                    parsedOk = false;
                }
            } while(!parsedOk);

            x = positions[0];
            y = positions[1];

            /* Set piece */
            successful = game.set(x, y, player);

            /* Switch turn */
            if(successful) {
                turn = !turn;
            } else {
                System.out.println("Illegal move!");
            }
        }

        System.out.println("Game over!");
        System.out.println(game);
    }

    private static int[] parseInput(String positionInput) throws Exception {
        int[] positions = new int[2];
        int x, y;

        positionInput = positionInput.strip();

        String[] parts = positionInput.split(" ");
        if(parts.length != 2) {
            String message = "Invalid input: " + positionInput;
            System.err.println(message);
            throw new Exception(message);
        }
        x = Integer.parseInt(parts[0]);
        y = Integer.parseInt(parts[1]);

        positions[0] = x;
        positions[1] = y;
        return positions;

    }
}
