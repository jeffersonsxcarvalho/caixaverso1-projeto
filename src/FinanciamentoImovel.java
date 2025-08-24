import java.math.BigDecimal;
import java.math.RoundingMode;


public class FinanciamentoImovel extends Financiamento {
    private BigDecimal entrada;

    private BigDecimal valorParcela;

    public FinanciamentoImovel(Cliente cliente, BigDecimal valorBem, int parcelas) {
        super(cliente, valorBem, parcelas);
    }

    public void avaliar() {
        if (cliente.getIdade() < 21) {
            aprovado = false;
            motivoReprovacao = "Idade mínima para financiamento de imóvel é 21 anos.";
            return;
        }

        entrada = valorBem.multiply(BigDecimal.valueOf(0.2));
        BigDecimal valorFinanciado = valorBem.subtract(entrada);
        double jurosAno = 0.08;
        double jurosMes = Math.pow((1 + jurosAno),(double)1/12) - 1;
        valorParcela = Resources.valorParcelas(valorFinanciado, jurosMes, parcelas);

        if (valorParcela.compareTo(cliente.getRendaMensal().multiply(BigDecimal.valueOf(0.30))) > 0) {
            aprovado = false;
            motivoReprovacao = "Parcela de R$" + Resources.formatMoeda(valorParcela) + " ultrapassa 30% da renda.";
        } else if (parcelas > 360) {
            motivoReprovacao = "Não é permitido mais de 360 parcelas no financiamento de imóveis.";
        } else {
            aprovado = true;
        }
    }

    protected void exibirDetalhes() {
        String entradaMin = Resources.formatMoeda(entrada.setScale(2, RoundingMode.HALF_UP));
        String valorFin = Resources.formatMoeda((valorBem.subtract(entrada)).setScale(2, RoundingMode.HALF_UP));
        String valorParc = Resources.formatMoeda(valorParcela);

        System.out.println("Entrada mínima: R$ " + entradaMin);
        System.out.println("Valor financiado: R$ " + valorFin);
        System.out.println("Número de parcelas: " + parcelas);
        System.out.println("Valor da parcela: R$ " + valorParc);
        System.out.println();
        getResumo();
    }
}
