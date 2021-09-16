import exceptions.*;

public class Ebook {
    private Integer isbn;
    private String name;
    private String publisher;
    private String author;
    private String hash;
    private Integer size;

    public Ebook(Integer isbn, String name, String publisher, String author, String hash, Integer size) throws InvalidBookNameException, InvalidBookFormatException, InvalidBookSizeException, InvalidBookHashException, InvalidBookPublisherException, InvalidBookAuthorException, InvalidBookIsbnException {
        if(isbn == null || isbn <= 0 || isbn > 2147483646)
            throw new InvalidBookIsbnException();
        else
            this.isbn = isbn;

        if(name == null || name.equals("")){
            throw new InvalidBookNameException();
        }

        if(getClass().equals(Pdf.class) && name.contains(".epub"))
            throw new InvalidBookFormatException();

        if(getClass().equals(Epub.class) && name.contains(".pdf"))
            throw new InvalidBookFormatException();

        if((name.contains("pdf") || name.contains("epub")) && name.matches("[a-zA-Z0-9.]*"))
            this.name = name;
        else
            throw new InvalidBookFormatException();

        if(publisher == null || publisher.equals("")){
            throw new InvalidBookPublisherException();
        }
        else
            this.publisher = publisher;

        if(author == null || author.equals("")){
            throw new InvalidBookAuthorException();
        }
        else
            this.author = author;

        if((hash == null) || hash.equals("") || hash.matches("[a-zA-Z0-9]*")){
            throw new InvalidBookHashException();
        }
        else
            this.hash = hash;

        if(size == null || size <= 0)
            throw new InvalidBookSizeException();
        else{
            if(size <= 500)
                this.size = size;
            else
                throw new InvalidBookSizeException();
        }
    }

    public Integer getIsbn() {
        return isbn;
    }

    public void setIsbn(Integer isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }
}
