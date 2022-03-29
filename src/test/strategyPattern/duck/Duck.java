package test.strategyPattern.duck;

public abstract class Duck implements IQuackBehavior, IFlyBehavior{

    IQuackBehavior qb;
    IFlyBehavior fb;

    public Duck(IQuackBehavior qb, IFlyBehavior fb){
        this.qb = qb;
        this.fb = fb;
    }


    public abstract void display();

    @Override
    public void fly() {
        fb.fly();
    }

    @Override
    public void quack() {
        qb.quack();
    }
}
