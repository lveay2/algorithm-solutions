package oa.uber;

import java.util.Objects;

/**
 * Design/Implement a BigInteger library, limiting the scope only to add() and subtract() methods on
 * Big Integers. Limit on length of number: 1 million digits
 *
 * Example usage of library involves below type of operations
 * BigInteger A, B;
 * A = new BigInteger("-1234567890");
 * B = new BigInteger("888888888888888");
 * BigInteger C = A.add(B);
 * BigInteger D = A.subtract(B);
 * C.toString() outputs 888887654320998
 * D.toString() outputs -888890123456778
 *
 * Example of all possible scenarios
 * Ends up addition
 * BigInteger("1319").Add(BigInteger("922")) => BigInteger("2241")
 * BigInteger("-1319").Add(BigInteger("-922")) => BigInteger("-2241")
 * BigInteger("1319").Subtract(BigInteger("-922")) => BigInteger("2241")
 * BigInteger("-1319").Subtract(BigInteger("922")) => BigInteger("-2241")
 *
 * Ends up subtraction
 * BigInteger("1319").Add(BigInteger("-922")) => BigInteger("397")
 * BigInteger("-1319").Add(BigInteger("922")) => BigInteger("-397")
 * BigInteger("1319").Subtract(BigInteger("922")) => BigInteger("397")
 * BigInteger("-1319").Subtract(BigInteger("-922")) => BigInteger("-397")
 */
public class BigInteger {
  String value;

  public BigInteger(String value) {
    this.value = value;
  }

  public static void main(String[] args) {
    System.out.println(
        new BigInteger("1319").add(new BigInteger("922")).equals(new BigInteger("2241")));
    System.out.println(
            new BigInteger("-1319").add(new BigInteger("-922")).equals(new BigInteger("-2241")));
    System.out.println(
            new BigInteger("-9").add(new BigInteger("-1")).equals(new BigInteger("-10")));
    System.out.println(
            new BigInteger("9").add(new BigInteger("1")).equals(new BigInteger("10")));
    System.out.println(
            new BigInteger("9999").add(new BigInteger("1")).equals(new BigInteger("10000")));
    System.out.println(
            new BigInteger("-9999").add(new BigInteger("-1")).equals(new BigInteger("-10000")));
  }

  public BigInteger add(BigInteger bigInteger) {
    if (bigInteger == null || bigInteger.value.isEmpty()) {
      return this;
    }

    String original = this.value;
    String add = bigInteger.value;
//    System.out.println(original + " " + add);

    if (!original.contains("-") && !add.contains("-")) {
      return new BigInteger(addOperation(original, add));
    } else if (original.contains("-") && add.contains("-")) {
      original = original.substring(1);
      add = add.substring(1);
      return new BigInteger("-" + addOperation(original, add));
    }

    return subtract(bigInteger);
  }

  private BigInteger subtract(BigInteger bigInteger) {
    if (bigInteger == null || bigInteger.value.isEmpty()) {
      return this;
    }

    String original = this.value;
    String add = bigInteger.value;
//    System.out.println(original + " " + add);

    if (!original.contains("-") && !add.contains("-")) {
      return new BigInteger(subtractOperation(original, add));
    } else if (original.contains("-") && add.contains("-")) {
      original = original.substring(1);
      add = add.substring(1);
      return new BigInteger("-" + subtractOperation(original, add));
    }

    if (add.contains("-")) {
      bigInteger.value = add.substring(1);
    } else {
      bigInteger.value = "-" + add;
    }
    
    return add(bigInteger);
  }

  private String subtractOperation(String original, String add) {

    return null;
  }


  private String addOperation(String original, String add) {
    StringBuilder sb = new StringBuilder();

    int carryOver = 0;
    int i = original.length() - 1, j = add.length() - 1;
    while (i >= 0 || j >= 0) {
      int originalInt = 0;
      int addInt = 0;
      if (i >= 0) {
        originalInt = Integer.parseInt(original.substring(i, i + 1));
      }
      if (j >= 0) {
        addInt = Integer.parseInt(add.substring(j, j + 1));
      }
//      System.out.println("---" + originalInt + " " + addInt);

      int sum = originalInt + addInt + carryOver;
      carryOver = sum / 10;
      int newValue = sum % 10;
      sb.append(newValue);

      i--;
      j--;
    }

    if (carryOver > 0) {
      sb.append(carryOver);
    }

    return sb.reverse().toString();
  }


  @Override
  public String toString() {
    return this.value;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    BigInteger that = (BigInteger) o;
    return value.equals(that.value);
  }

  @Override
  public int hashCode() {
    return Objects.hash(value);
  }
}
