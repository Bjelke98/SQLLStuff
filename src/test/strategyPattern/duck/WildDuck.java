package test.strategyPattern.duck;

public class WildDuck extends Duck{
    public WildDuck(IQuackBehavior qb, IFlyBehavior fb) {
        super(qb, fb);
    }

    @Override
    public void display() {
        System.out.println("Display Wild Duck");
    }
}
