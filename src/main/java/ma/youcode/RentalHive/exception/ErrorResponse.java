package ma.youcode.RentalHive.exception;

public record ErrorResponse(
        String timeStamp,
        Integer status,
        String error,
        String message,
        String path
) {
}
