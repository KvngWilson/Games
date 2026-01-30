# Chess, Shogi & Go Games

Three complete Java implementations of classic board games: Chess, Shogi (Japanese Chess), and Go (Baduk/Weiqi).

## Project Structure

```
Games/
├── run-games.sh (launcher)
├── run-tests.sh (test runner)
├── Chess/
│   ├── Chess/
│   │   ├── Board.java
│   │   ├── ChessGame.java (Console version)
│   │   ├── ChessGUI.java (Graphical version)
│   │   ├── Color.java
│   │   ├── Player.java
│   │   ├── Position.java
│   │   └── pieces/
│   │       ├── Piece.java
│   │       ├── King.java
│   │       ├── Queen.java
│   │       ├── Rook.java
│   │       ├── Bishop.java
│   │       ├── Knight.java
│   │       └── Pawn.java
│   └── .vscode/ (settings)
│
├── Shogi/
│   ├── Shogi/
│   │   ├── Board.java
│   │   ├── ShogiGame.java (Console version)
│   │   ├── ShogiGUI.java (Graphical version)
│   │   ├── Color.java
│   │   ├── Player.java
│   │   ├── Position.java
│   │   └── pieces/
│   │       ├── ShogiPiece.java
│   │       ├── King.java (王/玉)
│   │       ├── GoldGeneral.java (金)
│   │       ├── SilverGeneral.java (銀)
│   │       ├── Knight.java (桂)
│   │       ├── Lance.java (香)
│   │       ├── Rook.java (飛)
│   │       ├── Bishop.java (角)
│   │       └── Pawn.java (歩)
│   └── .vscode/ (settings)
│
└── Go/
    ├── Go/
    │   ├── Board.java
    │   ├── GoGame.java (Console version)
    │   ├── GoGUI.java (Graphical version)
    │   ├── Player.java
    │   ├── Position.java
    │   └── Stone.java
    └── .vscode/ (settings)
```

## Building

### Chess
```bash
cd Games/Chess
javac Chess/*.java Chess/pieces/*.java
```

### Shogi
```bash
cd Games/Shogi
javac Shogi/*.java Shogi/pieces/*.java
```

### Go
```bash
cd Games/Go
javac Go/*.java
```

## Running

### Quick Launcher (All Games)
```bash
cd Games
./run-games.sh
```

### Chess - Console Version
```bash
cd Games/Chess
java Chess.ChessGame
```

**Controls:**
- Enter moves in format: `e2 e4`
- Supports algebraic notation with optional piece prefixes: `Ng1`, `Nf3`
- Commands: `quit`, `resign`

### Chess - GUI Version
```bash
cd Games/Chess
java Chess.ChessGUI
```

**Controls:**
- Click a piece to select it (highlighted in yellow)
- Click destination square to move
- Click same piece again to deselect
- Pieces automatically promote to Queen when reaching the opposite rank

### Shogi - Console Version
```bash
cd Games/Shogi
java Shogi.ShogiGame
```

**Controls:**
- Enter moves in format: `a3 a4` (algebraic notation)
- Drop pieces from hand: `P* a4` (piece*destination)
- Commands: `quit`, `resign`

### Shogi - GUI Version
```bash
cd Games/Shogi
java Shogi.ShogiGUI
```

**Controls:**
- Click a piece to select it (highlighted in yellow)
- Click destination square to move
- Click same piece again to deselect
- Pieces automatically promote in promotion zones or with optional promotion
- Promoted pieces display in red for visual distinction
- Pentagonal piece shapes rotate to face each player

### Go - Console Version
```bash
cd Games/Go
java Go.GoGame
```

**Controls:**
- Enter moves in format: `D4` (column-row) or `3,3` (row,col)
- Commands: `pass`, `resign`, `quit`
- Two consecutive passes end the game

### Go - GUI Version
```bash
cd Games/Go
java Go.GoGUI
```

**Controls:**
- Select board size at startup (9×9, 13×13, or 19×19)
- Click intersections to place stones
- Pass button to pass turn
- Resign button to concede game
- New Game button to restart
- Two consecutive passes trigger automatic scoring

## Game Features

### Chess
✓ Full movement validation for all piece types  
✓ Pawn promotion to Queen  
✓ Castling support  
✓ En passant capture  
✓ Check detection  
✓ Legal move validation (cannot move into check)  
✓ Unicode piece symbols (♔, ♕, ♖, ♗, ♘, ♙)  
✓ Console and GUI interfaces  

### Shogi
✓ Full movement validation for all 8 piece types  
✓ Piece promotion system (駒の成り)  
✓ Promoted pieces display in red in GUI  
✓ Pentagonal piece shapes (traditional design)  
✓ Pieces rotate to face their owner  
✓ Capture and hand management  
✓ Promotion zone enforcement  
✓ Forced promotion when required  
✓ Japanese kanji piece symbols (玉, 金, 銀, 桂, 香, 飛, 角, 歩)  
✓ 9×9 board with standard starting position  
✓ Wooden board appearance  
✓ Console and GUI interfaces  

### Go
✓ Full Go rules implementation  
✓ Ko rule enforcement (prevents immediate recapture)  
✓ Suicide move prevention  
✓ Liberty counting and capture detection  
✓ Territory scoring with komi (6.5 points for white)  
✓ Group detection using flood-fill algorithm  
✓ Multiple board sizes (9×9, 13×13, 19×19)  
✓ Star points at traditional positions  
✓ Wooden board with gradient stones  
✓ Pass and resignation support  
✓ Automatic game end after two passes  
✓ Console and GUI interfaces  

