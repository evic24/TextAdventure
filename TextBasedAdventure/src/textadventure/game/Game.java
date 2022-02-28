package textadventure.game;

public class Game {
	
	 private Parser parser;
	    private Room currentRoom;
	    private boolean detailed;
	    private Player player;
	    private CLS cls_var;
	    private int count;
	    
	    public Game() {
	        parser = new Parser();
	        player = new Player();
	    }
	    
	    public static void main(String[] args) {
	        Game game = new Game();
	        game.setUpGame();
	        game.play();
	    }
	    
	    public void printInformation() {
	        System.out.println("room: " +currentRoom.getName());
	        if(detailed) {
	            System.out.println(currentRoom.getLongDescription());
	        }
	        else {
	            System.out.println(currentRoom.getShortDescription());
	        }
	        System.out.println(currentRoom.getExitString());
	        System.out.println(currentRoom.getInventoryString()); 
	    }
	    
	    public void setUpGame() {
	        //creates environment of game
	        Room stage = new Room("stage", "you are standing in the center of an empty stage looking out onto floor seats.", "you are standing in the center of an empty stage looking out onto rows of empty floor seats. velvet curtains block the stage from the wings. there’s an eerie vibe, as if you are not alone in this theater, yet there seems to be no sign of anyone else in this place.");
	        Room backstage = new Room("backstage", "you are backstage. there are lots of props and set pieces around.", "you have entered the backstage. a large velvet curtain separates you from the main stage. you can see many props and set pieces around, including a table and a pile of books.");
	        Room rightWing = new Room("right wing", "you are in the right wing of the backstage area.", "you have entered the right wing of the backstage area. to the southwest is the backstage and to the west is the stage. dark velvet curtains separate you from the main stage. you can see a piece of paper on the ground in front of you.");
	        Room leftWing = new Room("left wing", "you are in the left wing of the backstage area.", "you have entered the left wing of the backstage area. to the south is the dressing room, to the east is the stage, and to the southeast is the backstage. Dark velvet curtains separate you from the main stage. off to the side is a headset and a theater script.");
	        Room dressingRoom = new Room("dressing room", "you are in the dressing room. you can see costumes and a mirror.", "you have entered the dressing room. to the north is the left wing and to the east is the backstage. various costumes hang from clothing racks against the wall. there is a long mirror against the other wall with a poster attached to it.");
	        Room house = new Room("house", "you are standing in the main aisle of the house. rows of empty floor seats surround you.", "you are standing in the main aisle of the house. rows of empty floor seats surround you. there is an engraving on the side of one of the seats. to the north, you see a large balcony with technical machinery that looks onto the stage.");
	        Room techBooth = new Room("tech booth", "you are standing in the tech booth, also known as the control booth, on a balcony facing the stage.", "you are standing in the tech booth, also known as the control booth, on a balcony above the house facing the stage. there is various technical equipment here. on a long table are a soundboard, a lighting board, a computer, and tape.");
	        
	        Item paper = new Item("paper", "paper description"); //change description
	        Item key = new Item("key", "key description");
	        Item poster = new Item("poster", "poster description");
	        Item flashlight = new Item("flashlight", "flashlight description");
	        Item musicBox = new Item("music box", "music box description");
	        Item book = new Item("book", "book description");
	        Item holyWater = new Item("holy water", "holy water description");
	        Item matches = new Item("box of matches", "matches description");
	        
	        rightWing.setItem("paper", paper);
	        dressingRoom.setItem("poster", poster);
	        house.setItem("key", key);
	        dressingRoom.setItem("holy water", holyWater);
	        dressingRoom.setItem("box of matches", matches);
	        techBooth.setItem("flashlight", flashlight);
	        backstage.setItem("music box", musicBox);
	        backstage.setItem("book", book);
	        
	        stage.setExit("south", backstage);
	        stage.setExit("east", rightWing);
	        stage.setExit("west", leftWing);
	        backstage.setExit("north", stage);
	        backstage.setExit("northeast", rightWing);
	        backstage.setExit("west", dressingRoom);
	        backstage.setExit("northwest", leftWing);
	        rightWing.setExit("west", stage);
	        rightWing.setExit("southwest", backstage);
	        leftWing.setExit("south", dressingRoom);
	        leftWing.setExit("east", stage);
	        leftWing.setExit("southeast", backstage);
	        dressingRoom.setExit("north", leftWing);
	        dressingRoom.setExit("east", backstage);
	        house.setExit("south", stage);
	        house.setExit("north", techBooth);
	        techBooth.setExit("south", house);
	        
	        currentRoom = stage;
	        try {
	                cls_var.main(); 
	            }catch(Exception e) {
	                System.out.println(e); 
	            }
	        printInformation();
	    }
	    
	    public void play() {
	        while(true) {
	            Command command = parser.getCommand();
	            try {
	                cls_var.main(); 
	            }catch(Exception e) {
	                System.out.println(e); 
	            }
	            processCommand(command);
	            count++;
	        }
	    }
	    
