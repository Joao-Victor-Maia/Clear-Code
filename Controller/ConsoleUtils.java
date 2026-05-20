import java.util.Scanner;

public class ConsoleUtils {

    // Guardamos o Scanner aqui dentro pra que todos os Controllers
    // usem o mesmo Scanner. Se cada um criasse o seu, daria conflito.
    private final Scanner scanner;

    // Recebemos o Scanner pelo construtor (Injeção de Dependência).
    // Quem cria o Scanner é o Main, e ele passa pra cá.
    public ConsoleUtils(Scanner scanner) {
        this.scanner = scanner;
    }

    // Esse método lê um número inteiro digitado pelo usuário.
    // O segredo é: em vez de usar scanner.nextInt() (que causa bug),
    // eu leio TUDO como texto (nextLine) e depois converto pra número.
    // Se der erro na conversão, o catch segura e pede de novo.

    public int lerInt() {
        while (true) {
            try {
                // Lê como texto, tira os espaços das pontas e converte pra int
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                // Caiu aqui? Significa que o usuário digitou algo que não é número.
                // O while(true) vai rodar de novo e pedir outra vez — sem fechar o programa!
                System.out.print("⚠️  Digite um número inteiro válido: ");
            }
        }
    }

    // Mesma lógica do lerInt, mas pra números Long (que são maiores).
    // Usamos Long pros IDs das entidades, por isso precisamos desse método.

    public Long lerLong() {
        while (true) {
            try {
                return Long.parseLong(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("⚠️  Digite um número válido: ");
            }
        }
    }

    // Esse aqui lê valores com casas decimais, tipo preço de peça (49.90).
    // Tem um detalhe legal: o ".replace(",", ".")" troca a vírgula por ponto,
    // porque no Brasil escreve "49,90" mas o Java só entende "49.90".

    public double lerDouble() {
        while (true) {
            try {
                return Double.parseDouble(scanner.nextLine().trim().replace(",", "."));
            } catch (NumberFormatException e) {
                System.out.print("⚠️  Digite um valor numérico válido (ex: 49.90): ");
            }
        }
    }

    // Esse é o mais simples: só lê um texto direto, sem conversão nenhuma.
    // Usado pra nome, CPF, telefone, placa do carro, etc.
    
    public String lerString() {
        return scanner.nextLine();
    }
}
