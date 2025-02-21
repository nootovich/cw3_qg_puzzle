package nootovich.game;

import java.awt.GraphicsEnvironment;
import java.awt.Rectangle;
import nootovich.nglib.*;


public class Game extends NGMain {

    public static final int CELL_SIZE = 6;

    public static final int       BOARD_WIDTH  = 128 * 2;
    public static final int       BOARD_HEIGHT = 72 * 2;
    public static       float[][] board        = new float[BOARD_WIDTH][BOARD_HEIGHT];

    public static final int FLUID_FLOW_SEARCH_RANGE = 1;

    public static int curHeight = 1;

    public static Base    base    = new Base(BOARD_WIDTH / 4, 0, 5, 5);
    public static Emitter emitter = new Emitter(0, 0, 10, 10);

    public void main() {
        setTickRate(60);
        setFrameRate(60);
        createWindow(BOARD_WIDTH * CELL_SIZE, BOARD_HEIGHT * CELL_SIZE, new GameRenderer());
        Rectangle monitorBounds = GraphicsEnvironment.getLocalGraphicsEnvironment().getScreenDevices()[1].getDefaultConfiguration().getBounds();
        window.jf.setLocation(new NGVec2i(monitorBounds.getSize()).sub(window.size).divide(2).add(new NGVec2i(monitorBounds.getLocation())).toPoint());
        // for (int y = 0; y < BOARD_HEIGHT; y++) {
        //     for (int x = (int) (BOARD_WIDTH * 0.8f); x < BOARD_WIDTH; x++) {
        //         board[x][y] = y / 7.f + (int) (Math.random() * 40);
        //     }
        // }
        for (int x = 0; x < BOARD_WIDTH - 10; x++) board[x][10] = Float.NEGATIVE_INFINITY;
        for (int y = 10; y < BOARD_HEIGHT - 10; y++) board[BOARD_WIDTH - 10][y] = Float.NEGATIVE_INFINITY;
        for (int x = 10; x <= BOARD_WIDTH - 10; x++) board[x][BOARD_HEIGHT - 10] = Float.NEGATIVE_INFINITY;
        for (int y = 20; y < BOARD_HEIGHT - 10; y++) board[10][y] = Float.NEGATIVE_INFINITY;
        start();
    }

    @Override
    public void update() {
        for (int i = 0; i < 10; i++) flow();
        NGVec4i emitterArea = emitter.area;
        for (int x = emitterArea.x(); x < emitterArea.w(); x++) {
        for (int y = emitterArea.y(); y < emitterArea.h(); y++) {
            board[x][y]++;
        }
        }
    }

    public void flow() {
        float[][] nextBoard = new float[BOARD_WIDTH][BOARD_HEIGHT];
        for (int y = 0; y < BOARD_HEIGHT; y++) {
            for (int x = 0; x < BOARD_WIDTH; x++) {
                if (board[x][y] == Float.NEGATIVE_INFINITY) {
                    nextBoard[x][y] = Float.NEGATIVE_INFINITY;
                    continue;
                }
                float neighbourSum   = 0.0f;
                int   neighbourCount = 0;

                for (int dy = -FLUID_FLOW_SEARCH_RANGE; dy <= FLUID_FLOW_SEARCH_RANGE; dy++) {
                    for (int dx = -FLUID_FLOW_SEARCH_RANGE; dx <= FLUID_FLOW_SEARCH_RANGE; dx++) {
                        // if (dx == 0 && dy == 0) continue;
                        int cx = x + dx;
                        int cy = y + dy;
                        if (0 <= cx && cx < BOARD_WIDTH && 0 <= cy && cy < BOARD_HEIGHT && board[cx][cy] != Float.NEGATIVE_INFINITY) {
                            neighbourSum += board[cx][cy];
                            neighbourCount++;
                        }
                    }
                }

                nextBoard[x][y] = Math.max(neighbourSum / Math.max(neighbourCount, 1), 0);
            }
        }
        board = nextBoard;
    }

    @Override
    public void whileLMBHeld(NGVec2i pos) {
        board[pos.x() / CELL_SIZE][pos.y() / CELL_SIZE] = curHeight;
    }

    @Override
    public void whileRMBHeld(NGVec2i pos) {
        board[pos.x() / CELL_SIZE][pos.y() / CELL_SIZE] = Float.NEGATIVE_INFINITY;
    }

    @Override
    public void onMouseWheel(NGVec2i pos, int direction) {
        curHeight -= direction;
    }
}
