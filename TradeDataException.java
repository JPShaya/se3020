/**
 *
 * @author Jean-Paul Jacques <jjac403@cse.unsw.edu.au>
 */
public class TradeDataException extends Exception {

    /**
     * Creates a new instance of <code>TradeDataException</code> without detail message.
     */
    public TradeDataException() {
    }


    /**
     * Constructs an instance of <code>TradeDataException</code> with the specified detail message.
     * @param msg the detail message.
     */
    public TradeDataException(String msg) {
        super(msg);
    }
}
