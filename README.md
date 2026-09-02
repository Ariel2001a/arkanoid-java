# Arkanoid (Brick Breaker) Game in Java

A modular, object-oriented desktop implementation of the classic **Arkanoid** arcade game built in Java. The project emphasizes clean code, separation of concerns, and robust Object-Oriented Design Patterns.

---

## Features & Architecture
* **Modular Physics & Collision Detection:** Custom collision handling predicting trajectory and surface reflections using 2D geometry algorithms.
* **Event-Driven Architecture:** Observer pattern implementation for managing in-game events such as block destructions, score updates, and remaining ball tracking.
* **Layered Sprite Rendering:** Efficient collection management for drawing and animating dynamic game elements.

---

## Design Patterns & OOP Principles
* **Observer Pattern (`HitListener` / `HitNotifier`):** Decouples collision detection from game mechanics (e.g., `ScoreTrackingListener`, `BlockRemover`, `BallRemover`).
* **Composite / Collection Management (`SpriteCollection`):** Centralizes rendering and per-frame update cycles (`timePassed`).
* **Package by Feature:** Cleanly organized into logical packages (`Geometry`, `Collision`, `Sprites`, `Interfaces`, `GameCore`).

---

## Package Structure
```text
src/
├── Geometry/         # 2D primitives: Point, Line, Rectangle
├── Collision/        # Velocity and CollisionInfo computation
├── Sprites/          # Visual components: Ball, Paddle, Block, SpriteCollection
├── Interfaces/       # Core contracts: Sprite, Collidable, HitListener, HitNotifier
├── GameCore/         # Game loop, environment, listeners, and score management
└── ArkanoidGame.java # Main application entry point
```

---

## Getting Started

### Prerequisites
* **Java Development Kit (JDK):** Version 11 or higher
* Included GUI library: `lib/biuoop-1.4.jar`

---

## How to Run

### Windows (PowerShell)
```powershell
# 1. Compile all Java source files
javac -cp "lib/biuoop-1.4.jar;src" -d bin (Get-ChildItem -Path src -Filter *.java -Recurse | Select-Object -ExpandProperty FullName)

# 2. Run the game
java -cp "lib/biuoop-1.4.jar;bin" ArkanoidGame
```

### Windows (Command Prompt / CMD)
```cmd
:: 1. Compile all Java source files
javac -cp "lib/biuoop-1.4.jar;src" -d bin src/ArkanoidGame.java src/*/*.java

:: 2. Run the game
java -cp "lib/biuoop-1.4.jar;bin" ArkanoidGame
```

### Linux / macOS (Bash)
```bash
# 1. Compile all Java source files
javac -cp "lib/biuoop-1.4.jar:src" -d bin src/ArkanoidGame.java src/*/*.java

# 2. Run the game
java -cp "lib/biuoop-1.4.jar:bin" ArkanoidGame
```