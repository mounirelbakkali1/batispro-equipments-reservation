package ma.youcode.RentalHive.exception;

public class EquipmentNotFoundException extends RuntimeException{
    public EquipmentNotFoundException(String message) {    // constructor
        super(message);
    }
}
