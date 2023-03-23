# Sumo Battle Tap

The Sumo multiplayer game is a thrilling battle between two sumo-wrestlers. The objective of the
game is to push the opponent out of the ring, and the first player to succeed wins the match. The
player who has won the most matches out of three matches, wins the game. Each player controls their
sumo-wrestler by pressing rapidly on their respective side of the screen, thereby pushing their
opponent closer to the ring's edge. The game is inspired by the game Sumo from 2 PlayerGames.

The gameplay is fast-paced and requires quick reflexes, making it an exciting and engaging
experience for players. Get ready to enter the ring and become the ultimate sumo-wrestling champion!

## Development

### Prerequisites

- Java 8
- Android Studio with Android SDK 33

### Setup

1. `git clone https://github.com/LiviaValenti/TDT4240-sumo-battle-tap.git`
2. Open the project in Android Studio
3. Set up AVD: Tools -> Device Manager -> Create Virtual Device -> Pixel 6 API 32
4. Install Checkstyle plugin: File -> Settings -> Plugins -> Search
   for [CheckStyle-IDEA](https://plugins.jetbrains.com/plugin/1065-checkstyle-idea) -> Install
5. Configure Checkstyle: File -> Settings -> Tools -> Checkstyle -> ✔ Sun Checks
6. Set up Code Style in Android Studio: File -> Settings -> Editor -> Code Style -> Scheme ⚙ ->
   Import Scheme -> Checkstyle configuration -> Select `config/checkstyle/sun_checks.xml`
7. Optional: Format on save: File -> Settings -> Tools -> Actions on Save -> ✔ Reformat code
8. Run the app: Run -> Run 'android'. First time Virtual Device startup can take a few minutes!