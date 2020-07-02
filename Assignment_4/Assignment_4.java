/*@author: Dylan Toms
 *@version: 11/25/2019
 *
 *@Description: This program will ask the user to enter dimensions for a jungle that they will be able to explore. After they input the dimensions
 *the world will be created and a menu will be displayed. The menu will ask the user if they want to 1. insert an animal, 2. remove an animal, 3. explore 
 *the world, 4. exit the program. Depending on the option they choose the program will then allow them to insert or remove an animal. If they choose option
 *3. the program will display the game world and a movement menu. The user will then get to use the movement keys (w,a,s,d) to move throughout the 
 *world and discover animals that they placed in the world. If the user chooses option i then the list of observed animals will be displayed. If the 
 *user chooses option e then the program will send them back to the main menu. 
 *
 * 
 */
import java.util.Scanner;

public class Assignment_4 {
		// create static boolean 2D array called exploredWorld
		static boolean[][] exploredWorld;
		// create static Scanner input
		static Scanner input = new Scanner(System.in);
		// create two static int variables x and y
		static int x = 0;
		static int y = 0;
		
		public static void main(String[] args) {
			// create int choice set equal to 0
			int choice = 0;
			// output the welcome message and the info about the game
			System.out.println("Welcome to the jungle creator!\nIn this game, you will be inserting animals in certain places in the world\nthat you create! You will also be allowed to remove animals from certain locations before you start exploring!\nOnce you start exploring you will navigate around the world to observe the animals you have inserted.\nThe game will keep track of all the animals you have observed!");
			// ask the user to enter the length and width of the jungle. Store as length and width int variables
			System.out.println("Enter the dimensions of your jungle!");
			System.out.print("Enter length: ");
			int length = input.nextInt();
			
			System.out.print("Enter width: ");
			int width = input.nextInt();
			
			// initialize the arrays
			exploredWorld = new boolean[length][width];
			String[][] world = new String[length][width];
			String[][] animalLocations = new String[length][width];
			String[] observedAnimals = new String[length * width];
			
			// fill the exploredWorld array with false except for the 0,0 point, make it true
			for(int i = 0; i < exploredWorld.length; i++) {
				for(int j = 0; j < exploredWorld[0].length; j++) {
					if((i == 0) && (j == 0)) {
						exploredWorld[i][j] = true;
					} else {
						exploredWorld[i][j] = false;
					}
				}
			}
			
			// fill the animalLocations array with "" to replace null to avoid nullpointer exeption
			for(int i = 0; i < animalLocations.length; i++) {
				for(int j = 0; j < animalLocations[0].length; j++) {
					animalLocations[i][j] = "";
				}
			}
			
			// fill observedAnimals array with "" to replace null to avoid nullpointer exeption
			for(int i = 0; i < observedAnimals.length; i++) {
				observedAnimals[i] = "";
			}
			
			// create the world using the makeWorld method
			makeWorld(world, x, y);
			
			// while loop to run until the user enters 4
			while(choice != 4) {
				// print the mainMenu
				printMainMenu();
				// make choice equal the next input from the user
				choice = input.nextInt();
				
				// switch to test the user choice
				switch(choice) {
				// user chooses 1
				case 1: {
					// output the user chose insert an animal and then call the insertAnimalToWorld method
					System.out.println("You have chosen to insert an animal!");
					insertAnimalToWorld(animalLocations);
					
					break;
				}
				
				// user chooses 2
				case 2: {
					// output the user chose remove an animal and call the removeAnimalFromWorld method
					System.out.println("You have chosen to remove an animal!");
					removeAnimalFromWorld(animalLocations);
					
					break;
				}
				
				// user chooses 3
				case 3: {
					// call the explore method to begin exploring the world
					explore(world, animalLocations, observedAnimals);
					
					break;
				}
				
				}
			}
			// if the user chose 4 then exit
			System.out.println("Exiting");
			
			
			
		}
		
		// makeWorld method that takes in String[][] and two ints as parameters
		public static String[][] makeWorld(String[][] world, int posX, int posY) {
			// for loop to go through the world array
			for(int i = 0; i < world.length; i++) {
				for(int j = 0; j < world[0].length; j++) {
					
					// sets the world array at x and y equal to "X" this is the users current position
					world[posX][posY] = "X";
					// if statement using the isExplored method to see if the world is explored at [i][j]. if explored then "0" if not "*" 
					if(isExplored(i, j)) {
						world[i][j] = "0";
					} else {
						world[i][j] = "*";
					}
				}
			}
			// returns the world array
			return world;

		
		}

		// method to print the main menu
		public static void printMainMenu() {
			System.out.println("1. Insert an animal into the world." + "\n2. Remove an animal from the world." + "\n3. Explore the world." + "\n4. Exit");
			System.out.println("Enter your choice: ");

		}

		// method to print the move menu
		public static void printMoveMenu() {
			System.out.println("W. Move Forward" + "\nA. Move Left" + "\nS. Move Backward" + "\nD. Move Right" + "\nI. Display observed animals" +
		"\nE. Exit");
			System.out.println("Enter your choice: ");
		}

		// method to allow the user to insert an animal into the world
		public static void insertAnimalToWorld(String[][] animalLocations) {
			// ask the user for the x and y
			System.out.println("Enter x coordinate: ");
			int x = input.nextInt();
			System.out.println("Enter y coordinate: ");
			int y = input.nextInt();
			// ask the user for the animals name. Store as String animal
			System.out.println("Enter the animal you would like to place in the world: ");
			String animal = input.next();
			
			// if the coordinates are in bounds and the animalLocations array is empty at the coordinates then set it equal to the animal
			if((x < animalLocations.length) && (y < animalLocations[0].length)) {
				if(animalLocations[x][y].contentEquals("")) {
					animalLocations[x][y] = animal;
				}
			// if not say out of bounds
			} else {
				System.out.println("Out of bounds.");
			}
				
			
			
		}
		
