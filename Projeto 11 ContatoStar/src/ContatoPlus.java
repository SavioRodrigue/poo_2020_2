import java.util.ArrayList;

public class ContatoPlus extends Contato {
	private boolean starred;

	public ContatoPlus(String name, ArrayList<Fone> fones) {
		super(name, fones);
		this.starred = false;
  }

  public void setStarred(boolean value) {
    this.starred = value;
  }

  @Override
	public String toString() {
    int i = 0;
		String solver = "";
		if(starred == true) {
			solver += "@ " + getName();
    } else { 
			solver += "- " + getName();
    }

		for(Fone fone : getFones()){
			if(starred == true) {
        solver += " [" + i + ":" + fone.toString() + "]";
      } else {
        solver += " [" + i + ":" + fone.toString() + "]";
      }
			i++;
		}
		return solver;
	}
}