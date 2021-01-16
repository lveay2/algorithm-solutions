package educative.binary_search;

/**
 * Assume the given array is a circular list, which means that the last letter is assumed to be
 * connected with the first letter. This also means that the smallest letter in the given array is
 * greater than the last letter of the array and is also the first letter of the array.
 *
 * <p>Write a function to return the next letter of the given ‘key’.
 *
 * <p>Example 1:
 *
 * <p>Input: ['a', 'c', 'f', 'h'], key = 'f' Output: 'h' Explanation: The smallest letter greater
 * than 'f' is 'h' in the given array.
 *
 * Example 2:
 *
 * <p>Input: ['a', 'c', 'f', 'h'], key = 'b' Output: 'c' Explanation: The smallest letter greater
 * than 'b' is 'c'.
 *
 * Example 3:
 *
 * <p>Input: ['a', 'c', 'f', 'h'], key = 'm' Output: 'a' Explanation: As the array is assumed to be
 * circular, the smallest letter greater than 'm' is 'a'.
 *
 * Example 4:
 *
 * <p>Input: ['a', 'c', 'f', 'h'], key = 'h' Output: 'a' Explanation: As the array is assumed to be
 * circular, the smallest letter greater than 'h' is 'a'.
 */
public class NextLetter {

    public static char searchNextLetter(char[] letters, char key) {
        int start = 0, end = letters.length - 1;

        if (key <= letters[start] || letters[end] <= key) {
            return letters[start];
        }

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (letters[mid] > key) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        return letters[start];
    }

    public static void main(String[] args) {
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'f'));
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'b'));
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'm'));
        System.out.println(NextLetter.searchNextLetter(new char[]{'a', 'c', 'f', 'h'}, 'h'));
    }

}
