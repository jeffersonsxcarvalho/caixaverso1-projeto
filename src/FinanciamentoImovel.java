import java.math.BigDecimal;
import java.math.RoundingMode;


public class FinanciamentoImovel extends Financiamento {
    private BigDecimal entrada;
    private int parcelas;
    private BigDecimal valorParcela;

    public FinanciamentoImovel(Cliente cliente, BigDecimal valorBem) {
        super(cliente, valorBem);
    }

    public void avaliar() {
        if (cliente.getIdade() < 21) {
            aprovado = false;
            motivoReprovacao = "Idade mínima para financiamento de imóvel é 21 anos.";
            return;
        }

        entrada = valorBem.multiply(BigDecimal.valueOf(0.2));
        BigDecimal valorFinanciado = valorBem.subtract(entrada);
        parcelas = 360;
        double jurosAno = 0.08;
        double jurosMes = Math.pow((1 + jurosAno),(double)1/12) - 1;
        valorParcela = Resources.valorParcelas(valorFinanciado, jurosMes, parcelas);

        if (valorParcela.compareTo(cliente.getRendaMensal().multiply(BigDecimal.valueOf(0.30))) > 0) {
            aprovado = false;
            motivoReprovacao = "Parcela de R$" + valorParcela + " ultrapassa 30% da renda.";
        } else {
            aprovado = true;
        }
    }

    protected void exibirDetalhes() {
        System.out.println("Entrada mínima: R$ " + entrada.setScale(2, RoundingMode.HALF_UP));
        System.out.println("Valor financiado: R$ " + (valorBem.subtract(entrada)).setScale(2, RoundingMode.HALF_UP));
        System.out.println("Número de parcelas: " + parcelas);
        System.out.println("Valor da parcela: R$ " + valorParcela);
        System.out.println();
        getResumo();
    }
}