		// method to allow the user to remove an animal from the world
		public static void removeAnimalFromWorld(String[][] animalLocations) {
			// ask the user for the x and y
			System.out.println("Enter x coordinate: ");
			int x = input.nextInt();
			System.out.println("Enter y coordinate: ");
			int y = input.nextInt();
			
			// if the coordinates are in bounds then remove the animal and replace it with "" 
			if((x < animalLocations.length) && (y < animalLocations[0].length)) {
				if(!animalLocations[x][y].contentEquals("")) {
					animalLocations[x][y] = "";
				}
			} else {
				System.out.println("Out of bounds.");
			}
			

		}

		// boolean method to check if the animalLocations array is empty or not
		public static boolean isEmptyBlock(String[][] animalLocations, int x, int y) {
			if(animalLocations[x][y].contains("")) {
				return true;
			}
			
			return false;

		}

		// String[] method to update the observedAnimals array with every animal the user finds
		public static String[] updateObservedAnimals(String[] observedAnimals, String animal) {
			// for loop to go through the observedAnimals array 
			for(int i = 0; i < observedAnimals.length; i++) {
				// if statement to check if the space is empty if it is then set it equal to the animal and break. if not continue until an empty spot is found
				if(observedAnimals[i].contentEquals("")) {
					observedAnimals[i] = animal;
					break;
				} else {
					continue;
				}
			}

			// return the updated array
			return observedAnimals;

		}

		// method to print the world 
		public static void printWorld(String[][] world) {
			for (int i = 0; i < world.length; i++){
				for (String s : world[i]) {
					System.out.print(s + " ");
				}
				
			System.out.println();
			}
		}
		
		// method to begin exploring the world
		public static void explore(String[][] world, String[][] animalLocations, String[] observedAnimals) {
			
			// char choice = 'X' to make the loop run at least once
			char choice = 'X';
			
			// while loop to run until the user chooses to exit
			while(choice != 'e') {
				// makeWorld method to make the world
				makeWorld(world, x, y);
				// print the world
				printWorld(world);
				// print the move menu
				printMoveMenu();
				
				// make the choice variable lowercase or uppercase
				String choice1 = input.next();
				choice = choice1.toLowerCase().charAt(0);
				
				
				// switch to check the value of choice
				switch(choice) {
				// move forward
				case 'w': {
					// x is x - 1
					x = x - 1;
					// if the x falls out of bounds then say so and reset to 0,0
					if((x < 0) || (x > world.length - 1)) {
						System.out.println("Out of bounds, resetting position to 0,0.");
						x = 0;
						y = 0;
					// else call the move method to move the player and see if an animal is there
					} else {
						move(world, x, y, observedAnimals, animalLocations);
					}
					break;
				}
				// move left
				case 'a': { 
					// y is y - 1
					y = y - 1;
					// if the y falls out of bounds then say so and reset to 0,0
					if((y < 0) || (y > world[0].length - 1)) {
						System.out.println("Out of bounds, resetting position to 0,0.");
						x = 0;
						y = 0;
					// else call the move method to move the player and see if an animal is there
					} else {
						move(world, x, y, observedAnimals, animalLocations);
					}
					break;
				}
				// move backward
				case 's': {
					// x is x + 1
					x = x + 1;
					// if the x falls out of bounds then say so and reset to 0,0
					if((x < 0) || (x > world.length - 1)) {
						System.out.println("Out of bounds, resetting position to 0,0.");
						x = 0;
						y = 0;
					// else call the move method to move the player and see if an animal is there
					} else {
						move(world, x, y, observedAnimals, animalLocations);
					}
					break;
				}
				// move right
				case 'd': {
					// y is y + 1
					y = y + 1;
					// if the y falls out of bounds then say so and reset to 0,0
					if((y < 0) || (y > world[0].length - 1)) {
						System.out.println("Out of bounds, resetting position to 0,0.");
						x = 0;
						y = 0;
					// else call the move method to move the player and see if an animal is there
					} else {
						move(world, x, y, observedAnimals, animalLocations);
					}
					break;
				}
				
				// if the user chooses i then print the observed animals
				case 'i': {
					printobservedAnimals(observedAnimals);
					break;
				}
				
				}
			}
			// user choose option e to return to the main menu
			System.out.println("Returning to main menu. . .");
		}

		// String[][] method to move the player and check if an animal is at the position
		public static String[][] move(String[][] world, int i, int j, String[] observedAnimals, String[][] animalLocations) {
			// make the world
			makeWorld(world, x, y);
			// set the position to true
			exploredWorld[x][y] = true;
			
			// if the animalLocations array is empty say the user didn't encounter anything
			if(animalLocations[x][y].contentEquals("")) {
				System.out.println("You did not encounter anything.");
			// else say you found a . . . and update the observedAnimals array
			} else {
				System.out.println("You found a " + animalLocations[x][y]);
				updateObservedAnimals(observedAnimals, animalLocations[x][y]);
			}
			// returns the updated world
			return world;

			
			
			

		}

		// method to print the observedAnimals array
		public static void printobservedAnimals(String[] observedAnimals) {
			for (int i=0;i<observedAnimals.length;i++){
						
				if (observedAnimals[i] != null)
					System.out.print(observedAnimals[i] + " ");
			}
			System.out.println();
		}

		// boolean method to see if a position is explored or not
		public static boolean isExplored(int x, int y) {

			return exploredWorld[x][y];

		}

	

}


