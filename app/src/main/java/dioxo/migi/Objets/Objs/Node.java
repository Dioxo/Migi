package dioxo.migi.Objets.Objs;


public class Node<T> {
	private T text;
	private T color;
	private Node<T> next;
	
	public Node(T elem, T color){
		this.text = elem;
		this.color = color;
	}
	
	public Node(T elem, T color, Node<T> _next){
		this(elem, color);
		this.next = _next;
	}
	
	public T getText() {
		return this.text;
	}
	
	public void setText(T elem) {
		this.text = elem;
	}

	public T getColor() {
		return color;
	}

	public void setColor(T color) {
		this.color = color;
	}

	public Node<T> getNext(){
		return this.next;
	}
	
	public void setNext(Node<T> _next) {
		this.next = _next;
	}
	
	public boolean hasNext() {
		return this.next != null;
	}

    @Override
    public String toString() {
        return "Node{" + "text=" + text +  " color =" + color + ", next=" + next + '}';
    }
        

}
