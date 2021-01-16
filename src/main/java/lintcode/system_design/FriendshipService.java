package lintcode.system_design;

import java.util.*;

/**
 * https://www.lintcode.com/problem/friendship-service/description
 */
public class FriendshipService {

    Map<Integer, Set<Integer>> followers, followings;

    public FriendshipService() {
        followers = new HashMap<>();
        followings = new HashMap<>();
    }

    /**
     * @param userId: An integer
     * @return: all followers and sort by user_id
     */
    public List<Integer> getFollowers(int userId) {
        List<Integer> result = new ArrayList<>();
        if (followers.containsKey(userId)) {
            result = new ArrayList(followers.get(userId));
        }

        return result;
    }

    /*
     * @param user_id: An integer
     * @return: all followings and sort by user_id
     */
    public List<Integer> getFollowings(int user_id) {
        List<Integer> result = new ArrayList<>();
        if (followings.containsKey(user_id)) {
            result = new ArrayList(followings.get(user_id));
        }

        return result;
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int to_user_id, int from_user_id) {
        Set<Integer> set1 = followers.getOrDefault(to_user_id, new TreeSet<>());
        set1.add(from_user_id);
        followers.put(to_user_id, set1);

        Set<Integer> set2 = followings.getOrDefault(from_user_id, new TreeSet<>());
        set2.add(to_user_id);
        followings.put(from_user_id, set2);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int to_user_id, int from_user_id) {
        if (followers.containsKey(to_user_id)) {
            Set<Integer> set = followers.get(to_user_id);
            if (set.contains(from_user_id)) {
                set.remove(from_user_id);
            }
        }

        if (followings.containsKey(from_user_id)) {
            Set<Integer> set = followings.get(from_user_id);
            if (set.contains(to_user_id)) {
                set.remove(to_user_id);
            }
        }
    }

    public static void main(String[] args) {
        FriendshipService fs = new FriendshipService();
        fs.follow(1, 3);
        System.out.println(fs.getFollowers(1));
        System.out.println(fs.getFollowings(3));
        fs.follow(2, 3);
        System.out.println(fs.getFollowings(3));
        fs.unfollow(1, 3);
        System.out.println(fs.getFollowings(3));

        fs = new FriendshipService();
        fs.follow(1, 2);
        fs.unfollow(1, 2);
        System.out.println(fs.getFollowings(1));
        fs.unfollow(1, 2);
        System.out.println(fs.getFollowings(1));
        fs.unfollow(1, 2);
        fs.follow(1, 2);
        System.out.println(fs.getFollowings(1));
    }

}
