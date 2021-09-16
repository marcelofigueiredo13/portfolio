import exceptions.*;

public class Pdf extends Ebook{

    public Pdf(Integer isbn, String name, String publisher, String author, String hash, Integer size) throws InvalidBookNameException, InvalidBookFormatException, InvalidBookSizeException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookPublisherException, InvalidBookIsbnException {
        super(isbn, name, publisher, author, hash, size);
    }
}
