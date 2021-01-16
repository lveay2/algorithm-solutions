package lintcode.system_design.min_twitter;

import java.util.*;
import java.util.stream.Collectors;

/**
 * https://www.lintcode.com/problem/design-twitter/description
 */
public class MiniTwitter {

    Map<Integer, List<Node>> map;
    Map<Integer, Set<Integer>> followship;
    static int id = 1;

    public static int getId() {
        return id++ ;
    }


    public MiniTwitter() {
        map = new HashMap<>();
        followship = new HashMap<>();
        id = 1;
    }

    /*
     * @param user_id: An integer
     * @param tweet_text: a string
     * @return: a tweet
     */
    public Tweet postTweet(int user_id, String tweet_text) {
        Tweet t = Tweet.create(user_id, tweet_text);
        Node n = new Node(id, t);
        List<Node> tweets = map.getOrDefault(user_id, new ArrayList<>());
        tweets.add(n);
        map.put(user_id, tweets);
        return t;
    }

    private List<Node> getLastTen(List<Node> tweets) {
        tweets.sort(Comparator.comparingInt(n -> n.id));
        int last = 10;
        int n = tweets.size();
        if (n < last) {
            last = n;
        }

        return tweets.subList(n - last, n);
    }

    private List<Node> getFirstTen(List<Node> tweets) {
        tweets.sort((a, b) -> b.id - a.id);
        int first = 10;
        int n = tweets.size();
        if (n < first) {
            first = n;
        }

        return tweets.subList(0, first);
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new feeds recently and sort by timeline
     */
    public List<Tweet> getNewsFeed(int user_id) {
        List<Node> ts = new ArrayList<>();

        if(map.containsKey(user_id)) {
            ts.addAll(map.get(user_id));
        }

        if (followship.containsKey(user_id)) {
            Set<Integer> followers = followship.get(user_id);

            for (int follower : followers) {
                if (map.containsKey(follower)) {
                    List<Node> tweets = map.get(follower);
                    ts.addAll(getLastTen(tweets));
                }
            }
        }

        ts.sort((a, b) -> b.id - a.id);
        ts = getFirstTen(ts);
        return ts.stream().map(n -> n.tweet).collect(Collectors.toList());
    }

    /*
     * @param user_id: An integer
     * @return: a list of 10 new posts recently and sort by timeline
     */
    public List<Tweet> getTimeline(int user_id) {
        List<Node> ts = new ArrayList<>();

        if (map.containsKey(user_id)) {
            List<Node> tweets = map.get(user_id);
            ts = getLastTen(tweets);
        }

        ts.sort((a, b) -> b.id - a.id);
        return ts.stream().map(n -> n.tweet).collect(Collectors.toList());
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void follow(int from_user_id, int to_user_id) {
        Set<Integer> set = followship.getOrDefault(from_user_id, new HashSet<>());
        set.add(to_user_id);
        followship.put(from_user_id, set);
    }

    /*
     * @param from_user_id: An integer
     * @param to_user_id: An integer
     * @return: nothing
     */
    public void unfollow(int from_user_id, int to_user_id) {
        if (followship.containsKey(from_user_id)) {
            followship.get(from_user_id).remove(to_user_id);
        }
    }

    public static void main(String[] args) {
        MiniTwitter miniTwitter = new MiniTwitter();
        System.out.println(miniTwitter.postTweet(1, "LintCode is Good!!!"));
        System.out.println(miniTwitter.getNewsFeed(1));
        System.out.println(miniTwitter.getTimeline(1));
        miniTwitter.follow(2, 1);
        System.out.println(miniTwitter.getNewsFeed(2));
        miniTwitter.unfollow(2, 1);
        System.out.println(miniTwitter.getNewsFeed(2));

        miniTwitter = new MiniTwitter();
        System.out.println(miniTwitter.postTweet(1, "LintCode is Good!!!"));
        System.out.println(miniTwitter.getNewsFeed(1));
        System.out.println(miniTwitter.getTimeline(1));
        miniTwitter.follow(2, 1);
        System.out.println(miniTwitter.postTweet(1, "LintCode is best!!!"));
        System.out.println(miniTwitter.getNewsFeed(2));
        miniTwitter.unfollow(2, 1);
        System.out.println(miniTwitter.getNewsFeed(2));
    }

    static class Node {
        int id;
        Tweet tweet;

        Node(int id, Tweet tweet) {
            this.id = id;
            this.tweet = tweet;
        }

    }

}
