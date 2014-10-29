package uk.ac.ebi.pride.cluster.ws.error.exception;

/**
 * @author Jose A. Dianes <jdianes@ebi.ac.uk>
 *
 */
public class ResourceNotFoundException extends RuntimeException {

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
