
package backend;

/**
 * Exception qui surgit lors du prelevement de montant
 * @author jo17-dev
 */
public class PreleverMontantException extends Exception {
    public PreleverMontantException(String message){
        super(message);
    }
    
    public PreleverMontantException(){
        super("Impossible de prelever le montant souhaité ");
    }
}
