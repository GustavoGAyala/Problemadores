package combates;

public abstract class Competidor implements Comparable<Competidor> {

	private boolean estoyEnLiga;
	private boolean tipo;

	private String pseudo = "";
	private String nombre = "";

	private Caracteristicas caracteristicas;

	public Competidor(boolean tipo, String nombreDeLiga) throws CaracteristicaNegativaException {
		this.tipo = tipo;
		this.pseudo = nombreDeLiga;
		caracteristicas = new Caracteristicas(0, 0, 0, 0);
	}

	public Competidor(boolean tipo, String nombre, String pseudo, int fuerza, int destreza, int velocidad,
			int resistencia) throws CaracteristicaNegativaException {
		this.tipo = tipo;
		setNombre(nombre);
		setPseudo(pseudo);
		caracteristicas = new Caracteristicas(fuerza, destreza, velocidad, resistencia);
	}

	public boolean esGanador(Competidor o, Caracteristica c) {
		Caracteristica[] caracteristicas = Caracteristica.values();
		boolean hayGanador = false;
		boolean esGanador = false;
		int ordinal = c.ordinal();
		int ganador = 0;
		for (int i = 0; i < 7 && !hayGanador; i++) {
			ganador = this.caracteristicas.getCaracteristica(caracteristicas[ordinal])
					.compareTo(o.caracteristicas.getCaracteristica(caracteristicas[ordinal]));

			if (ganador == 1) {
				esGanador = true;
			}
			if (ganador == 0) {
				ordinal++;
				if (ordinal == 4)
					ordinal = 0;

			}

		}
		if(ganador==0) {
			System.out.println("EMPATE");			
		}
		return esGanador;
	}

//	public boolean esGanador(Competidor competidor, Caracteristica c) {
//		if(this.esHeroe() == competidor.esHeroe()) {
//			System.out.println("no se puede competir siendo del mismo tipo");
//		}
//
//		Caracteristica[] todasLasCaracteristicas = new Caracteristica[7];
//
//		todasLasCaracteristicas[0] = Caracteristica.FUERZA;
//		todasLasCaracteristicas[1] = Caracteristica.DESTREZA;
//		todasLasCaracteristicas[2] = Caracteristica.VELOCIDAD;
//		todasLasCaracteristicas[3] = Caracteristica.RESISTENCIA;
//		todasLasCaracteristicas[4] = Caracteristica.FUERZA;
//		todasLasCaracteristicas[5] = Caracteristica.DESTREZA;
//		todasLasCaracteristicas[6] = Caracteristica.VELOCIDAD;
//		
////		for(int i = 0; i<todasLasCaracteristicas.length; i++) {
////			
////		}
//
//		boolean aux = false;
//
//		for (int i = c.ordinal(); i < (c.ordinal() + 4); i++) {
//
//			if (compararCaracteristicas(competidor, todasLasCaracteristicas, i) == -1) {
//				aux = true;
//				System.out.println("gane");
//				return aux;
//
//			}
//			if (compararCaracteristicas(competidor, todasLasCaracteristicas, i) == 1) {
//				aux = false;
//				System.out.println("gano el");
//				return aux;
//			}
//
//		}
//		System.out.println("EMPATE");
//		return aux;
//	}

//	private int compararCaracteristicas(Competidor competidor, Caracteristica[] todasLasCaracteristicas, int i) {
//		return this.caracteristicas.getCaracteristica(todasLasCaracteristicas[i])
//				.compareTo(competidor.caracteristicas.getCaracteristica(todasLasCaracteristicas[i]));
//	}

//	public boolean esGanador(Competidor otroCompetidor) {
//		private int[]
//		if(this.compareTo(otroCompetidor) == 1) {
//		return true;	
//		}
//		else return false;
	
	
	
	public int compareTo(Competidor otroCompetidor) {

		return this.caracteristicas.compareTo(otroCompetidor.caracteristicas);
	}

	@Override
	public String toString() {
		String tipo ="Villano , ";
		if(esHeroe()) {
			tipo = "Heroe , ";
		}
		return tipo+getNombre()+", "+getPseudo()+obtenerCaracteristica(Caracteristica.FUERZA)+", "+obtenerCaracteristica(Caracteristica.DESTREZA)
		+", "+obtenerCaracteristica(Caracteristica.VELOCIDAD)+", "+obtenerCaracteristica(Caracteristica.RESISTENCIA);
	}

	public boolean getEstoyEnLiga() {
		return estoyEnLiga;
	}

	public void setEstoyEnLiga(boolean estoyEnLiga) {
		this.estoyEnLiga = estoyEnLiga;
	}

	public Caracteristicas getTodasLasCaracteristicas() {
		return caracteristicas;
	}
	

	public int obtenerCaracteristica(Caracteristica c) {
		return caracteristicas.getCaracteristica(c);
	}

	public String getPseudo() {
		return pseudo;
	}

	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public boolean esHeroe() {
		return tipo;
	}

}
