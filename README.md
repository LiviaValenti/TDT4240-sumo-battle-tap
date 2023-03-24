# Sumo Battle Tap

_The Sumo multiplayer game is a thrilling battle between two sumo-wrestlers. The objective of the game is to push the opponent out of the ring, and the first player to succeed wins the match. The player who has won the most matches out of three matches, wins the game. Each player controls their sumo-wrestler by pressing rapidly on their respective side of the screen, thereby pushing their opponent closer to the ring's edge. The game is inspired by the game Sumo from 2 PlayerGames._

_The gameplay is fast-paced and requires quick reflexes, making it an exciting and engaging experience for players. Get ready to enter the ring and become the ultimate sumo-wrestling champion!_

## Development

To proceed, make sure you have Android Studio with Android SDK 33 installed.

### Setup

1. `git clone https://github.com/LiviaValenti/TDT4240-sumo-battle-tap.git`
2. Open the project in Android Studio
3. Set up AVD: Tools -> Device Manager -> Create Virtual Device -> Pixel 6 API 32
4. Install Checkstyle plugin: File -> Settings -> Plugins -> Search
   for [CheckStyle-IDEA](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) -> Install
5. Configure Checkstyle: File -> Settings -> Tools -> Checkstyle -> ✔ Sun Checks
6. Set up Code Style in Android Studio: File -> Settings -> Editor -> Code Style -> Scheme ⚙ ->
   Import Scheme -> Checkstyle configuration -> Select `config/checkstyle/sun_checks.xml`
8. Run the app: Run -> Run 'android'. First-time Android Virtual Device (AVD) startup can take a few minutes!

#### Automatic code formatting (optional)

Format files on save save: File -> Settings -> Tools -> Actions on Save -> ✔ Reformat code

IntelliJ IDEA/Android Studio does not automatically wrap lines when you import a Checkstyle configuration like sun_checks.xml. However, this can be manually configured. 

To set the line wrapping limit in IntelliJ IDEA, follow these steps:

1. Go to File > Settings (or IntelliJ IDEA > Preferences on macOS).
2. In the Settings dialog, navigate to Editor > Code Style > Java.
3. In the Java settings, switch to the Wrapping and Braces tab.
4. Find the Hard wrap at option (usually under the General section) and set it to 80. 
5. To ensure that your comments are also wrapped, find the Wrap at right margin option under the Comments section and check it.
6. Click OK to save your settings.

Now, when you're typing in the editor, IntelliJ IDEA will automatically wrap lines at the 80th character. However, existing lines won't be reformatted automatically. To reformat existing code according to the new settings, select the code you want to reformat, then use the following keyboard shortcuts:

Windows/Linux: `Ctrl + Alt + L`  
macOS: `Cmd + Option + L`

This will reformat the selected code according to your code style settings, including the line wrapping configuration you just set up.
