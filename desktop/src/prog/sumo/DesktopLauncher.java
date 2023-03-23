package prog.sumo;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;

/**
 * The DesktopLauncher class is the entry point for the desktop version of the
 * game.
 */
public final class DesktopLauncher {
    /**
     * The number of frames per second to render when the game is in the
     * foreground.
     */
    private static final int FOREGROUND_FPS = 60;

    /**
     * Prevents instantiation of the utility class.
     */
    private DesktopLauncher() {
        // Utility class constructor
    }

    /**
     * The main method for the desktop version of the game.
     *
     * @param arg The command-line arguments.
     */
    public static void main(final String[] arg) {
        Lwjgl3ApplicationConfiguration config =
                new Lwjgl3ApplicationConfiguration();
        config.setForegroundFPS(FOREGROUND_FPS);
        config.setTitle("Sumo Battle Tap");
        new Lwjgl3Application(new SumoBattleTapGame(new DesktopScoreAPI()),
                config);
    }
}
