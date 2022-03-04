package textadventure.game;

public class Game {
	
	 private Parser parser;
	    private Room currentRoom;
	    private boolean detailed;
	    private Player player;
	    //private CLS cls_var;
	    private int count;
	    private boolean seeKey;
	    private boolean seeFlashlight;
	    private boolean seeHolyWater;
	    private boolean seeMatches;
	    private boolean seePocket;
	    private boolean shineFlashlight;
	    private String password;
	    private boolean unlockComputer;
	    private boolean enterUsername;
	    
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
	        Item flashlight = new Item("flashlight", "flashlight description");
	        Item book = new Item("book", "book description");
	        Item holyWater = new Item("holy water", "holy water description");
	        Item matches = new Item("box of matches", "matches description");
	        
	        rightWing.setItem("paper", paper);
	        house.setItem("key", key);
	        dressingRoom.setItem("holy water", holyWater);
	        dressingRoom.setItem("matches", matches);
	        techBooth.setItem("flashlight", flashlight);
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
	        password = "E24E20R11J16";
	        
	        try {
	                //cls_var.main(); 
	            }catch(Exception e) {
	                System.out.println(e); 
	            }
	        
	        printInformation();
	    }
	    
	    public void play() {
	        while(true) {
	            Command command = parser.getCommand();
	            try {
	                //cls_var.main(); 
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
	            case "take":
	                grab(command);
	                break;
	            case "drop":
	                drop(command);
	                break;
	            case "examine":
	                examine(command);
	                break;
	            case "counter":
	                counter(command);
	            case "help":
	                break;
	            case "read":
	                read(command);
	                break;
	            case "shine":
	            	shine(command);
	            	break;
	            case "look":
	            	look(command);
	            	break;
	            case "unlock":
	            	unlock(command);
	            	break;
	            case "username":
	            	username(command);
	            	break;
	            case "password":
	            	password(command);
	            	break;
	            default:
	                System.out.println("that is not a valid command");
	        }
	    }
	    
	    public void password(Command command) {
	    	if(!enterUsername) {
	    		System.out.println("you cannot enter a password until you have entered a username. please enter a username");
	    		return;
	    	}
	    	else if(command.getSecondWord().equals(password)) {
	    		System.out.println("you have entered '" +password +"' as the password. you have successfully unlocked the computer. you can see a video with a play button on it on the screen that looks like a recording of a musical at this theater. the date says it was recorded yesterday");
	    		unlockComputer = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("that is not the right password. please try again");
	    		return;
	    	}
	    }
	    
	    public void username(Command command) {
	    	if(command.getSecondWord().equals("brian") && command.getLine().equals("adams")) {
	    		System.out.println("you entered 'Brian Adams' as the username. you now have to enter a password");
	    		enterUsername = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("that is not the correct username. please try again");
	    		return;
	    	}
	    }
	    
	    public void unlock(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("unlock what?");
	    		return;
	    	}
	    	else if(!command.getSecondWord().equals("toolbox")) {
	    		System.out.println("you cannot unlock that");
	    		return;
	    	}
	    	else if(!currentRoom.getName().equals("tech booth")) {
	    		System.out.println("you cannot do that here");
	    		return;
	    	}
	    	else if(player.getItem("key")==null) {
	    		System.out.println("you do not have a key to unlock the toolbox with");
	    		return;
	    	}
	    	else {
	    		System.out.println("you unlock the toolbox with the key. within the toolbox, you see a flashlight as well as other unimportant tools");
	    		player.removeItem("key");
	    		seeFlashlight = true;
	    	}
	    }
	    
	    public void hold(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("hold what?");
	    		return;
	    	}
	    	else if(!command.getSecondWord().equals("paper")) {
	    		System.out.println("you cannot do that");
	    		return;
	    	}
	    	else if(player.getItem("paper") == null) {
	    		System.out.println("you do not have the paper in your inventory");
	    		return;
	    	}
	    	else if(!currentRoom.getName().equals("dressing room")) {
	    		System.out.println("you cannot do that here");
	    		return;
	    	}
	    	else if(command.getLine().equals("up") || command.getLine().equals("to mirror")) {
	    		if(!shineFlashlight) {
	    			System.out.println("you hold the paper up to the mirror. you still see nothing on the paper");
	    			return;
	    		}
	    		System.out.println("you hold the paper up to the mirror.");
	    		player.getItem("paper").changeDescription("holding the paper up to the mirror, you can now see that the paper reads PASSWORD: E24E20R11J16. the writing has been magically changed and now does not appear backwards when you read it normally");
	    		System.out.println(player.getItem("paper").getDescription());
	    		return;
	    	}
	    	else {
	    		System.out.println("i don't understand. try saying hold ___ to ____ or hold ___ up");
	    		return;
	    	}
	    }
	    
	    public void look(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("look where? try up or down");
	    		return;
	    	}
	    	else if(!command.getSecondWord().equals("up") || !command.getSecondWord().equals("down")) {
	    		System.out.println("you cannot do that. try look up or look down");
	    		return;
	    	}
	    	else if(currentRoom.getName().equals("stage") && command.getSecondWord().equals("up")) {
	    		System.out.println("you look up and see a giant cage hanging from the ceiling by a rope. it is probably connected to a pulley and used as a set piece.");
	    		return;
	    	}
	    	else if(currentRoom.getName().equals("house") && command.getSecondWord().equals("down")) {
	    		System.out.println("you look down and see a key under one of the seats");
	    		seeKey = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("you cannot look "+command.getSecondWord() +" here");
	    		return;
	    	}
	    }
	    
	    public void shine(Command command) {
	        if(shineFlashlight) {
	        	System.out.println("you shine the flashlight on the paper. nothing changes");
	        	return;
	        }
	        else if(!command.getSecondWord().equals("flashlight")) {
	            System.out.println("you cannot do that");
	            return;
	        }
	        else if(command.getLine().equals("on paper") || command.getLine().equals("at paper")) {
	        	if(player.getItem("paper")==null) {
	        		System.out.println("you do not have the piece of paper in your inventory");
	        		return;
	        	}
	        	System.out.println("you shined the flashlight on the piece on paper.");
	        	player.getItem("paper").changeDescription("after shining the flashlight on the paper, you can now see writing, presumably written in invisible ink. the writing appears to be backwards and you cannot read it.");
	        	System.out.println(player.getItem("paper").getDescription());
	        	shineFlashlight = true;
	        	return;
	        }
	        else {
	        	System.out.println("you cannot do that. try shining the flashlight at something");
	        	return;
	        }
	    }
	    
	    public void read(Command command) {
	        if(!command.hasSecondWord()) {
	            System.out.println("read what?");
	            return;
	        }
	        else if(currentRoom.getName().equals("house") && command.getSecondWord().equals("engraving")) {
	        	System.out.println("the engraving reads: \n This theater is dedicated to Brian Adams, the founder of Lab Productions.");
	        	return;
	        }
	        else {
	        	System.out.println("you cannot read that. try examine or check your spelling");
	        	return;
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
	        else if((thingToExamine.equals("nun costume") || thingToExamine.equals("costume")) && currentRoom.getName().equals("dressing room")) {
	        	System.out.println("you examine the nun costume. you can see a pocket with things inside of it.");
	        	seePocket = true;
	        	return;
	        }
	        else if(thingToExamine.equals("pocket") && currentRoom.getName().equals("dressing room") && seePocket) {
	        	System.out.println("you examine the pocket of the costume. inside you see a bottle of holy water and a box of matches");
	        	seeMatches = true;
	        	seeHolyWater = true;
	        	return;
	        }
	        else if(currentRoom.getItem(thingToExamine) != null) {
	            if(seeKey || thingToExamine.equals("key")) {
	            	System.out.println("you do not see a key right now");
	            	return;
	            }
	            else if(seeFlashlight || thingToExamine.equals("flashlight")) {
	            	System.out.println("you do not see a flashlight right now");
	            	return;
	            }
	            else if(seeHolyWater || thingToExamine.equals("holy water")) {
	            	System.out.println("you do not see holy water right now");
	            	return;
	            }
	            else if(seeMatches || thingToExamine.equals("matches")) {
	            	System.out.println("you do not see matches right now");
	            	return;
	            }
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
	        String item;
	        if(command.hasLine()) {
	            item = command.getSecondWord() + command.getLine();
	        }
	        else {
	        	item = command.getSecondWord();
	        }
	        Item itemToGrab = currentRoom.getItem(item);
	        if(itemToGrab == null) {
	            System.out.println("you cannot " +command.getCommandWord() +" that");
	            return;
	        }
	        else if(seeKey || item.equals("key")) {
	        	System.out.println("you do not see a key right now");
            	return;
	        }
	        else if(seeFlashlight || item.equals("flashlight")) {
	        	System.out.println("you do not see a flashlight right now");
	        	return;
	        }
	        else if(seeHolyWater || item.equals("holy water")) {
	        	System.out.println("you do not see holy water right now");
	        	return;
	        }
	        else if(seeMatches || item.equals("matches")) {
	        	System.out.println("you do not see matches right now");
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
	        else if(command.hasLine()) {
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
	        else if(command.hasLine()) {
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
