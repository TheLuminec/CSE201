# Text-Based Adventure Game

This is a text-based adventure game written in Java, featuring a customizable scripting language that allows you to define game content, including rooms, descriptions, options, conditions, actions, and an in-game timer.
## Table of Contents

- [Overview](#overview)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Setup and Compilation](#setup-and-compilation)
- [Running the Game](#running-the-game)
- [Scripting Language Guide](#scripting-language-guide)
  - [Script Structure](#script-structure)
  - [Global Settings](#global-settings)
  - [Rooms](#rooms)
  - [Descriptions](#descriptions)
  - [Options](#options)
  - [Conditions](#conditions)
  - [Actions](#actions)
  - [Time Costs](#time-costs)
  - [Special Rooms](#special-rooms)
  - [Example Script](#example-script)
- [Extending the Game](#extending-the-game)
- [License](#license)

## Overview

This game engine allows you to create interactive text-based adventure games. By writing scripts in a simple custom scripting language, you can define the game's rooms, descriptions, player options, and game logic without modifying the Java code.

## Features

- **Custom Scripting Language**: Define game content using a simple and flexible script format.
- **Conditional Descriptions and Options**: Show or hide descriptions and options based on player inventory and conditions.
- **Inventory Management**: Players can acquire and lose items that affect game progression.
- **In-Game Timer**: Actions can consume time, and the game can end when the timer runs out.
- **Multiple Conditions**: Options and descriptions can have multiple conditions for complex game logic.
- **Customizable Endings**: Define special rooms for game endings, such as winning or running out of time.

## Prerequisites

- Java Development Kit (JDK) 8 or higher
- A text editor to modify scripts and code
- Command-line interface (CLI) access for compilation and execution

## Setup and Compilation

1. **Clone or Download the Repository**: Obtain the Java source files (`Main.java`, `Game.java`, `Room.java`, `Option.java`, `Description.java`, `Player.java`) and the script file (`script.txt`).

2. **Ensure All Files Are in the Same Directory**: Place all `.java` files and the `script.txt` file in a single directory.

3. **Open a Terminal or Command Prompt**: Navigate to the directory containing the files.

4. **Compile the Java Source Files**:

   ```bash
   javac Main.java Game.java Room.java Option.java Description.java Player.java
   ```

   This command compiles the Java classes and generates `.class` files.

## Running the Game

After successful compilation, run the game using the following command:

```bash
java Main
```

The game will start, and you'll be presented with descriptions and options based on the `script.txt` file.

## Scripting Language Guide

The scripting language is designed to be intuitive and easy to use, allowing you to create rich game narratives without needing to modify the Java code.

### Script Structure

- The script is a plain text file (e.g., `script.txt`).
- Use `#` to add comments; any line starting with `#` is ignored by the parser.
- The script is divided into sections: global settings, rooms, descriptions, options, and actions.

### Global Settings

Define global game settings at the beginning of the script.

- `TIMER=<number>`: Sets the starting time for the game.
- `START=<room_id>`: Specifies the starting room ID.

**Example:**

```plaintext
TIMER=10
START=room1
```

### Rooms

Define rooms using the `<room_id>` syntax.

- Start a room definition with `<room_id>`.
- Each room can have multiple descriptions and options.

**Example:**

```plaintext
<room1>
```

### Descriptions

Descriptions are texts displayed to the player when they enter a room. You can have multiple descriptions per room, each with optional conditions.

- Start a description block with `[DESCRIPTION]`.
- Use `TEXT=` to define the description text. For multi-line text, leave the value empty and place the text on subsequent lines, ending with `ENDTEXT`.
- Use `CONDITION=` to specify conditions under which the description is shown.

**Example:**

```plaintext
[DESCRIPTION]
TEXT=
You are in a dark room.
There is a door to the north.
ENDTEXT
```

### Options

Options represent the choices available to the player in a room.

- Start an option block with `[O]`.
- Use `TEXT=` to define the option text.
- Use `CONDITION=` to specify conditions for the option to be available.
- Use `ACTIONS=` to define actions that occur when the option is selected.
- Use `TIMECOST=` to specify how much time the action consumes (default is 1).
- Use `NEXT=` to specify the ID of the next room to navigate to.

**Example:**

```plaintext
[O]
TEXT=Open the door
CONDITION=has_key
ACTIONS=removeItem:has_key
TIMECOST=1
NEXT=room2
```

### Conditions

Conditions determine whether a description or option is available, based on the player's inventory.

- List multiple conditions separated by commas.
- Use `!` before an item to check if the player does **not** have the item.
- All conditions must be met (logical AND).

**Example:**

```plaintext
CONDITION=has_key,!door_open
```

### Actions

Actions are executed when an option is selected.

- `addItem:<item>`: Adds an item to the player's inventory.
- `removeItem:<item>`: Removes an item from the player's inventory.
- List multiple actions separated by commas.

**Example:**

```plaintext
ACTIONS=addItem:torch,removeItem:match
```

### Time Costs

- `TIMECOST=<number>`: Specifies the time units consumed when the option is selected.
- Default time cost is 1 if not specified.
- Time can be increased by setting a negative `TIMECOST`.

**Example:**

```plaintext
TIMECOST=2
```

### Special Rooms

You can define special rooms for game endings or specific events.

#### Time End Room (`timeEnd`)

- If the timer runs out, the game transitions to the `timeEnd` room, if defined.
- Use this room to display a custom message or options when time expires.

**Example:**

```plaintext
<timeEnd>
[DESCRIPTION]
TEXT=Time's up! You failed to complete your mission.
[O]
TEXT=Restart the game
NEXT=room1
```

#### Winning Room

- Use a specific room conditon `endGame` to signify that the game ends at that room.

**Example:**

```plaintext
<win>
[DESCRIPTION]
TEXT=Congratulations! You have won the game.
```

### Example Script

Below is a simplified example script demonstrating various features:

```plaintext
# Global settings
TIMER=10
START=room1

# Room Definitions
<room1>
[DESCRIPTION]
TEXT=
You wake up in a mysterious room.
There is a door to the north and a key on the table.
ENDTEXT

[O]
TEXT=Take the key
CONDITION=!has_key
ACTIONS=addItem:has_key
NEXT=room1

[O]
TEXT=Open the door
CONDITION=has_key
ACTIONS=removeItem:has_key
NEXT=room2

<room2>
[DESCRIPTION]
TEXT=You enter a hallway with doors to the east and west.

[O]
TEXT=Go east
NEXT=win

[O]
TEXT=Go west
NEXT=lose

<win>
[DESCRIPTION]
TEXT=You found the exit and escaped! You win!

<lose>
[DESCRIPTION]
TEXT=You walked into a trap. Game over.

<timeEnd>
[DESCRIPTION]
TEXT=Time has run out. You failed to escape.

[O]
TEXT=Try again
NEXT=room1
```

## Extending the Game

- **Add New Rooms**: Define new rooms using the `<room_id>` syntax.
- **Create Complex Conditions**: Use multiple conditions to create intricate game logic.
- **Define New Actions**: Implement additional actions in the `processAction` method in `Game.java` if needed.
- **Adjust Time Mechanics**: Modify `TIMECOST` values and the starting `TIMER` to balance game difficulty.
- **Implement Save/Load**: Extend the Java code to support saving and loading game states.

## Disclosure
I'm bad at writing README's so this was written my ChatGPT.