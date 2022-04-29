package oa.karat;

import java.util.*;

/*
You are analyzing data for Aquaintly, a hot new social network.

One of the fun features of Aquaintly is that users can rate movies they have
seen from 1 to 5. We want to use these ratings to make movie recommendations.

Ratings will be provided in the following format:
  [Member Name, Movie Name, Rating]

We consider two users to have similar taste in movies if they have both rated
the same movie as 4 or 5.

A movie should be recommended to a user if:
- They haven't rated the movie
- A user with similar taste has rated the movie as 4 or 5

Example:
ratings = [
    ["Alice", "Frozen", "5"],
    ["Bob", "Mad Max", "5"],
    ["Charlie", "Lost In Translation", "4"],
    ["Charlie", "Inception", "4"],
    ["Bob", "All About Eve", "3"],
    ["Bob", "Lost In Translation", "5"],
    ["Dennis", "All About Eve", "5"],
    ["Dennis", "Mad Max", "4"],
    ["Charlie", "Topsy-Turvy", "2"],
    ["Dennis", "Topsy-Turvy", "4"],
    ["Alice", "Lost In Translation", "1"]
]
If we want to recommend a movie to Charlie, we would recommend "Mad Max" because:
- Charlie has not rated "Mad Max"
- Charlie and Bob have similar taste as they both rated "Lost in Translation" 4 or 5
- Bob rated "Mad Max" a 5

Write a function that takes the name of a user and a collection of ratings, and returns a collection of all movie recommendations that can be made for the given user.

All test cases:
recommendations("Charlie", ratings) => ["Mad Max"]
recommendations("Bob", ratings) => ["Inception", "Topsy-Turvy"]
recommendations("Dennis", ratings) => ["Lost In Translation"]
recommendations("Alice", ratings) => []

recommendations -> similar teaste (4,5) -> rate

loop rating -> list of movies of input user rating >= 4.
movies -> users rating >= 4
movies of users rating >= 4 -> input user

user [movie]
movie [user]

Complexity Variable:
R = number of ratings
M = number of movies
U = number of users
*/
public class MovieRecommandation {
    public static void main(String[] argv) {
        String[][] ratings = {
                {"Alice", "Frozen", "5"},
                {"Bob", "Mad Max", "5"},
                {"Charlie", "Lost In Translation", "4"},
                {"Charlie", "Inception", "4"},
                {"Bob", "All About Eve", "3"},
                {"Bob", "Lost In Translation", "5"},
                {"Dennis", "All About Eve", "5"},
                {"Dennis", "Mad Max", "4"},
                {"Charlie", "Topsy-Turvy", "2"},
                {"Dennis", "Topsy-Turvy", "4"},
                {"Alice", "Lost In Translation", "1"}
        };

        System.out.println(recommend("Charlie", ratings));
        System.out.println(recommend("Bob", ratings));
        System.out.println(recommend("Dennis", ratings));
        System.out.println(recommend("Alice", ratings));
    }

    private static Set<String> recommend(String user, String[][] ratings) {
        Set<String> result = new HashSet<>();

        Map<String, List<String>> user2Movie = new HashMap<>();
        Map<String, List<String>> user2PreferredMovie = new HashMap<>();
        Map<String, Set<String>> movie2User = new HashMap<>();
        for (int i = 0; i < ratings.length; i++) { // U
            String[] rating = ratings[i];
            String name = rating[0];
            String movie = rating[1];
            int rate = Integer.parseInt(rating[2]);

            if (!user2Movie.containsKey(name)) {
                user2Movie.put(name, new ArrayList<>());
                user2PreferredMovie.put(name, new ArrayList<>());
            }
            List<String> movies = user2Movie.get(name);
            movies.add(movie);

            if (!movie2User.containsKey(movie)) {
                movie2User.put(movie, new HashSet<>());
            }
            if (rate >=4) {
                Set<String> users = movie2User.get(movie);
                users.add(name);

                List<String> pm = user2PreferredMovie.get(name);
                pm.add(movie);
            }
        }

        // System.out.println("user2Movie: " + user2Movie);
        // System.out.println("movie2User: " + movie2User);
        // System.out.println("user2PreferredMovie: " + user2PreferredMovie);

        Set<String> adjUsers = new HashSet<>();
        for (Map.Entry<String, Set<String>> e: movie2User.entrySet()) { // m * u
            Set<String> usersHasSameTaste = e.getValue();
            if (usersHasSameTaste.contains(user)) {
                for (String name: usersHasSameTaste) {
                    if (name.equals(user)) {
                        continue;
                    }
                    adjUsers.add(name);
                }
            }
        }
        // System.out.println("adjUsers: " + adjUsers);

        for (String name: adjUsers) {
            List<String> recommondMovies = user2PreferredMovie.get(name);
            for (String rm: recommondMovies) {
                if (!user2Movie.get(user).contains(rm)) {
                    result.add(rm);
                }
            }
        }

        return result;
    }
}

