import java.math.BigDecimal;

public abstract class Financiamento {
    protected Cliente cliente;
    protected BigDecimal valorBem;
    protected boolean aprovado;
    protected String motivoReprovacao;

    //Adicionei
    protected int parcelas;

    public Financiamento(Cliente cliente, BigDecimal valorBem, int parcelas) {
        this.cliente = cliente;
        this.valorBem = valorBem;
        this.parcelas = parcelas;
    }

    public abstract void avaliar();

    public void exibirResultado() {
        if (aprovado) {
            System.out.println("Financiamento Aprovado!");
            exibirDetalhes();
        } else {
            System.out.println("Financiamento Reprovado!");
            System.out.println("Motivo: " + motivoReprovacao);
        }
    }

    protected abstract void exibirDetalhes();

    public void getResumo() {
        System.out.println(cliente.getNome() + " - Valor: R$" + Resources.formatMoeda(valorBem) +
                " - Status: " + (aprovado ? "Aprovado" : "Reprovado (" + motivoReprovacao + ")"));
    }
}
