import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        var service = new CurrencyConverterService(
                System.getenv().getOrDefault("EXCHANGE_API_KEY", "4a7f9c2000f2bffd7ac8ecd3"),
                "https://v6.exchangerate-api.com/v6"
        );

        var scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== Conversor de Moedas ===");
            System.out.println("1 - USD → BRL");
            System.out.println("2 - BRL → USD");
            System.out.println("3 - EUR → BRL");
            System.out.println("4 - BRL → EUR");
            System.out.println("5 - USD → EUR");
            System.out.println("6 - EUR → USD");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            int opcao;
            try {
                opcao = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Entrada inválida. Digite um número válido!");
                scanner.nextLine(); // limpa buffer
                continue;
            }

            if (opcao == 0) {
                System.out.println("Encerrando programa!");
                break;
            }

            String from, to;
            switch (opcao) {
                case 1 -> { from = "USD"; to = "BRL"; }
                case 2 -> { from = "BRL"; to = "USD"; }
                case 3 -> { from = "EUR"; to = "BRL"; }
                case 4 -> { from = "BRL"; to = "EUR"; }
                case 5 -> { from = "USD"; to = "EUR"; }
                case 6 -> { from = "EUR"; to = "USD"; }
                default -> {
                    System.out.println("Opção inválida.");
                    continue;
                }
            }

            System.out.print("Digite o valor a converter: ");
            double valor;
            try {
                valor = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Valor inválido. Tente novamente.");
                scanner.nextLine(); // limpa buffer
                continue;
            }

            try {
                double convertido = service.convert(from, to, valor);
                System.out.printf("➡  %.2f %s = %.2f %s%n", valor, from, convertido, to);
            } catch (IllegalStateException ex) {
                System.out.println("Erro na conversão: " + ex.getMessage());
            } catch (Exception ex) {
                System.out.println("Erro inesperado: " + ex.getMessage());
            }
        }

        scanner.close();
    }
}
