package test.strategyPattern.duck.behavior;

import test.strategyPattern.duck.IFlyBehavior;

public class SimpleFlyBehavior implements IFlyBehavior {
    @Override
    public void fly(){
        System.out.println("Simple Fly");
    }
}
