package DoD;

public class Capsule {

    private boolean capsule;

    public void controlX(String command){
        //aquí quiero que haga lo mismo que en Nemo.
    }

    public Capsule() {
        this.capsule = false;
    }

    public void freeCapsule() {
        this.capsule = true;
    }

    public boolean getCapsule() {
        return capsule;
    }

}
