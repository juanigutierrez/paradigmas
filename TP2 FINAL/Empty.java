package queue;

public  class Empty extends DualState{
    public Object take(){
        throw new Error( "Queue is empty" );
    }
    public Object head(){
        throw new Error( "Queue is empty" );
    }
}