/*
 * Copyright (C) 2015 disoji
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

import java.util.List;

/**
 *
 * @author disoji
 */

public class Wrapper {
    
    public double Fuelcost;
    Gnodo Nodo;
    List<Gnodo> Path;
    
    public Wrapper(double FuelCost,Gnodo Nodo, List<Gnodo> Path){
        this.Fuelcost = FuelCost;
        this.Nodo = Nodo;
        this.Path = Path;
    }
    public double getFuelCost(){
        return this.Fuelcost;
    }
    public String getName(){
        return this.Nodo.nombre;
    }
}