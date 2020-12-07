import java.util.Scanner;

public class Calango{
  int bucho;
  int maxBucho;
  int nPatas;
  boolean sujo;
  boolean alive;
  int nVidas;

  Calango(){
    this.bucho = 100;
    this.maxBucho = 100;
    this.nPatas = 4;
    this.sujo = false;
    this.alive = true;
    this.nVidas = 3;
  }

  void comer(int qtd){
    if(bucho < maxBucho){
      bucho += maxBucho;
      if(bucho > maxBucho) {
        bucho = maxBucho;
      }
      System.out.println("Comi");
      return;
    }
    if(bucho == 0) {
      alive = false;
      return;
    }
    System.out.println("Tô cheio");
  }

  void andar(int qtd){
    if(nPatas < 2){
      System.out.println("Estou impossibilitado de tal tarefa");
      return;
    }
    if(sujo) {
      System.out.println("Estou sujo");
      return;
    }
    if(bucho > 0){
      if(bucho <= qtd) {
        bucho = 0;
        System.out.println("Morri");
        alive = false;
        nVidas--;
        if(nVidas == 0) {
          System.out.println("Não tem mais jeito");
        }
        return;
      } else {
        bucho -= qtd;
        System.out.println("Que passeio agradavel");
        sujo = true;
        return;
      }
    }
  }

  void acidentar(){
    if(nPatas > 0){
      nPatas -= 1;
      System.out.println("Ouch! Perdi uma pata");
    }
    if(nPatas == 0) {
      alive = false;
      nVidas--;
      if(nVidas == 0) {
        System.out.println("Não tem mais jeito");
      }
      System.out.println("Morri");
    }
  }

  void regenerar(){
    if(nPatas == 4){
      System.out.println("Estou perfeito");
    }else if (bucho > 0){
      nPatas += 1;
      bucho -= 25;
      System.out.println("Opa! Recuperei uma pata!");
    }else{
      System.out.println("Nao tenho energia suficiente para me recuperar");
    }
  }

  void dormir() {
    if(bucho < maxBucho) {
      bucho += 25;
      System.out.println("Dormi");
      return;
    }
    System.out.println("Estou sem sono");
  }

  void banho() {
    if(sujo) {
      sujo = false;
      System.out.println("estou limpinho");
      return;
    }
    System.out.println("ja estou limpo");
  }

  void ressuscitar() {
    if(nVidas != 0){
      bucho = 100;
      nPatas = 4;
      sujo = false;
      alive = true;
      System.out.println("Estou vivo de novo");
      return;
    }
    System.out.println("Ainda estou vivo");
  }

  public String toString() {
    return "Bucho: " + bucho + "/" + maxBucho + ", Patas: " + nPatas + ", Vivo: " + alive + ", Sujo: " + sujo + ", Vidas: " + nVidas;
  }

  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Calango deadlango = new Calango();

    while(true) {
      String line = scanner.nextLine();
      String ui[] = line.split(" ");
      if(ui[0].equals("comer")) {
        int qtd = Integer.parseInt(ui[1]);
        deadlango.comer(qtd);
      } else if(ui[0].equals("show")) {
        System.out.println(deadlango);
      } else if(ui[0].equals("andar")) {
        int qtd = Integer.parseInt(ui[1]);
        deadlango.andar(qtd);
      } else if(ui[0].equals("acidentar")) {
        deadlango.acidentar();
      } else if(ui[0].equals("regenerar")) {
        deadlango.regenerar();
      } else if(ui[0].equals("dormir")) {
        deadlango.dormir();
      } else if(ui[0].equals("banho")) {
        deadlango.banho();
      } else if(ui[0].equals("ressuscitar")) {
        deadlango.ressuscitar();
      } else if(ui[0].equals("end")) {
        break;
      }
    }
    scanner.close();
  }
}
