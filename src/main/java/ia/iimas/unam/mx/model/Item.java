package ia.iimas.unam.mx.model;

import java.math.RoundingMode;
import java.text.DecimalFormat;

public class Item implements Comparable<Item>{
    private int weight;
    private int value;

    private double heuristic;

    private boolean picked;

    private int i;

    public Item(int i , int value, int weight) {
        this.i = i;
        this.weight = weight;
        this.value = value;
        DecimalFormat df = new DecimalFormat("0.000000");//<---- presicion
        this.heuristic = Double.valueOf(df.format((double)this.value/this.weight));
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public double getHeuristic() {
        return this.heuristic ;
    }

    public void pick(){
        this.picked= true;
    }


    @Override
    public int compareTo(Item o) {
        return o.heuristic > this.heuristic ? 1 : -1;
    }

    @Override
    public String toString(){
        return "{i: " +i+", value: "+value+", weight: "+weight+", heuristic (value/weight): "+heuristic+", picked: "+((picked)?1:0)+"}";
    }

}
