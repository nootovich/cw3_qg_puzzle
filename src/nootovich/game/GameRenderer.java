package nootovich.game;

import java.awt.Color;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import static java.awt.Color.*;
import static nootovich.game.Game.*;

public class GameRenderer extends NGRenderer {

    public static int framecount = 0;

    @Override
    public void render(NGGraphics g) {
        g.drawRect(0, 0, w, h, DARK_GRAY);
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                g.drawRect(x * CELL_SIZE, y * CELL_SIZE, CELL_SIZE, CELL_SIZE, getColor(board[x][y]));
            }
        }

        g.drawTextCentered(String.valueOf(heldKeys), w / 2, 10, BLACK);
        g.drawText(String.valueOf(curHeight), 4, 10, WHITE);
        g.drawText(String.valueOf(curHeight), 5, 9, WHITE);
        g.drawText(String.valueOf(curHeight), 6, 10, WHITE);
        g.drawText(String.valueOf(curHeight), 5, 11, WHITE);
        g.drawText(String.valueOf(curHeight), 5, 10, BLACK);

        emitter.draw(g);
        base.draw(g);
        framecount++;
    }

    public Color getColor(float n) {
        if (n == Float.NEGATIVE_INFINITY) return BLACK;
        else if (n < 1) return new Color(0x426942);
        else if (n < 18) return new Color(0x49 + (int) n * 10, 0x62, 0x42);
        else return new Color(0xFF4242);
        // if (n > 255) return WHITE;
        // return new Color(n);
        // if (n == -1) return  BLACK;
        // if (n-- == 0) return RED;
        // if (n-- == 0) return ORANGE;
        // if (n-- == 0) return YELLOW;
        // if (n-- == 0) return GREEN;
        // if (n-- == 0) return CYAN;
        // if (n-- == 0) return BLUE;
        // if (n-- == 0) return new Color(150, 0, 255);
        // if (n-- == 0) return MAGENTA;
        // return WHITE;
    }

    @Override
    public void reset() {

    }
}
