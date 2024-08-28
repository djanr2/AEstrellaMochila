package ia.iimas.unam.mx;

import ia.iimas.unam.mx.model.Item;

import java.io.*;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    private static PriorityQueue<Item> items;
    private static PriorityQueue<Item> ks;

    private static int i_element; //<--- not used
    private static int pack_capacity;
    private static String pack_19_file_path = "src/main/resources/ks_19_0";
    private static String pack_10000_file_path = "src/main/resources/ks_10000_0";

    Scanner myObj;


    public static void main(String[] args) {
        items = new PriorityQueue<>();
        ks = new PriorityQueue<>();
        Item i=null;
        //ordered by heuristic

        Scanner scanner = new Scanner(System.in);
        System.out.println("Introduce el numero del archivo a analizar:");
        System.out.println("1. ks_19_0");
        System.out.println("2. ks_10000_0");
        System.out.print("> ");

        String option = scanner.nextLine();

        if(option.equals("1")){
            getArrayFromFile(pack_19_file_path, items);
        }else if (option.equals("2")){
            getArrayFromFile(pack_10000_file_path, items);
        }else{
            System.out.println("Opcion no valida");
            System.exit(0);
        }

        while (!items.isEmpty()) {
            i = items.poll();
            if(pack_capacity>=i.getWeight()){
                pack_capacity-=i.getWeight();
                i.pick();
                ks.add(i);
            }
        }

        printSolution(ks);

    }

    public static PriorityQueue<Item> getArrayFromFile(String filePath, PriorityQueue<Item> it){
        boolean firstline=  true;
        String[] file_values;
        int i= 0;
        try{
            File file=new File(filePath);
            FileReader fr=new FileReader(file);
            BufferedReader br=new BufferedReader(fr);
            StringBuffer sb=new StringBuffer();
            String line;
            while((line=br.readLine())!=null)
            {
                i++;
                file_values = line.split(" ");
                if(firstline){
                    firstline = !firstline;
                    i_element = Integer.valueOf(file_values[0]);
                    pack_capacity = Integer.valueOf(file_values[1]);
                }else{
                    it.add(new Item(i,Integer.valueOf(file_values[0]), Integer.valueOf(file_values[1])));
                }
            }
            fr.close();
        } catch (FileNotFoundException e ) {
            e.printStackTrace();
        } catch(IOException e){
            e.printStackTrace();
        }
        items = it;
        return items;
    }

    public static void printSolution(PriorityQueue<Item> it){
        while (!it.isEmpty()) {
            System.out.println(it.poll());
        }
    }

}
