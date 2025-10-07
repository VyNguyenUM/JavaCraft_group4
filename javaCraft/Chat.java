

import java.io.*;
import java.net.*;
import java.util.*;

public class Chat {
    public static void main(String[] args) {
        try {
            chatSession();
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    public static void chatSession() {
        // A chat session starts by connecting to the server's TCP/IP socket.
        // We also create a new Scanner, to receive chat messages from the local user
        // in order to forward them to the server.
        //
        // By allocating these resources in a "try" statement, we ensure they
        // are released when control exits this procedure. See
        // https://docs.oracle.com/javase/tutorial/essential/exceptions/tryResourceClose.html
        //
        try (
            final var inputFromUser = new Scanner(System.in);
            final var socket = new Socket("chat.bcs1110.svc.leastfixedpoint.nl", 5999);
        ) {
            // If control reaches here, the socket exists and is connected. We
            // extract an *input stream*, for reading messages from the server, and
            // an *output stream*, for sending messages to the server.
            final var inputFromServer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            final var output = new PrintWriter(socket.getOutputStream());
        

            // Our chat system follows a very simple *communication protocol*.
            // https://en.wikipedia.org/wiki/Communication_protocol
            //
            // The first step in the protocol is for a connecting client to send
            // a "password" to access the server. It's not a big secret - it just
            // keeps opportunistic passers-by (e.g. AI crawler bots) out.
            output.println("F4EF9A36-5FCD-4D27-8A0A-FC7C77D3DBB2");
            output.flush();

           
            // Q. Why is this ^ `flush()` statement necessary?
            //    See https://stackoverflow.com/questions/2340106/what-is-the-purpose-of-flush-in-java-streams

            // After the password, the protocol proceeds *asynchronously*. The server
            // sends stuff to us when it has anything for us, and we send stuff to it
            // when we have anything to say. These can happen at any time. But in Java,
            // things happen one after the other! There's no notion of asynchronous
            // event! We *could* introduce threads or some other construct for representing
            // concurrent activity. (See https://en.wikipedia.org/wiki/Concurrency_(computer_science))
            //
            // But to keep it simple, THIS client program chooses to proceed in alternating
            // stages:
            //
            // 1 - "ping" the server, and print output we get back until we see the response to
            //     our "ping", indicating that there's nothing more to show just at the minute;
            // 2 - then, wait for input from the user.
            // 3 - send the message the user typed to the server, and loop back to the
            //     beginning again.
            //
            // We take special care not to send an empty line because that's what the protocol
            // uses to mean "ping"!
            //
         
            while (true) {
                // 1. Ping the server.
                output.println("");
                output.flush(); // Don't forget to actually send the output through!
             String name = "@Player";
                // 1(b). Collect responses until we see the one that specifically indicates
                // that the server has received and processed our "ping".
                while (true) {
                
                    String fromServer = inputFromServer.readLine();
                    if (fromServer == null) return; // We get null if the server disconnects.

                    if (fromServer.equals("+")) {
                        // Aha! The response to our ping! We're done with this round: move
                        // on to step 2.
                        break;
                    }
                 
                    if(fromServer.contains(name)){
                        int i = fromServer.indexOf(":");
                        if(i != 1){
                            if(i + 1 < fromServer.length()){
                                String prefix = fromServer .substring(0, i + 1);
                                String encryptedPart = fromServer.substring(i+1).trim();
                                System.out.println(prefix + " " + encryptedPart);
                            }
                        }else{
                            System.out.println(fromServer);
                        }

                    // Some other message from the server: just print it.
                    //System.out.println(fromServer);
                  
                
                } else {
                    System.out.println(fromServer);
                }
            }

                // 2. Collect a line of input from the user.
                
                String messageToSend = inputFromUser.nextLine().trim();
                        if (!messageToSend.equals("")) {
                            
                            String encryptedMessage = encrypt(messageToSend);
                              output.println(name + ": "  + encryptedMessage);
                              output.flush();   
                        
                        // 3. If it WASN'T a blank line, send it: it will be interpreted as
                        // a chat message.
                        }

                    // Q. What would happen if we DIDN'T take care not to send empty lines?
                    //    Hint: what does our code do if it sees two "ping" responses ("+" lines) in a row?
                
                }
            
            
                // 3(b). Loop back to step 1.
            
        } catch (Throwable t) {
            // We are being very lazy here and not handling errors properly.
            // In a real program, we would want to handle errors relating to network
            // failure differently from errors relating to, say, errors we made in our
            // own program being signalled, or errors relating to collecting input from
            // the user.
            t.printStackTrace();
        }
    }

    
     public static String encrypt(String messageToSend){
        int shift = 3;
        char[] letters = messageToSend.toCharArray();

            for(int i = 0;i < letters.length;i++){
                if(Character.isUpperCase(letters[i])){
                    letters[i] = (char)('A' + (letters[i] - 'A' + shift) % 26);
                }else if(Character.isLowerCase(letters[i])){
                    letters[i] = (char)('a' + (letters[i] - 'a' + shift) % 26);
                }

                }
                String encrypted = new String(letters);
          
                return encrypted;
            }

         public static String decrypt(String messageToSend) {
        int shift = 3;
        char[] letters = messageToSend.toCharArray();

        for (int i = 0; i < letters.length; i++) {
            if (Character.isUpperCase(letters[i])) {
                letters[i] = (char) ('A' + ((letters[i] - 'A' - shift + 26) % 26));
            } else if (Character.isLowerCase(letters[i])) {
                letters[i] = (char) ('a' + ((letters[i] - 'a' - shift + 26) % 26));
            }
        }
        return new String(letters);
    }
        }
           



   
        

    

    
    
    
    
    


