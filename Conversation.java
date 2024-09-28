/*
 * File name: Conversation.java
 * Description: Create a Chatbot
 * Author: Ivy Li
 * Date: 27 September 2024
 */

import java.util.Scanner;

import java.util.Random;


/*
 * class conversation creates a chatbot that is able to mirror certain words in the response or uses canned responses.
 */
class Conversation {

  static String[] CannedResponse ={
    "Mhm!",
    "Interesting!",
    "Tell me more about it"
  };

  static String[][] Mirror ={
    {"i", "you"},
    {"me", "you"},
    {"am", "are"},
    {"you", "I"},
    {"my", "your"},
    {"your", "my"}
  };
  static int rounds;
  static String imput[];
  
  public static void main(String[] arguments) {
    BeginConversation();
  }

  /*
   * Ask user how many rounds and store it into rounds
   * return the number of rounds
   */
  public static int Rounds() {
    Scanner scanner = new Scanner(System.in);
    System.out.println("How many rounds?");
    int rounds = scanner.nextInt();
    scanner.nextLine(); 
    return rounds;

  }

  /*
   * This method starts the conversation and store the conversation into transcript
   * return nothing
   */
  public static void BeginConversation() {
    int rounds = Rounds();
    String[] transcript = new String[rounds]; 
    Scanner scanner = new Scanner(System.in);
    for (int i = 0; i < rounds; i++) {
      // Scanner scanner = new Scanner(System.in);
      System.out.println("You: ");
      String imput = scanner.nextLine().toLowerCase(); 
      String response = Response(imput, i);
      transcript[i] = "You: " + imput + "\nBot: " + response;
      System.out.println("Bot: " + response);
      
    }
    scanner.close();
    Transcript(transcript);
    
  }

  /*
   * This method randomize canned response although it didn't really work
   * returns CannedResponse[]
   */
  public static String CannedResponse(){
    Random random = new Random();
    int num = random.nextInt(3);
    return CannedResponse[num];
  }

  /*
   * This method is generated with the help of Chat GPT
   * This method generates response and determine whether words should be mirrored or should print out a canned response.
   * Returns userResponse
   */
  public static String Response(String userInput, int rounds){
    String userResponse = mirroredResponse(userInput);
    if (userResponse.equals(userInput)){
      userResponse=CannedResponse [rounds % CannedResponse.length];
    }
    return userResponse;

  }

  /*
   * This method is generated with the help of Chat GPT
   * This method generates mirrored words
   */
  public static String mirroredResponse(String userInput){
    String[] words = userInput.split(" "); 
        for (int i = 0; i < words.length; i++) {
            for (String[] mirror : Mirror) {
                if (words[i].equals(mirror[0])) {
                    words[i] = mirror[1];
                    break;
                  }
              }
          }
      return String.join(" ", words);
    }

/*
 * This method print out the transcript in the end
 * Returns nothing
 */
public static void Transcript(String[] transcript ){
  System.out.println("\nConversation Transcript:");
  for(String entry: transcript){
    System.out.println(entry);
  }
}
  


  
}
