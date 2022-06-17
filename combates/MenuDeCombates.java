package combates;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

public class MenuDeCombates {

	private List<Competidor> listaDeHeroes = new ArrayList<Competidor>();
	private List<Competidor> listaDeVillanos = new ArrayList<Competidor>();
	private List<Liga> listaDeLigasDeHeroes = new ArrayList<Liga>();
	private List<Liga> listaDeLigasDeVillanos = new ArrayList<Liga>();
	private Map<String, Competidor> mapaDePersonajes = new HashMap<String, Competidor>();
	private Map<String, Liga> mapaDeLigas = new HashMap<String, Liga>();

	/*
	 * El menu se crea con una lista de personajes y ligas predeterminados.
	 */
	public MenuDeCombates() throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada,
			YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {
		
	}

	public void iniciarMenu() 
			throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException,
			TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		String msj = "Bienvenido a la liga de combates, que deseas hacer?\n" + "1: Cargar desde Archivo\n"
				+ "2: Guardar en Archivo\n" + "3: Crear Personaje\n" + "4: Crear Liga\n" + "5: Competir\n"
				+ "6: SALIR\n";

		System.out.println(msj);
		BufferedReader in = null;
		String linea = null;

		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			linea = in.readLine();
			if (Integer.parseInt(linea) != 1 && Integer.parseInt(linea) != 2 && Integer.parseInt(linea) != 3
					&& Integer.parseInt(linea) != 4 && Integer.parseInt(linea) != 5 && Integer.parseInt(linea) != 6) {
				iniciarMenu();
			}
			if (Integer.parseInt(linea) == 1) {
				cargarDesdeArchivo();
			}
			if (Integer.parseInt(linea) == 2) {
				guardarEnArchivo();
			}
			if (Integer.parseInt(linea) == 3) {
				crearPersonaje();
			}
			if (Integer.parseInt(linea) == 5) {
				seleccionarPersonajeOLiga();
			}
			if (Integer.parseInt(linea) == 6) {
				in.close();
			}
		} catch (NumberFormatException e) {
			iniciarMenu();
		}
	}
	private void crearPersonaje() throws TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada,
			YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {
		System.out.println("Seleccione:\n 1:Crear un Heroe\n 2:Crear un Villano\n 3:Volver");
		BufferedReader in = null;
		String linea = null;
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			linea = in.readLine();
			if (Integer.parseInt(linea) != 1 && Integer.parseInt(linea) != 2 && Integer.parseInt(linea) != 3) {
				crearPersonaje();
			}
			if (Integer.parseInt(linea) == 1) {
				crearHeroe();
			}
			if (Integer.parseInt(linea) == 2) {
				crearVillano();
			}
			if (Integer.parseInt(linea) == 3) {

				iniciarMenu();
			}
		} catch (IOException | NumberFormatException e) {
			crearPersonaje();
		}
	}

	private void crearHeroe() throws TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException,
			CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {
		System.out.println("Ingrese : Heroe , 'nombre del heroe', 'pseudo', 'fuerza', 'destreza', 'velocidad', 'resistencia'\n"
						+ "Ejemplo: Heroe, Giriu, Caballero Dragon, 900, 1000, 600, 450");
		BufferedReader in = null;
		String linea = null;
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			linea = in.readLine();

			if (linea == null) {

				crearHeroe();
			}
			agregarPersonaje(linea);

		} catch (IOException | NumberFormatException e) {
			crearPersonaje();
		}

	}

	private void crearVillano() throws CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException,
			TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		System.out.println(
				"Ingrese : Villano , 'nombre del villano', 'pseudo', 'fuerza', 'destreza', 'velocidad', 'resistencia'\n"
						+ "Ejemplo: Villano, Felipe, Malefica, 800, 1000, 600, 750");
		BufferedReader in = null;
		String linea = null;
		try {
			in = new BufferedReader(new InputStreamReader(System.in));
			linea = in.readLine();

			if (linea == null) {

				crearVillano();
			}
			agregarPersonaje(linea);

		} catch (IOException | NumberFormatException e) {
			crearPersonaje();
		}

	}

	private void guardarEnArchivo() {
		guardarPersonajesEnArchivo();
		guardarLigasEnArchivo();
	}

	/*
	 * cargarCompetidores("src/personajes.in"); cargar ligas lo mismo con ligas
	 * cargarLigas("src/ligas.in");
	 */
	private void cargarDesdeArchivo()
			throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException,
			TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		System.out.println("1: Cargar archivo de competidores");
		System.out.println("2: Cargar archivo de ligas");
		System.out.println("3: Volver");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		try {
			if (Integer.parseInt(linea) == 1) {
				escribirRutaDeArchivoCompetidores();
				iniciarMenu();
			}
			if (Integer.parseInt(linea) == 2) {
				escribirRutaDeArchivoLigas();
				iniciarMenu();
			}
			if (Integer.parseInt(linea) == 3) {
				iniciarMenu();
			}
		} catch (NumberFormatException e) {
			cargarPersonajesDesdeArchivo(linea);
			System.out.println(listaDeHeroes.toString() + listaDeVillanos.toString());
		}
	}

	private void escribirRutaDeArchivoCompetidores()
			throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException,
			TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		System.out.println("Escriba la ruta del archivo de competidores");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		cargarPersonajesDesdeArchivo(linea);
		Iterator<Competidor> itrH = listaDeHeroes.iterator();
		Iterator<Competidor> itrV = listaDeVillanos.iterator();

		while (itrH.hasNext()) {
			System.out.println("Lista De Heroes: " + itrH.next().toString());

		}
		while (itrV.hasNext()) {
			System.out.println("Lista De Villanos: " + itrV.next().toString());
		}
	}

	private void escribirRutaDeArchivoLigas()
			throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException,
			CaracteristicaNegativaException, CompetidorCaracteristicaInvalidaException {
		System.out.println("Escriba la ruta del archivo de ligas");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		cargarLigaDesdeArchivo(linea);
	}

	private void seleccionarPersonajeOLiga()
			throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException,
			TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		System.out.println("Seleccione:\n 1:Personaje\n 2:Liga\n 3:Volver");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		try {
			if (Integer.parseInt(linea) != 1 && Integer.parseInt(linea) != 2 && Integer.parseInt(linea) != 3) {
				seleccionarPersonajeOLiga();
			}
			if (Integer.parseInt(linea) == 1) {
				seleccionarPersonaje();
			}
			if (Integer.parseInt(linea) == 2) {
				seleccionarLiga();
			}
			if (Integer.parseInt(linea) == 3) {

				iniciarMenu();
			}
		} catch (NumberFormatException e) {
			seleccionarPersonajeOLiga();
		}
	}
	
	private void seleccionarLiga() throws IOException, TipoDeCompetidorInvalidoException, YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, NoHayArchivoDeEntrada {
		System.out.println("Escriba el el nombre de la liga a seleccionar:\n" + verListaLigas());
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		
		if (!mapaDeLigas.containsKey(linea)) {
			seleccionarLiga();
		}
		if (mapaDeLigas.containsKey(linea)) {
			elegirLiga(linea);
		}
	}

	private void elegirLiga(String nombreDeLiga) throws IOException, TipoDeCompetidorInvalidoException, YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, NoHayArchivoDeEntrada {
		seleccionarContrincante(mapaDeLigas.get(nombreDeLiga));
	
	}

	private void seleccionarPersonaje() throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException,
			TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		System.out.println("Escriba el el nombre del personaje a seleccionar:\n" + verListaPersonajes());
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		
		if (!mapaDePersonajes.containsKey(linea)) {
			seleccionarPersonaje();
		}
		if (mapaDePersonajes.containsKey(linea)) {
			elegirPersonaje(linea);
		}
		

	}

	private void elegirPersonaje(String pseudo) throws IOException, TipoDeCompetidorInvalidoException,
			YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, NoHayArchivoDeEntrada {
		seleccionarContrincante(mapaDePersonajes.get(pseudo));
		

	}

	private void seleccionarContrincante(Competidor yo) throws IOException, TipoDeCompetidorInvalidoException,
			YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, NoHayArchivoDeEntrada {
		System.out.println("Seleccione Un Contrincante:\n 1.Personaje\n 2.Liga");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();

		try {
			if (Integer.parseInt(linea) != 1 && Integer.parseInt(linea) != 2) {
				seleccionarContrincante(yo);
			}
			if (Integer.parseInt(linea) == 1) {
				seleccionarContrincantePersonaje(yo);
			}
			if (Integer.parseInt(linea) == 2) {
				seleccionarContrincanteLiga(yo);
			}
		} catch (NumberFormatException e) {
			seleccionarContrincante(yo);
		}

	}

	private void seleccionarContrincanteLiga(Competidor yo) throws IOException, TipoDeCompetidorInvalidoException, YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, NoHayArchivoDeEntrada {
		if (yo.esHeroe()) {
			for (Liga liga : listaDeLigasDeVillanos) {

				System.out.println(liga.getNombre() + liga.getTodasLasCaracteristicas() + "\n");

			}
		} else {
			for (Liga liga : listaDeLigasDeHeroes) {

				System.out.println(liga.getNombre() + liga.getTodasLasCaracteristicas() + "\n");

			}
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		
		if (!mapaDeLigas.containsKey(linea)) {
			seleccionarContrincanteLiga(yo);
		}
		if (mapaDeLigas.containsKey(linea)) {
			elegirContrincanteLiga(linea, yo);
		}
		
	}

	private void elegirContrincanteLiga(String nombreDeLigaContrincante, Competidor yo) throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		comenzarPelea(yo, mapaDeLigas.get(nombreDeLigaContrincante));
		
	}

	private void seleccionarContrincantePersonaje(Competidor yo) throws IOException, TipoDeCompetidorInvalidoException,
			YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, NoHayArchivoDeEntrada {
		System.out.println("Escriba el el nombre del contrincante: \n");
		if (yo.esHeroe()) {
			for (Competidor competidor : listaDeVillanos) {

				System.out.println(competidor.getPseudo() + competidor.getTodasLasCaracteristicas() + "\n");

			}
		} else {
			for (Competidor competidor : listaDeHeroes) {

				System.out.println(competidor.getPseudo() + competidor.getTodasLasCaracteristicas() + "\n");

			}
		}
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		
		if (!mapaDePersonajes.containsKey(linea)) {
			seleccionarContrincantePersonaje(yo);
		}
		if (mapaDePersonajes.containsKey(linea)) {
			elegirContrincantePersonaje(linea, yo);
		}
		

	}

	private void elegirContrincantePersonaje(String pseudo, Competidor yo) throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		comenzarPelea(yo, mapaDePersonajes.get(pseudo));

	}

	private void comenzarPelea(Competidor yo, Competidor contrincante) throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		System.out.println(
				"Seleccione el atributo por el que pelearan:\n 1.Fuerza\n 2.Destreza\n 3.Velocidad\n 4.Resistencia\n");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();
		Caracteristica[] c = Caracteristica.values();
		try {
			try {
				if(yo.esGanador(contrincante, c[Integer.parseInt(linea)-1]))
					System.out.println(yo.getPseudo() + " WINS!");
				else System.out.println(contrincante.getPseudo() + " WINS!");
			peleaTerminada(yo, contrincante);
			
			}catch(ArrayIndexOutOfBoundsException e) {
				comenzarPelea(yo, contrincante);
			}
		} catch (NumberFormatException e) {
			comenzarPelea(yo, contrincante);
		}

	}

	private void peleaTerminada(Competidor yo, Competidor contrincante)
			throws IOException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException,
			TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada, YaEstoyEnLigaException {
		System.out.println(
				"Pelea Terminada:\n 1:Volver Al Menu\n 2:Pelear Por Otra Caracteristica\n 3:Cambiar Personajes\n");
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String linea = in.readLine();

		try {
			if (Integer.parseInt(linea) != 1 && Integer.parseInt(linea) != 2 && Integer.parseInt(linea) != 3) {
				peleaTerminada(yo, contrincante);
			}
			if (Integer.parseInt(linea) == 1) {
				iniciarMenu();
			}
			if (Integer.parseInt(linea) == 2) {
				comenzarPelea(yo, contrincante);
			}
			if (Integer.parseInt(linea) == 3) {
				seleccionarPersonajeOLiga();
				
			}

		} catch (NumberFormatException e) {
			peleaTerminada(yo, contrincante);
		}
	}

	private String verListaPersonajes() {
		String msj = "Lista De Heroes:\n";
		for (Competidor competidor : listaDeHeroes) {

			msj += (competidor.getPseudo() + competidor.getTodasLasCaracteristicas() + "\n");

		}
		msj += "Lista De Villanos:\n";
		for (Competidor competidor : listaDeVillanos) {

			msj += (competidor.getPseudo() + competidor.getTodasLasCaracteristicas() + "\n");

		}
		return msj;
	}
	
	private String verListaLigas() {
		String msj = "Lista De Ligas De Heroes:\n";
		for (Liga liga : listaDeLigasDeHeroes) {

			msj += (liga.getPseudo() + liga.getTodasLasCaracteristicas() + "\n");

		}
		msj += "Lista De Ligas De Villanos:\n";
		for (Liga liga : listaDeLigasDeVillanos) {

			msj += (liga.getPseudo() + liga.getTodasLasCaracteristicas() + "\n");

		}
		return msj;
	}

	private void cargarPersonajesDesdeArchivo(String archivoLigas)
			throws TipoDeCompetidorInvalidoException, IOException, NoHayArchivoDeEntrada, YaEstoyEnLigaException,
			CaracteristicaNegativaException, CompetidorCaracteristicaInvalidaException {

		FileReader in = null;
		BufferedReader lector = null;

		try {
			in = new FileReader(archivoLigas);
			lector = new BufferedReader(in);
			String linea = lector.readLine();

			while (linea != null) {
				linea = linea.trim();
				if (!linea.isEmpty()) {
					
					Competidor competidor = agregarPersonaje(linea);
					if (competidor.esHeroe()) {
						listaDeHeroes.add(competidor);
					} else {
						listaDeVillanos.add(competidor);
					}
					linea = lector.readLine();

				}

			}
		} catch (IOException e) {
			throw new NoHayArchivoDeEntrada("No se leyo el archivo");
		} finally {
			if (lector != null) {
				lector.close();
			}
		}
	}

	
	private void cargarLigaDesdeArchivo(String archivoLigas)
			throws TipoDeCompetidorInvalidoException, IOException, NoHayArchivoDeEntrada, YaEstoyEnLigaException,
			CaracteristicaNegativaException, CompetidorCaracteristicaInvalidaException, FileNotFoundException {

		FileReader in = null;
		BufferedReader lector = null;

		try {
			
			in = new FileReader(archivoLigas);
			lector = new BufferedReader(in);
			String linea = lector.readLine();

			while (linea != null) {
				linea = linea.trim();
				if (!linea.isEmpty()) {

					Liga liga = agregarLiga(linea);

					if (liga.esHeroe()) {
						listaDeLigasDeHeroes.add(liga);
					} else {
						listaDeLigasDeVillanos.add(liga);
					}
					mapaDeLigas.put(liga.getPseudo(), liga);

					linea = lector.readLine();

				}

			}
		} catch (IOException e) {
			throw new NoHayArchivoDeEntrada("No se leyo el archivo");
		} finally {
			lector.close();
		}
	}

	private Liga agregarLiga(String linea)
			throws YaEstoyEnLigaException, TipoDeCompetidorInvalidoException, CaracteristicaNegativaException {

		Liga liga = null;
		String[] campos = linea.split(",");
		
		Competidor personaje= mapaDePersonajes.get(campos[1].trim());
		try {
			if (personaje.esHeroe()) {
				liga = new Liga(true, campos[0].trim());
			} else {
				liga = new Liga(false, campos[0].trim());
			}

			for (int i = 1; i < campos.length; i++) {
				personaje = mapaDePersonajes.get(campos[i].trim());
				personaje.setEstoyEnLiga(false);
				liga.addCompetidor(mapaDePersonajes.get(campos[i].trim()));
			}
		} catch (YaEstoyEnLigaException e) {

		}
		return liga;
	}

	private Competidor agregarPersonaje(String linea)
			throws CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {

		Competidor competidor = null;
		String[] campos = linea.split(",");

		try {
			if(!mapaDePersonajes.containsKey(campos[2])){
			if (campos[0].trim().equals("Heroe")) {

				competidor = new Personaje(true, campos[1].trim(), campos[2].trim(), Integer.parseInt(campos[3].trim()),
						Integer.parseInt(campos[4].trim()), Integer.parseInt(campos[5].trim()),
						Integer.parseInt(campos[6].trim()));
			} else if (campos[0].trim().equals("Villano")) {

				competidor = new Personaje(false, campos[1].trim(), campos[2].trim(),
						Integer.parseInt(campos[3].trim()), Integer.parseInt(campos[4].trim()),
						Integer.parseInt(campos[5].trim()), Integer.parseInt(campos[6].trim()));
			}
			mapaDePersonajes.put(competidor.getPseudo(), competidor);
			}
		} catch (NumberFormatException nfe) {
			throw new CompetidorCaracteristicaInvalidaException("Las caracteristicas de batalla deben ser enteros");
		}
		return competidor;
	}

	public void guardarPersonajesEnArchivo() {
		FileWriter fw = null;
		PrintWriter p = null;
		try {
			fw = new FileWriter("src/personajes.in");
			p = new PrintWriter(fw);

			Iterator<Competidor> itrHeroe = listaDeHeroes.listIterator();
			while (itrHeroe.hasNext()) {
				p.println(itrHeroe.next().toString());
			}

			Iterator<Competidor> itrVillano = listaDeVillanos.listIterator();
			while (itrVillano.hasNext()) {
				p.println(itrVillano.next().toString());
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (p != null) {
				p.close();
			}
		}
	}

	public void guardarLigasEnArchivo() {
		FileWriter fw = null;
		PrintWriter p = null;
		try {
			fw = new FileWriter("src/ligas.in");
			p = new PrintWriter(fw);

			Iterator<Liga> itrLigaHeroe = listaDeLigasDeHeroes.listIterator();
			while (itrLigaHeroe.hasNext()) {
				p.println(itrLigaHeroe.next().toString());
			}

			Iterator<Liga> itrLigaVillano = listaDeLigasDeVillanos.listIterator();
			while (itrLigaVillano.hasNext()) {
				p.println(itrLigaVillano.next().toString());
			}

		} catch (IOException e) {

			e.printStackTrace();
		} finally {
			if (p != null) {
				p.close();
			}
		}
	}

	public static void main(String[] args) throws IOException, TipoDeCompetidorInvalidoException, NoHayArchivoDeEntrada,
			YaEstoyEnLigaException, CompetidorCaracteristicaInvalidaException, CaracteristicaNegativaException {
		MenuDeCombates menu = new MenuDeCombates();
		menu.iniciarMenu();

	}
}