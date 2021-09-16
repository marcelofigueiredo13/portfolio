public class Livros_Encomenda {
    private Livro livro;
    private int quantidade;

    public Livros_Encomenda(Livro livro, int quantidade) {
        this.livro = livro;
        this.quantidade = quantidade;
    }

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public void MostrarLivros_Encomenda(){
        livro.MostrarLivro();
        System.out.println("Quantidade: " + quantidade);
    }
}
