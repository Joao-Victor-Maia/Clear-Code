// Essa classe eu criei pra ter um tipo de erro "nosso", específico do sistema.
// Quando algo dá errado nas regras da oficina (CPF duplicado, nome vazio, etc.),
// o Service lança essa exceção com uma mensagem explicando o problema.
//
// Ela herda de RuntimeException (e não de Exception) porque assim eu NÃO preciso
// ficar escrevendo "throws RegraNegocioException" em todos os métodos.
// O Java deixa ela "subir" sozinha até o Controller, onde o try-catch captura.
public class RegraNegocioException extends RuntimeException {
    
    // O construtor recebe a mensagem de erro (ex: "CPF já cadastrado!")
    // e repassa pra classe mãe RuntimeException com o super().
    // Depois, no Controller, eu acesso essa mensagem com o e.getMessage().
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
