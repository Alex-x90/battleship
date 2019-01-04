public class Battleship extends ConsoleProgram
{
    Player players[] = new Player[2];
    public void run()
    {
        players[0]=new Player();
        players[1]=new Player();
        System.out.println("Place your ships.");
        players[0].printMyShips();
        for(int i=0;i<Player.SHIP_LENGTHS.length;i++)
        {
            placeShip();
            players[0].printMyShips();
        }
        
        for(int i=0;i<Player.SHIP_LENGTHS.length;i++)
        {
            int col=-1;
            int row=-1;
            int direction=-1;
            boolean finished=false;
            while(!finished)
            {
                col = Randomizer.nextInt(0,Grid.NUM_COLS-1);
                row = Randomizer.nextInt(0,Grid.NUM_ROWS-1);
                direction = Randomizer.nextInt(0,1);
                finished=true;
                if(players[1].validShip(row,col,direction))
                    players[1].chooseShipLocation(row,col,direction);
                else
                    finished=false;
            }
        }
        
        while(!players[0].win()&&!players[1].win())
        {
            System.out.println("Your guesses:");
            players[1].printOpponentGuesses();
            askForGuess();
            if(players[1].win())
            {
                System.out.println("Congrats! You win.");
                continue;
            }
            
            int col=-1;
            int row=-1;
            boolean finished=false;
            while(!finished)
            {
                col = Randomizer.nextInt(0,Grid.NUM_COLS-1);
                row = Randomizer.nextInt(0,Grid.NUM_ROWS-1);
                finished=true;
                if(players[0].alreadyGuessed(row,col))
                    finished = false;
                else
                {
                    finished = true;
                    players[0].recordOpponentGuess(row,col);
                }
            }
            sleep(1000);
            System.out.println("Opponent's guesses:");
            players[0].printOpponentGuesses();
            sleep(1000);
            System.out.println("Your ships:");
            players[0].printMyShips();
            sleep(1000);
            if(players[0].win())
                System.out.println("Sorry, you lose.");
        }
        
    }
    
    void sleep(int time)
    {
       try {
            Thread.sleep(time);
         } catch (Exception e) {} 
    }
    
    void askForGuess()
    {
        int col=-1;
        int row=-1;
        while(col<1||col>Grid.NUM_COLS)
        {
            col = readInt("Please enter a valid column: ");
        }
        while(row<1||row>Grid.NUM_ROWS)
        {
            row = readInt("Please enter a valid row: ");
        }
        players[1].recordOpponentGuess(row-1,col-1);
        
    }
    
    void placeShip()
    {
        boolean repeat = false;
        int col;
        int row;
        int direction;
        boolean finished=false;
        while(!finished)
        {
            if(repeat)
            {
                System.out.println("You can't place overlapping ships or");
                System.out.println("ships that go out of the field.");
            }
            col=-1;
            row=-1;
            direction=-1;
            while(col<1||col>Grid.NUM_COLS)
            {
                col = readInt("Please enter a valid column: ");
            }
            while(row<1||row>Grid.NUM_ROWS)
            {
                row = readInt("Please enter a valid row: ");
            }
            while(direction<1||direction>2)
            {
                direction = readInt("Please enter a valid direction: ");
            }
            finished=true;
            if(players[0].validShip(row-1,col-1,direction-1))
                players[0].chooseShipLocation(row-1,col-1,direction-1);
            else
                finished = false;
            if(!repeat)
                repeat=true;
        }
        
    }
}
