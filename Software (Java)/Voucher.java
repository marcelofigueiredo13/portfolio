import java.time.LocalDate;

public class Voucher {
    private String codigo;
    private double desconto;
    private LocalDate validade;

    public Voucher(String codigo, double desconto, LocalDate validade) {
        this.codigo = codigo;
        this.desconto = desconto;
        this.validade = validade;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public double getDesconto() {
        return desconto;
    }

    public void setDesconto(Float desconto) {
        this.desconto = desconto;
    }

    public LocalDate getValidade() {
        return validade;
    }

    public void setValidade(LocalDate validade) {
        this.validade = validade;
    }

    public void MostrarVoucher(){
        System.out.println("\nCódigo: " + codigo + "\nDesconto: " + desconto*100 + "%\nValidade: " + validade);
    }
}
