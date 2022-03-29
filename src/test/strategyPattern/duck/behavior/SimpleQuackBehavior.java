package test.strategyPattern.duck.behavior;

import test.strategyPattern.duck.IQuackBehavior;

public class SimpleQuackBehavior implements IQuackBehavior {
    @Override
    public void quack(){
        System.out.println("Simple Quack");
    }
}
