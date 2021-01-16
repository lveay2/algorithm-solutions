package oa.oscar;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

/**
 * monthly card, 5 mins,
 *
 * "Harry" arrives at 12:01 --> accept
 * "Sam" arrives at 12:02 --> accept
 * "Rebecca" arrives at 12:05 --> accept
 * "Rebecca" arrives at 12:06 --> reject
 * "Harry" arrives at 12:06 –> accept
 */
public class MTA {

    private static Map<String, LocalDateTime> map = new HashMap<>();

    // cardId -> user
    // last swipe time -> time
    public boolean swipe(String cardId) throws Exception {
        LocalDateTime now = LocalDateTime.now();
        if (map.containsKey(cardId)) {
            LocalDateTime lastSwipTime = map.get(cardId);

            // compare time
            if (now.minusMinutes(5).compareTo(lastSwipTime) > 0) {
                map.put(cardId, now);
                return true;
            } else {
                return false;
            }
        }

        map.put(cardId, now);

        return true; // if accepted, false if rejected
    }

    public static void main(String[] args) throws Exception {
        MTA mta = new MTA();
        System.out.println(mta.swipe("Harry")); // true
        System.out.println(mta.swipe("Harry")); // false
        System.out.println(mta.swipe("Rebecca")); // true
        map.put("Harry", map.get("Harry").minusMinutes(10));
        System.out.println(mta.swipe("Harry")); // true
    }

}
