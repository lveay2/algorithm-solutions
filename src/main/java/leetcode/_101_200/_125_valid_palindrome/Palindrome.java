package leetcode._101_200._125_valid_palindrome;

/**
 * Given a string, determine if it is a palindrome, considering only alphanumeric characters and ignoring cases.
 *
 * Note: For the purpose of this problem, we define empty string as valid palindrome.
 *
 * Example 1:
 *
 * Input: "A man, a plan, a canal: Panama"
 * Output: true
 * Example 2:
 *
 * Input: "race a car"
 * Output: false
 *
 */
public class Palindrome {

    public static void main(String[] args) {

        System.out.println("true == " + new Palindrome().isPalindrome("A man, a plan, a canal: Panama"));

        System.out.println("false == " + new Palindrome().isPalindrome("race a car"));

        System.out.println("false == " + new Palindrome().isPalindrome(""));

        System.out.println("false == " + new Palindrome().isPalindrome("0P"));

    }

    public boolean isPalindrome(String s) {
        if (s == null) {
            return false;
        }

        char[] array = s.toCharArray();

        int l = array.length;

        int i = 0, j = l - 1;

        while (i < j) {
            char head = array[i];

            char tail = array[j];

            if (!Character.isLetterOrDigit(head)) {
                i++;
            } else if (!Character.isLetterOrDigit(tail)) {
                j--;
            } else {
                if (Character.toLowerCase(head) != Character.toLowerCase(tail)) {
                    return false;
                }

                i++;

                j--;
            }
        }

        return true;
    }

}
