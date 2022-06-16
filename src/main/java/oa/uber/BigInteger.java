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
    BigInteger b = new BigInteger("1319").add(new BigInteger("922"));
    System.out.println(b.toString() + " == 2241");
    b = new BigInteger("-1319").add(new BigInteger("-922"));
    System.out.println(b.toString() + " == -2241");
    b = new BigInteger("9").add(new BigInteger("1"));
    System.out.println(b.toString() + " == 10");
    b = new BigInteger("-9").add(new BigInteger("-1"));
    System.out.println(b.toString() + " == -10");
    b = new BigInteger("9999").add(new BigInteger("1"));
    System.out.println(b.toString() + " == 10000");
    b = new BigInteger("-9999").add(new BigInteger("-1"));
    System.out.println(b.toString() + " == -10000");
    b = new BigInteger("-1").add(new BigInteger("-9999"));
    System.out.println(b.toString() + " == -10000");

    System.out.println();
    b = new BigInteger("1319").subtract(new BigInteger("922"));
    System.out.println(b.toString() + " == 397");
    b = new BigInteger("-1319").subtract(new BigInteger("-922"));
    System.out.println(b.toString() + " == -397");
    b = new BigInteger("9").subtract(new BigInteger("1"));
    System.out.println(b.toString() + " == 8");
    b = new BigInteger("-9").subtract(new BigInteger("-1"));
    System.out.println(b.toString() + " == -8");
    b = new BigInteger("9999").subtract(new BigInteger("1"));
    System.out.println(b.toString() + " == 9998");
    b = new BigInteger("-9999").subtract(new BigInteger("-1"));
    System.out.println(b.toString() + " == -9998");
    b = new BigInteger("-1").subtract(new BigInteger("-9999"));
    System.out.println(b.toString() + " == 9998");
    b = new BigInteger("1111").subtract(new BigInteger("1110"));
    System.out.println(b.toString() + " == 1");
    b = new BigInteger("1110").subtract(new BigInteger("1111"));
    System.out.println(b.toString() + " == -1");
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

      String result = subtractOperation(original, add);
      return new BigInteger(result.contains("-") ? result.substring(1) : "-" + result);
    }

    if (add.contains("-")) {
      bigInteger.value = add.substring(1);
    } else {
      bigInteger.value = "-" + add;
    }
    
    return add(bigInteger);
  }

  private String subtractOperation(String original, String subtracter) {
    return subtractOperation(original, subtracter, "");
  }

    private String subtractOperation(String original, String subtracter, String prefix) {
//    System.out.println(original + " - " + subtracter);
    StringBuilder sb = new StringBuilder();

    int carryOver = 0;
    int i = original.length() - 1, j = subtracter.length() - 1;
    while (i >= 0 || j >= 0) {
      int originalInt = 0;
      int subtracterInt = 0;
      if (i >= 0) {
        originalInt = Integer.parseInt(original.substring(i, i + 1));
      }
      if (j >= 0) {
        subtracterInt = Integer.parseInt(subtracter.substring(j, j + 1));
      }
//      System.out.println("---" + originalInt + " " + addInt);

      int sum = originalInt - subtracterInt + carryOver;
      if (sum < 0) {
        sum = sum + 10;
        carryOver = -1;
      } else {
        carryOver = 0;
      }
      sb.append(sum);

      i--;
      j--;
    }

    if (carryOver < 0) {
      return subtractOperation(subtracter, original, "-");
    }

    String result = sb.reverse().toString();
    while (result.startsWith("0") && result.length() > 1) {
      result = result.substring(1);
    }

    result = prefix + result;
//    System.out.println("sub result: " + result);
    return result;
  }

  private String addOperation(String original, String add) {
//    System.out.println(original + " + " + add);
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
