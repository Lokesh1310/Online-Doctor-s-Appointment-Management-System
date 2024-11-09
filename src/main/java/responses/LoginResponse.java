package responses;

public class LoginResponse {
	 private int userId;
	    private String message;

	    public LoginResponse(int userId, String message) {
	        this.userId = userId;
	        this.message = message;
	    }

	    public int getUserId() {
	        return userId;
	    }

	    public String getMessage() {
	        return message;
	    }
}
