package oa.karat;

import java.util.*;

/*
You and your friends are all fans of the hit TV show ThroneWorld and like to discuss
it on social media. Unfortunately, some of your friends don't watch every new episode
the minute it comes out. Out of consideration for them you would like to obfuscate
your status updates so as to keep them spoiler-free.

You decide to create a substitution cipher. The cipher alphabet is based on a key shared
amongst those of your friends who don't mind spoilers.

Suppose the key is:
"The quick onyx goblin, Grabbing his sword ==}  jumps over the 1st lazy dwarf!".

We use only the unique letters in this key to set the order of the characters in the
substitution table.
T H E Q U I C K O N Y X G B L R A S W D J M P V Z F

(spaces added for readability)

We then align it with the regular alphabet:
A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
T H E Q U I C K O N Y X G B L R A S W D J M P V Z F

Which gives us the substitution mapping: A becomes T, B becomes H, C becomes E, etc.

Write a function that takes a key and a string and encrypts the string with the key.

Example:
key = "The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!"
encrypt("It was all a dream.", key) -> "Od ptw txx t qsutg."
encrypt("Would you kindly?", key) -> "Pljxq zlj yobqxz?"

Complexity analysis:
m: The length of the message
k: The length of the key
 */
public class EncryptMessage {

    public static void main(String[] argv) {
        String key = "The quick onyx goblin, Grabbing his sword ==}-------- jumps over the 1st lazy dwarf!";
        String message = "It was all a dream.";
        String message2 = "Would you kindly?";

        System.out.println(encrypt("abc", key));
        System.out.println(encrypt(message, key));
        System.out.println(encrypt(message2, key));
    }

    private static String encrypt(String message, String key) {
        Map<Integer, Character> newOrders = new HashMap<>();
        Set<Integer> set = new HashSet<>();

        int index = 0;
        for (int i = 0; i < key.length(); i++) {
            // System.out.println( key.charAt(i) + " " + (Character.toLowerCase(key.charAt(i)) - 'a'));
            int cindex = Character.toLowerCase(key.charAt(i)) - 'a';
            if (Character.isLetter(key.charAt(i)) && !set.contains(cindex)) {
                char c = Character.toLowerCase(key.charAt(i));
                newOrders.put(index++, Character.toLowerCase(key.charAt(i)));
                set.add(cindex);
            }
        }
        // System.out.println(newOrders);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < message.length(); i++) {
            char current = message.charAt(i);
            if (!Character.isLetter(current)) {
                sb.append(current);
                continue;
            }

            int j = Character.toLowerCase(current) - 'a';
            char newChar = newOrders.get(j);

            if (Character.isUpperCase(current)) {
                newChar = Character.toUpperCase(newChar);
            }
            sb.append(newChar);
        }

        return sb.toString();
    }

}
