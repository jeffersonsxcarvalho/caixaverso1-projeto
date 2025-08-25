import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinanciamentoVeiculo extends Financiamento {
    private BigDecimal valorParcela;

    public FinanciamentoVeiculo(Cliente cliente, BigDecimal valorBem, int parcelas, BigDecimal entrada) {
        super(cliente, valorBem, parcelas, entrada);
    }

    public void avaliar() {
        if (cliente.getIdade() < 18) {
            aprovado = false;
            motivoReprovacao = "Idade mínima para financiamento de veículo é 18 anos.";
            return;
        }

        BigDecimal entradaMin = valorBem.multiply(BigDecimal.valueOf(0.10));
        if (entrada.compareTo(entradaMin) < 0){
            aprovado = false;
            motivoReprovacao = "A entrada deve ser maior que R$" + Resources.formatMoeda(entradaMin) + ".";
            return;
        }

        BigDecimal valorFinanciado = valorBem.subtract(entrada);
        double jurosMes = 0.015;
        valorParcela = Resources.valorParcelas(valorFinanciado, jurosMes, parcelas);

        if (valorParcela.compareTo(cliente.getRendaMensal().multiply(BigDecimal.valueOf(0.20))) > 0) {
            aprovado = false;
            motivoReprovacao = "Parcela de R$" + Resources.formatMoeda(valorParcela) + " ultrapassa 20% da renda.";
        } else if (parcelas > 60) {
            motivoReprovacao = "Não é permitido mais de 60 parcelas no financiamento de veículos.";
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
