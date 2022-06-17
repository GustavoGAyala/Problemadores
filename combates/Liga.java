package combates;

import java.util.ArrayList;
import java.util.Iterator;

public class Liga extends Competidor {

	private ArrayList<Competidor> liga;

	public Liga(boolean tipo, String nombre) throws CaracteristicaNegativaException {
		super(tipo, nombre);
		liga = new ArrayList<Competidor>();
	}

	@Override
	public String toString() {
		String aux = "";
		Iterator<Competidor> it = liga.iterator();
		// int index=0;
		while (it.hasNext()) {
			if (aux == "") {
				aux = it.next().getPseudo();

			}
			aux = "," + " " + it.next().getPseudo();
		}

		return aux;
	}

	public void addCompetidor(Competidor competidor) throws YaEstoyEnLigaException, TipoDeCompetidorInvalidoException {
		if (competidor.getEstoyEnLiga()) {
			throw new YaEstoyEnLigaException("Soy " + competidor.getPseudo() + " y ya estoy en una liga");
		}
		if (this.esHeroe() != competidor.esHeroe()) {
			throw new TipoDeCompetidorInvalidoException("El tipo de competidor no coincide con el tipo de la liga.");
		}
		liga.add(competidor);
		competidor.setEstoyEnLiga(true);
		this.getTodasLasCaracteristicas().aumentarCaracteristica(Caracteristica.FUERZA,
				competidor.obtenerCaracteristica(Caracteristica.FUERZA));
		this.getTodasLasCaracteristicas().aumentarCaracteristica(Caracteristica.DESTREZA,
				competidor.obtenerCaracteristica(Caracteristica.DESTREZA));
		this.getTodasLasCaracteristicas().aumentarCaracteristica(Caracteristica.RESISTENCIA,
				competidor.obtenerCaracteristica(Caracteristica.RESISTENCIA));
		this.getTodasLasCaracteristicas().aumentarCaracteristica(Caracteristica.VELOCIDAD,
				competidor.obtenerCaracteristica(Caracteristica.VELOCIDAD));
	}

	public Integer obtenerPromedio(Caracteristica c) {
		return this.obtenerCaracteristica(c) / liga.size();
	}
}
