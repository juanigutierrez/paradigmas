package queue;

public class NotEmpty extends DualState{
    private Object elements;
    public NotEmpty(Object elements){
        this.elements = elements;
    }
   public Object take(){
        return elements;
    }
    public Object head(){
        return elements;
    }
}