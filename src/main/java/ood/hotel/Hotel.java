package ood.hotel;

import java.util.*;

enum RoomType {
    SINGLE,
    DOUBLE
}

/**
 * 设计宾馆 OO Design
 * 中文English
 * Hotel目前有两种房间类型：SINGLE和DOUBLE
 *
 * 能够支持搜索，输入日期，返回能住的房间
 *
 * 能够支持预定
 *
 * 能够取消预定
 *
 * 使用LRUCache来储存搜索结果, 每次搜索时使用Cache
 *
 * Hotel, Room 需要大家实现, 在函数searchRequest和reservationRequest(makeReservation)之后我们会调用printCache来验证程序的正确性。
 *
 * 样例
 * 输入
 *
 * Hotel(4)
 * Room(1, "Single")
 * Room(2, "Single")
 * Room(3, "Double")
 * Room(4, "Double")
 * searchRequest("2013-01-01", "2013-10-10")
 * roomsNeeded("Single", 1)
 * roomsNeeded("Single", 1, "Double", 2)
 * roomsNeeded("Single", 1)
 * reservationRequest("2013-01-06", "2013-10-10", 2)
 * 输出
 *
 * Printing Cache ...
 * Search Request -> Start date is: Jan 1, 2013 12:00:00 AM, End date is: Oct 10, 2013 12:00:00 AM
 * Value -> For room type: DOUBLE, available rooms are: 3; 4; . For room type: SINGLE, available rooms are: 1; 2; .
 *
 * *****************************
 *
 * Printing Cache ...
 * Search Request -> Start date is: Jan 1, 2013 12:00:00 AM, End date is: Oct 10, 2013 12:00:00 AM
 * Value -> For room type: DOUBLE, available rooms are: 3; 4; . For room type: SINGLE, available rooms are: 1; 2; .
 *
 * Search Request -> Start date is: Jan 6, 2013 12:00:00 AM, End date is: Oct 10, 2013 12:00:00 AM
 * Value -> For room type: DOUBLE, available rooms are: . For room type: SINGLE, available rooms are: 1; 2; .
 *
 * ****************************
 *
 *
 */
public class Hotel {

    public static final int DAY = 1 * 24 * 60 * 60 * 1000;
    public LRUCache cache;
    private final List<Room> rooms;

    public Hotel(int cacheSize) {
        this.rooms = new ArrayList<>();

        this.cache = new LRUCache(cacheSize);
    }

    public Reservation makeReservation(ReservationRequest request) {
        Reservation r = new Reservation(request.getStartDate(), request.getEndDate());

        return r;
    }

    public Map<RoomType, List<Room>> handleSearchResult(SearchRequest request) {
        Map<RoomType, List<Room>> result = getAvailableRooms(request);

        return result;
    }

    public void cancelReservation(Reservation reservation) {
        for (Room room : reservation.getRooms()) {
            room.cancelReservation(reservation);
        }
    }

    public List<Room> getRooms() {
        return this.rooms;
    }

    private Map<RoomType, List<Room>> getAvailableRooms(SearchRequest request) {
        Map<RoomType, List<Room>> result = new HashMap<>();

        return result;
    }

    public String printCache() {
        return "Printing Cache ...\n" + cache.toString() +
                "*****************************\n";
    }

}

class Room {

    public static final int DAY = 1 * 24 * 60 * 60 * 1000;

    private final int id;
    private final RoomType roomType;
    private final Set<Date> reservations;

    public Room(int id, RoomType roomType) {
        this.id = id;

        this.roomType = roomType;

        this.reservations = new HashSet<>();
    }

    public boolean isValidRequest(SearchRequest request) {
        return true;
    }

    public void makeReservation(Date startDate, Date endDate) {
        Date date = new Date(startDate.getTime());

        for (; date.before(endDate); date.setTime(date.getTime() + DAY)) {
            Date tempDate = new Date(date.getTime());

            reservations.add(tempDate);
        }
    }

    public RoomType getRoomType() {
        return this.roomType;
    }

    public int getId() {
        return this.id;
    }

    public void cancelReservation(Reservation reservation) {
        Date date = new Date(reservation.getStartDate().getTime());
        for (; date.before(reservation.getEndDate()); date.setTime(date.getTime() + DAY)) {
            Date tempDate = new Date(date.getTime());
            reservations.remove(tempDate);
        }
    }

}

class LRUCache extends LinkedHashMap<SearchRequest, Map<RoomType, List<Room>>> {

    private static final long serialVersionUID = 1L;
    private final int capacity;

    public LRUCache(int capacity) {
        super(capacity);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<SearchRequest, Map<RoomType, List<Room>>> eldest) {
        // TODO Auto-generated method stub
        return size() > this.capacity;
    }

    private String printAvailableRooms(Map<RoomType, List<Room>> rooms) {
        String res = "";
        for (Map.Entry<RoomType, List<Room>> entry : rooms.entrySet()) {
            res += "For room type: " + entry.getKey() + ", available rooms are: ";
            for (Room room : entry.getValue()) {
                res += room.getId() + "; ";
            }
            res += ". ";
        }
        return res;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        String res = "";

        for (Map.Entry<SearchRequest, Map<RoomType, List<Room>>> entry : super.entrySet()) {
            res += ("Search Request -> " + entry.getKey().toString() + "\n");
            res += ("Value -> " + printAvailableRooms(entry.getValue()) + "\n");
            res += "\n";
        }

        return res;
    }

}

class ReservationRequest {

    private final Date startDate;
    private final Date endDate;
    private final Map<RoomType, Integer> roomsNeeded;

    public ReservationRequest(Date startDate, Date endDate, Map<RoomType, Integer> roomsNeeded) {
        // TODO Auto-generated constructor stub
        this.startDate = startDate;
        this.endDate = endDate;
        this.roomsNeeded = roomsNeeded;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public Map<RoomType, Integer> getRoomsNeeded() {
        return roomsNeeded;
    }

}

class Reservation {

    private final Date startDate;
    private final Date endDate;
    private final List<Room> rooms;

    public Reservation(Date startDate, Date endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
        rooms = new ArrayList<>();
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub


        String res = "Start date is: " + startDate.toLocaleString() + ", End date is: " + endDate.toLocaleString()
                + ", rooms are: ";

        for (Room room : rooms) {
            res += room.getId() + "; ";
        }
        res += ". ";

        return res;
    }

}

class SearchRequest {

    private final Date startDate;
    private final Date endDate;

    public SearchRequest(Date startDate, Date endDate) {
        // TODO Auto-generated constructor stub
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Date getStartDate() {
        return startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    @Override
    public String toString() {
        // TODO Auto-generated method stub

        String res = "Start date is: " + startDate.toLocaleString() + ", End date is: " + endDate.toLocaleString();

        return res;
    }

    @Override
    public boolean equals(Object obj) {
        // TODO Auto-generated method stub
        if (obj == this) return true;
        if (!(obj instanceof SearchRequest)) return false;

        SearchRequest request = (SearchRequest) obj;

        return request.startDate == this.startDate && request.endDate == this.endDate;
    }

    @Override
    public int hashCode() {
        // TODO Auto-generated method stub
        int result = 17;
        result = 31 * result + startDate.hashCode();
        result = 31 * result + endDate.hashCode();
        return result;
    }

}
