/*
Author: C Yousief
*/

import java.util.Scanner;
import java.util.ArrayList;

public class PlayerRoster {
	static Scanner scnr = new Scanner(System.in);
	
	public static void main( String args[]) {

      ArrayList<Player> myTeam = new ArrayList<Player>();
		
		initializeRoster(myTeam);
		menu(myTeam);
        
      System.out.println("End of PlayerRoster run.");
	}
	
	public static void initializeRoster( ArrayList<Player> team) {		

      String playerName;
      int jerseyNum;
      int playerRating;
      int numPlayers = 0;
      

      do {
        System.out.print("Enter player name (hit enter to quit): ");
        playerName = scnr.nextLine();
        if(playerName.equals("")){
           break;
        }

        System.out.print("Enter " +  playerName + "'s jersey number: ");
        jerseyNum = scnr.nextInt();

        System.out.print("Enter " +  playerName + "'s rating: ");
        playerRating = scnr.nextInt();

        scnr.nextLine();
        System.out.println();

        team.add(new Player(playerName, jerseyNum, playerRating));
        numPlayers++;
      }while(true);

      System.out.println("\n" + numPlayers + " players were entered.");
    }
	
	public static void outputRoster( ArrayList<Player> team) {

      System.out.println("Team is: ");
      for( Player player : team) {
         System.out.print("\t");
         System.out.println(player.toString());
      }
      System.out.println("End of team.");	
      
	}
	
	public static void updateRating( ArrayList<Player> team) {

		System.out.print("Enter a jersey number: ");
      Integer playerJersey = scnr.nextInt();

      for( Player player: team) {
         if(player.getJerseyNum() == playerJersey){
            System.out.print("Enter a new rating for player: ");
            Integer newRating = scnr.nextInt();

            player.setRating(newRating);
            return;
         }
      }
      System.out.println("There is no player with jersey number: " + playerJersey);
	}
	
	public static void outputHighRated( ArrayList<Player> team) {

		System.out.print("Enter the rating: ");
      int highRating = scnr.nextInt();
      System.out.println("\nPlayers above rating " + highRating + ":");

      for( Player player : team) {
         if(player.getRating() > highRating){
            System.out.print("\t");
            System.out.println(player.toString());
         }
      }

	}
	
	public static void replacePlayer( ArrayList<Player> team) {

      boolean taken;
      System.out.println("Enter player's name: ");
      String toReplace = scnr.next();

      if(containsName(toReplace, team)){
         System.out.println("Enter the new player's name: ");
         String newPlayerName = scnr.next();
         Integer newJerseyNumber;

         do {
            taken = false;
            System.out.print("Enter jersey number for " + newPlayerName + ": ");
            newJerseyNumber = scnr.nextInt();
            for (Player player : team) {
               if(player.getJerseyNum() == newJerseyNumber && !player.getName().equals(toReplace)){
                  System.out.println("Jersey number " + newJerseyNumber + " is already taken by player " + player.getName() + "\nTry again.");
                  taken = true;
               }
            }
         }while(taken);

         System.out.print("Enter rating for new player : ");
         Integer newPlayerRating = scnr.nextInt();
         
         for( Player player : team) {
            if(player.getName().equals(toReplace)) {
               player.setName(newPlayerName);
               player.setJerseyNum(newJerseyNumber);
               player.setRating(newPlayerRating);
            }
         }
      }else{
         System.out.println("Player: " + toReplace + " not in the roster.");
      }

   }
   
   public static boolean containsName( String name,  ArrayList<Player> team) {
      for( Player player : team){
         if(player.getName().equals(name)){
            return true;
         }
      }
      return false;
   }

	
	public static void menu( ArrayList<Player> team) {

		char menuOp;
		do {
			System.out.println("\nMENU");
			System.out.println("u - Update player rating");
			System.out.println("h - Output players above a rating");
			System.out.println("r - Replace player");
			System.out.println("o - Output roster");
			System.out.println("q - Quit");
	  
			System.out.println("\nChoose an option:");
			menuOp = scnr.next().toUpperCase().charAt(0);

			if (menuOp != 'Q')
				switch (menuOp) {
					case 'U' :	updateRating(team);
								break;
					case 'H' :	outputHighRated(team);
								break;
					case 'R' :	replacePlayer(team);
								break;
					case 'O' :	outputRoster(team);
								break;
					default	 :	System.out.println("Illegal menu item.");
								break;
				}
		} while (menuOp != 'Q');
	}	
}
