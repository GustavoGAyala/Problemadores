package combates;

import java.util.HashMap;

import java.util.Map;

public class Caracteristicas implements Comparable<Caracteristicas> {

	private Map<Caracteristica, Integer> caracteristicas = new HashMap<Caracteristica, Integer>();
	

	public Caracteristicas(int fuerza, int destreza, int velocidad, int resistencia)
			throws CaracteristicaNegativaException {
		if (fuerza < 0 || velocidad < 0 || destreza < 0 || resistencia < 0)
			throw new CaracteristicaNegativaException("Solo se pueden ingresar caracteristicas con valor 0 o superior");
		caracteristicas.put(Caracteristica.FUERZA, fuerza);
		caracteristicas.put(Caracteristica.DESTREZA, destreza);
		caracteristicas.put(Caracteristica.VELOCIDAD, velocidad);
		caracteristicas.put(Caracteristica.RESISTENCIA, resistencia);
	}

	public Integer getCaracteristica(Caracteristica c) {
		return caracteristicas.get(c);
	}

	public void aumentarCaracteristica(Caracteristica c, int value) {
		caracteristicas.replace(c, getCaracteristica(c) + value);
	}

	@Override
	public int compareTo(Caracteristicas o) {
		if (this.caracteristicas.equals(o.caracteristicas))
			return 0;

		return Integer.compare(this.caracteristicas.hashCode(), o.caracteristicas.hashCode()); // FALTA IMPLEMENTAR
	}

	@Override
	public String toString() {
		return " " + caracteristicas;
	}
}