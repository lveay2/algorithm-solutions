package leetcode._1_100._71_simplify_path;

import java.util.Stack;

/**
 * Given an absolute path for a file (Unix-style), simplify it. Or in other words, convert it to the
 * canonical path.
 *
 * <p>In a UNIX-style file system, a period . refers to the current directory. Furthermore, a double
 * period .. moves the directory up a level. For more information, see: Absolute path vs relative
 * path in Linux/Unix
 *
 * <p>Note that the returned canonical path must always begin with a slash /, and there must be only
 * a single slash / between two directory names. The last directory name (if it exists) must not end
 * with a trailing /. Also, the canonical path must be the shortest string representing the absolute
 * path.
 *
 * <p>Example 1:
 *
 * <p>Input: "/home/" Output: "/home" Explanation: Note that there is no trailing slash after the
 * last directory name. Example 2:
 *
 * <p>Input: "/../" Output: "/" Explanation: Going one level up from the root directory is a no-op,
 * as the root level is the highest level you can go. Example 3:
 *
 * <p>Input: "/home//foo/" Output: "/home/foo" Explanation: In the canonical path, multiple
 * consecutive slashes are replaced by a single one. Example 4:
 *
 * <p>Input: "/a/./b/../../c/" Output: "/c" Example 5:
 *
 * <p>Input: "/a/../../b/../c//.//" Output: "/c" Example 6:
 *
 * <p>Input: "/a//b////c/d//././/.." Output: "/a/b/c"
 */
public class SimplifyPath {

  public static void main(String[] args) {
    System.out.println("/home == " + new SimplifyPath().simplifyPath("/home/"));
    System.out.println("/ == " + new SimplifyPath().simplifyPath("/../"));
    System.out.println("/home/foo == " + new SimplifyPath().simplifyPath("/home//foo/"));
    System.out.println("/c == " + new SimplifyPath().simplifyPath("/a/./b/../../c/"));
    System.out.println("/c == " + new SimplifyPath().simplifyPath("/a/../../b/../c//.//"));
    System.out.println("/a/b/c == " + new SimplifyPath().simplifyPath("/a//b////c/d//././/.."));
  }

  public String simplifyPath(String path) {

    if (path == null || path.length() == 0) return "/";

    StringBuilder sb = new StringBuilder();

    Stack<String> stack = new Stack<>();

    for (String s : path.split("/")) {
      if (s.equals("..")) {
        if (!stack.isEmpty()) {
          stack.pop();
        }
      } else if (s.equals(".") || s.equals("")) {
        continue;
      } else {
        stack.push(s);
      }
    }

    while (!stack.isEmpty()) {
      sb.insert(0, "/" + stack.pop());
    }

    if (sb.length() == 0) {
      sb.append("/");
    }

    return sb.toString();
  }
}
