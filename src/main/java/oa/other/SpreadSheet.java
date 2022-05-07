package oa.other;

import java.security.Key;
import java.util.*;

/*
Design and implement an object oriented Excel Spreadsheet. You are building a
library that a frontend team may use to surface a spreadsheet to some users.
Don't worry about storing the data durably in a database. We want this to be a MVP
library that can be expanded in the future if necessary. All data can be stored
in memory.

Key functionality:
o  Must be able to view the spreadsheet.
This will be used by the frontend to
render the spreadsheet.

o Cells can have the following potential
values:
- Integer values (-9, 2, 3, 10000, etc)
- Formula values (=2+8, =9-1, =100+2)
(you can pick a single operator)

B2 = 10
B3 = 5
- Reference values (F2=B2+B3)
B2 = 5

o Things to Consider:
- Most spreadsheets will be sparse
- Many cells could reference the same cell and when the referenced cell gets
updated, we want to propagate this information to the client easily.
*/
public class SpreadSheet {

  public static void main(String[] args) {
    System.out.println("Hello Java");

    SpreadSheet spreadSheet = new SpreadSheet();
    System.out.println(spreadSheet.updateCell("A1", "2"));
    spreadSheet.toString();
    // F2=B2+B3
    System.out.println(spreadSheet.updateCell("B2", "5"));
    spreadSheet.toString();
    System.out.println(spreadSheet.updateCell("B3", "10"));
    spreadSheet.toString();
    System.out.println(spreadSheet.updateCell("F2", "=B2+B3"));
    spreadSheet.toString();
    System.out.println(spreadSheet.updateCell("F3", "=F2"));
    spreadSheet.toString();
    System.out.println(spreadSheet.updateCell("F3", "=100"));
    spreadSheet.toString();
    System.out.println(spreadSheet.updateCell("B2", "=6"));
    spreadSheet.toString();
    System.out.println(spreadSheet.updateCell("B3", "=11"));
    spreadSheet.toString();
    System.out.println(spreadSheet.updateCell("B3", "=0"));
    spreadSheet.toString();

    System.out.println();
    SpreadSheet spreadSheet2 = new SpreadSheet();
    spreadSheet2.toString();
    System.out.println(spreadSheet2.updateCell("A1", "=A2"));
    spreadSheet2.toString();
    System.out.println(spreadSheet2.updateCell("A2", "=A1"));
    spreadSheet2.toString();
    System.out.println(spreadSheet2.updateCell("A2", "1"));
    spreadSheet2.toString();
    System.out.println(spreadSheet2.updateCell("A1", "3"));
    spreadSheet2.toString();
  }

  Map<String, Cell> sheet;
  Map<String, Set<String>> trigger;
  Map<String, Set<String>> beTriggered;

  public SpreadSheet() {
    this.sheet = new HashMap<>();
    this.trigger = new HashMap<>();
    this.beTriggered = new HashMap<>();
  }

  public int updateCell(String key, String value) {
    System.out.println("key:" + key + " targetValue: " + value);
    Cell cell = sheet.getOrDefault(key, new Cell());
    cell.key = key;
    sheet.put(key, cell);
    // System.out.println(key + " " + value +  " " +sheet);

    int newValue = 0;
    if (value.startsWith("=")) {
      // System.out.println(key + " " + value);
      cell.formular = value;

      newValue = calculateFormular(cell);
    } else {
      newValue = Integer.parseInt(value);
    }
    System.out.println("key:" + key + " newValue: " + newValue);
    cell.value = newValue;

    updateTrigger(cell);

    return newValue;
  }

  private void updateTrigger(Cell cell) {
    if (!trigger.containsKey(cell.key)) {
      return;
    }
    Queue<String> queue = new LinkedList<>(trigger.get(cell.key));
    Set<String> dup = new HashSet<>(trigger.get(cell.key));

    while (!queue.isEmpty()) {
      int size = queue.size();

      for (int i = 0; i < size; i++) {
        String key = queue.poll();
        Cell current = sheet.get(key);
        current.value = calculateFormular(current);

        if (!trigger.containsKey(key) || dup.contains(key)) {
          continue;
        }

        queue.addAll(trigger.get(key));
        dup.add(key);
      }
    }
  }

  // only 2+8 or 2 + E1
  // - Formula values (=2+8, =9-1, =100+2)
  private int calculateFormular(Cell cell) {
    String formular = cell.formular.trim().substring(1);
    // System.out.println("formular " + formular + " index: "+ formular.indexOf("+"));
    if (formular.contains("+")) {
      // System.out.println("+ " + formular.indexOf("+"));
      String[] factors = formular.split("\\+");
      // System.out.println("factors " +factors);
      // System.out.println("calculateFormular " +cell);

      int factor1 = getValue(factors[0], cell.key);
      int factor2 = getValue(factors[1], cell.key);
      // System.out.println("factor1 " +factor1 + "factor2 " +factor2 );

      return factor1 + factor2;
    }

    // 2 B2
    return getValue(formular, cell.key);
  }

  private int getValue(String factor, String key) {
    try {
      int newValue = Integer.parseInt(factor);
      //      System.out.println("link newValue " + newValue + " and break key if needed");
      breakTrigger(key);
      return newValue;
    } catch (Exception e) {
      //      System.out.println("link factorKey " + factor + " and " + key + " " + e.getMessage());
      String factorKey = factor;
      if (!trigger.containsKey(factorKey)) {
        trigger.put(factorKey, new HashSet<>());
      }
      trigger.get(factorKey).add(key);
      if (!beTriggered.containsKey(key)) {
        beTriggered.put(key, new HashSet<>());
      }
      beTriggered.get(key).add(factorKey);
      return sheet.getOrDefault(factorKey, new Cell()).value;
    }
  }

  private void breakTrigger(String key) {
    if (beTriggered.containsKey(key)) {
      for (String tk : beTriggered.get(key)) {
        trigger.get(tk).remove(key);
      }
      beTriggered.put(key, null);
    }
  }

  @Override
  public String toString() {
    System.out.println("sheet: " + sheet);
    System.out.println("trigger: " + trigger);
    System.out.println("beTriggered: " + beTriggered);
    System.out.println("---");
    return "--";
  }

  static class Cell {
    int value;
    String key;
    // int row;
    // int
    String formular;

    @Override
    public String toString() {
      return value + " " + formular;
    }
  }
}
