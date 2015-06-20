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
import java.util.List;
import static com.infiniteloop.ride.Ship.location;

/**
 *
 * @author DiSoJi
 */
public class Graph {
    public static GMapa map = new GMapa();
    public static ArrayList<Wrapper> Paths = new ArrayList();
    public static Dijkstra dis = new Dijkstra();
    static ArrayList<Gnodo> vertices = new ArrayList();


    public static void InitMapGraph() {

        Gnodo A = new Gnodo("A");
        Gnodo B = new Gnodo("B");
        Gnodo C = new Gnodo("C");
        Gnodo D = new Gnodo("D");
        Gnodo E = new Gnodo("E");
        Gnodo F = new Gnodo("F");
        Gnodo G = new Gnodo("G");
        Gnodo H = new Gnodo("H");
        Gnodo I = new Gnodo("I");



        vertices.add(A);
        vertices.add(B);
        vertices.add(C);
        vertices.add(D);
        vertices.add(E);
        vertices.add(F);
        vertices.add(G);
        vertices.add(H);

        map.map = vertices;
        for (int i = 0; i < map.map.size(); i++) {
            map.map.get(i).foes = 10;
        }
        I.adyacencia = new Aristas[]{new Aristas(A, 5), new Aristas(C, 5), new Aristas(E, 5)};
        A.adyacencia = new Aristas[]{new Aristas(B, 10), new Aristas(D, 10), new Aristas(G, 10)};
        B.adyacencia = new Aristas[]{new Aristas(C, 10), new Aristas(H, 10)};
        C.adyacencia = new Aristas[]{new Aristas(E, 10), new Aristas(G, 10)};
        D.adyacencia = new Aristas[]{new Aristas(B, 10), new Aristas(C, 10), new Aristas(G, 10)};
        E.adyacencia = new Aristas[]{new Aristas(D, 10), new Aristas(F, 10)};
        F.adyacencia = new Aristas[]{new Aristas(A, 10), new Aristas(D, 10), new Aristas(E, 10)};
        G.adyacencia = new Aristas[]{new Aristas(B, 10), new Aristas(F, 10), new Aristas(H, 10)};
        H.adyacencia = new Aristas[]{new Aristas(A, 10), new Aristas(E, 10), new Aristas(F, 10)};


        //Siguientes De I///////////////////////////////////////
        I.addtoSiguientes(A);
        I.addtoSiguientes(C);
        I.addtoSiguientes(E);
        //Siguientes De A//////////////////////////////////////
        A.addtoSiguientes(B);
        A.addtoSiguientes(D);
        A.addtoSiguientes(G);
        //Siguientes De B//////////////////////////////////////
        B.addtoSiguientes(C);
        B.addtoSiguientes(H);
        //Siguientes De C//////////////////////////////////////
        C.addtoSiguientes(E);
        C.addtoSiguientes(G);
        //Siguietnes De D/////////////////////////////////////
        D.addtoSiguientes(G);
        D.addtoSiguientes(C);
        D.addtoSiguientes(B);
        //Siguientes De E/////////////////////////////////////
        E.addtoSiguientes(D);
        E.addtoSiguientes(F);
        //Siguientes De F/////////////////////////////////////
        F.addtoSiguientes(E);
        F.addtoSiguientes(D);
        F.addtoSiguientes(A);
        //Siguientes De G/////////////////////////////////////
        G.addtoSiguientes(B);
        G.addtoSiguientes(F);
        G.addtoSiguientes(H);
        //Siguientes De H/////////////////////////////////////
        H.addtoSiguientes(A);
        H.addtoSiguientes(E);
        //////////////////////////////////////////////////////
    }


        //Se comparan los caminos/////////////////////////////
        //Se crea el arreglo que contendrá las posibles rutas/
        //Se encuentran las rutas/////////////////////////////

    public static Gnodo findPath(String Objetivo, Gnodo Actual){
        dis.compararCaminos(Actual);
        for (Gnodo Vertice : vertices) {
            System.out.println(Vertice + ": " + Vertice.maximo);
            List<Gnodo> camino = dis.Ruta(Vertice);
            System.out.println("Camino: " + camino);
            Wrapper Route = new Wrapper(Vertice.maximo, Vertice, camino);
            Paths.add(Route);
        }
        getShortestPath ShortestPath = new getShortestPath();
        return ShortestPath.ShortestPath(Objetivo, Paths);
    }

    public static void MoveFoes(){
        Movement moveitmoveit = new Movement();
        moveitmoveit.dowork();
        for (int i = 0; i < map.map.size(); i++) {
            System.out.println("Foes antes del moviemiento en; " + map.map.get(i).nombre + "es: " + map.map.get(i).foes);
            Gnodo ToGo = findPath(location, map.map.get(i));
            System.out.println("Foes antes del moviemiento en; " + ToGo.nombre + "es: " + ToGo.foes);
            map.map.get(i).decreasefoes();
            ToGo.increasefoes();
        }

    }

    public static void main(String [] args){
        InitMapGraph();
        MoveFoes();
    }
        //for (int i=0;i < Paths.size();i++){
        //   System.out.println("Path: " + Paths.get(i).Path + " Al Nodo: " + Paths.get(i).Nodo.nombre );
       // }
}
