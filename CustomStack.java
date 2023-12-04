import java.util.Iterator;

public class CustomStack implements Iterable<Integer> {

    private final int DEFAULT_SIZE = 2;
    private Integer[] items;
    private int n;

    public CustomStack(){
        this.n = 0;
        this.items = new Integer[DEFAULT_SIZE];

    }
    public CustomStack(int capacity){
        this.items = new Integer[capacity];
        this.n = 0;

    }

    @Override
    public Iterator<Integer> iterator() {
        return null;
    }
    
    public boolean isEmpty(){
        return this.n==0;

    }

    public void push (int item){
        this.items[n++] = item;

    }

    public int pull(){
        return this.items[--n];
    }

    public int peek(){
        return this.items[n-1];
    }

    public int size(){
        return n;
    }
    public static void main(String[] args) {
        CustomStack testStack = new CustomStack(5);
        testStack.push(4);
        testStack.push(17);
        testStack.push(69);

        System.out.println("Size of stack: " + testStack.size());
        System.out.println(testStack.peek());
        testStack.pull();
        testStack.pull();
        System.out.println(testStack.peek());
        testStack.pull();

        System.out.println(testStack.isEmpty());

        
    }


    
}
