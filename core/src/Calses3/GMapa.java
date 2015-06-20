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
public class GMapa {
    Gnodo inicio;
    
    ArrayList<Gnodo> map = new ArrayList();
    
    public  GMapa(){
    }
    
    public void addtomap(Gnodo nodo){

        this.map.add(nodo);
    }
    
    public void setInicio (Gnodo Inicio){

        this.inicio = Inicio;
    }
}