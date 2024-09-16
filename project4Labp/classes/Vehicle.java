/**
 * Instancias desta classe representam veiculos numa via rapida
 * 
 * @author
 *
 */
public class Vehicle {
	// Momento de tempo em que o veiculo chegou a fila
	private int arrival;
	// Unidades de tempo necessarias para processar o pagamento de
	// portagens deste veiculo quando se encontrar em primeiro lugar da fila
	private int duration;
	// A dado instante, as unidades de tempo ainda necessarias para terminar
	// o processamento do veiculo
	private int timeLeft;
	// O valor da portagem a pagar pelo veiculo
	private double toll;

	/**
	 * Construtor
	 * 
	 * @param arrival  O instante de tempo em que o veiculo chegou a fila
	 *
	 * @param duration Unidades de tempo necessarias para processar o pagamento
	 * 				   quando o veiculo e o primeiro na fila
	 * 
	 * @param toll     Valor da portagem a pagar pelo veiculo
	 * 
	 * @requires arrival > 0 && duration > 0
	 */
	public Vehicle(int arrival, int duration, double toll) {
		this.arrival = arrival;
		this.duration = duration;
		this.timeLeft = duration;
		this.toll = toll;
	}

	/**
	 * Decrementar numa unidade, o tempo necessario para processar o veiculo
	 */
	public void decreaseOneTimeUnit() {
		timeLeft--;
	}

	/**
	 * A determinado momento, as unidades de tempo ainda necessarias para
     * terminar o processamento veiculo quando este se encontrar em primeiro lugar da fila
	 */
	public int timeLeft() {
		return timeLeft;
	}

	/**
	 * O instante de tempo em que o veiculo chegou a fila
	 */
	public int arrival() {
		return arrival;
	}

	/**
	 * Unidades de tempo necessarias para processar o veiculo quando ele se encontra
	 * na primeira posicao da fila
	 */
	public int duration() {
		return duration;
	}
	
	/**
     * Valor de portagem a pagar
     */
    public double toll() {
        return toll;
    }

	/**
	 * Representacao textual do veiculo
	 */
	public String toString() {
		return "(" + arrival + "," + duration + ":" + timeLeft + ")";
	}
}
