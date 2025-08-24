import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemaUsuario {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Financiamento> lista = new ArrayList<>();
        int opcao;
        String menu = """
                1 - Financiamento de Imóvel
                2 - Financiamento de Veículo
                3 - Listar Financiamentos de Imóveis
                4 - Listar Financiamentos de Veícurlos
                0 - Sair
                """;

        //System.out.println(Resources.formatMoeda(BigDecimal.valueOf(3528.58)));

        do {
            System.out.println();
            System.out.println(menu);
            System.out.print("Escolha uma opção: ");
            opcao = sc.nextInt();
            sc.nextLine(); // consumir quebra de linha

            System.out.println();


            if (opcao == 1 || opcao == 2) {

                if (opcao == 1) {
                    System.out.println("Financiamentos de Imóveis");
                }else{
                    System.out.println("Financiamentos de Automóveis");
                }
                System.out.println();

                System.out.print("Digite seu nome: ");
                String nome = sc.nextLine();
                System.out.print("Digite sua idade: ");
                int idade = sc.nextInt();
                System.out.print("Digite sua renda mensal: ");
                BigDecimal renda = sc.nextBigDecimal();
                System.out.print("Digite o valor do bem: ");
                BigDecimal valor = sc.nextBigDecimal();
                System.out.print("Digite o número de parcelas: ");
                int parcelas = sc.nextInt();
                System.out.println();

                Cliente cliente = new Cliente(nome, idade, renda);
                Financiamento f;

                if (opcao == 1) {
                    f = new FinanciamentoImovel(cliente, valor, parcelas);
                } else {
                    f = new FinanciamentoVeiculo(cliente, valor, parcelas);
                }

                f.avaliar();
                f.exibirResultado();
                lista.add(f);
            } else if (opcao == 3) {
                System.out.println("\n--- Lista de Financiamentos de Imóveis ---");
                for (Financiamento f : lista) {
                    if (f instanceof FinanciamentoImovel) {
                        f.getResumo();
                    }
                }
            } else if (opcao == 4) {
                System.out.println("\n--- Lista de Financiamentos de Veículos ---");
                for (Financiamento f : lista) {
                    if (f instanceof FinanciamentoVeiculo) {
                        f.getResumo();
                    }
                }
            }
        } while (opcao != 0);

        sc.close();
    }
}
