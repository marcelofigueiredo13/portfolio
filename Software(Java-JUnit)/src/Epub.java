import exceptions.*;

public class Epub extends Ebook{

    public Epub(Integer isbn, String name, String publisher, String author, String hash, Integer size) throws InvalidBookNameException, InvalidBookFormatException, InvalidBookSizeException, InvalidBookHashException, InvalidBookAuthorException, InvalidBookPublisherException, InvalidBookIsbnException {
            super(isbn, name, publisher, author, hash, size);
    }
}
