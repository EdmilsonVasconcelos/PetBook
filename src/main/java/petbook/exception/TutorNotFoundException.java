package petbook.exception;

public class TutorNotFoundException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;
    
    public TutorNotFoundException(String message){
        super(message);
    }

}