package petbook.exception;

import java.io.Serializable;
import java.util.List;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@ToString
@EqualsAndHashCode
@Getter
public class ExceptionResponse implements Serializable {

    private static final long serialVersionUID = 1L;

    private String code;
	private String message;
	private List<String> details;
    
    public ExceptionResponse(String errorCode, String message, List<String> details) {
        this.code = errorCode;
        this.message = message;
        this.details = details;
    }

}
