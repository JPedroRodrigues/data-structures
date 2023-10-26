public class Circulo {
	private float raio;
	
	public Circulo(float raio) {
		this.raio = raio;
	}
	
	public void setRaio(float raio) {
		if (raio > 0) this.raio = raio;
		else System.out.println("Raio inválido.");
	}
	
	public float getRaio() {
		return raio;
	}
	
	public float calculaDiametro() {
		return raio * 2;
	}
	
	public float calculaArea() {
		return (float) (Math.PI * Math.pow(raio, 2));
	}
	
	public void mostraDados() {
		System.out.printf("Raio: %.2f\nDiâmetro: %.2f\nÁrea: %.2f\n", raio, calculaDiametro(), calculaArea());
	}
}
