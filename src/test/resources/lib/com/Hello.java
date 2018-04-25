package lib.com;

import lib.com.sitdh.Greeting;

public class Hello {

  private int initStarting = 0;

  private boolean isValidate = true;

  private float decimalNumber = 4.2f;

  private double floating = 4.2;

  private String greetingTemplate = "Hello %s";

  public String people = "";

  public Hello(String name) {
    this.people = name;
  }

  public String sayHello() {
    Greeting g = new Greeting(people);

    return g.toString();
  }
}
