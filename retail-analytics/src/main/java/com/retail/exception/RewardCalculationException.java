package  com.retail.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
/**
 * To handle Reward calculation Exception
 * @author muthu
 *
 */
public class RewardCalculationException extends Exception {
    /**
	 * 
	 */
	private static final long serialVersionUID = -1844208336834071265L;
	public RewardCalculationException() {
        super();
    }
    public RewardCalculationException(String message, Throwable cause) {
        super(message, cause);
    }
    public RewardCalculationException(String message) {
        super(message);
    }
    public RewardCalculationException(Throwable cause) {
        super(cause);
    }
}
