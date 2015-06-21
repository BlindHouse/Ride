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

import java.util.ArrayList;

/**
 *
 * @author DiSoJi
 */

public class Gnodo implements Comparable<Gnodo>{
    
    public String nombre;
    public Aristas[] adyacencia;
    public double maximo = Double.POSITIVE_INFINITY;
    public Gnodo anterior;
    public int foes;
    public boolean visited = true;
    public boolean completed = false;

    public ArrayList<Gnodo> siguientes = new ArrayList();

    public Gnodo(String Nombre){
        this.nombre = Nombre;
    }

    @Override
    public String toString(){
        return this.nombre;
    }

    @Override
    public int compareTo(Gnodo adyacente){

        return Double.compare(this.maximo, adyacente.maximo);
    }
    public void foes(int Cantidad_Normales){

        this.foes = Cantidad_Normales;
    }
    public void increasefoes(){

            this.foes = this.foes + 1;
    }
    public void decreasefoes(){

            this.foes = this.foes - 1;
    }
    public boolean wasVisited(){

        return this.visited;
    }
    public void addtoSiguientes(Gnodo Nodo){

        this.siguientes.add(Nodo);
    }
    public String Accesible() {
        if (this.visited == true) {
            return "";

        } else {
            if (this.completed == true) {
                return "";
            }
            return "A";
        }
    }

    public boolean isHere(ArrayList<String> lista){
        for (int i = 0; i < lista.size();i++){
            if (lista.get(i).equals(this.nombre)){
                return true;
            }else{}
        }
        return false;
    }

}
    
