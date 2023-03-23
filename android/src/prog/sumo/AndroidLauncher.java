package prog.sumo;

import android.os.Bundle;

import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;

/**
 * Android launcher for the Sumo Battle Tap game.
 */
public class AndroidLauncher extends AndroidApplication {
    /**
     * Initializes the Android application with the Sumo Battle Tap game.
     * Subclasses can safely override this method, as long as they call the
     * superclass's onCreate method.
     *
     * @param savedInstanceState The saved instance state of the application.
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AndroidApplicationConfiguration config =
                new AndroidApplicationConfiguration();
        initialize(new SumoBattleTapGame(new AndroidFirebaseScoreAPI()),
                config);
    }
}
