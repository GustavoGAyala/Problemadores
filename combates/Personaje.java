package combates;


public class Personaje extends Competidor {

	// private TipoCompetidor type = TipoCompetidor.HEROE;
	public Personaje(boolean tipo, String nombre, String pseudo, int velocidad, int fuerza, int resistencia,
			int destreza) throws CaracteristicaNegativaException {
		super(tipo, nombre, pseudo, velocidad, fuerza, resistencia, destreza);
	}

	@Override
	public String toString() {
		return "Nombre= " + this.getNombre() + ", " + "Pseudo= " + this.getPseudo() + ", " + this.getTodasLasCaracteristicas();
	}

}
