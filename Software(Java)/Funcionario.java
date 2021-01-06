public class Funcionario {
    private int numero;
    private String password;
    private String nome;
    private int telefone;
    private String morada;
    private String email;

    public Funcionario(String password, String nome, int telefone, String morada, String email) {
        this.password = password;
        this.nome = nome;
        this.telefone = telefone;
        this.morada = morada;
        this.email = email;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getMorada() {
        return morada;
    }

    public void setMorada(String morada) {
        this.morada = morada;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void MostrarFuncionario(){
        System.out.println("\nNúmero de Funcionário: " + numero + "\nPalavra-passe: " + password + "\nNome: " + nome + "\nTelefone: " + telefone + "\nEmail: " + email + "\nMorada: " + morada);
    }

    public void ProcessarEncomenda(int n_encomenda) {
        Encomenda enc = Repositorio.devolveEncomenda(n_encomenda);

        if (enc == null)
            System.out.println("\nEncomenda não existe!");
        else {
            if (enc.getLe().size() != 0) {
                enc.setEstado("processada");
                System.out.println("\nA encomenda foi processada!");
            }
            else
                System.out.println("\nA encomenda não tem livros a encomendar!");
        }
    }

    public void EnviarEncomenda(int n_encomenda) {
        Encomenda enc = Repositorio.devolveEncomenda(n_encomenda);
        //LocalDate d_prevista = enc.getData_encomenda().plusMonths(1);

        if (enc == null)
            System.out.println("\nEncomenda não existe!");
        else {
            if (enc.getLe().size() != 0) {
                enc.setEstado("enviada");
                //enc.setData_prevista(d_prevista);
                System.out.println("\nA encomenda foi enviada!");
            }
            else
                System.out.println("\nA encomenda não tem livros a encomendar!");
        }
    }
}
