package lib.com.sitdh;

public class Greeting {

  private String hi;

  public Greeting(String hi) {
    this.hi = hi;
  }

  public String toString() {
    return String.format("Hello, %s", hi);
  }

}
