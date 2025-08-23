import java.math.BigDecimal;

public class Cliente {
    private final String nome;
    private final int idade;
    private final BigDecimal rendaMensal;

    public Cliente(String nome, int idade, BigDecimal rendaMensal) {
        this.nome = nome;
        this.idade = idade;
        this.rendaMensal = rendaMensal;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public BigDecimal getRendaMensal() {
        return rendaMensal;
    }
}
