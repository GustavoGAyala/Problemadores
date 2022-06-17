package combates;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Iterator;

public class OrdenadorDeCaracteristicas implements Comparator<Competidor> {
		private List<Caracteristica> caracteristicas = new ArrayList<Caracteristica>();

		public OrdenadorDeCaracteristicas(List<Caracteristica> caracteristicas) {
			this.caracteristicas = caracteristicas;
		}

		@Override
		public int compare(Competidor o1, Competidor o2) {
			int resultado = 0;
			Caracteristica caracteristica = null;
			Iterator<Caracteristica> itr = caracteristicas.iterator();
			while (itr.hasNext() && resultado == 0) {
				caracteristica = itr.next();
				resultado = Integer.compare(o1.obtenerCaracteristica(caracteristica), o2.obtenerCaracteristica(caracteristica));
			}
			return resultado;
		
	}
}
