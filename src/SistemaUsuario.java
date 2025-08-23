import java.math.BigDecimal;
import java.util.Scanner;

public class SistemaUsuario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcao;
        String menu = """
                1 - Financiamento de Imóvel
                2 - Financiamento de Veículo
                0 - Sair
                """;

        do {
            System.out.println();
            System.out.println(menu);
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            System.out.println();


            if (opcao == 1 || opcao == 2) {
                System.out.print("Digite seu nome: ");
                String nome = sc.nextLine();
                System.out.print("Digite sua idade: ");
                int idade = sc.nextInt();
                System.out.print("Digite sua renda mensal: ");
                BigDecimal renda = sc.nextBigDecimal();
                System.out.print("Digite o valor do bem: ");
                BigDecimal valor = sc.nextBigDecimal();
                System.out.println();

                Cliente cliente = new Cliente(nome, idade, renda);
                Financiamento f;

                if (opcao == 1) {
                    f = new FinanciamentoImovel(cliente, valor);
                } else {
                    f = new FinanciamentoVeiculo(cliente, valor);
                }

                f.avaliar();
                f.exibirResultado();
            }
        } while (opcao != 0);

        sc.close();
    }
}
