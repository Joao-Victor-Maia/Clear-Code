public class RegraNegocioException extends RuntimeException {
    
    // Esta exceção será lançada (throw) sempre que uma regra de negócio for violada

    // na camada de Service. Ela extende RuntimeException para que não precise ser

    // declarada obrigatoriamente em todos os métodos, mas possa parar a execução na hora.
    public RegraNegocioException(String mensagem) {
        super(mensagem);
    }
}
