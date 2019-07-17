
import java.io.File;


public class AbstractMediaItem implements java.io.Serializable {
    protected String name;
    protected String path;
    protected int year;
    protected int rating;

    protected boolean isValid(String path, int year, int rating){
        File file = new File(path);
        if(!file.exists())
            return false;
        if(year < 0 || year > 2017)
            return false;
        if(rating < 0 || rating > 5)
            return false;

        return true;
    }

    public AbstractMediaItem(String name, String path, int year, int rating){
        if (isValid(path, year, rating)) {
            this.name = name;
            this.path = path;
            this.year = year;
            this.rating = rating;
        }
    }

    @Override
    public String toString(){
        return (name + "\t" + year + "\t" + rating+"/5");
    }
}
