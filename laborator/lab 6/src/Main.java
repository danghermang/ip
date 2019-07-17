import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.*;


public class Main {
    public static void catalog(){
        Catalog catalog = new Catalog();
        catalog.add(new Book("Algoritmi Genetici", "Henri Lucian", "D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\Gherman Dan-Gabriel cv.pdf", 2012, 4));
        catalog.add(new Book("Cartof", "Uncle John", "D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\Gherman Dan-Gabriel cv.pdf", 2017, 5));
        catalog.save("D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\data.ser");
        catalog.load("D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\data.ser");
        catalog.list();
    }

    public static void main(String args[]){
        catalog();
    }

}
