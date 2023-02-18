package it.unicam.cs.ids.loyaltyplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ResourceAlreadyExistsException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nuova risorsa already exists exception con un messaggio che specifica i dettagli
     *
     * @param message Il messaggio che specifica i dettagli.
     *                Esso viene salvato per poi essere usato dal metodo {@link #getMessage()}.
     */
    public ResourceAlreadyExistsException(String message) {
        super(message);
    }

    /**
     * Crea una nuova risorsa already exists exception con un messaggio di default
     */
    public ResourceAlreadyExistsException() {
        super("Resource already exists");
    }
}