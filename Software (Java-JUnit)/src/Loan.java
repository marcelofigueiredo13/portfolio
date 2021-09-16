import exceptions.InvalidLoanDataException;

import java.time.LocalDate;

public class Loan {
    private User user;
    private EbookCopy ebookCopy;
    private LocalDate startDate;
    private LocalDate limitDate;
    private Integer extended;

    public Loan(User user, EbookCopy ebookCopy) throws InvalidLoanDataException {
        if(user == null || ebookCopy == null)
            throw new InvalidLoanDataException();
        else {
//            if(user.getLatitude() == null || user.getLongitude() == null || user.getName() == null || user.getPassword() == null || user.getAge() == null || user.getActive() == null)
//                throw new InvalidLoanUserNullAttributesException();
            this.user = user;
            this.ebookCopy = ebookCopy;
        }
        startDate = LocalDate.now();
        limitDate = LocalDate.now().plusDays(7);
        extended = 0;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public EbookCopy getEbookCopy() {
        return ebookCopy;
    }

    public void setEbookCopy(EbookCopy ebookCopy) {
        this.ebookCopy = ebookCopy;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getLimitDate() {
        return limitDate;
    }

    public void setLimitDate(LocalDate limitDate) {
        this.limitDate = limitDate;
    }

    public Integer getExtended() {
        return extended;
    }

    public void setExtended(Integer extended) {
        this.extended = extended;
    }
}
