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

import java.util.ArrayList;

/**
 *
 * @author disoji
 */
public class getShortestPath {
    
    public Gnodo ShortestPath(String ToGo, ArrayList<Wrapper> Paths){
        for (int i=0;i < Paths.size();i++){
            if (ToGo.equals(Paths.get(i).getName())){
                return Paths.get(i).Path.get(1);
            }
        }
        System.out.println("There is no Way to that node");
        return null;
    }
}
