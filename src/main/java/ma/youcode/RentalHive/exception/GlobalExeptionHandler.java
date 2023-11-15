package ma.youcode.RentalHive.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@ControllerAdvice
@Slf4j
public class GlobalExeptionHandler {

@ExceptionHandler(EquipmentNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleEquipmentNotFoundException(EquipmentNotFoundException ex, HttpServletRequest request){

    ErrorResponse errorResponse = new ErrorResponse(
            LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            HttpStatus.NOT_FOUND.value(),
            HttpStatus.NOT_FOUND.getReasonPhrase(),
            ex.getMessage(),
            request.getRequestURI()
    );

    return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);

}
}
