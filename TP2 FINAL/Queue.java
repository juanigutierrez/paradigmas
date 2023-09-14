package queue;
import java.util.ArrayList;
import java.util.List;

public class Queue {
    public List<DualState> list_object = new ArrayList<>(List.of(new Empty()));
    
    public Queue add(Object elements) {
        list_object.add(1,new NotEmpty(elements));
        return this;
    }
    public int size() {
        return list_object.size() - 1;
    }
    public Boolean isEmpty() {
        return size() == 0;
    }
    public Object take() {
        Object getObject= list_object.get(size()).take();
        list_object.remove(size());
        return getObject;
    }
    public Object head() {
        return list_object.get(size()).head();
    }
}
