# Text Adventure Game

This is a simple text-based adventure game developed for the CSE201 class project. In this game, you navigate through different rooms, interact with objects, and make choices that affect the outcome. You have a limited number of turns to complete the game, so plan your actions wisely!

## Table of Contents

- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup and Installation](#setup-and-installation)
- [How to Play](#how-to-play)
- [Game Mechanics](#game-mechanics)
- [Class Structure](#class-structure)
- [Contributing](#contributing)

## Features

- **Interactive Storyline**: Explore different rooms and uncover the story through your choices.
- **Multiple Rooms**: Navigate through the Bedroom, Closet, Hallway, Storage, Purifier, and Control Room.
- **Choice-Based Outcomes**: Your decisions trigger flags that open up new options or endings.
- **Turn-Based System**: You have a limited number of turns (default is 30) to complete the game.
- **Simple Interface**: Text-based interaction that's easy to use.

## Prerequisites

- **Java Development Kit (JDK) 8 or higher**: Ensure that Java is installed on your system.
- **Command Line Interface**: A terminal or command prompt to compile and run the game.

## Setup and Installation

1. **Download the Source Code**

   Save the `.java` files (`Driver.java`, `Player.java`, `Room.java`, `Option.java`) into a single directory on your computer.

2. **Open the Terminal**

   Navigate to the directory where you saved the Java files.

3. **Compile the Code**

   Run the following command to compile all the Java files:

   ```bash
   javac *.java
   ```

   This will generate `.class` files for each Java file.

4. **Run the Game**

   After successful compilation, run the game using:

   ```bash
   java Driver
   ```

## How to Play

1. **Start the Game**

   When you run `java Driver`, the game will start and prompt you to enter your name.

2. **Enter Your Name**

   Type your name and press `Enter`.

3. **Read the Descriptions**

   The game will display descriptions of the rooms you enter and the options available.

4. **Make Choices**

   - Options will be presented in a numbered list.
   - Type the number corresponding to your choice and press `Enter`.
   - If you enter an invalid option, the game will prompt you to try again.

5. **Interact with Objects**

   - Some options may involve picking up items or interacting with objects.
   - Your actions may trigger flags that unlock new options or rooms.

6. **Manage Your Turns**

   - Each action consumes a turn.
   - You have a limited number of turns to complete the game.
   - The game ends when you run out of turns or reach an ending condition.

7. **Explore and Enjoy**

   - Navigate through different rooms.
   - Make strategic decisions to reach the best possible outcome.

## Game Mechanics

- **Rooms**

  - **Bedroom**: Your starting point.
  - **Closet**: Connected to the bedroom.
  - **Hallway**: Central hub leading to other rooms.
  - **Storage**: Contains important items or events.
  - **Purifier**: Holds crucial game elements.
  - **Control Room**: Possible endgame location.

- **Options**

  - Each room presents several options.
  - Options may require certain conditions (flags) to be available.
  - Choosing an option can trigger flags, consume turns, and possibly change your location.

- **Flags**

  - Flags represent the game's state and track your progress.
  - Actions can set flags that unlock new options or affect outcomes.
  - Examples include `hasHammer`, `gameEnd`, `alarmVisit`, etc.

- **Turn Counter**

  - You start with a maximum number of turns (default is 30).
  - Each action reduces your remaining turns.
  - The game ends when you have no turns left.

## Class Structure

- **Driver.java**

  - The main class that initializes the game.
  - Handles user input and the game loop.
  - Creates rooms and sets up the story.

- **Player.java**

  - Represents the player character.
  - Tracks the player's name, current room, inventory (if implemented), and turn counter.

- **Room.java**

  - Represents a location in the game.
  - Contains descriptions, options, and flags.
  - Manages available options based on flags.

- **Option.java**

  - Represents an actionable choice in a room.
  - Contains conditions (flags needed), effects (flags triggered), descriptions, and results.
  - May lead to other rooms or change the game state.
