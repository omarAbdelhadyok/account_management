package sa.account.management.constant;

public enum Exceptions {
    GENERAL_EXCEPTION("Something went wrong, please try again"),
    INVALID_MIN_AMOUNT("Invalid minimum amount"),
    INVALID_MAX_AMOUNT("Invalid maximum amount");

    private final String message;

    Exceptions(String message) {
        this.message = message;
    }

    public String get() {
        return message;
    }
}
