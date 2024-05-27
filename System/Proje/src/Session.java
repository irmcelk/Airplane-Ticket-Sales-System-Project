public class Session {
    private static Login loggedInUser;
    //private static Menu reachMenu;

    public static Login getLoggedInUser() {
        return loggedInUser;
    }

    public static void setLoggedInUser(Login user) {
        loggedInUser = user;
    }
    
}