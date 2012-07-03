/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package algoritmosgeneticos.controller;

import algoritmosgeneticos.model.Row;
import java.util.ArrayList;
import java.util.List;

/**
 * @version 0.1
 * @author Manuel
 */
public class GeneticoController {
    /*cantidad de ejecuciones*/

    private Integer times;
    /*factor genetico*/
    private Float factor;
    /*numero de elementos*/
    private int number;
    /*poblacion dentro del individuo*/
    private int population;
    /*tabla de individuos*/
    private List<Row> table;
    /*
     * constructor
     */

    public GeneticoController() {
        init();
        ejecute();
    }

    private void init() {
        number = 6;
        table = new ArrayList<Row>();
        generateVal();
        times=8;
    }
    
    /*
     * Genera valores para llenar la primera tabla
     */
    private void generateVal() {
        for (int i = 0; i < number; i++) {
            Row aux = new Row(i, new ArrayList<Integer>());
            for (int j = 0; j < 5; j++) {
                aux.addElement(((int) (Math.random() * 10)) % 2);
            }
            table.add(aux);
        }
    }
    
    /*
     * Algoritmo Genetico
     */
    private void ejecute(){
        System.out.println(average());
        drawTable();
        while(times>0){
            pairRandom();
            selection();
            pairRandom();
            cross();
            System.out.println(average());
            drawTable();
            times--;
        }
    }
    
    
    /*
     *Calcula la media 
     */
    public float average() {
        float av = 0;
        Integer sum = 0;
        for (Row row : table) {
            sum = sum + row.getFunction();
        }
        if (table.size() > 0) {
            av = sum / table.size();
        }
        return av;
    }
    /*
     * Seleccion y eliminacion de parejas
     */

    public void selection() {
        List<Row> Paux = new ArrayList<Row>();
        for (Row row : table) {
            Paux.add(row);
        }
        int i = 0;
        while (Paux.size() > 0) {
            Row rauxA = Paux.get(i);
            Paux.remove(i);
            int j = rauxA.getPair();
            Row rauxB = table.get(j);
            Paux.remove(rauxB);
            int rr = 0;
            if (rauxA.getVal() > rauxB.getVal()) {
                for (Integer a : rauxA.getCode()) {
                    int aux = a;
                    rauxB.setElement(rr, aux);
                    rr++;
                }
            } else {
                for (Integer b : rauxB.getCode()) {
                    int aux = b;
                    rauxA.setElement(rr, aux);
                    rr++;
                }
            }
            if (Paux.size() < 2) {
                break;
            }
        }
    }
    /*
     * parejas aleatorias
     */

    public void pairRandom() {
        List<Row> Paux = new ArrayList<Row>();
        for (Row row : table) {
            Paux.add(row);
        }
        while (Paux.size() > 0) {
            if (Paux.size() >= 2) {
                int i = (int) (Math.random() * 100) % Paux.size();
                Row rauxA = Paux.get(i);
                Paux.remove(i);
                i = (int) (Math.random() * 100) % Paux.size();
                Row rauxB = Paux.get(i);
                Paux.remove(i);
                rauxA.setPair(rauxB.getId());
                rauxB.setPair(rauxA.getId());
            } else {
                int i = (int) (Math.random() * 100) % Paux.size();
                Row rauxA = Paux.get(i);
                Paux.remove(i);
                rauxA.setPair(rauxA.getId());
                break;
            }
        }
    }
    /*
     * cruce
     */

    public void cross() {
        List<Row> Paux = new ArrayList<Row>();
        for (Row row : table) {
            Paux.add(row);
        }
        int i = 0;
        while (Paux.size() > 0) {
            Row rauxA = Paux.get(i);
            Paux.remove(i);
            int j = rauxA.getPair();
            Row rauxB = table.get(j);
            Paux.remove(rauxB);
            int rr = (int) (Math.random() * 100) % (rauxA.getCode().size() - 1) + 1;
            for (int k = 0; k < rr; k++) {
                int aux=rauxA.getCode().get(k);
                rauxB.setElement(k, aux);
            }
            for (int k = rr; k < rauxA.getCode().size(); k++) {
                int aux=rauxB.getCode().get(k);
                rauxA.setElement(k, aux);
            }
            if (Paux.size() < 2) {
                break;
            }
        }
    }
    /*
     * dibuja la tabla en consola
     */
    public void drawTable(){
        System.out.println("------------------------------------------------");
        for (Row row : table) {
            
            System.out.println(row.toString());
        }
    }
}
