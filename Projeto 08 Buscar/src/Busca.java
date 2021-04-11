import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

class Contato {
	private String name;
	private ArrayList<Fone> fones;
	
	public Contato(String name) {
		this.name = name;
		this.fones = new ArrayList<>();
	}

	public ArrayList<Fone> getFones() {
		return fones;
	}

	public String getName() {
		return name;
	}

	public void addFone(String label, String numero) {
		if(!Fone.validateNumero(numero)) {
			System.out.println("fail: numero repassado está invalido");
			return;
		}

		fones.add(new Fone(label, numero));
	}

	public void rmFone(int index) {
		if(index < 0 || index >= fones.size()) {
			System.out.println("fail: indice repassado está invalido");
			return;
		}

		fones.remove(index);
	}

	public String toString() {
		String aux = "- " + this.name + " ";
		for(int i = 0; i < fones.size(); i++) {
			aux += "[" + i + ":" + this.fones.get(i) + "] ";
		}
		return aux;
	}
}

class Fone {
	private String label;
	private String numero;

	public Fone(String serial) {
		String[] temp = serial.split(":");
		this.label = temp[0];
		this.numero = temp[1];
	}

	public Fone(String label, String numero) {
		this.label = label;
		this.numero = numero;
	}

	public String getLabel() {
		return this.label;
	}

	public String getNumero() {
		return this.numero;
	}

	public static boolean validateNumero(String numero) {
		String solve = "0123456789()-";
		for(int i = 0; i < numero.length(); i++) {
			if(solve.indexOf(numero.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return this.label + ":" + this.numero;
	}
}

class ComparatorContatos implements Comparator<Contato> {

	@Override
	public int compare(Contato arg0, Contato arg1) {
		if(arg0 == null || arg1 == null)
			return -1;
		
		return arg0.getName().compareTo(arg1.getName());
	}
}

class Agenda {
	private ArrayList<Contato> contatos;

	public Agenda() {
		this.contatos = new ArrayList<>();
	}

	public int findContact(String name) {
		for(int i = 0; i < contatos.size(); i++) {
			if(contatos.get(i).getName().equals(name)) {
				return i;
			}
		}
		return -1;
	}

	public Contato getContato(String name) {
		if(findContact(name) != -1) {
			return this.contatos.get(findContact(name));
		}
		return null;
	}

	public ArrayList<Contato> getContatos() {
		return this.contatos;
	}

	public void addContato(String name, ArrayList<Fone> fones) {
		if(fones.isEmpty()) {
			return;
		}

		if(findContact(name) == -1) {
			contatos.add(new Contato(name));

			for(Fone fone : fones) {
				contatos.get(findContact(name)).addFone(fone.getLabel(), fone.getNumero());
			}
			Collections.sort(contatos, new ComparatorContatos());
            return;
		}

        for(Fone fone : fones) {
			contatos.get(findContact(name)).addFone(fone.getLabel(), fone.getNumero());
		}
		Collections.sort(contatos, new ComparatorContatos());
	}

	public boolean rmContato(String name) {
		if(findContact(name) == -1) {
			return false;
		}

		this.contatos.remove(findContact(name));
		return true;
	}

	public void rmFone(String name, int index) {
		if(findContact(name) == -1) {
			System.out.println("fail: Contato inexistente");
			return;
		}

		this.contatos.get(findContact(name)).rmFone(index);
	}

	public ArrayList<Contato> busca(String pattern) {
		ArrayList<Contato> busca = new ArrayList<>();

		for(Contato contato : contatos) {
			if(contato.toString().contains(pattern)) {
				busca.add(contato);
			}
		}

		return busca;
	}

	public String toString(){
        String saida = "";
        for(Contato contato : contatos)
            saida += contato + "\n";
        return saida + "";
    }

	public static void main(String[] args){
		Agenda agenda = new Agenda();
		Scanner scanner = new Scanner(System.in);

		while(true) {
			String line = scanner.nextLine();
			String[] ui = line.split(" ");

			if(ui[0].equals("add")) {
				ArrayList<Fone> fones = new ArrayList<>();
				String name = ui[1];

				for(int i = 2; i < ui.length; i++) {
					String[] fone = ui[i].split(":");
					fones.add(new Fone(fone[0], fone[1]));
				}

				agenda.addContato(name, fones);
			} else if(ui[0].equals("rmFone")) {
				agenda.rmFone(ui[1], Integer.parseInt(ui[2]));
			} else if(ui[0].equals("rm")) {
				agenda.rmContato(ui[1]);
			} else if(ui[0].equals("search")) {
				ArrayList<Contato> aux = agenda.busca(ui[1]);

				String solver = "";
				for(Contato contato : aux) {
					solver += contato.toString() + "\n";
				}

				System.out.println(solver);
			} else if(ui[0].equals("show")) {
				System.out.println(agenda);
			} else if(ui[0].equals("end")) {
				break;
			} else {
				System.out.println("Escolha invalida");
			}
		}

		scanner.close();
    }
}