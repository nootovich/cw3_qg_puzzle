package nootovich.game;

import java.awt.Color;
import nootovich.nglib.NGGraphics;
import nootovich.nglib.NGRenderer;

import static nootovich.nglib.NGMain.*;

public class GameRenderer extends NGRenderer {

    @Override
    public void render(NGGraphics g) {
        g.drawRect(0, 0, w, h, Color.DARK_GRAY);
        g.drawText(String.valueOf(tickCount), 0, 20, Color.WHITE, 24);
    }

    @Override
    public void reset() {

    }
}