	    public void processCommand(Command command) {
	        String commandWord = command.getCommandWord();
	        
	        switch(commandWord) {
	            case "speak":
	                System.out.println("you wanted me to speak this word, " +command.getSecondWord());
	                break;
	            case "go":
	                goRoom(command);
	                break;
	            case "verbose":
	                detailed = true;
	                break;
	            case "brief":
	                detailed = false;
	                break;
	            case "inventory":
	                System.out.println(player.getInventoryString());
	                break;
	            case "grab":
	            case "get":
	            case "take":
	                grab(command);
	                break;
	            case "drop":
	                drop(command);
	                break;
	            case "examine":
	            case "x":
	                examine(command);
	                break;
	            case "counter":
	                counter(command);
	            case "help":
	                break;
	            case "read":
	                read(command);
	                break;
	            case "look":
	            	
	            default:
	                System.out.println("that is not a valid command");
	        }
	    }
	    
	    public void look(Command command) {
	    	
	    }
	    
	    public void shine(Command command) {
	        if(!command.getSecondWord().equals("flashlight")) {
	            System.out.println("you cannot do that");
	            return;
	        }
	        if(command.getLine().equals("on paper") || command.getLine().equals("at paper")) {
	        	System.out.println("you shined the flashlight on the piece on paper. you can now see writing")
	        }
	    }
	    
	    public void read(Command command) {
	        if(!command.hasSecondWord()) {
	            System.out.println("read what?");
	        }
	        String item = command.getSecondWord();
	        Item itemToRead = currentRoom.getItem(item);
	        if(itemToRead == null) {
	            itemToRead = player.getItem(item);
	        }
	        switch(item) {
	            case "paper":
	                System.out.println(itemToRead.getDescription());
	                break;
	            case "engraving":
	                System.out.println("this is what the engraving says"); //change this later
	                break;
	            default: 
	                System.out.println("you cannot read that");
	        }
	    }
	    
	    public void counter(Command command) {
	        if(command.hasSecondWord()) {
	            System.out.println("i don't understand");
	            return;
	        }
	        int movesLeft = 200 - count;
	        System.out.println("moves left: " +movesLeft);
	    }
	    
	    public void examine(Command command) {
	        String printString = "examining ";
	        String thingToExamine = null;
	        if(!command.hasSecondWord()) {
	            System.out.println("examine what?");
	            return;
	        }
	        if(!command.hasLine()) {
	            thingToExamine = command.getSecondWord();
	        }
	        else if(command.hasLine()) {
	            thingToExamine = command.getSecondWord() + command.getLine();
	        }
	        
	        if(thingToExamine.equals(currentRoom.getName())) {
	            printString += "the room: " +currentRoom.getName() + "\n" + currentRoom.getLongDescription();
	        }
	        else if(currentRoom.getItem(thingToExamine) != null) {
	            printString += "the item: " +currentRoom.getItem(thingToExamine).getName() + "\n" +currentRoom.getItem(thingToExamine).getDescription();
	        }
	        else if(player.getItem(thingToExamine) != null) {
	            printString += "the item: " +player.getItem(thingToExamine).getName() + "\n" +player.getItem(thingToExamine).getDescription();
	        }
	        else {
	            System.out.println("you can't examine that");
	            return;
	        }
	        System.out.println(printString);
	    }
	    
	    public void grab(Command command) {
	        if(!command.hasSecondWord()) {
	            System.out.println(command.getCommandWord() +" what?");
	            return;
	        }
	        if(command.hasLine()) {
	            System.out.println("i don't understand");
	            return;
	        }
	        String item = command.getSecondWord();
	        Item itemToGrab = currentRoom.getItem(item);
	        if(itemToGrab == null) {
	            System.out.println("you cannot " +command.getCommandWord() +" that");
	            return;
	        }
	        else {
	            currentRoom.removeItem(item);
	            player.setItem(item, itemToGrab);
	            System.out.println("you took the " +item); 
	        }
	    }
	    
	    public void drop(Command command) {
	        if(!command.hasSecondWord()) {
	            System.out.println("drop what?");
	            return;
	        }
	        if(command.hasLine()) {
	            System.out.println("i don't understand");
	            return;
	        }
	        String item = command.getSecondWord();
	        Item itemToDrop = player.getItem(item);
	        if(itemToDrop == null) {
	            System.out.println("you do not have " +command.getSecondWord() +" in your inventory");
	            return;
	        }
	        else {
	            player.removeItem(item);
	            currentRoom.setItem(item, itemToDrop);
	            System.out.println("you dropped the " +item); 
	        }
	    }
	    
	    public void goRoom(Command command) {
	        if(!command.hasSecondWord()) {
	            System.out.println("go where?");
	            return;
	        }
	        if(command.hasLine()) {
	            System.out.println("i don't understand");
	            return;
	        }
	        String direction = command.getSecondWord();
	        Room nextRoom = currentRoom.getExit(direction);
	        if(nextRoom == null) {
	            System.out.println("you can't go there");
	            return;
	        }
	        else {
	            currentRoom = nextRoom;
	            printInformation();
	        }
	    }
	}
