import java.util.TreeSet;

public class Tweet {
  private int id;
  private String username;
  private String msg;
  private TreeSet<String> likes;

  public Tweet(int id, String username, String msg) {
    this.id = id;
    this.username = username;
    this.msg = msg;
    this.likes = new TreeSet<>();
  }

  public int getId() {
    return this.id;
  }

  public String getUsername() {
    return this.username;
  }

  public String getMessage() {
    return this.msg;
  }

  public void like(String username) {
    for(String user : likes) {
      if(user.equals(username)) {
        System.out.println("fail: Tweet já curtido");
        return;
      }
    }
    this.likes.add(username);
  }

  public String toString() {
    String solver = "";
    String users = "";

    if(likes.size() == 0) {
      solver += this.id + ":" + this.username + "( " + this.msg + ")\n"; 
      return solver;
    } else {
      for(String user : this.likes) {
        users += user + " ";
      }
      solver += this.id + ":" + this.username + "( " + this.msg + ")" + "[ " + users + "]\n";
    }
    return solver;
  }
}