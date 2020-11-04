package petbook.exception;

public class NotAllowedException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public NotAllowedException() {
        super("Not Allowed for user");
    }

}
