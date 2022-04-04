package oa.abstractor_static;

public class MonitorVehicleTracker {

  public static void main(String[] args) {
    Door door = new AlarmDoor();
    Door.get();
  }
}

abstract class Door {

  public static void get() {
    System.out.print("Door");
  }
}

class AlarmDoor extends Door {

  public static void get() {
    System.out.print("AlarmDoor");
  }
}
