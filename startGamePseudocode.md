    
    #Main loop of the game (startGame)
    
    METHOD startGame():
        WHILE true dDO:
            clearScreen()
            displayLegend()
            displayWorld()
            displayInventory()
            
            PRINT("Enter your action: 'WASD': Move, 'M': Mine, 'P': Place, 'C': Craft, 'I': Interact, 'Save': Save, 'Load': Load, 'Exit': Quit, 'Unlock': Unlock Secret Door"")

            INPUT command

            IF command is (W,A,S,D,Up,Down,Left,Right)
                if (unlockMode)
                    movementCommandEntered is True
                movePlayer(command)

            ELSE IF command is "m" 
                if (unlockMode)
                    miningCommandEntered is true
            mineBlock();

            ELSE IF command is "p" then
                displayInventory()
                ASK user for blockType
                placeBlock(blockType)
            
            ELSE IF command is "c" then
                displayCraftingRecipes()
                ASK user for recipe
                craftItem(recipe)
            
            ELSE IF command is "i" then
                interactWithWorld()

            ELSE IF  command is "save" then 
                ASK for fileName to save the game 
                saveGame(fileName)
            
            ELSE IF command is "load" THEN
                ASK for fileName to load the game
                loadGame(fileName)
            
            ELSE IF command is "exit" THEN
                PRINT("Exiting the game. Goodbye!")
                break;
            
            ELSE IF command is "look" 
                lookAround();
            
            ELSE IF command is "unlock"
                unlockMode = TRUE
            
            ELSE IF command is "open"

                IF unlockMode && craftingCommandEntered && miningCommandEntered && movementCommandEntered && secretDoorUnlocked = true THEN
                    resetWorld()
                    PRINT("Secret door unlocked!")
                    waitForEnter()

                ELSE  
                    PRINT("Invalid passkey. Try again!)
                    waitForEnter()
                    unlockMode = false;

                craftingCommandEntered = false;
                miningCommandEntered = false;
                movementCommandEntered = false;
                openCommandEntered = false;
            ELSE 
            PRINT("Invalid input. Please try again.")

            IF unlockMode
                IF command is "c"
                    craftingCommandEntered = true;
                ELSE IF command is "m"
                    miningCommandEntered = true
                ELSE IF command is "open"
                    openCommandEntered = true
            
            IF secretDoorUnlocked
                clearScreen()
                PRINT("You have entered the secret area!)
                inSecretArea is true
                resetWorld()
                secretDoorUnlocked = false
                fillInventory()
                waitForEnter()

        END WHILE
    END METHOD
            
