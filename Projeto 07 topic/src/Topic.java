import java.util.ArrayList;
import java.util.Scanner;

class Passageiro {
	String nome;
	int idade;

	public Passageiro(String nome, int idade) {
		this.nome = nome;
		this.idade = idade;
	}

	public String toString() {
		return this.nome + ":" + this.idade;
	}
}

class Topic {
	ArrayList<Passageiro> cadeiras;
	ArrayList<Passageiro> cadeiraspreferenciais;

	public Topic(int lotacao, int qtdpreferenciais) {
		cadeiras = new ArrayList<>();
		cadeiraspreferenciais = new ArrayList<>();
		for(int i = 0; i < lotacao; i++) {
			if(i < qtdpreferenciais) {
				cadeiraspreferenciais.add(null);
			} else {
				cadeiras.add(null);
			}
		}
	}

	public boolean subir(Passageiro passageiro) {
		if(passageiro.idade > 64) {
			for(int i = 0; i < cadeiraspreferenciais.size(); i++) {
				if(cadeiraspreferenciais.get(i) == null) {
					cadeiraspreferenciais.set(i, passageiro);
					return true;
				}
			}
		}
		
		for(int i = 0; i < cadeiras.size(); i++) {
			if(cadeiras.get(i) == null) {
				cadeiras.set(i, passageiro);
				return true;
			}
		}

		for(int i = 0; i < cadeiraspreferenciais.size(); i++) {
			if(cadeiraspreferenciais.get(i) == null) {
				cadeiraspreferenciais.set(i, passageiro);
				return true;
			}
		}
		System.out.println("fail: Topic está lotada");
		return false;
	}

	public Passageiro descer(String nome) {
		for(int i = 0; i < cadeiras.size(); i++) {
			if(cadeiras.get(i) != null && cadeiras.get(i).nome.equals(nome)) {
				cadeiras.set(i, null);
				return cadeiras.get(i);
			}
		}

		for(int i = 0; i < cadeiraspreferenciais.size(); i++) {
			if(cadeiraspreferenciais.get(i) != null && cadeiraspreferenciais.get(i).nome.equals(nome)) {
				cadeiraspreferenciais.set(i, null);
				return cadeiraspreferenciais.get(i);
			}
		}

		System.out.println("fail: O passageiro não se encontra na topic");
		return null;
	}

	public String toString() {
		String saida = "[ ";
		for(Passageiro passageiro : cadeiraspreferenciais) {
			if(passageiro != null) {
				saida += "@" + passageiro + " ";
			} else {
				saida += "@ ";
			}
		}

		for(Passageiro passageiro : cadeiras) {
			if(passageiro != null) {
				saida += "=" + passageiro + " ";
			} else {
				saida += "= ";
			}
		}
		return saida + "]"; 
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Topic topic = null;
		
		while(true) {
		 	String line = scanner.nextLine();
		 	String[] ui = line.split(" ");
		 	
		 	if(ui[0].equals("init")){
	 			topic = new Topic(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
		 	} else if(ui[0].equals("in")) {
		 		topic.subir(new Passageiro(ui[1], Integer.parseInt(ui[2])));
		 	} else if(ui[0].equals("out")) {
		 		topic.descer(ui[1]);
		 	} else if(ui[0].equals("show")) {
		 		System.out.println(topic);
		 	} else if(ui[0].equals("end")) {
		 		break;	
		 	} else {
				System.out.println("Comando invalido");
			}
		}
		
		scanner.close();
  }
}