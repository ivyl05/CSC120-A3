/*
 * File name: Conversation.java
 * Description: Create a Chatbot
 * Author: Ivy Li
 * Date: 27 September 2024
 * Last Modified: 15 December 2024
 */

import java.util.Scanner;
import java.util.Random;

/*
 * The Conversation class creates a chatbot that interacts with the user. 
 */
class Conversation {

    private int rounds; //the number of rounds of chatting
    private String[] input; //input of the user
    private String[] transcript; //stores the entire chat

    //an arrary of canned responses
    private static final String[] cannedResponse = {
        "Mhm!",
        "Interesting!",
        "Wow sounds great!"
    };

    //an array of the the words that need to be mirrored
    private static final String[][] mirrorRules = {
      {"i", "you"},
      {"me", "you"},
      {"am", "are"},
      {"you", "I"},
      {"my", "your"},
      {"your", "my"}
    };

    /**
     * Constructor that takes rounds as input and initializes the arrays
     * 
     * @param rounds the number of rounds 
     */

    public Conversation(int rounds) {
        this.rounds = rounds;
        this.input = new String[rounds];
        this.transcript = new String[rounds];
    }

    /**
     * This method creates random canned response
     * 
     * @return a random canned response
     */
    public String getCannedResponse() {
        Random random = new Random();
        int num = random.nextInt(cannedResponse.length);
        return cannedResponse[num];
    }

    /**
     * Generates a response by either mirroring input or using a canned response.
     *
     * @param userInput The input of the user 
     * @param round the number of rounds
     * @return the generated response.
     */
    public String generateResponse(String userInput, int round) {
        String[] words = userInput.split(" ");
        for (int i = 0; i < words.length; i++) {
            for (String[] rule: mirrorRules) {
                if (words[i].equals(rule[0])) {
                    words[i] = rule[1];
                    String response = String.join(" ", words);
                    transcript[round] = "You: " + userInput + "\nBot: " + response + "? Tell me more!";
                    System.out.println("Bot: " + response + "? Tell me more!");
                    return response;
                }
            }
        }
        String response = getCannedResponse();
        transcript[round] = "You: " + userInput + "\nBot: " + response + "? Tell me more!";
        System.out.println("Bot: " + response);

        return String.join(" ", words);
    }

    /**
     * Prints the entire conversation transcript
     */
    public void printTranscript() {
        System.out.println("\nConversation Transcript:");
        for (String entry: transcript) {
            System.out.println(entry);
        }
    }

    /**
     * The main method allows the user to interact with chatbot
     * 
     * @param arguments
     */
    public static void main(String[] arguments) {
        Scanner scanner = new Scanner(System.in);
        int rounds = 0;

        //ask for number of rounds
        while (true) {
            System.out.println("How many rounds?");
            try {
                rounds = scanner.nextInt();
                scanner.nextLine();
                if (rounds <= 0) {
                    System.out.println("Please enter a positive integer for the number of rounds.");
                } else {
                    break;
                }
            } catch (RuntimeException e) {
                System.out.println("Invalid entry. Please enter a valid integer!");
                scanner.nextLine();
            }
        }

        System.out.println("Chatbot: Hello! What do you want to chat about?");
        Conversation chatbot = new Conversation(rounds);

        //Conversation starts 
        for (int i = 0; i < rounds; i++) {
            System.out.print("You: ");
            String input = scanner.nextLine().toLowerCase();
            chatbot.input[i] = input;
            chatbot.generateResponse(input, i);
        }
        //Conversation ends and print out transcript
        scanner.close();
        chatbot.printTranscript();
        System.out.println("Bot: Nice chatting with you! Goodbye!");
    }
}