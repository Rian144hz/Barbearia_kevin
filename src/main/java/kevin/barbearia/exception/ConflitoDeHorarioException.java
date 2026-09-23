package kevin.barbearia.exception;

public class ConflitoDeHorarioException extends RuntimeException {
    public ConflitoDeHorarioException(String mensagem) {
        super(mensagem);
    }
}