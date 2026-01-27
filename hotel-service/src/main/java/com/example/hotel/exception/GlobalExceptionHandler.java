@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> badRequest(Exception e) {
        return ResponseEntity
            .badRequest()
            .body(Map.of("error", e.getMessage()));
    }
}