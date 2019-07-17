

/**
 * Created by iuli on 13.03.2017.
 */
public class Book extends AbstractMediaItem {
    private String author;
    public Book(String name, String author, String path, int year, int rating){
        super(name, path, year, rating);
        this.author = author;
    }

    @Override
    public String toString(){
        return (name + "\t" + author + "\t" + year + "\t" + rating+"/5");
    }
}
