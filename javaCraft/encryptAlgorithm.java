import java.util.Scanner;

public class encryptAlgorithm{
    public static void main (String[] args){
        Scanner scan = new Scanner(System.in);
       char[] alphabet = {
    'a','b','c','d','e','f','g','h','i','j','k','l','m','n',
    'o','p','q','r','s','t','u','v','w','x','y','z'
};
    int shift = 3;
    System.out.println("Write a message: ");
    String message = scan.nextLine();

    char[] letters = message.toCharArray();

        for(int i = 0;i < letters.length;i++){
            if(Character.isUpperCase(letters[i])){
                letters[i] = (char)('A' + (letters[i] - 'A' + shift) % 26);
            }else if(Character.isLowerCase(letters[i])){
                letters[i] = (char)('a' + (letters[i] - 'a' + shift) % 26);
            }

            }
            String encrypted = new String(letters);
            System.out.println(encrypted);
               
        }
              
        }
    

