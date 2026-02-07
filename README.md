# Chess Game 

⚠️ **Work in Progress**

A chess game implemented in Java.  
This project models a chessboard and pieces using object-oriented principles, preparing for piece movement and graphical interface integration.

## Features

- Initial board setup only.
- Immutable `Piece` class with enums for `Color` and `Type`.
- Chessboard represented as a 2D array.
- Unicode symbols for pieces for console visualization.
- Clean and maintainable code using constants, loops, and clear structure.
- Ready for future implementation of piece movement and game rules.

## Project Structure
```
ChessGame/
├── Board.java
├── Chess.java
├── Piece.java
├── README.md
└── images/
    └── chessboard.png
```
## Setup

1. Clone the repository: 

``` 
git clone https://github.com/moubalsera/chess-game.git
```

2. Navigate to the project folder: 

```
cd chess-game
```

3. Compile the project: 

```
javac *.java
```

4. Run the project: 

```
java Chess
```

For now, you should see a console representation of the chessboard with Unicode chess pieces.

## Usage

- The project currently sets up the initial positions of all chess pieces.
- Empty squares are represented by a ". ".
- Piece movement, turns, and rules will be implemented in future versions.

## Screenshots

Here’s the current console board setup:

![Chessboard Example](images/chessboard.jpg)

## Future Improvements

- Implement piece movement rules and turn management.
- Detect check, checkmate, and stalemate.
- Develop a graphical interface using Java Swing or JavaFX.
- Add AI opponent or multiplayer support.
- Improve board representation with a Board class for better modularity.

## License
This project is licensed under the MIT License.