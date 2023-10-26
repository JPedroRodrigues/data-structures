public class Carro {
	private int ano;
	private String marca;
	private String modelo;
	private String categoria;
	
	public Carro() {
		this(0, "Sem marca", "Sem modelo", "Sem categoria");
	}
	
	public Carro(int ano, String marca, String modelo, String categoria) {
		this.ano = ano;
		this.marca = marca;
		this.modelo = modelo;
		this.categoria = categoria;
	}
	
	public int getAno() {
		return ano;
	}
	
	public void setAno(int ano) {
		this.ano = ano;
	}
	
	public String getMarca() {
		return marca;
	}
	
	public void setMarca(String marca) {
		this.marca = marca;
	}
	
	public String getModelo() {
		return modelo;
	}
	
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	
	public String getCategoria() {
		return categoria;
	}
	
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public void mostra() {
		System.out.printf("Ano: %d, Marca: %s, Modelo: %s, Categoria: %s\n", ano, marca, modelo, categoria);
	}
}
