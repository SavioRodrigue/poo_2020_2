import java.util.TreeMap;

class Controller { 
  private TreeMap<String, User> usuarios;
  private TreeMap<Integer, Tweet> tweets;
  private int nextId;

  public Controller() {
    this.usuarios = new TreeMap<>();
    this.tweets = new TreeMap<>();
    this.nextId = 0;
  }

  public void enviarTweet(String username, String msg) {
    if(!usuarios.containsKey(username)) {
      System.out.println("fail: Usuário inexistente");
    }
    Tweet tweet = new Tweet(this.nextId, username, msg);
    this.tweets.put(this.nextId, tweet);
    this.usuarios.get(username).enviarTweet(tweet);
    nextId++;
  }

  public void addUser(String username) {
    if(usuarios.containsKey(username)) {
      System.out.println("fail: Usuário já está cadastrado");
    }
    this.usuarios.put(username, new User(username));
  }

  public User getUser(String username) {
    if(!usuarios.containsKey(username)) {
      System.out.println("Usuário inexistente");
      return null;
    }
    return this.usuarios.get(username);
  }

  public String toString() {
    String saida = "";
    for(User user : usuarios.values()) {
      saida += user.toString() + "\n";
    }
    return saida;
  }
}