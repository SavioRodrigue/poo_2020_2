import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Controller sistema = new Controller();
  
    while(true){
        String line = scanner.nextLine();
        String ui[] = line.split(" ");
            if(ui[0].equals("addUser")) {
                sistema.addUser(ui[1]);
            } else if(ui[0].equals("Seguir")) {
                User user = sistema.getUser(ui[1]);
                if(user != null) {
                    User other = sistema.getUser(ui[2]);
                    if(other != null) {
                        user.follow(other);
                    }
                }
            }  else if(ui[0].equals("Deixardeseguir")) {
                User user = sistema.getUser(ui[1]);
                User other = sistema.getUser(ui[2]);
                user.unfollow(other);
            }  else if(ui[0].equals("like")) {
                User user = sistema.getUser(ui[1]);
                if(user != null) {
                    Tweet tweet = user.getTweet(Integer.parseInt(ui[2]));
                    if(tweet != null) {
                        tweet.like(ui[1]);
                    }
                }
            } else if(ui[0].equals("twittar")) {
                String username = ui[1];
                String msg = "";
                for(int i = 2; i < ui.length; i++) {
                    msg += ui[i] + " ";
                }
                sistema.enviarTweet(username, msg);
            } else if(ui[0].equals("timeline")) {
                User user = sistema.getUser(ui[1]);
                if(user != null) {
                    System.out.print(user.getpostagens());
                }
            } else if(ui[0].equals("show")) {
                System.out.print(sistema);
            } else if(ui[0].equals("end")){
                break;
            } else {
                System.out.println("fail: comando inexistente");
            }

    }
    scanner.close();
  }
}