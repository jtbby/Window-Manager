
import java.util.Comparator;

/**
 * Class to implement a linked list.
 *
 * @param <T> Generic parameter
 * @author Justin Thomas
 */
class ThreeTenLinkedList<T> {
  
    /**
     * method that checks if the specified list is sorted.
     *
     * @param pairs Node pairs to compare
     * @param comp value to use to compare
     * @param <X> generic
     * @return whether the list is sorted or not
     */
    static <X> boolean isSorted(NodePair<X> pairs, Comparator<X> comp) {
        //Determine if the provided list is sorted based on the comparator.
        if(pairs == null || comp == null){
            throw new IllegalArgumentException("Null Values");
        }
        else if(pairs.head == null && pairs.tail == null){
            return true;
        }
        else{
            Node<X> head = pairs.head;
            Node<X> tail = pairs.tail;


            Node<X> curr = head;
            while (curr.next != null && comp.compare(curr.data, curr.next.data) <= 0) {
                curr = curr.next;  
            }
            if(curr.data == tail.data){
                return true;
            }
        }

        
        return false; 
    }

    /**
     * Sorts the linked list by comparing the nodes and shifting to the left if one is
     * element is smaller than the other.
     *
     * @param pairs Node pairs to compare
     * @param comp value to use to compare
     * @param <X> generic
     * @return new Node pair
     */
    static <X> NodePair<X> sort(NodePair<X> pairs, Comparator<X> comp) {

      
        
        Node<X> head = pairs.head;
        Node<X> tail = pairs.tail;
        
        while(!isSorted(pairs, comp)){
            Node<X> curr = head;
            while (curr != null) {
                Node<X> curr2 = curr.next;

                while(curr2 != null && comp.compare(curr.data, curr2.data) <= 0){
                    //System.out.println("Comparing "+curr.data+" with "+curr2.data);
                    curr2 = curr2.next;
                }
                if(curr2 != null){
                    X temp = curr2.data;
                    curr2.data = curr.data;
                    curr.data = temp;

                }

                curr = curr.next;
            }
        }
        
        return new NodePair<>(head, tail); 
    }


    /**
     * initialize Node head.
     */
    private Node<T> head;
    /**
     * initialize Node tail.
     */
    private Node<T> tail;
    /**
     * initialize variable size.
     */
    private int size;

    /**
     * construct an empty linked list.
     */
    public ThreeTenLinkedList(){
        head = null;
        tail = null;
        size = 0;
    }

    /**
     * method that inserts a new Node at the head of the linked list.
     *
     * @param data value being passed to node
     */
    public void insertAtHead(T data) {
        if(head == null){
            head = new Node<>(data);
            tail = head;   
        }
        else{
            Node<T> node = new Node<>(data);
            node.next = head;
            head.prev = node;
            head = node;
        }
        size++;
    }

    /**
     * insert a new node at the end of a list.
     *
     * @param data value being passed to node
     */
    public void insert(T data) {  
        Node<T> newNode = new Node<>(data);
        if(head == null) {  
            head = tail = newNode;  
            head.prev = null;  
            tail.next = null;
        }  
        else {  
            tail.next = newNode;  
            newNode.prev = tail;  
            tail = newNode;  
            tail.next = null;  
        }
        size++;
    }

    /**
     * get the head of the list.
     *
     * @return head
     */
    public Node<T> getHead(){
        return head;
    }

    /**
     * get the tail of node.
     *
     * @return tail
     */
    public Node<T> getTail(){
        return tail;
    }

    /**
     * get the size of a linked list (# of elements).
     *
     * @return size
     */
    public int getSize(){
        return size;
    }

    /**
     * prints out the linked list by printing each element from left to right.
     */
    public void display() {
        if(head == null) {  
            return;  
        }
        Node curr = head;
        while(curr != null) {
            System.out.print(curr.data + " ");  
            curr = curr.next;  
        }
        System.out.println("");
    }

    /**
     *  delete the current list's head and return the value being deleted.
     *
     * @return the value being deleted or null
     */
    public T deleteHead() {
        if(head != null){
            T data = head.data;
            head.prev = null;
            head = head.next;
            size--;
            return data;
        }
        return null;
    }

    /**
     * method that deletes a specific a value that a user passes and
     * it iterates through the list to delete the specific value and
     * returns true if it was deleted or else false.
     *
     * @param data value to be deleted
     * @return whether the value that was passed was deleted or not from the list
     */
    public boolean delete(T data) {
        if (head == null || data == null) { 
            return false; 
        }
        if (head.data.equals(data)) {
            if(deleteHead() != null){
                return true;
            }
        }
        if (tail.data.equals(data)) { 
            tail = tail.prev;
            tail.next = null;
            size--;
            return true;
        }
        else{
            Node<T> curr = head.next;
            Node<T> prev = head;
            while(curr != null){
                if(curr.data.equals(data)){
                    curr = curr.next;
                    prev.next = curr;
                    curr.prev = prev;
                    size--;
                    return true;
                }
                curr = curr.next;
                prev = prev.next;
            }
        }
        return false; 
    }

    /**
     * main method of the class.
     *
     * @param args CDL arguments
     */
    public static void main(String[] args){
        ThreeTenLinkedList<Integer> list = new ThreeTenLinkedList<>();
        
        Comparator<Integer> comp = new Comparator<Integer>() {
            public int compare(Integer i1, Integer i2) {
                return i1 - i2;
            }
        };
    
        
        list.insert(44);
        list.insert(20);
        list.insert(10);
        list.insert(95);
        list.insert(47);
        list.insert(56);
        list.insert(48);
        list.insert(7);
        list.insert(6);
        list.insert(10);
        
        System.out.print("Before Sorting: ");
        list.display();
        ThreeTenLinkedList.sort(new NodePair<>(list.getHead(), list.getTail()), comp);
       
        System.out.print("After Sorting: ");
        list.display();
        
        System.out.println("Sorted? "+ThreeTenLinkedList.isSorted(new NodePair<>(list.getHead(), list.getTail()), comp));
        
        
        
        
        System.exit(0);
        list.display();
        list.delete(10);
        list.insert(15);
        list.insert(25);
        list.delete(25);
        list.insert(235);
        list.display();
        list.delete(95);
        list.delete(56);
        list.delete(44);
        list.delete(20);
        list.delete(48);
        list.delete(15);
        list.delete(235);
        list.delete(47);
        list.insert(101);
        list.delete(101);
        list.display();
        list.insertAtHead(99);
        list.insertAtHead(98);
        list.insertAtHead(97);
        
        System.out.println("Last: ");
        list.display();
        
        
        
    }
    
    
    


    /**
     * Node Pair nested class provided.
     *
     * @param <Y> generic identifier
     */
    public static class NodePair<Y> {

        /**
         * head Node for Y.
         */
        public Node<Y> head;
        /**
         * tail Node for Y.
         */
        public Node<Y> tail;

        /**
         * constructor for NodePair.
         *
         * @param head Node
         * @param tail Node
         */
        public NodePair(Node<Y> head, Node<Y> tail) {
            this.head = head;
            this.tail = tail;
        }
        
    }


}
