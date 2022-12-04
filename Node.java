
/**
 * class that initiates a node and its functions.
 *
 * @param <T> Generic parameter
 */
class Node<T> {

    /**
     * the nodes value.
     */
    public T data;
    /**
     * points the next node.
     */
    public Node<T> next;
    /**
     * points to previous node.
     */
    public Node<T> prev;

    /**
     * initialization of node.
     */
    public Node() {

    }

    /**
     * Node constructor with data passed.
     *
     * @param data passed value for node
     */
    public Node(T data) {
        this.data = data;
    }
}
