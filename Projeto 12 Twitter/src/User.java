import java.util.TreeMap;

public class User {
  private String username;
  private TreeMap<String, User> seguidores;
  private TreeMap<String, User> seguindo;
  private TreeMap<Integer, Tweet> postagens;
  private int unreadCount;

  public User(String username) {
    this.username = username;
    this.seguidores = new TreeMap<>();
    this.seguindo = new TreeMap<>();
    this.postagens = new TreeMap<>();
    unreadCount = 0;
  }
  
  public String getUsername() {
    return this.username;
  }
  
  public Tweet getTweet(int id) {
    if(postagens.containsKey(id)) {
      return this.postagens.get(id);
    }
    System.out.println("fail: tweet não foi encontrado");
    return null;
  }

  public String getUnread() {
    String saida = "";
    for(int i = this.postagens.size() - unreadCount; i < this.postagens.size(); i++) {
      saida += this.postagens.get(i);
    }
    unreadCount = 0;
    return saida;
  }

  public String getpostagens() {
    String saida = "";
    for(Tweet tweet : this.postagens.values()) {
      saida += tweet;
    }
    unreadCount = 0;
    return saida;
  }

  public void follow(User user) {
    if(seguindo.containsKey(user.username)) {
      System.out.println("fail: Você já está seguido este usuário");
      return;
    }
    this.seguindo.put(user.username, user);
    user.seguidores.put(this.username, this);
  }
  
  public void unfollow(User user) {
    if(!seguindo.containsKey(user.getUsername())) {
      System.out.println("fail: Você não segue esse usuário");
      return;
    }
    this.seguindo.remove(user.getUsername());
    user.seguidores.remove(this.getUsername());
  }
  
  public void enviarTweet(Tweet tweet) {
    this.postagens.put(tweet.getId(), tweet);
    for(User user : seguidores.values()) {
      user.postagens.put(tweet.getId(), tweet);
      user.unreadCount++;
    }
  }

  public String toString() {
    String saida = "";
    saida += this.getUsername() + "\n" + " seguidos\t[";

    for(User user : this.seguindo.values()) {
      saida += " " + user.getUsername() + " ";
    }
    saida += "]\n seguidores\t[";
    for(User user : this.seguidores.values()) {
      saida += " " + user.getUsername() + " ";
    }
    saida += "]";
    return saida;
  }
}