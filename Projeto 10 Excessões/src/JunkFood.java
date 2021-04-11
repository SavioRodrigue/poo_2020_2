import java.util.ArrayList;
import java.util.Scanner;

class Espiral{
	String nome;
	int qtd;
	float preco;

	public Espiral(String nome, int qtd, float preco) {
		this.nome = nome;
		this.qtd = qtd;
		this.preco = preco;
	}

	public String toString() {
		return "[" + this.nome + " : " + this.qtd + "U : " + this.preco + " RS]";
	}
}

class Maquina {
	ArrayList<Espiral> espirais;
    float saldoCliente;
    float lucro;
	int maxProdutos;
	
    public Maquina(int qtdEspirais, int maxProdutos){
		this.espirais = new ArrayList<>();
		this.maxProdutos = maxProdutos;
		this.lucro = 0;
		this.saldoCliente = 0;
		Espiral espiral = null;
		
		for(int i = 0; i < qtdEspirais; i++) {
			espirais.add(espiral);
		}
	}
	
	public void inserirDinheiro(float valor) {
		this.saldoCliente += valor;
	}

	public void pedirTroco() {
		if(saldoCliente < 0) {
			throw new RuntimeException("fail: saldo insuficiente");
		}
		System.out.println("Você recebeu " + this.saldoCliente + " RS");
		this.saldoCliente = 0;
	}

	public void vender(int indice) {
		if(indice < 0 || indice >= this.espirais.size()) {
			throw new IndexOutOfBoundsException("fail: indice invalido");
		}
		if(this.saldoCliente < this.espirais.get(indice).preco) {
			throw new RuntimeException("fail: saldo insuficiente");
		}
		if(this.espirais.get(indice).qtd == 0){
			throw new RuntimeException("fail: espiral sem produto");
		}
		this.saldoCliente -= this.espirais.get(indice).preco;
		this.espirais.get(indice).qtd -= 1; 
		System.out.println("Compra efetuada");
	}

	public void alterarEspiral(int indice, String nome, int qtd, float preco){
		if(indice < 0 || indice >= this.espirais.size()) {
			throw new IndexOutOfBoundsException("fail: indice invalido");
		}
		if(qtd > this.maxProdutos) {
			throw new RuntimeException("fail: quantidade excedida");
		}
		espirais.set(indice, new Espiral(nome, qtd, preco));
	}

	public void limparEspiral(int indice) {
		if(indice < 0 || indice >= this.espirais.size()) {
			throw new IndexOutOfBoundsException("fail: indice invalido");
		}
		espirais.set(indice, new Espiral("empty", 0, 0));
	}

	public String toString() {
        String saida = "saldo: " + this.saldoCliente + "\n";
        int i = 0;
        for(Espiral espiral : espirais){
            saida += i + " " + espiral + " \n";
            i++;
        }
        return saida;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Maquina maquina = null;

        while(true){
			try {
				String line = scanner.nextLine();
				String[] ui = line.split(" ");

				if(ui[0].equals("init")){
					maquina = new Maquina(Integer.parseInt(ui[1]), Integer.parseInt(ui[2]));
				}
				else if(ui[0].equals("dinheiro")){
					maquina.inserirDinheiro(Float.parseFloat(ui[1]));

				}
				else if(ui[0].equals("set")){
					maquina.alterarEspiral(Integer.parseInt(ui[1]), ui[2], Integer.parseInt(ui[3]), Float.parseFloat(ui[4]));
				}
				else if(ui[0].equals("limpar")) {
					maquina.limparEspiral(Integer.parseInt(ui[1]));
				}
				else if(ui[0].equals("troco")){
					maquina.pedirTroco();
				}
				else if(ui[0].equals("comprar")){
					maquina.vender(Integer.parseInt(ui[1]));
				}
				else if(ui[0].equals("show")){
					System.out.println(maquina);
				}
				else if(ui[0].equals("end")){
					break;
				} else {
					System.out.println("Comando invalido");
				}
			} catch(IndexOutOfBoundsException e) {
				System.out.println(e.getMessage());
			} catch(RuntimeException e) {
				System.out.println(e.getMessage());
			}
        }
        scanner.close();
    
    }
}