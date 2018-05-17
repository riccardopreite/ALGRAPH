package sample;

public class List<T> {

    public T element;
    public List<T> enter;
    public List<T> exit;

    public List() {
        this.element = null;
        this.enter = null;
        this.exit = null;

    }


    public void addNode(List<T> enter2, List<T> exit2) {
        /*
        T element2,
        this.element = element2;
        */
        this.enter = enter2;
        this.exit = exit2;

    }


}