## Coordinates

### Chess
- Columns: a-h (left to right)
- Rows: 1-8 (bottom to top for white, top to bottom for black)
- Example: `e2` = white pawn starting position

### Shogi
- Columns: a-i (left to right)
- Rows: 1-9 (top to bottom)
- Example: `a3` = black pawn row, `a7` = white pawn row

### Go
- Columns: A-T (left to right, skipping I)
- Rows: 1-19 (bottom to top)
- Example: `D4`, `K10` (intersection points, not squares)

## Movement Rules Summary

### Chess Pieces
- **King (K/k)**: One square any direction
- **Queen (Q/q)**: Any number of squares horizontally, vertically, or diagonally
- **Rook (R/r)**: Any number of squares horizontally or vertically
- **Bishop (B/b)**: Any number of squares diagonally
- **Knight (N/n)**: L-shape (2 squares + 1 square perpendicular)
- **Pawn (P/p)**: Forward 1 square, or 2 on first move; captures diagonally

### Shogi Pieces
- **King (王/玉)**: One square any direction (cannot be captured)
- **Gold General (金)**: One square forward, sideways, or diagonally forward
- **Silver General (銀)**: One square forward or diagonally (promotes to Gold movement)
- **Knight (桂)**: Two squares forward, one square sideways
- **Lance (香)**: Any squares straight forward (promotes to Gold movement)
- **Rook (飛)**: Any squares straight/horizontal (promoted: Rook + one diagonal)
- **Bishop (角)**: Any squares diagonally (promoted: Bishop + one orthogonal)
- **Pawn (歩)**: One square forward (promotes to Gold movement)

### Go Rules
- **Placing Stones**: Black plays first, then players alternate placing stones on intersections
- **Capture**: Surround opponent's stones to capture them (remove stones with no liberties)
- **Liberties**: Empty adjacent intersections connected to a stone or group
- **Ko Rule**: Cannot immediately recapture a single stone that just captured your stone
- **Suicide**: Cannot place a stone with no liberties unless it captures opponent stones
- **Territory**: Empty intersections surrounded by your stones
- **Scoring**: Territory + captured stones + komi (6.5 for white)
- **Game End**: Both players pass consecutively

## Compilation Status

All three games compile without errors  
All three games run without runtime errors  
All piece/stone movements validated  
GUI interfaces fully functional with custom graphics  

## Technical Details

**Language**: Java 11+
**GUI Framework**: Swing with custom Graphics2D rendering
**Board Representation**: 2D arrays
**Package Structure**: Organized by game with separate pieces package
**Encoding**: UTF-8 (supports Japanese kanji characters)
**Graphics**: Custom pentagonal pieces (Shogi), gradient stones (Go), wooden board backgrounds
**Testing**: JUnit 5 (Jupiter) for unit and end-to-end tests

## Testing

### Running Tests

Tests require JUnit 5 (Jupiter). The test runner script will automatically download JUnit if not present.

```bash
cd Games
./run-tests.sh
```

### Test Structure

Each game has comprehensive test coverage:

**Unit Tests** (`*GameTest.java`):
- Board initialization
- Piece/stone movement validation
- Position handling
- Capture mechanics
- Promotion logic (Chess/Shogi)
- Liberty counting (Go)
- Special rules (ko, castling, en passant)

**End-to-End Tests** (`*E2ETest.java`):
- Full game scenarios
- Opening sequences
- Capture chains
- Complex tactical patterns
- Victory conditions
- Multi-move sequences

### Test Coverage

- **Chess**: 25+ unit tests, 10+ e2e tests
- **Shogi**: 30+ unit tests, 12+ e2e tests
- **Go**: 30+ unit tests, 15+ e2e tests

### Manual Testing

Run individual test files:
```bash
# Download JUnit if needed
wget https://repo1.maven.org/maven2/org/junit/platform/junit-platform-console-standalone/1.10.1/junit-platform-console-standalone-1.10.1.jar

# Chess tests
cd Chess
javac -cp "../junit-platform-console-standalone-1.10.1.jar:." Chess/*.java Chess/pieces/*.java test/*.java
java -jar ../junit-platform-console-standalone-1.10.1.jar --class-path "." --scan-class-path

# Shogi tests  
cd Shogi
javac -cp "../junit-platform-console-standalone-1.10.1.jar:." Shogi/*.java Shogi/pieces/*.java test/*.java
java -jar ../junit-platform-console-standalone-1.10.1.jar --class-path "." --scan-class-path

# Go tests
cd Go
javac -cp "../junit-platform-console-standalone-1.10.1.jar:." Go/*.java test/*.java
java -jar ../junit-platform-console-standalone-1.10.1.jar --class-path "." --scan-class-path
```

## Known Limitations

### Chess & Shogi
- Simplified checkmate/stalemate detection
- No move history/undo functionality
- No time controls
- No network multiplayer

### Go
- Simplified territory counting (doesn't detect all dead stones)
- No handicap stones support
- No game records (SGF format)
- No time controls

## Future Enhancements

### All Games
- Move history and undo
- Game save/load
- AI opponents with difficulty levels
- Network multiplayer
- Move timing/clocks
- Move notation display
- Tutorial mode

### Go Specific
- Advanced territory counting (life and death analysis)
- Handicap stones
- SGF file support
- Game review mode
- Variation trees
