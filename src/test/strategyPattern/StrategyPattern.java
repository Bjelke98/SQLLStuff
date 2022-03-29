package test.strategyPattern;

import test.strategyPattern.duck.Duck;
import test.strategyPattern.duck.behavior.SimpleFlyBehavior;
import test.strategyPattern.duck.behavior.SimpleQuackBehavior;
import test.strategyPattern.duck.WildDuck;

public class StrategyPattern {
    public static void main(String[] args) {
        Duck d = new WildDuck(new SimpleQuackBehavior(), new SimpleFlyBehavior());
        d.fly();
        d.quack();
    }
}
