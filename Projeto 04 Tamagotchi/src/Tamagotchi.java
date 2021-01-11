import java.util.Scanner;

public class Tamagotchi{
    private int energia;
    private int energiaMax;
    private int saciedade;
    private int saciedadeMax;
    private int limpeza;
    private int limpezaMax;
    private boolean vivo;
    int idade;
    
    public Tamagotchi(int energiaMax, int saciedadeMax, int limpezaMax){
        this.energiaMax = energiaMax;
        this.energia = energiaMax;
        this.saciedadeMax = saciedadeMax;
        this.saciedade = saciedadeMax;
        this.limpezaMax = limpezaMax;
        this.limpeza = limpezaMax;
        this.vivo = true;
        this.idade = 0;
    }
    public int getEnergiaMax(){
        return this.energiaMax;
    }
    public int getEnergia(){
        return this.energia;
    }
    public int getSaciedadeMax(){
        return this.saciedadeMax;
    }
    public int getSaciedade(){
        return this.saciedade;
    }
    public int getLimpezaMax(){
        return this.limpezaMax;
    }
    public int getLimpeza(){
        return this.limpeza;
    }
    private void setEnergia(int valor){
		this.energia += valor;
		if(this.getEnergia() <= 0) {
			this.energia = 0;
			System.out.println("Morreu por falta de energia");
			vivo = false;
			return;
		}
		if(this.getEnergia() > this.getEnergiaMax()) {
			this.energia = this.energiaMax;
		}
    }
    private void setSaciedade(int valor){
        this.saciedade += valor;
        if(this.getSaciedade() <= 0){
            this.saciedade = 0;
            System.out.println("Morreu de fome");
            vivo = false;
            return;
        }
        if(this.getSaciedade() > this.getSaciedadeMax()){
            this.saciedade = this.saciedadeMax;
        }
    }
    private void setLimpeza(int valor){
        this.limpeza += valor;
        if(this.getLimpeza() <= 0){
            this.limpeza = 0;
            System.out.println("Morreu por esta sujo demais");
            vivo = false;
            return;
        }
        if(this.getLimpeza() > this.getLimpezaMax()){
            this.limpeza = this.limpezaMax;
        }
	}
    void comer(){
        if(vivo){
            this.setEnergia(-1);
            this.setSaciedade(+5);
            this.setLimpeza(-2);
            this.idade++;
            System.out.println("Otima comida");
        } else {
			System.out.println("Pet esta morto");
		}
    }	
    void dormir(){
        if(vivo){
			this.idade += this.getEnergiaMax() - this.getEnergia();
			this.setEnergia(this.getEnergiaMax());
        } else {
			System.out.println(" Pet esta morto ");
		}
    }
    void brincar(){
        if(vivo){
            this.setEnergia(-1);
            this.setSaciedade(-2);
            this.setLimpeza(-2);
            this.idade++;
            System.out.println("Brincadeira boa");
        } else {
			System.out.println(" Pet esta morto ");
		}
    }
    void tomarBanho(){
        if(vivo){
            this.setEnergia(-1);
            this.setSaciedade(-2);
            this.setLimpeza(this.getLimpezaMax());
            this.idade++;
            System.out.println("Otimo banho");
        } else {
			System.out.println("Pet esta morto");
		}
    }

    public String toString(){
        return "Energia: " + this.energia + "/" + this.energiaMax + " Saciedade: " + this.saciedade + "/" + this.saciedadeMax + " Limpeza: " + this.limpeza + "/" + this.limpezaMax + " Idade: " + this.idade;
    }
    public static void main(String[] args) {
		Tamagotchi tamagotchi = null;
        Scanner scanner = new Scanner(System.in);
		while(true){
			String line = scanner.nextLine();
			String ui[] = line.split(" ");
            if(ui[0].equals("init")){
				int energia = Integer.parseInt(ui[1]);
				int saciedade = Integer.parseInt(ui[2]);
				int limpeza = Integer.parseInt(ui[3]);
				
                tamagotchi = new Tamagotchi(energia, saciedade, limpeza);
            }else if(ui[0].equals("comer")){
                tamagotchi.comer();
            }
            else if(ui[0].equals("brincar")){
                tamagotchi.brincar();
            }
            else if(ui[0].equals("banho")){
                tamagotchi.tomarBanho();
            }
            else if(ui[0].equals("dormir")){
                tamagotchi.dormir();
            }
            else if(ui[0].equals("show")){
                System.out.println(tamagotchi);
            }
            else if(ui[0].equals("sair")){
                break;
            }
            else{
                System.out.println("Comando não aceito");
            }
		}    
		scanner.close();
    }
}