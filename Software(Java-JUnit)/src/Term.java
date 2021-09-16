import exceptions.InvalidTermDataException;

public class Term {
    private String publisher;
    private String term;

    public Term(String publisher, String term) throws InvalidTermDataException {
        if(publisher == null || publisher == "" || term == null || term == ""){
            throw new InvalidTermDataException();
        }
        else{
            this.publisher = publisher;
            this.term = term;
        }
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getTerm() {
        return term;
    }

    public void setTerm(String term) {
        this.term = term;
    }
}
