import java.util.Scanner;

class Pessoa {
  String nome;
  int idade;
  Pessoa(String nome, int idade) {
      this.nome = nome;
      this.idade = idade;
  }
  public String toString() { 
      return "Nome: " + nome + " | " + "Idade: " + idade;
  }
}

class Moto {
  int potencia;
  Pessoa pessoa;
  int tempo;

  Moto(int potencia) {
      this.potencia = potencia;
      this.tempo = 0;
  }

  void subir(Pessoa pass) {
      if(pessoa == null) {
          this.pessoa = pass;
          System.out.println(pass.nome + " Subiu ");
      } else {
          System.out.println("Já tem uma pessoa");
      }
  }

  Pessoa descer(){
      if (pessoa == null) {
          System.out.println("Não tem ninguém");
          return null;
      } else {
          Pessoa aux = pessoa;
          pessoa = null;
          System.out.println("pessoa desceu");
          return aux;
      }
  }

  void comprarTempo(int tempo){
      this.tempo += tempo;
      System.out.println("Adicionado " + tempo );
  }
  
  void dirigir(int tempo){ 
      if (this.pessoa == null){
          System.out.println("não tem ninguém na moto");
      } else if (pessoa.idade > 10 ){
          System.out.println("idade superior");
      } else if (this.tempo >= tempo) {
          this.tempo -= tempo;
          System.out.println("Você andou por " + tempo + "minutos");
      } else {
          System.out.println("Você não tem tempo suficiente.");
      }
  }

  public String toString() {
    return "potencia: " + potencia + ", tempo: " + tempo + ", pessoa: " + this.pessoa;  
  }
}

public class Motoca {
    public static void main(String[] args) {
        Moto motoca = new Moto(1);
        Scanner scanner = new Scanner(System.in);

        while(true) {
            String line = scanner.nextLine();
            String ui[] = line.split(" ");

            if(ui[0].equals("show")) {
                System.out.println(motoca);
            } else if(ui[0].equals("init")) {
                motoca = new Moto(Integer.parseInt(ui[1])); 
            } else if(ui[0].equals("in")) {
                Pessoa pessoa = new Pessoa(ui[1], Integer.parseInt(ui[2]));
                motoca.subir(pessoa);
            } else if(ui[0].equals("out")) {
                motoca.descer();
            } else if(ui[0].equals("buy")) {
                motoca.comprarTempo(Integer.parseInt(ui[1]));
            } else if(ui[0].equals("drive")) {
                motoca.dirigir(Integer.parseInt(ui[1]));
            } else {
                break;
            }
        }
        scanner.close();
    }
}