package mindGames.controller;

public class MoveResult {

    public boolean isValid = true;
    public String reason = "";

    public MoveResult(boolean isValid, String reason) {
        this.isValid = isValid;
        this.reason = reason;
    }

    public MoveResult() {
        isValid = true;
        reason = "";
    }
}
