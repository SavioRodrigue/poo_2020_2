import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Scanner;

class Contato {
    private String name;
	private ArrayList<Fone> telefone;
    private boolean starred;

    public Contato(String name) {
		this.name = name;
        this.telefone = new ArrayList<>();
        this.starred = false;
	}

    public ArrayList<Fone> gettelefone() {
		return telefone;
	}

	public String getName() {
		return name;
	}

	public boolean getStarred() {
		return starred;
	}

    public void setBookmark(boolean value) {
        this.starred = value;
    }

    public void addFone(String label, String number) {
		if(!Fone.validateNumber(number)) {
			System.out.println("fail: número está inválido");
			return;
		}
		telefone.add(new Fone(label, number));
	}

    public void rmTelefone(int index) {
		if(index < 0 || index >= telefone.size()) {
			System.out.println("fail: indice está invalido");
			return;
		}

		telefone.remove(index);
	}
    
	public String toString() {
		String aux = this.name + " ";
		for(int i = 0; i < telefone.size(); i++) {
			aux += "[" + i + ":" + this.telefone.get(i) + "] ";
		}
		return aux;
	}
}

class Fone {
    private String label;
	private String number;

	public Fone(String serial) {
		String[] temp = serial.split(":");
		this.label = temp[0];
		this.number = temp[1];
	}

	public Fone(String label, String number) {
		this.label = label;
		this.number = number;
	}

	public String getLabel() {
		return this.label;
	}

	public String getNumber() {
		return this.number;
	}

	public static boolean validateNumber(String number) {
		String solve = "0123456789()-";
		for(int i = 0; i < number.length(); i++) {
			if(solve.indexOf(number.charAt(i)) == -1) {
				return false;
			}
		}
		return true;
	}

	public String toString() {
		return this.label + ":" + this.number;
	}
}

class Agenda {
    private TreeMap<String, Contato> contatos;
    private TreeMap<String, Contato> favoritos;

    public Agenda() {
        this.contatos = new TreeMap<>();
        this.favoritos = new TreeMap<>();
    }

    public ArrayList<Contato> getContatos() {
        ArrayList<Contato> aux = new ArrayList<>();
        for(Contato contato : this.contatos.values()) {
            aux.add(contato);
        }
        return aux;
    }

	public Contato getContato(String name) {
		return this.contatos.get(name);
	}

    public ArrayList<Contato> getFavoritos() {
        ArrayList<Contato> aux = new ArrayList<>();

        for(Contato contato : this.favoritos.values()) {
			aux.add(contato);
		}

		return aux;
    }

    public void addContato(String name, ArrayList<Fone> telefone) {
        if(telefone.isEmpty()) {
            return;
        }

        if(this.contatos.containsKey(name)) {
            for(Fone fone : telefone) {
                this.contatos.get(name).addFone(fone.getLabel(), fone.getNumber());
            }
            return;
        }

        this.contatos.put(name, new Contato(name));
        for(Fone fone : telefone) {
            this.contatos.get(name).addFone(fone.getLabel(), fone.getNumber());
        }
    }

    public boolean rmContato(String name) {
        if(this.contatos.containsKey(name)) {
            this.contatos.remove(name);
            this.favoritos.remove(name);
            return true;
        }
        return false;
    }

    public void rmTelefone(String name, int index) {
        if(!this.contatos.containsKey(name)) {
            System.out.println("Contato inexistente");
            return;
        }

        this.contatos.get(name).rmTelefone(index);
    }

    public ArrayList<Contato> busca(String pattern) {
		ArrayList<Contato> busca = new ArrayList<>();

		for(Contato contato : this.contatos.values()) {
			if(contato.toString().contains(pattern)) {
				busca.add(contato);
			}
		}

		return busca;
	}

    public void favoritar(String name) {
        if(!this.contatos.containsKey(name)) {
            System.out.println("Contato inexistente");
            return;
        }

        if(this.favoritos.containsKey(name)) {
            System.out.println("Contato já favoritado");
            return;
        }

        this.contatos.get(name).setBookmark(true);
        this.favoritos.put(name, this.contatos.get(name));
    }

    public void desfavoritar(String name) {
        if(!this.contatos.containsKey(name)) {
            System.out.println("Contato inexistente");
            return;
        }

        if(!this.favoritos.containsKey(name)) {
            System.out.println("Contato nao eh favorito");
            return;
        }

        this.contatos.get(name).setBookmark(false);
        this.favoritos.remove(name);
    }

    public String toString() {
        String saida = "";
        for(Contato contato : this.favoritos.values()) {
            saida += "@ " + contato + "\n";
        }
        for(Contato contato : this.contatos.values()) {
            if(!this.favoritos.containsKey(contato.getName())) {
                saida += "- " + contato + "\n";
            }
        }

        return saida;
    }

    public static void main(String[] args){
		Agenda agenda = new Agenda();
		Scanner scanner = new Scanner(System.in);

		while(true) {
			String line = scanner.nextLine();
			String[] ui = line.split(" ");


			if(ui[0].equals("add")) {
				ArrayList<Fone> telefone = new ArrayList<>();
				String name = ui[1];

				for(int i = 2; i < ui.length; i++) {
					String[] fone = ui[i].split(":");
					telefone.add(new Fone(fone[0], fone[1]));
				}

				agenda.addContato(name, telefone);
			} else if(ui[0].equals("rmTelefone")) {
				agenda.rmTelefone(ui[1], Integer.parseInt(ui[2]));
			} else if(ui[0].equals("rm")) {
				agenda.rmContato(ui[1]);
			} else if(ui[0].equals("star")) {
				agenda.favoritar(ui[1]);
			} else if(ui[0].equals("unstar")) {
				agenda.desfavoritar(ui[1]);
			} else if(ui[0].equals("search")) {
				ArrayList<Contato> procurar = agenda.busca(ui[1]);

				String aux = "";
				for(Contato contato : procurar) {
					aux += contato.toString() + "\n";
				}

				System.out.println(aux);
			} else if(ui[0].equals("starred")) {
				for(Contato contato : agenda.getFavoritos()) {
					System.out.println(contato);
				}
			} else if(ui[0].equals("show")) {
				System.out.println(agenda);
			} else if(ui[0].equals("end")) {
				break;
			}
		}

		scanner.close();
    }
}
