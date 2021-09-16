import exceptions.InvalidEbookException;

public class EbookCopy {
    private Ebook ebookCopy;

    public EbookCopy(Ebook ebook) throws InvalidEbookException {
        if(ebook == null)
            throw new InvalidEbookException();
        else
            ebookCopy = ebook;
    }

    public Ebook getEbookCopy() {
        return ebookCopy;
    }

    public void setEbookCopy(Ebook ebookCopy) {
        this.ebookCopy = ebookCopy;
    }
}
