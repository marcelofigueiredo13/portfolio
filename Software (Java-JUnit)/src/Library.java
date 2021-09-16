import exceptions.*;

import java.net.MalformedURLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Library {
    private ArrayList<ServerReplica> serverReplicas = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    private ArrayList<Loan> loans = new ArrayList<>();
    private ArrayList<Term> terms = new ArrayList<>();
    private HashMap<String, String> signedTerms = new HashMap<>();

    public Library() {

    }

    public ArrayList<ServerReplica> getServerReplicas() {
        return serverReplicas;
    }

    public void setServerReplicas(ArrayList<ServerReplica> serverReplicas) {
        this.serverReplicas = serverReplicas;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public ArrayList<Loan> getLoans() {
        return loans;
    }

    public void setLoans(ArrayList<Loan> loans) {
        this.loans = loans;
    }

    public ArrayList<Term> getTerms() {
        return terms;
    }

    public void setTerms(ArrayList<Term> terms) {
        this.terms = terms;
    }

    public void addServerReplica(ServerReplica serverReplica){
        serverReplicas.add(serverReplica);
    }

    public void addUser(User user){
        users.add(user);
    }

    public void addTerm(Term term){
        terms.add(term);
    }

    public String requestLoan(String username, String ebookName) throws EbookCopyNotFoundException, InvalidLoanDataException, UserAccountIsCanceledException, InvalidUserNameException {
        //System.out.println("Library: Request Loan");
        for(int i = 0; i < users.size(); ++i)
            if(users.get(i).getName().equals(username)){
                //User requesting the loan
                User u = users.get(i);

                //Verify if user account is active
                if(u.getActive()){
                    //Distances from user to server
                    HashMap<ServerReplica, Double> distances = new HashMap<>();

                    //Calculate and store the distance from each server to the user location
                    for(int j = 0; j < serverReplicas.size(); ++j){
                        distances.put(serverReplicas.get(j), distance(serverReplicas.get(j).getLatitude(), serverReplicas.get(j).getLongitude(), u.getLatitude(), u.getLongitude()));
                    }

                    //Servers ordered by distance to user
                    HashMap<ServerReplica, Double> sortedDistances = distances.entrySet().stream()
                            .sorted(Map.Entry.comparingByValue())
                            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));

                    for(ServerReplica m: sortedDistances.keySet()){
                        if(m.getEbookCopy(ebookName) != null){
                            loans.add(new Loan(u, m.getEbookCopy(ebookName)));
                            System.out.println("Found in server: " + m.getPlace());
                            return "New Loan Created";
                        }
                    }
                    throw new EbookCopyNotFoundException();
                }
                else
                    throw new UserAccountIsCanceledException();
            }
        throw new InvalidUserNameException();
    }

    public String signTerm(String username, String publisher) throws UserAccountIsCanceledException, InvalidTermPublisherException, InvalidUserNameException {
        //System.out.println("Library: Sign Term");
        for(int i = 0; i < users.size(); ++i)
            if(users.get(i).getName().equals(username)){
                //User signing the term
                User u = users.get(i);

                //Verify if user account is active
                if(u.getActive()) {
                    //Verify if there is a term for this publisher
                    for(int j = 0; j < terms.size(); ++j){
                        if(terms.get(j).getPublisher().equals(publisher)){
                            //If there are no terms signed
                            if(signedTerms.size() > 0){
                                //Verify if the publisher term already has been signed
                                if(signedTerms.containsKey(publisher)){
                                    //If YES, verify if it is already signed by this user
                                    if(signedTerms.get(publisher).contains(username))
                                        return "Term already signed";
                                }
                                //Else, sign new term
                                else{
                                    signedTerms.put(publisher, username);
                                    return "Term signed";
                                }
                            }
                            else{
                                signedTerms.put(publisher, username);
//                                System.out.println(signedTerms.keySet());
//                                System.out.println(signedTerms.get(publisher));
                                return "Term signed";
                            }
                        }
                    }
                    throw new InvalidTermPublisherException();
                }
                else
                    throw new UserAccountIsCanceledException();
            }
        throw new InvalidUserNameException();
    }

    public String visualizeEbook(String username, String ebookName) throws InvalidUserNameException, UserAccountIsCanceledException, NoLoanForThisBookException, LoanExpiredException {
        //System.out.println("Library: Visualize Book");
        for(int i = 0; i < users.size(); ++i)
            if(users.get(i).getName().equals(username)){
                //User signing the term
                User u = users.get(i);

                //Verify if user account is active
                if(u.getActive()) {
                    //Go through loans and find if there is one with this username for this book
                    for(int j = 0; j < loans.size(); ++j){
                        if(loans.get(j).getUser().equals(u) && loans.get(j).getEbookCopy().getEbookCopy().getName().equals(ebookName)){
                            //If loan exists verify if loan limit date is after actual date
                            if(loans.get(j).getLimitDate().isAfter(LocalDate.now()) || loans.get(j).getLimitDate().isEqual(LocalDate.now())){
                                WebComponent webComponent;
                                webComponent = new StubWebComponent();
                                return webComponent.visualizeBook(ebookName);
                            }
                            //Else, loan date has expired
                            else
                                throw new LoanExpiredException();
                        }
                    }
                    throw new NoLoanForThisBookException();
                }
                else
                    throw new UserAccountIsCanceledException();
            }
        throw new InvalidUserNameException();
    }

    public String extendLoan(String username, String ebookName) throws NoLoanForThisBookException, UserAccountIsCanceledException, InvalidUserNameException, LoanExtensionLimitReachedException {
        //System.out.println("Library: Extend Loan");
        for(int i = 0; i < users.size(); ++i)
            if(users.get(i).getName().equals(username)){
                //User signing the term
                User u = users.get(i);

                //Verify if user account is active
                if(u.getActive()) {
                    //Go through loans and find if there is one with this username for this book
                    for(int j = 0; j < loans.size(); ++j){
                        if(loans.get(j).getUser().equals(u) && loans.get(j).getEbookCopy().getEbookCopy().getName().equals(ebookName)){
                            //If loan exists verify if loan has been extended less than 2 times
                            if(loans.get(j).getExtended() < 2){
                                loans.get(j).setExtended(loans.get(j).getExtended() + 1);
                                loans.get(j).setLimitDate(loans.get(j).getLimitDate().plusDays(7));
                                return "Loan limit date extended to " + loans.get(j).getLimitDate();
                            }
                            else
                                throw new LoanExtensionLimitReachedException();
                        }
                    }
                    throw new NoLoanForThisBookException();
                }
                else
                    throw new UserAccountIsCanceledException();
            }
        throw new InvalidUserNameException();
    }

    public String downloadEbook(String username, String ebookName) throws NoLoanForThisBookException, UserAccountIsCanceledException, InvalidUserNameException {
        //System.out.println("Library: Download Ebook");
        for(int i = 0; i < users.size(); ++i)
            if(users.get(i).getName().equals(username)){
                //User signing the term
                User u = users.get(i);

                //Verify if user account is active
                if(u.getActive()) {
                    //Go through loans and find if there is one with this username for this book
                    for(int j = 0; j < loans.size(); ++j){
                        if(loans.get(j).getUser().equals(u) && loans.get(j).getEbookCopy().getEbookCopy().getName().equals(ebookName)){
                            //If loan exists verify if loan limit date is after actual date
                            //loans.get(j).setLimitDate(LocalDate.now().minusDays(5));
                            if(loans.get(j).getLimitDate().isAfter(LocalDate.now()) || loans.get(j).getLimitDate().isEqual(LocalDate.now())){
                                return "Ebook download successful";
                            }
                            //Else, its an illegal download - cancel user account
                            else{
                                u.setActive(false);
                                return "Illegal download detected";
                            }
                        }
                    }
                    throw new NoLoanForThisBookException();
                }
                else
                    throw new UserAccountIsCanceledException();
            }
        throw new InvalidUserNameException();
    }

    private double distance(double lat1, double lon1, double lat2, double lon2) {
        double theta = lon1 - lon2;
        double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
        dist = Math.acos(dist);
        dist = rad2deg(dist);
        dist = dist * 60 * 1.1515;
        dist = dist * 1.609344;
        return (dist);
    }

    private double deg2rad(double deg) {
        return (deg * Math.PI / 180.0);
    }

    private double rad2deg(double rad) {
        return (rad * 180.0 / Math.PI);
    }
}
