# Function Analysis
- Line 75-82 initGame
    - This function places the player in the middle of the map, and the inventory of the player became an empty array.

- Line 84-102 generateWorld
    - Creates a random number generator.
    - Loops trough the entire world, outer loop goes over each row y, and  the inner loop goes over each column x, covering the whole map. 
     - This methos generates a random value between 0-100 forn each cell.
     - Assign block types based on the cells randomly generated number each block type has their own range.

- Line 123-145 getBlockSymbol
    - removes color from AIR block
    - Gives color to every block

- Line 330-358 movePlayer
    - reads the input from the keyboard and if the player can move in the desiered direction moves the player.

- Line 361 mineBlock
    - checks the block type on which the player is standing, makes sure that it is not air
    - adds the mined block type to the players inventory then changes the block to air after mining it out(there is a bug, I can mine one block an infinite amount of times)
    - Prints what kind of block was mined out, if the block was AIR, then it says that theres no block to mine here

    
- Line 104-120 displayWorld
    - first, it prints out "World map: " text in CYAN color.
    - second, it prints out the top of the world map pattern
    - it uses iteration to display vertical borders, and to get players when they are in a secret area or not. Otherwise, a symbol of a block is printed out.
    -Then it prints out the end of the map 

- line 257-264 fillInventory
    -first, it clears inventory,
    - then with iteration, a certain type of block is added to the inventory

- line 433-448 craftItem
    - this function ensures the possibility to craft item
    - depending of the variable of switch(), it crafts different type of items.
    - at the end, there is a waitForEnter() function that ensures crafting when an enter is pressed.

- line 519 - 544 interactWithWorld
    - First, it initializes a variable to define the type of block that gathered
    - then it goes through a switch - case function, depending of the type of block, it prints out a sentence about gathering a block every time we gather a certain block. Then the block is added to the inventory 
    - after gathering a block, the waitForEnter() function provides a confirmation of gathering a block when an enter is pressed
