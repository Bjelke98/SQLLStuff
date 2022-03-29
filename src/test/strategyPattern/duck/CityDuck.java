package test.strategyPattern.duck;

public class CityDuck extends Duck{
    public CityDuck(IQuackBehavior qb, IFlyBehavior fb) {
        super(qb, fb);
    }

    @Override
    public void display() {
        System.out.println("Display City Duck");
    }
}
