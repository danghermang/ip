import org.junit.Test;

import java.io.File;
import java.util.ArrayList;

import static org.junit.Assert.*;

/**
 * Created by dangh on 31.03.2017.
 */
public class CatalogTest {
    @Test
    public void testadd() throws Exception {
        Catalog catalog = new Catalog();
        Book b1 = new Book("Algoritmi Genetici", "Henri Lucian", "D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\Gherman Dan-Gabriel cv.pdf", 2012, 4);
        Book b2 = new Book("Cartof", "Uncle John", "D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\Gherman Dan-Gabriel cv.pdf", 2017, 5);
        catalog.add(b1);
        catalog.add(b2);
        ArrayList<AbstractMediaItem> newlist;
        newlist = catalog.getList();
        if (b1.toString() != newlist.get(newlist.size() - 1).toString() && b2.toString() != newlist.get(newlist.size() - 2).toString())
            fail("Elementele nu au fost adaugate in array.");
    }


    @Test
    public void testsave() throws Exception {
        Catalog catalog = new Catalog();
        Book b1 = new Book("Algoritmi Genetici", "Henri Lucian", "D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\Gherman Dan-Gabriel cv.pdf", 2012, 4);
        Book b2 = new Book("Cartof", "Uncle John", "D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\Gherman Dan-Gabriel cv.pdf", 2017, 5);
        catalog.add(b1);
        catalog.add(b2);
        catalog.save("D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\data.ser");
        File f = new File("D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\data.ser");
        if (!f.exists() || f.isDirectory()) {
            fail("data file doesn't exist");
        }
        Catalog secondCatalog = new Catalog();
        secondCatalog.load("D:\\Facultate\\an 2 sem 2\\ip\\laborator\\lab 6\\src\\data.ser");
        if (catalog != secondCatalog)
            fail("arraylist not serialized correctly.(load error)");
    }

}