package it.unicam.cs.ids.loyaltyplatform.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.io.Serial;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Crea una nuova risorsa not found exception con un messaggio che specifica i dettagli
     *
     * @param message il messaggio che specifica i dettagli.
     *                Esso viene salvato per poi essere usato dal metodo {@link #getMessage()}.
     */
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
