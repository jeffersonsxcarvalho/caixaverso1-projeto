import java.math.BigDecimal;
import java.math.RoundingMode;

public class FinanciamentoVeiculo extends Financiamento {
    private BigDecimal entrada;
    private int parcelas;
    private BigDecimal valorParcela;

    public FinanciamentoVeiculo(Cliente cliente, BigDecimal valorBem) {
        super(cliente, valorBem);
    }

    public void avaliar() {
        if (cliente.getIdade() < 18) {
            aprovado = false;
            motivoReprovacao = "Idade mínima para financiamento de veículo é 18 anos.";
            return;
        }


        entrada = valorBem.multiply(BigDecimal.valueOf(0.10));
        BigDecimal valorFinanciado = valorBem.subtract(entrada);
        parcelas = 60;
        double jurosMes = 0.015;
        valorParcela = Resources.valorParcelas(valorFinanciado, jurosMes, parcelas);

        if (valorParcela.compareTo(cliente.getRendaMensal().multiply(BigDecimal.valueOf(0.20))) > 0) {
            aprovado = false;
            motivoReprovacao = "Parcela de R$" + valorParcela + " ultrapassa 20% da renda.";
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
