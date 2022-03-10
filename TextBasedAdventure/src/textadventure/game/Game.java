package textadventure.game;

public class Game {
	
	 private Parser parser;
	    private Room currentRoom;
	    private boolean detailed;
	    private Player player;
	    //private CLS cls_var;
	    private int moveCount;
	    private boolean seeKey;
	    private boolean seeFlashlight;
	    private boolean seeHolyWater;
	    private boolean seeMatches;
	    private boolean seePocket;
	    private boolean shineFlashlight;
	    private boolean holdToMirror;
	    private String password;
	    private boolean unlockComputer;
	    private boolean enterUsername;
	    private boolean musicBoxOn;
	    private int musicCount;
	    private boolean play;
	    private boolean phantomAppear;
	    private boolean pulleyDown;
	    
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
					printItemsInRoom();
	    }
	    
	    public void setUpGame() {
	        //creates environment of game
	        Room stage = new Room("stage", "you are standing in the center of an empty stage looking out onto floor seats.", "you are standing in the center of an empty stage looking out onto rows of empty floor seats. velvet curtains block the stage from the wings. there's an eerie vibe, as if you are not alone in this theater, yet there seems to be no sign of anyone else in this place.");
	        Room backstage = new Room("backstage", "you are backstage. there are lots of props and set pieces around.", "you have entered the backstage. a large velvet curtain separates you from the main stage. you can see many props and set pieces around, including a table with a scroll and a music box on it.");
	        Room rightWing = new Room("right wing", "you are in the right wing of the backstage area.", "you have entered the right wing of the backstage area. to the southwest is the backstage and to the west is the stage. dark velvet curtains separate you from the main stage. you can see a piece of paper on the ground in front of you.");
	        Room leftWing = new Room("left wing", "you are in the left wing of the backstage area.", "you have entered the left wing of the backstage area. to the south is the dressing room, to the east is the stage, and to the southeast is the backstage. Dark velvet curtains separate you from the main stage. on the wall are two pulleys, one tied to the wall and the other open and ready to pull");
	        Room dressingRoom = new Room("dressing room", "you are in the dressing room. you can see costumes and a mirror.", "you have entered the dressing room. to the north is the left wing and to the east is the backstage. various costumes hang from clothing racks against the wall including a nun costume. there is a long mirror against the other wall.");
	        Room house = new Room("house", "you are standing in the main aisle of the house. rows of empty floor seats surround you.", "you are standing in the main aisle of the house. rows of empty floor seats surround you. there is an engraving on the side of one of the seats. there seems to be some object underneath one of the seats. to the north, you see a large balcony with technical machinery that looks onto the stage.");
	        Room techBooth = new Room("tech booth", "you are standing in the tech booth, also known as the control booth, on a balcony facing the stage.", "you are standing in the tech booth, also known as the control booth, on a balcony above the house facing the stage. there is various technical equipment here. on a long table is a computer. next to it is a large toolbox with what appears to be a keyhole.");
	        
	        Item paper = new Item("paper", "the paper is small and appears to have no writing on it. however, you can see a slight difference in texture in some areas of the paper");
	        Item key = new Item("key", "the key is silver and metal. it probably unlocks something in this theater.");
	        Item flashlight = new Item("flashlight", "the flashlight is an LED UV flashlight. it is black and lightweight.");
	        Item scroll = new Item("scroll", "the scroll is small and fragile. it appears to be old and contains writing on it");//"the  is titled 'Getting Rid of Ghosts. the front page says:\n'when you have a ghost or phantom trapped, spray holy water on it and light it on fire to get rid of it.'"
	        Item holyWater = new Item("holy water", "the holy water is contained in a plastic spray bottle with a label that says 'holy water'. it is clear and somewhat shimmery.");
	        Item matches = new Item("matches", "the box of matches is small and contains 12 matches. it seems to be unused.");
	        Item musicBox = new Item("music box", "the music box is pink and looks very old. it has a hand crank on the side of it. the top is open and you can see a small ballerina in the center of the box.");
	        
	        rightWing.setItem("paper", paper);
	        house.setItem("key", key);
	        dressingRoom.setItem("holy water", holyWater);
	        dressingRoom.setItem("matches", matches);
	        techBooth.setItem("flashlight", flashlight);
	        backstage.setItem("scroll", scroll);
	        backstage.setItem("music box", musicBox);
	        
	        stage.setExit("south", backstage);
	        stage.setExit("east", rightWing);
	        stage.setExit("west", leftWing);
	        stage.setExit("north", house);
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
	        
	        welcomeString();
	        printInformation();
	        play = true;
	    }
	    
	    public void play() {
	        while(play) {
	            Command command = parser.getCommand();
	            try {
	                //cls_var.main(); 
	            }catch(Exception e) {
	                System.out.println(e); 
	            }
	            processCommand(command);
	            loseMoveCount();
	            loseMusicBox();
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
	            	System.out.println("long room descriptions will always be printed now");
	                detailed = true;
	                break;
	            case "brief":
	            	System.out.println("short room descriptions will always be printed now");
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
	                break;
	            case "help":
	            	help();
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
	            case "play":
	            	play(command);
	            	break;
	            case "info":
	            	welcomeString();
	            	break;
	            case "hold":
	            	hold(command);
	            	break;
	            case "stop":
	            	stop(command);
	            	break;
	            default:
	                System.out.println("that is not a valid command");
	        }
	    }
	    
	    public void help() {
	    	System.out.println("here is a list of valid commands:\n\nBRIEF, to only print short room descriptions\nVERBOSE, to only print long room descriptions\nGO followed by a direction, to move to another room\nINVENTORY, to print a list of the items in your personal inventory\nGRAB or TAKE followed by an item, to place it in your personal inventory\nDROP followed by an item, to remove it from your personal inventory and place it in the current room\nEXAMINE followed by a room or object, to get the description of the room or object\nCOUNTER, to see how many moves you have left\nHELP, to get this list again\nREAD followed by an object\nSHINE ____ AT ____\nLOOK followed by up or down\nUNLOCK followed by an object\nUSERNAME\nPASSWORD\nPLAY followed by an object\nHOLD ___ TO ____\nINFO, to print the welcome message");
	    }
	    
	    public void pull(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("pull what?");
	    		return;
	    	}
	    	else if(!command.getSecondWord().equals("pulley")) {
	    		System.out.println("you cannot pull that");
	    		return;
	    	}
	    	else if(!phantomAppear) {
	    		if(pulleyDown) {
	    			System.out.println("you pull the pulley. the cage is lifted up back to the ceiling");
	    			pulleyDown = false;
	    			return;
	    		}
	    		else {
	    			System.out.println("you pull the pulley. the cage drops down from the ceiling to the stage floor. it makes a loud sound");
	    			pulleyDown = true;
	    			return;
	    		}
	    	}
	    	else if(!pulleyDown) {
	    		System.out.println("you pull the pulley. the cage drops down and traps the phantom inside of it");
	    		pulleyDown = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("you pull the pulley. the cage is lifted up back to the ceiling. the phantom is not trapped anymore and looks like he is going to kill you unless you trap him again");
	    		pulleyDown = false;
	    	}
	    }
	    
	    public void play(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("play what?");
	    		return;
	    	}
	    	else if(command.getSecondWord().equals("music")) {
	    		if(musicBoxOn) {
	    			System.out.println("the music box is already playing");
	    			return;
	    		}
	    		else if(currentRoom.getItem("music box")!=null) {
	    			System.out.println("you turn the handle of the music box. music starts playing and the ballerina starts turning. it is a gentle sound and you are vibing with it");
	    			musicBoxOn = true;
	    		}
	    		else if(player.getItem("music box")!=null) {
	    			System.out.println("you must drop the music box to play it.");
	    			return;
	    		}
	    		else {
	    			System.out.println("you do not see a music box");
	    			return;
	    		}
	    	}
	    	else {
	    		System.out.println("you cannot play that");
	    		return;
	    	}
	    }
	    
	    public void stop(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("stop what?");
	    		return;
	    	}
	    	else if(command.getSecondWord().equals("music")) {
	    		if(!musicBoxOn) {
	    			System.out.println("the music box is not playing right now so you cannot stop it");
	    			return;
	    		}
	    		else if(currentRoom.getItem("music box")!=null) {
	    			System.out.println("you stop the handle of the music box. music stops playing and the ballerina stops turning. you feel normal");
	    			musicBoxOn = true;
	    		}
	    		else if(player.getItem("music box")!=null) {
	    			System.out.println("you must drop the music box to stop it.");
	    			return;
	    		}
	    		else {
	    			System.out.println("you do not see a music box");
	    			return;
	    		}
	    	}
	    	else {
	    		System.out.println("you cannot stop that");
	    		return;
	    	}
	    }
	    
	    public void password(Command command) {
	    	if(unlockComputer) {
	    		System.out.println("you already unlocked the computer. try a different command");
	    		return;
	    	}
	    	else if(!enterUsername) {
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
	    	if(unlockComputer) {
	    		System.out.println("you already unlocked the computer");
	    		return;
	    	}
	    	else if(enterUsername) {
	    		System.out.println("you already entered the username. you now have to enter a password");
	    		return;
	    	}
	    	else if(command.getSecondWord().equals("brian") && command.getLine().equals(" adams")) {
	    		System.out.println("you entered 'Brian Adams' as the username. the computer reads: enter a password");
	    		enterUsername = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("the computer reads: incorrect username. try again");
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
	    		System.out.println("you do not see anything to unlock");
	    		return;
	    	}
	    	else if(player.getItem("key")==null) {
	    		System.out.println("you need a key to unlock the toolbox");
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
	    	String item;
	    	if(command.hasLine()) {
	    		item = command.getSecondWord() + command.getLine();
	    	}
	    	else {
	    		item = command.getSecondWord();
	    	}
	    	if(currentRoom.getName().equals("dressing room") && item.equals("paper to mirror") && player.getItem("paper")!=null) {
	    		if(!shineFlashlight) {
	    			System.out.println("you hold the paper up to the mirror. you still see nothing on the paper");
	    			return;
	    		}
	    		else {
	    			System.out.println("you hold the paper up to the mirror.");
	    			player.getItem("paper").changeDescription("the paper is small and contains writing on it that does not appear backwards anymore. you can see that it has been magically changed and you can read it while looking at it normally too");
		    		System.out.println(player.getItem("paper").getDescription());
		    		holdToMirror = true;
		    		return;
	    		}
	    	}
	    	else if(player.getItem(item)!=null) {
	    		System.out.println("you hold the " +item);
	    		return;
	    	}
	    	else if(player.getItem(item)==null) {
	    		System.out.println("you do not have the " +item +" in your inventory");
	    		return;
	    	}
	    	else {
	    		System.out.println("you cannot hold that");
	    		return;
	    	}
	    }
	    
	    public void look(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("look where? try up or down");
	    		return;
	    	}
	    	else if(!command.getSecondWord().equals("up") && !command.getSecondWord().equals("down")) {
	    		System.out.println("you cannot look there. try look up or look down");
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
	    		System.out.println("you look "+command.getSecondWord() +". you do not see anything special");
	    		return;
	    	}
	    }
	    
	    public void shine(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("shine what?");
	    		return;
	    	}
	        else if(!command.getSecondWord().equals("flashlight")) {
	            System.out.println("you cannot shine that");
	            return;
	        }
	        else if(!command.hasLine()) {
	        	System.out.println("you shine the flashlight. nothing changes so you turn it off");
	        	return;
	        }
	        else if(command.getLine().equals(" at paper")) {
	        	if(player.getItem("paper")==null) {
	        		System.out.println("you do not have the paper in your inventory");
	        		return;
	        	}
	        	else if(shineFlashlight) {
		        	System.out.println("you shine the flashlight on the paper. nothing changes so you turn it off");
		        	return;
		        }
	        	else {
	        		System.out.println("you shine the flashlight on the piece on paper.");
	        		player.getItem("paper").changeDescription("the paper is small and you can see writing on it now, presumably written in invisible ink. the writing appears to be backwards and you cannot read it.");
		        	System.out.println(player.getItem("paper").getDescription());
		        	shineFlashlight = true;
		        	return;
	        	}
	        }
	        else {
	        	System.out.println("you cannot shine the flashlight there");
	        	return;
	        }
	    }
	    
	    public void read(Command command) {
	        if(!command.hasSecondWord()) {
	            System.out.println("read what?");
	            return;
	        }
	        else if(currentRoom.getName().equals("house") && command.getSecondWord().equals("engraving")) {
	        	System.out.println("the engraving reads: \n\tThis theater is dedicated to Brian Adams, the founder of Lab Productions.");
	        	return;
	        }
	        else if(command.getSecondWord().equals("paper")) {
	        	if(player.getItem("paper")==null) {
	        		System.out.println("you must have the paper in your inventory to read it");
	        		return;
	        	}
	        	else if(!shineFlashlight) {
	        		System.out.println("the paper is blank to your eye");
	        		return;
	        	}
	        	else if(holdToMirror) {
	        		System.out.println("the paper reads: \n\t'PASSWORD: E24E20R11J16'");
	        		return;
	        	}
	        	else {
	        		System.out.println("the writing on the paper is backwards. you cannot read it");
	        		return;
	        	}
	        }
	        else if(command.getSecondWord().equals("scroll")) {
	        	if(player.getItem("scroll")==null) {
	        		System.out.println("you must have the scroll in your inventory to read it");
	        		return;
	        	}
	        	else {
	        		System.out.println("the scroll reads: \n\t'Getting Rid of Ghosts: when you have a ghost or phantom trapped, spray holy water on it and light it on fire to get rid of it.'");
	        		return;
	        	}
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
	        int movesLeft = 200 - moveCount;
	        System.out.println("moves left: " +movesLeft);
	    }
	    
	    public void examine(Command command) {
	        String printString = "examining ";
	        String thingToExamine = null;
	        if(!command.hasSecondWord()) {
	            System.out.println("examine what?");
	            return;
	        }
	        else if(!command.hasLine()) {
	            thingToExamine = command.getSecondWord();
	        }
	        else {
	            thingToExamine = command.getSecondWord() + command.getLine();
	        }
	        if(thingToExamine.equals(currentRoom.getName()) || thingToExamine.equals("room")) {
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
	        else if(thingToExamine.equals("computer") && currentRoom.getName().equals("tech booth") && !unlockComputer) {
	        	System.out.println("you examine the computer. on the front screen is a login page with places to enter in a username and password. you have a feeling that there is something important on this computer. to enter a username, type 'username' followed by the username. to enter a password, type 'password' followed by a password.");
	        	return;
	        }
	        else if(currentRoom.getItem(thingToExamine) != null) {
	            if(!seeKey && thingToExamine.equals("key")) {
	            	System.out.println("you do not see a key right now");
	            	return;
	            }
	            else if(!seeFlashlight && thingToExamine.equals("flashlight")) {
	            	System.out.println("you do not see a flashlight right now");
	            	return;
	            }
	            else if(!seeHolyWater && thingToExamine.equals("holy water")) {
	            	System.out.println("you do not see holy water right now");
	            	return;
	            }
	            else if(!seeMatches && thingToExamine.equals("matches")) {
	            	System.out.println("you do not see matches right now");
	            	return;
	            }
	        	printString += "the item: " +currentRoom.getItem(thingToExamine).getName() + "\n" +currentRoom.getItem(thingToExamine).getDescription();
	        }
	        else if(player.getItem(thingToExamine) != null) {
	            printString += "the item: " +player.getItem(thingToExamine).getName() + "\n" +player.getItem(thingToExamine).getDescription();
	        }
	        else {
	            System.out.println("you see nothing special about the " +thingToExamine);
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
	        else if(!seeKey && item.equals("key")) {
	        	System.out.println("you do not see a key right now");
            	return;
	        }
	        else if(!seeFlashlight && item.equals("flashlight")) {
	        	System.out.println("you do not see a flashlight right now");
	        	return;
	        }
	        else if(!seeHolyWater && item.equals("holy water")) {
	        	System.out.println("you do not see holy water right now");
	        	return;
	        }
	        else if(!seeMatches && item.equals("matches")) {
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
	        String item;
	        if(command.hasLine()) {
	        	item = command.getSecondWord() + command.getLine();  
	        }
	        else {
	        	item = command.getSecondWord();
	        }
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
	            System.out.println("you cannot go there");
	            return;
	        }
	        String direction = command.getSecondWord();
	        Room nextRoom = currentRoom.getExit(direction);
	        if(nextRoom == null) {
	            System.out.println("you cannot go there");
	            return;
	        }
	        else {
	            currentRoom = nextRoom;
	            printInformation();
	        }
	    }
	    
	    public void printItemsInRoom() {
	    	String invString = currentRoom.getInventoryString();
	    	if(!seeKey && invString.indexOf("key")!=-1) {
	    		String newInvString = invString.substring(0, invString.indexOf("key")) + invString.substring(invString.indexOf("key") + 3, invString.length());
	    		System.out.println(newInvString);
					return;
	    	}
				else if(!seeFlashlight && invString.indexOf("flashlight")!=-1) {
					String newInvString = invString.substring(0, invString.indexOf("flashlight")) + invString.substring(invString.indexOf("flashlight") + 10, invString.length());
	    		System.out.println(newInvString);
					return;
				}
				else if(!seeHolyWater && invString.indexOf("holy water")!=-1) {
					String newInvString = invString.substring(0, invString.indexOf("holy water")) + invString.substring(invString.indexOf("matches") + 7, invString.length());
	    		System.out.println(newInvString);
					return;
				}
				else {
					System.out.println(invString);
					return;
				}
	    }
	    
	    public void welcomeString() {
	    	System.out.println("welcome to elena's text adventure game!\n\nyou are in a theater that is currently haunted by a phantom. the entire cast of the musical Phantom of the Opera has disappeared just before opening night! you must figure out how to get them back and make them reappear in time for their show before the phantom kills you. \n\nthe theater contains multiple rooms and various objects that will be useful for you. some objects cannot be taken unless you have seen them. many of these objects work together to aid you on your quest. \n\nto win the game, you must make the phantom bring everyone back within 200 moves (each move is one command). to see how many moves you have left, type 'counter'. \n\nto always print long room descriptions, type 'verbose'. to always print short room descriptions, type 'brief'. if you want help or a list of valid commands, type 'help'. to see this message again, type 'info'.\n\n");
	    }
	    
	    public void loseMusicBox() {
	    	if(musicBoxOn) {
	    		musicCount++;
	    	}
	    	else {
	    		return;
	    	}
	    	if(musicCount>8) {
	    		System.out.println("out of the corner of your eye, you see a shadow swoop down. by leaving the music box playing, you have attracted the phantom. you lose your vision and die. game over.");
	    		play = false;
	    		return;
	    	}
	    	else if(musicCount>=5) {
	    		
	    		System.out.println("the music continues to play. it sounds very creepy. you have a weird feeling and think that you should probably stop the music before something bad happens.");
	    		return;
	    	}
	    	else {
	    		System.out.println("the music continues to play. it sounds a bit creepy now");
	    		return;
	    	}
	    }
	    
	    public void loseMoveCount() {
	    	moveCount++;
	    	if(moveCount==200) {
	    		System.out.println("\nyour move count = 200. you are out of moves! game over.");
	    		play = false;
	    		return;
	    	}
	    	else if(moveCount==150) {
	    		System.out.println("\nwarning: your move count = 150. you only have 50 moves left! good luck!");
	    		return;
	    	}
	    	else if(moveCount==100) {
	    		System.out.println("\nwarning: your move count = 100. you only have 100 moves left! good luck!");
	    		return;
	    	}
	    	else {
	    		return;
	    	}
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	}