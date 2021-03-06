package Pokemon;
import java.util.Scanner;
import java.io.*;

public class ProjectIO {
	public static Player playing;
	public static Player waiting;
	public static Scanner input = new Scanner(System.in);
	
	public static void main(String[] args) {
		System.out.println("Hello, and welcome to Pokemon");
		makePlayers();
		loadDeck("deck1.txt", playing);
		loadDeck("deck2.txt", waiting);
		playing.switchPokemon(2);
		playing.play(1);
		printField(playing,waiting);
		turnEngine();
		
		printHand(playing);
		printField(playing,waiting);
		
	}
	 
	public static void printField(Player playing, Player waiting){
		if(playing.attackPosition != null){
			System.out.println(playing.name + " has a " + ((PokemonCard)playing.attackPosition).name);
		}else{
			System.out.println(playing.name+"'s field is empty");
		}
		
		if(waiting.attackPosition != null){
			System.out.println(waiting.name + " has a " + ((PokemonCard)waiting.attackPosition).name);
		}else{
			System.out.println(waiting.name+"'s field is empty");
		}
		for (int i = 0; i<playing.bench.length;i++){
			System.out.println(playing.bench[i]);
		}
	}
	
	public static void printHand(Player playing){
			for (int i = 0; i<=playing.hand.size();i++){
				System.out.print("["+i+"] ");
				if (playing.hand.get(i).isPokemonCard){
					((PokemonCard) playing.hand.get(i)).print();
				}
				if (!playing.hand.get(i).isPokemonCard){
					((TrainerCard) playing.hand.get(i)).print();
				}
			}
		}

	
	public static void makePokemon(String name, Player playing){
		if (name.equals("JohnnyMan")){
			Card c = new JohnnyMan();
			playing.hand.add(c);
		}
		
		if (name.equals("JackMan")){
			Card c = new JackMan();
			playing.hand.add(c);
		}
		if (name.equals("Potion")){
			Card c = new Potion();
			playing.hand.add(c);
		}
	}
	
	public static void loadDeck(String deck, Player playing){
		String line = null;
		try {
			FileReader fileReader = new FileReader(deck);
			BufferedReader bufferedReader =  new BufferedReader(fileReader);

			while((line = bufferedReader.readLine()) != null) {
				makePokemon(line,playing);
			}
				bufferedReader.close();
			}
		
		catch(FileNotFoundException ex) {
			System.out.println(
					"Unable to open deck '" + 
							deck + "'");
		}
		catch(IOException ex) {
			System.out.println(
					"Error reading file '" 
							+ deck + "'");
		
		}
	}
		
	

	public static void turnEngine(){
		
		//((TrainerCard) playing.JP).execute(playing, waiting);

		//explain game and commands
		String command = input.next();
		
	
		if (command.equals("switch")){
			
			int val = Integer.parseInt(input.next());
			playing.switchPokemon(val);
			System.out.println("Moving "+val+ " to "+playing.name+"'s attack position");
		}
		if (command.equals("play")){
			int val = Integer.parseInt(input.next());
			playing.play(val);
			System.out.println("playing "+val+ " to "+playing.name+"'s attack position");
		}
		printHand(playing);
		printField(playing,waiting);
		//System.out.println(playing.name +" "+ command + "ed " + waiting.name);
//		playing.hand[2].execute(playing,waiting);
		turnEngine();
	}

	public static void makePlayers(){
		//player 1
		//Scanner input = new Scanner(System.in);
		String name = "";
		System.out.println("Player 1, please enter your name");
		name = input.next();
		playing = new Player(name);
		System.out.println("Welcome "+name+"!");
		//player 2
		System.out.println("Player 2, please enter your name");
		name = input.next();
		waiting = new Player(name);
		System.out.println("Welcome "+name+"!");

	}
	public static void changeTurn(){
		Player temp;
		temp = waiting;
		waiting = playing;
		playing = temp;

	}
	//EXAMPLE
}

//this needs to be able handle these commands 
//print field:prints all Pokemon in both players’ fields, and their HP (status)
//print hand:prints only your hand, and the number of cards left in each player’s deck
//attack:the Pokemon in your arena position attacks,ending your turn
//switch [NUMBER]: switches your arena position Pokemon with the Pokemon in the indicated spot in the bench. If that spot is empty, the Pokemon in the arena position just goes to that spot in the bench.
//play [NUMBER]:chooses a card from your hand to play. If it is a Pokemon card,it is put in the bench (if there is no room, it is discarded); if it’s a Trainer card, its effect is carried out.
//pass:endsyourturn

