package nootovich.game;

import java.awt.Dimension;
import java.awt.Toolkit;
import nootovich.nglib.NGMain;
import nootovich.nglib.NGVec2i;

public class Game extends NGMain {

    public void main() {
        setTickRate(60);
        setFrameRate(60);
        createWindow(1005, 560, new GameRenderer());
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        NGVec2i   windowPos  = new NGVec2i(screenSize.width, screenSize.height).sub(w, h).divide(2);
        window.jf.setLocation(windowPos.x(), windowPos.y());

        start();
    }

}
