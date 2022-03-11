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
	    private boolean play;
	    private boolean phantomAppear;
	    private boolean pulleyDown;
	    private boolean playVideo;
	    private boolean trapPhantom;
	    private int musicCount;
	    private int phantomCount;
	    private boolean sprayWater;
	    private boolean lightMatch;
	    private boolean takeHolyWater;
	    private boolean takeMatches;
	    private boolean seeCage;
	    
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
	        printItemsInRoom();
	        System.out.println(currentRoom.getExitString()); 
	    }
	    
	    public void setUpGame() {
	        //creates environment of game
	        Room stage = new Room("stage", "you are standing in the center of an empty stage looking out onto floor seats.", "you are standing in the center of an empty stage looking out onto rows of empty floor seats. velvet curtains block the stage from the wings. there's an eerie vibe, as if you are not alone in this theater, yet there seems to be no sign of anyone else in this place.");
	        Room backstage = new Room("backstage", "you are backstage. there are lots of props and set pieces around.", "you have entered the backstage. a large velvet curtain separates you from the main stage. you can see many props and set pieces around.");
	        Room rightWing = new Room("right wing", "you are in the right wing of the backstage area.", "you have entered the right wing of the backstage area. to the southwest is the backstage and to the west is the stage. dark velvet curtains separate you from the main stage.");
	        Room leftWing = new Room("left wing", "you are in the left wing of the backstage area.", "you have entered the left wing of the backstage area. to the south is the dressing room, to the east is the stage, and to the southeast is the backstage. Dark velvet curtains separate you from the main stage. on the wall are two pulleys, one tied to the wall and the other open and ready to pull");
	        Room dressingRoom = new Room("dressing room", "you are in the dressing room. you can see costumes and a mirror.", "you have entered the dressing room. to the north is the left wing and to the east is the backstage. various costumes hang from clothing racks against the wall including a nun costume. there is a long mirror against the other wall.");
	        Room house = new Room("house", "you are standing in the main aisle of the house. rows of empty floor seats surround you.", "you are standing in the main aisle of the house. rows of empty floor seats surround you. there is an small metal plaque on the side of one of the seats.");
	        Room techBooth = new Room("tech booth", "you are standing in the tech booth, also known as the control booth, on a balcony facing the stage.", "you are standing in the tech booth, also known as the control booth, on a balcony above the house facing the stage. there is various technical equipment here. on a long table is a computer. next to it is a large toolbox with what appears to be a keyhole.");
	        
	        Item paper = new Item("paper", "the paper is small and appears to have no writing on it. however, you can see a slight difference in texture in some areas of the paper");
	        Item key = new Item("key", "the key is silver and metal. it probably unlocks something in this theater.");
	        Item flashlight = new Item("flashlight", "the flashlight is an LED UV flashlight. it is black and lightweight.");
	        Item scroll = new Item("scroll", "the scroll is small and fragile. it appears to be old and contains writing on it");
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
	        password = "e24e20r11j16";
	        
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
	            losePhantomAppear();
	        }
	    }
	    
	    public void processCommand(Command command) {
	        String commandWord = command.getCommandWord();
	        if(commandWord==null) {
	        	System.out.println("what do you want to do?");
	        	return;
	        }
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
	            case "light":
	            	light(command);
	            	break;
	            case "spray":
	            	spray(command);
	            	break;
	            case "pull":
	            	pull(command);
	            	break;
	            default:
	                System.out.println("that is not a valid command");
	        }
	    }
	    
	    public void help() {
	    	System.out.println("are you stuck? try going to every room and examining everything you can\n\nhere is a list of valid commands:\n\nBRIEF, to only print short room descriptions\nVERBOSE, to only print long room descriptions\nINFO, to print the welcome message\nHELP, to get this list again\nGO followed by a direction, to move to another room\nINVENTORY, to print a list of the items in your personal inventory\nGRAB or TAKE followed by an item, to place it in your personal inventory\nDROP followed by an item, to remove it from your personal inventory and place it in the current room\nEXAMINE followed by a room or object, to get the description of the room or object\nCOUNTER, to see how many moves you have left\nREAD\nSHINE ____ AT ____\nLOOK followed by up or down\nUNLOCK\nUSERNAME\nPASSWORD\nPLAY\nSTOP\nHOLD ___ TO ____\nPULL\nLIGHT\nSPRAY");
	    }
	    
	    public void light(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("light what?");
	    		return;
	    	}
	    	else if(command.getSecondWord().equals("match") || command.getSecondWord().equals("matches")) {
	    		if(player.getItem("matches")==null) {
	    			System.out.println("you do not have matches in your inventory");
	    			return;
	    		}
	    		else if(sprayWater) {
	    			System.out.println("you light one of the matches. the fire burns steadily");
	    			lightMatch = true;
	    			return;
	    		}
	    		else {
	    			System.out.println("you light one of the matches. the fire burns for a little but then goes out");
	    			return;
	    		}
	    	}
	    	else if(command.getSecondWord().equals("phantom")) {
	    		if(lightMatch && currentRoom.getName().equals("stage")) {
	    			System.out.println("you light the phantom on fire with the match. the phantom slowly reduces to nothing and dies. you see that the entire cast and crew of Phantom of the Opera has reappeared in the theater!");
	    			winEnding();
	    			return;
	    		}
	    		else if(!lightMatch){
	    			System.out.println("you need to light a match first");
	    			return;
	    		}
	    		else {
	    			System.out.println("you cannot light the phantom on fire because it is out of your reach");
	    			return;
	    		}
	    	}
	    	else {
	    		System.out.println("you cannot light that");
	    	}
	    }
	    
	    public void spray(Command command) {
	    	if(!command.hasSecondWord()) {
	    		System.out.println("spray what?");
	    		return;
	    	}
	    	else if(!command.getSecondWord().equals("holy") && !command.getLine().equals(" water")) {
	    		System.out.println("you cannot spray that");
	    		return;
	    	}
	    	else if(player.getItem("holy water")==null) {
	    		System.out.println("you do not have holy water in your inventory");
	    		return;
	    	}
	    	else if(currentRoom.getName().equals("stage") && trapPhantom) {
	    		System.out.println("you spray the holy water onto the trapped phantom. the phantom starts to faintly shimmer");
	    		sprayWater = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("you spray the holy water into the air. nothing happens");
	    	}
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
	    			System.out.println("you pull the pulley. a cage drops down from the ceiling to the stage floor. it makes a loud sound");
	    			pulleyDown = true;
	    			seeCage = true;
	    			return;
	    		}
	    	}
	    	else if(!pulleyDown) {
	    		System.out.println("you pull the pulley. a cage drops down and traps the phantom inside of it. it cannot kill you while it is trapped");
	    		pulleyDown = true;
	    		trapPhantom = true;
	    		seeCage = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("you pull the pulley. the cage is lifted up back to the ceiling. the phantom is not trapped anymore and looks like it is going to kill you unless you trap it again");
	    		pulleyDown = false;
	    		trapPhantom = false;
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
	    			if(currentRoom.getName().equals("stage") && playVideo) {
	    				System.out.println("you turn the handle of the music box. music starts playing and the ballerina starts turning. you get a strange feeling and think that you should probably move out of the way before the phantom appears");
	    				phantomAppear = true;
	    				return;
	    			}
	    			System.out.println("you turn the handle of the music box. music starts playing and the ballerina starts turning. it is a gentle sound and you are vibing with it");
	    			musicBoxOn = true;
	    		}
	    		else if(player.getItem("music box")!=null) {
	    			System.out.println("you must drop the music box to play it");
	    			return;
	    		}
	    		else {
	    			System.out.println("you do not see a music box");
	    			return;
	    		}
	    	}
	    	else if(command.getSecondWord().equals("video")) {
	    		if(!currentRoom.getName().equals("tech booth") || !unlockComputer) {
	    			System.out.println("you do not see a video to play");
	    			return;
	    		}
	    		else {
	    			System.out.println("you play the video. it seems to be a recording of a rehearsal for the musical. the video shows someone bringing a music box to the front of the stage, dropping it, and playing it. you hear a whoosh sound from the video and out of nowhere a phantom appears on stage. everyone in the video suddenly disappears and the screen turns black. the video ends there");
	    			playVideo = true;
	    			return;
	    		}
	    	}
	    	else {
	    		System.out.println("you cannot play that");
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
	    		else if(phantomAppear) {
	    			System.out.println("you stop the handle of the music box. music stops playing and the ballerina stops turning. you have already attracted the phantom though");
	    			musicBoxOn = false;
	    			return;
	    		}
	    		else if(currentRoom.getItem("music box")!=null) {
	    			System.out.println("you stop the handle of the music box. music stops playing and the ballerina stops turning. you feel normal");
	    			musicBoxOn = false;
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
	    	}
	    }
	    
	    public void password(Command command) {
	    	if(unlockComputer) {
	    		System.out.println("you already unlocked the computer");
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
	    		Item key = player.getItem("key");
	    		player.removeItem("key");
	    		currentRoom.setItem("key", key);
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
	    		seeCage = true;
	    		return;
	    	}
	    	else if(currentRoom.getName().equals("house") && command.getSecondWord().equals("down")) {
	    		System.out.println("you look down and see a key under one of the seats");
	    		seeKey = true;
	    		return;
	    	}
	    	else {
	    		System.out.println("you look "+command.getSecondWord() +". you do not see anything special");
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
		        	System.out.println("you shine the flashlight at the paper. nothing changes so you turn it off");
		        	return;
		        }
	        	else {
	        		System.out.println("you shine the flashlight at the paper.");
	        		player.getItem("paper").changeDescription("the paper is small and you can see writing on it now, presumably written in invisible ink. the writing appears to be backwards and you cannot read it.");
		        	System.out.println(player.getItem("paper").getDescription());
		        	shineFlashlight = true;
		        	return;
	        	}
	        }
	        else {
	        	System.out.println("you cannot do that");
	        }
	    }
	    
	    public void read(Command command) {
	        if(!command.hasSecondWord()) {
	            System.out.println("read what?");
	            return;
	        }
	        else if(currentRoom.getName().equals("house") && command.getSecondWord().equals("plaque")) {
	        	System.out.println("the plaque reads: \n\t'This theater is dedicated to Brian Adams, the founder of Lab Productions.'");
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
	        		System.out.println("the scroll reads: \n\t'Getting Rid of Ghosts: when you have a ghost or phantom trapped, first spray holy water on it and then light it on fire to get rid of it. killing a ghost or phantom will undo any spell or curse the phantom has performed/placed.'");
	        		return;
	        	}
	        }
	        else {
	        	System.out.println("you cannot read that");
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
	        	if(takeHolyWater && takeMatches) {
	        		System.out.println("you examine the nun costume. you can see a pocket that is empty");
	        		return;
	        	}
	        	else {
	        		System.out.println("you examine the nun costume. you can see a pocket with something inside of it.");
	        		seePocket = true;
	        		return;
	        	}
	        }
	        else if(thingToExamine.equals("pocket") && currentRoom.getName().equals("dressing room") && seePocket) {
	        	if(!takeHolyWater && !takeMatches) {
	        	System.out.println("you examine the pocket of the nun costume. inside you see a bottle of holy water and a box of matches");
	        	seeMatches = true;
	        	seeHolyWater = true;
	        	return;
	        	}
	        	else if(takeHolyWater && !takeMatches) {
	        		System.out.println("you examine the pocket of the nun costume. inside you see a box of matches");
	        		return;
	        	}
	        	else if(takeMatches && !takeHolyWater) {
	        		System.out.println("you examine the pocket of the nun costume. inside you see a bottle of holy water");
	        		return;
	        	}
	        	else {
	        		System.out.println("you examine the pocket of the nun costume. it is empty");
	        		return;
	        	}
	        }
	        else if(thingToExamine.equals("computer") && currentRoom.getName().equals("tech booth")) {
	        	if(!unlockComputer) {
	        	System.out.println("you examine the computer. on the front screen is a login page with places to enter in a username and password. you have a feeling that there is something important on this computer. to enter a username, type 'username' followed by the username. to enter a password, type 'password' followed by a password.");
	        	return;
	        	}
	        	else {
	        		System.out.println("you examine the computer. on the screen, you can see a video with a play button on it that looks like a recording of a musical at this theater. the date says it was recorded yesterday");
	        		return;
	        	}
	        }
	        else if(thingToExamine.equals("toolbox") && currentRoom.getName().equals("tech booth")) {
	        	if(!seeFlashlight) {
	        		System.out.println("you examine the toolbox. it is red and has a handle on the top. you can see a keyhole on the front of it. it appears to be locked");
	        		return;
	        	}
	        	else {
	        		System.out.println("you examine the toolbox. it is red and has a handle on the top. it is open and you can see many tools inside of it");
	        		return;
	        	}
	        }
	        else if(thingToExamine.equals("pulley") && currentRoom.getName().equals("left wing")) {
	        	System.out.println("you examine the pulley. it consists of a long rope that runs all the way up to the ceiling and out of sight to the right. it seems that it can be pulled");
	        	return;
	        }
	        else if((thingToExamine.equals("plaque") || thingToExamine.equals("metal plaque")) && currentRoom.getName().equals("house")) {
	        	System.out.println("you examine the plaque. it is small and metal and is attached to the side of one of the seats. it looks to be brand new. there seems to be writing engraved on it");
	        	return;
	        }
	        else if(thingToExamine.equals("cage")) {
	        	if(currentRoom.getName().equals("left wing") && pulleyDown) {
	        		System.out.println("you examine the cage. it is large and silver. it seems like it could fit a person inside it");
	        		return;
	        	}
	        	else if(currentRoom.getName().equals("stage") && seeCage) {
	        		System.out.println("you examine the cage. it is large and silver. it seems like it could fit a person inside it");
	        		return;
	        	}
	        	else {
	        		System.out.println("you do not see a cage right now");
	        		return;
	        	}
	        }
	        else if(thingToExamine.equals("phantom") && phantomAppear) {
	        	System.out.println("you examine the phantom. it is a massive transparent figure and looks straight out of a horror movie");
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
	        else if(item.equals("holy water") && !takeHolyWater) {
	        	currentRoom.removeItem(item);
	        	player.setItem(item, itemToGrab);
	        	System.out.println("you took the holy water");
	        	takeHolyWater = true;
	        	return;
	        }
	        else if(item.equals("matches") && !takeMatches) {
	        	currentRoom.removeItem(item);
	        	player.setItem(item, itemToGrab);
	        	System.out.println("you took the matches");
	        	takeMatches = true;
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
	    		System.out.println("there seems to be an object underneath one of the seats");
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
			}
	    }
	    
	    public void welcomeString() {
	    	System.out.println("welcome to elena's text adventure game!\n\nyou are in a theater that is currently haunted by a phantom. the entire cast of the musical Phantom of the Opera has disappeared just before opening night! you must figure out how to get them back and make them reappear in time for their show before the phantom kills you. \n\nthe theater contains multiple rooms and various objects that will be useful for you. some objects cannot be taken unless you have seen them. many of these objects work together to aid you on your quest. \n\nto win the game, you must make the phantom bring everyone back within 200 moves (each move is one command). to see how many moves you have left, type 'counter'. \n\nto always print long room descriptions, type 'verbose'. to always print short room descriptions, type 'brief'. if you want help or a list of valid commands, type 'help'. to see this message again, type 'info'.\n\n");
	    }
	    
	    public void winEnding() {
	    	System.out.println("\ncongratulations! you have won the game by successfully saving the cast and crew of Phantom of the Opera in time for opening night! amazing job! thanks for playing!!");
	    	play = false;
	    }
	    
	    public void loseMusicBox() {
	    	if(phantomAppear) {
	    		return;
	    	}
	    	else if(musicBoxOn) {
	    		musicCount++;
	    	}
	    	else {
	    		musicCount = 0;
	    		return;
	    	}
	    	if(musicCount>8) {
	    		System.out.println("out of the corner of your eye, you see a shadow swoop down. by leaving the music box playing, you have attracted the phantom. you lose your vision and die. game over.");
	    		play = false;
	    		return;
	    	}
	    	else if(musicCount>=5) {
	    		
	    		System.out.println("the music continues to play. it sounds very creepy. you have a weird feeling and think that you should probably stop the music before something bad happens");
	    		return;
	    	}
	    	else if(musicCount==1) {
	    		return;
	    	}
	    	else {
	    		System.out.println("the music continues to play. it sounds a bit creepy now");
	    	}
	    }
	    
	    public void losePhantomAppear() {
	    	if(!phantomAppear || trapPhantom) {
	    		return;
	    	}
	    	else {
	    		phantomCount++;
	    	}
	    	if(phantomCount==1) {
	    		return;
	    	}
	    	else if(phantomCount==2) {
	    		System.out.println("you see the phantom above the stage. you realize that you will probably get killed soon if you don't do something about the phantom");
	    		return;
	    	}
	    	else if(phantomCount<=4) {
	    		System.out.println("the phantom looks like it is about to lunge at you. you will die if you don't do something about it quick");
	    		return;
	    	}
	    	else {
	    		System.out.println("the phantom swoops down at you. you lose your vision and die. game over.");
	    		play = false;
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