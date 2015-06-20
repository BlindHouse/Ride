/*
 * Copyright (C) 2015 Usuario
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Calses3;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author Gaby
 */

public class Dijkstra{
    public static void compararCaminos(Gnodo vertice){
        vertice.maximo = 0.;
        PriorityQueue<Gnodo> colaPrioridad = new PriorityQueue<Gnodo>();
      	colaPrioridad.add(vertice);
        
	while (!colaPrioridad.isEmpty()) {
	    Gnodo Vertice = colaPrioridad.poll();
            for (Aristas arista : Vertice.adyacencia){
                Gnodo nodo = arista.objeto;
                double peso = arista.peso;
                double DistanciaTotal = Vertice.maximo + peso;
		if (DistanciaTotal < nodo.maximo){
		    colaPrioridad.remove(nodo);
		    nodo.maximo = DistanciaTotal;
		    nodo.anterior = Vertice;
		    colaPrioridad.add(nodo);
		}
            }
        }
    }

    public static List<Gnodo> Ruta(Gnodo target){
        List<Gnodo> camino = new ArrayList<Gnodo>();
        for (Gnodo vertice = target; vertice != null; vertice = vertice.anterior){
            camino.add(vertice);
        }
        Collections.reverse(camino);
        return camino;
    }
}