import java.util.*;

class UsernameAvailabilityChecker {

    private HashMap<String, Integer> userMap;
    private HashMap<String, Integer> attemptMap;
    private int userIdCounter;

    public UsernameAvailabilityChecker() {
        userMap = new HashMap<>();
        attemptMap = new HashMap<>();
        userIdCounter = 1;
    }

    public void registerUser(String username) {
        userMap.put(username, userIdCounter++);
    }

    public boolean checkAvailability(String username) {

        attemptMap.put(username,
                attemptMap.getOrDefault(username, 0) + 1);

        return !userMap.containsKey(username);
    }

    public List<String> suggestAlternatives(String username) {

        List<String> suggestions = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            String suggestion = username + i;

            if (!userMap.containsKey(suggestion))
                suggestions.add(suggestion);
        }

        suggestions.add(username.replace("_", "."));

        return suggestions;
    }

    public String getMostAttempted() {

        String maxUser = "";
        int maxCount = 0;

        for (String key : attemptMap.keySet()) {

            int count = attemptMap.get(key);

            if (count > maxCount) {
                maxCount = count;
                maxUser = key;
            }
        }

        return maxUser + " (" + maxCount + " attempts)";
    }
}

class Main {

    public static void main(String[] args) {

        UsernameAvailabilityChecker checker =
                new UsernameAvailabilityChecker();

        checker.registerUser("john_doe");
        checker.registerUser("alex123");

        System.out.println(
                checker.checkAvailability("john_doe"));

        System.out.println(
                checker.checkAvailability("jane_smith"));

        System.out.println(
                checker.suggestAlternatives("john_doe"));

        System.out.println(
                checker.getMostAttempted());
    }
}