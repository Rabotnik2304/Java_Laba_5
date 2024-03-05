package JavaLaba5.Service;

import JavaLaba5.Model.UserProfile;

import java.util.HashMap;
import java.util.Map;

public class AccountService {
    private static Map<String, UserProfile> loginToProfile = new HashMap<>(){{put("admin",new UserProfile("admin","123","AAAAAAAAAAAAAAA"));}};
    private static Map<String, UserProfile> sessionIdToProfile= new HashMap<>();;

    public static void addNewUser(UserProfile userProfile) {
        loginToProfile.put(userProfile.getLogin(), userProfile);
    }

    public static UserProfile getUserByLogin(String login) {
        return loginToProfile.get(login);
    }

    public static UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public static void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public static void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}
