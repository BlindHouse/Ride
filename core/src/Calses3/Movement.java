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

import java.util.Random;
import static Calses3.Graph.map;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author DiSoJi
 */
public class Movement {
    public Random RandomChoose = new Random();
    
    public void dowork(){
        
        Iterator<Gnodo> Iter = map.map.iterator();
        
        while (Iter.hasNext()){
            System.out.println("Choosing a node to move enemies");
            Gnodo A = Iter.next();
            if (A.wasVisited()){
                System.out.println("Yeah, you fucked it, congrats you moron");
            }else{
                System.out.println("We keep it");
                Gnodo Ubicación = A;
                ArrayList<Gnodo> copy2 = new ArrayList(Ubicación.siguientes);
                Iterator<Gnodo> iter = copy2.iterator();
                while (iter.hasNext()){
                    Gnodo GoTo = iter.next();
                    if (GoTo.wasVisited()){
                        System.out.println("Fuck You, there was someone in here"); 
                    }else{
                        if (Ubicación.foes <=3){
                            System.out.print("This Scenario is empty");
                        }else{
                            System.out.println(Ubicación.nombre);
                            System.out.println(GoTo.nombre);
                            System.out.println("Normales en GoTo antes del movimiento: " + GoTo.foes);
                            System.out.println("Normales en Ubicación antes del movimiento: " + Ubicación.foes);
                            GoTo.increasefoes();
                            Ubicación.decreasefoes();
                            System.out.println("Normales en GoTo despues del movimiento: " + GoTo.foes);
                            System.out.println("Normales en Ubicación despues del movimiento: " + Ubicación.foes);
                        }
                    }
                }
            }
        }
    }
}
    

