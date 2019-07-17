
import java.awt.Desktop;
import java.io.*;
import java.util.ArrayList;


public class Catalog {
    ArrayList<AbstractMediaItem> list;
    public ArrayList<AbstractMediaItem> getList() {
        return list;
    }

    public void setList(ArrayList<AbstractMediaItem> list) {
        this.list = list;
    }
    public Catalog(){
        list = new ArrayList<>();
    }

    public void add(AbstractMediaItem item){
        list.add(item);
    }

    public void list(){
        if(list.size() > 0)
            for (AbstractMediaItem abs: list) {
                System.out.println(abs.toString());
            }
    }

    public void play(){
        for (AbstractMediaItem abs: list) {
            try{
                Desktop dsk = Desktop.getDesktop();
                File file = new File(abs.path);
                if(file.exists())
                    dsk.open(file);
            }
            catch (IOException ioe){
                System.out.print(ioe);
            }
            catch (NullPointerException npe){
                System.out.print(npe);
            }
        }
    }

    public void save(String path){
        try {
            FileOutputStream fileOut = new FileOutputStream(path);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(list);
            out.close();
            fileOut.close();
            System.out.println("Serialized data is saved in " + path);
        }catch(IOException i) {
            i.printStackTrace();
        }
    }

    public void load(String path){
        ArrayList e;
        try {
            FileInputStream fileIn = new FileInputStream(path);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            e = (ArrayList) in.readObject();
            this.list = e;
            in.close();
            fileIn.close();
        }catch(IOException i) {
            i.printStackTrace();
            return;
        }catch(ClassNotFoundException c) {
            System.out.println("Class not found");
            c.printStackTrace();
            return;
        }
    }
}