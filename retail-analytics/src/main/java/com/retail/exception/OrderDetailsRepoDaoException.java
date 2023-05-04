package  com.retail.exception;
/**
 * Service layer exception handler
 * @author muthu
 *
 */
public class OrderDetailsRepoDaoException extends Exception{
	public OrderDetailsRepoDaoException() {
        super();
    }
    public OrderDetailsRepoDaoException(String message, Throwable cause) {
        super(message, cause);
    }
    public OrderDetailsRepoDaoException(String message) {
        super(message);
    }
    public OrderDetailsRepoDaoException(Throwable cause) {
        super(cause);
    }
}
