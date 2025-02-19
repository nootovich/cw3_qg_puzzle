package nootovich.game;

import java.awt.Color;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGVec4i;

public class Base {

    public NGVec4i area;

    public int health = 100;

    public Base(int x, int y, int w, int h) {
        this.area = new NGVec4i(x, y, h, w);
    }

    public void draw(NGGraphics g) {
        g.drawRect(area.scale(Game.CELL_SIZE), new Color(150, GameRenderer.framecount * 8 % 256, 255));
    }
}
