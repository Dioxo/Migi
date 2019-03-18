package dioxo.migi.Objets.Objs;

public class LList<T> {

    private Node<T> head;
    private int size;

    public LList() {
        this.head = null;
        size = 0;
    }

    public LList(T elem, T color) {
        this.head = new Node<T>(elem, color);
        size++;
    }

    public LList(T head, T color, LList<T> tail) {
        this.head = new Node<T>(head,color, tail.getHead());
    }

    public Node<T> getHead() {
        return this.head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void add(T elem, T color) {
        if (this.head == null) {
            head = new Node<T>(elem, color);
        } else {
            Node<T> p = head;
            while (p.hasNext()) {
                p = p.getNext();
            }
            p.setNext(new Node<T>(elem, color));
        }
        size++;
    }

    public int getSize() {
        return this.size;
    }

    public String toString() {
        String res = "";
        Node<T> p = head;
        while (p != null) {
            res += p.getText().toString();
            if (p.hasNext()) {
                res += ", ";
            }
            p = p.getNext();
        }
        return "(" + res + ")";
    }

}