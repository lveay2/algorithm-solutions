package lintcode.system_design.min_twitter;

public class Tweet {
  public int id;
  public int user_id;
  public String text;

  private Tweet(int id, int user_id, String text) {
    this.id = id;
    this.user_id = user_id;
    this.text = text;
  }

  public static Tweet create(int user_id, String tweet_text) {
    return new Tweet(MiniTwitter.getId(), user_id, tweet_text);
  }

  @Override
  public String toString() {
    return String.valueOf(id);
  }
}
