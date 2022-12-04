

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * A list of squares within a single window.
 *
 * <p>
 * Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by Mike Clancy in
 * 2001. Original code by Mike Clancy. Updated Fall 2022 by K. Raven
 * Russell.</p>
 */
public class SquareList {
    
    /**
     * initializes list.
     */
    private ThreeTenLinkedList<Square> list;

    /**
     * Initialize an empty list of squares.
     */
    public SquareList() {
        
        list = new ThreeTenLinkedList<>();
    }

    /**
     * get the value of the list's head.
     *
     * @return Head of list
     */
    public Node<Square> getHead() {
        
        return list.getHead();
    }

    /**
     * get the value of the list's tail.
     *
     * @return tail of list
     */
    public Node<Square> getTail() {
        
        return list.getTail(); //dummy return, replace this!
    }

    /**
     * Get the # of squares in the list.
     *
     * @return the list size
     */
    public int numSquares() {
      
        return list.getSize();
    }

    /**
     * adds a square to the list.
     *
     * @param sq square to be added
     */
    public void add(Square sq) {
       
        if(sq == null){
            throw new IllegalArgumentException("Invalid Square");
        }
        else{
            list.insert(sq);
        }
    }

    /**
     * Deletes squares within an X and Y position.
     *
     * @param x position
     * @param y position
     * @return whether the squares were deleted or not
     */
    public boolean handleClick(int x, int y) {
       
        Node<Square> curr = list.getHead();
        boolean deleted = false;
        while(curr != null){
            if(curr.data.contains(x, y)){
                list.delete(curr.data);
                deleted = true;
            }
            curr = curr.next;
        }
        return deleted;
    }

    /**
     * Gets an iterator for the list of squares. Squares are returned in the
     * order added.
     *
     * @return the iterator requested
     */
    public Iterator<Square> elements() {
       

        return new Iterator<Square>() {
            /**
             * The current node pointed to by the iterator (containing the next
             * value to be returned).
             */
            private Node<Square> current = getHead();

            /**
             * {@inheritDoc}
             */
            @Override
            public Square next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Square ret = current.data;
                current = current.next;
                return ret;
            }

            /**
             * {@inheritDoc}
             */
            @Override
            public boolean hasNext() {
                return (current != null);
            }
        };
    }

    /**
     * Sorts all the squares in the window by the time they were created.
     */
    public void sortCreation() {
    
        
        Comparator<Square> comp = new Comparator<Square>() {
            public int compare(Square s1, Square s2) {
                return (s1.id() - s2.id());
            }
        };
        ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
        ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);
        
    }

    /**
     * Method that sorts the squares by location.
     */
    public void sortLoc() {
       
        Comparator<Square> comp = new Comparator<Square>() {
            public int compare(Square s1, Square s2) {
                return (s1.getUpperLeftX() * s1.getUpperLeftY()) - (s2.getUpperLeftX() * s2.getUpperLeftY());
            }
        };
        ThreeTenLinkedList.NodePair<Square> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
        ThreeTenLinkedList.NodePair<Square> ret = ThreeTenLinkedList.sort(pair, comp);
    }
}