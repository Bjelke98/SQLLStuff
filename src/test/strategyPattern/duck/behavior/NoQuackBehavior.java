package test.strategyPattern.duck.behavior;

import test.strategyPattern.duck.IQuackBehavior;

public class NoQuackBehavior implements IQuackBehavior {
    @Override
    public void quack() {
        System.out.println("I dont quack");
    }
}
