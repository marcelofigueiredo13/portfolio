public class Livro {
    private int isbn;
    private String titulo;
    private String autor;
    private String editora;
    private int ano_publicacao;

    public Livro(int isbn, String titulo, String autor, String editora, int ano_publicacao) {
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.editora = editora;
        this.ano_publicacao = ano_publicacao;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditora() {
        return editora;
    }

    public void setEditora(String editora) {
        this.editora = editora;
    }

    public int getAno_publicacao() {
        return ano_publicacao;
    }

    public void setAno_publicacao(int ano_publicacao) {
        this.ano_publicacao = ano_publicacao;
    }

    public void MostrarLivro(){
        System.out.println("\nISBN: " + isbn + " \nTítulo: " + titulo + "\nAutor: " + autor + "\nEditora: " + editora + "\nAno de Publicação: " + ano_publicacao);
    }
}
