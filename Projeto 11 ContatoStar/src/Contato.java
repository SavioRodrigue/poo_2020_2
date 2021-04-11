import java.util.ArrayList;

public class Contato {
	private String name;
	private ArrayList<Fone> fones;
	
	public Contato(String name, ArrayList<Fone> fones) {
		this.name = name;
		this.fones = fones;
	}

	public ArrayList<Fone> getFones() {
		return fones;
	}

	public String getName() {
		return name;
	}

	public void addFone(String label, String number) {
		if(!Fone.validateNumber(number)) {
			System.out.println("fail: Numero está invalido");
			return;
		}

		fones.add(new Fone(label, number));
	}

	public void rmFone(int index) {
		if(index < 0 || index >= fones.size()) {
			System.out.println("fail: Indice está invalido");
			return;
		}

		fones.remove(index);
	}

	public String toString() {
		String solver = "- " + this.name + " ";
		for(int i = 0; i < fones.size(); i++) {
			solver += "[" + i + ":" + this.fones.get(i) + "] ";
		}
		return solver;
	}
}