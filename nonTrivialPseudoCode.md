#Non-trivial functions pseudocode

METHOD generateWorld
    CREATE a random number generator 
    FOR each row y from 0 up to worldHeight
        FOR each row x from 0 to worldWidth
            GENERATE randvalue from 0 to 99 
                IF randValue < 20 
                     SET world[x][y] = WOOD 
                ELSE IF randValue < 35
                    SET world[x][y] = LEAVES 
                ELSE IF randValue < 50 
                    SET world[x][y] = STONE
                ELSE IF randValue < 70 
                    SET world[x][y] = IRON_ONE
                ELSE
                    world[x][y] = AIR
                END IF
            END FOR
    END METHOD


    METHOD movePlayer
        SWITCH direction toUpperCase
            CASE "W"
            CASE "UP"
                IF playerY > 0
                    DECREASE playerY by 1
            break

            CASE "S"
            CASE "DOWN"
                IF playerY < worldHeight -1 
                    INCREASE playerY by 1 
            CASE "A"
            CASE "LEFT"
                IF playerY > 0 
                    DECREASE playerX by 1
            break
            CASE "D"
            CASE "RIGHT"
                if playerX < worldWidth -1 
                    INCREASE playerX by 1 
            break 

    END METHOD


    METHOD displayWorld
        PRINT "world map:"
        PRINT top border 
        
        FOR each row from 0 to worldHeight
            PRINT left border
        
        FOR each column from 0 to worldWith
            IF x == playerX and y == playerX and !inSecretArea
                PRINT P in green 
            ELSE IF x == playerX and y == playerY and inSecretArea
                PRINT P in blue
            ELSE 
                PRINT symbol of the block in world[x][y]
        PRINT right border 
        END FOR
        PRINT bottom border
    END METHOD


    METHOD interactWithWorld
        SET blocktype = world[playerX][playerY]

        SWITCH blockType
            CASE WOOD:
                PRINT "You gather wood from the tree."
                ADD WOOD to inventory  
            CASE LEAVES:
                PRINT "You gather leaves from the tree."
                ADD LEAVES to inventory  
            CASE AIR:
                PRINT "Nothing to interact with here."
                ADD AIR to inventory  
            CASE STONE:
                PRINT "You gather stones from the ground."
                ADD STONE to inventory  
            CASE IRON_ORE:
                PRINT ""You mine iron ore from the ground."
                ADD IRON_ORE to inventory  
        DEFAULT:
            PRINT "Unrecognized block. Cannot interact."
        CALL waitForEnter
    END METHOD







