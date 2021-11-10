package vn.techmaster.hiaop.service;

import org.springframework.stereotype.Service;

@Service
public class Account {
  private int amount;
  public Account() {
    this.amount = 10;
  }
  public Account(int initialAmount) {
    this.amount = initialAmount;
  }
  public void doSomething() {
    System.out.println("do something");
  }

  public void saySomething() {
    System.out.println("say something");
  }

  public void transfer(int amount) {
    if (amount > this.amount) {
      throw new IllegalArgumentException("amount must be less " + this.amount);
    }
    this.amount -= amount;
    System.out.println("Transfer  " + amount);
  }
  public int getAmount() {
    return amount;
  }
  public void setAmount(int amount) {
    this.amount = amount;
  }

 

}
