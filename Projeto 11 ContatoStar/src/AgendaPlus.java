import java.util.TreeMap;
import java.util.ArrayList;

public class AgendaPlus extends Agenda {
  private TreeMap<String, ContatoPlus> favoritos;

  public AgendaPlus() {
    favoritos = new TreeMap<>();
  }

  public ArrayList<ContatoPlus> getFavoritos() {
		ArrayList<ContatoPlus> temp = new ArrayList<>();
		for(ContatoPlus contato : this.favoritos.values()) {
			temp.add(contato);
		}
		return temp;
	}

  @Override
	public void rmContato(String name) {
		if(this.getContato(name) == null) {
			System.out.println("fail: Contato inexistente");
			return;
		}

		super.rmContato(name);
		this.favoritos.remove(name);
	}

  public void favoritar(String name) {
    if(this.favoritos.containsKey(name)) {
		System.out.println("fail: Contato já favoritado");
		return;
		}

		if(this.getContato(name) == null) {
			System.out.println("fail: Contato inexistente");
			return;
		}

    ContatoPlus contato = (ContatoPlus) getContato(name);
    contato.setStarred(true);
    this.favoritos.put(name, contato);
  }

  public void desfavoritar(String name) {
    if(!this.favoritos.containsKey(name)) {
		System.out.println("fail: Contato não favoritado");
		return;
		}

		if(this.getContato(name) == null) {
			System.out.println("fail: Contato inexistente");
			return;
		}

    ContatoPlus contato = (ContatoPlus) getContato(name);
    contato.setStarred(false);
    this.favoritos.remove(name, contato);
  }
}
