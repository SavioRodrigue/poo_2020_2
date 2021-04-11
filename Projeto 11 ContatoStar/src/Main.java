import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		AgendaPlus agenda = new AgendaPlus();
		Scanner scanner = new Scanner(System.in);

        while(true) {
            String line = scanner.nextLine();
			String[] ui = line.split(" ");

                if(ui[0].equals("add")) {
                    ArrayList<Fone> fones = new ArrayList<>();
                    for(int i = 2; i < ui.length; i++) {
                        String[] fone = ui[i].split(":");
                        fones.add(new Fone(fone[0], fone[1]));
                    }
    
                    agenda.addContato(new ContatoPlus(ui[1], fones));
                } else if(ui[0].equals("rmFone")) {
                    agenda.rmFone(ui[1], Integer.parseInt(ui[2]));
                } else if(ui[0].equals("rmContact")) {
                    agenda.rmContato(ui[1]);
                } else if(ui[0].equals("star")) {
                    agenda.favoritar(ui[1]);
                } else if(ui[0].equals("unstar")) {
                    agenda.desfavoritar(ui[1]);
                } else if(ui[0].equals("starred")) {
                    for(Contato contact : agenda.getFavoritos()) {
                        System.out.println(contact);
                    }
                } else if(ui[0].equals("search")) {
                    ArrayList<Contato> find = agenda.search(ui[1]);
    
                    String solver = "";
                    for(Contato contato : find) {
                        solver += contato.toString() + "\n";
                    }
    
                    System.out.println(solver);
                } else if(ui[0].equals("show")) {
                    System.out.println(agenda);
                } else if(ui[0].equals("end")) {
                    break;
                } else {
                    System.out.println("Escolha inválida");
                }
 
        }
        scanner.close();
	}
}