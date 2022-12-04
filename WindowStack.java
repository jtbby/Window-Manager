
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Comparator;

/**
 * A stack of windows within the window.
 *
 * <p>
 * Adapterion of Nifty Assignment (http://nifty.stanford.edu/) by Mike Clancy in
 * 2001. Original code by Mike Clancy. Updated Fall 2022 by K. Raven
 * Russell.</p>
 */
public class WindowStack {
   
    /**
     * initialize the stack.
     */
    private ThreeTenLinkedList<Window> stack;

    /**
     * construct the stack.
     */
    public WindowStack() {
      
        stack = new ThreeTenLinkedList<>();
    }

    /**
     * gets the top of the stack.
     *
     * @return head of stack
     */
    public Node<Window> getHead() {
      
        
        return stack.getHead(); 
    }

    /**
     * gets the bottom of stack.
     *
     * @return tail of stack
     */
    public Node<Window> getTail() {
       
        return stack.getTail();
    }

    /**
     * gets the current size of the stack.
     *
     * @return size of stack
     */
    public int numWindows() {
        
        return stack.getSize();
    }

    /**
     * Adds a window to the top of the stack.
     *
     * @param r value to pass
     */
    public void add(Window r) {
        
        if(r == null){
            throw new IllegalArgumentException("Invalid Window");
        }
        else{
            stack.insertAtHead(r);
        }
    }

    /**
     * method that moves a window to top of the stack if it's been leftClicked
     * and removes it if it was clicked with right.
     *
     * @param x position
     * @param y position
     * @param leftClick state of the click
     * @return whether or not a window was moved/deleted
     */
    public boolean handleClick(int x, int y, boolean leftClick) {
        
        ThreeTenLinkedList<Window> tempStack = new ThreeTenLinkedList<>();
        if(stack.getHead() == null){
            return false;
        }
        if(leftClick){
            if(stack.getHead().data.contains(x, y)){
                stack.getHead().data.handleClick(x, y);
                return true;
            }
            else{
                stack.getHead().data.setSelected(false);
                Window found = null;
                while(stack.getSize() > 0){
                    Window top = stack.deleteHead();
                    if(top != null){
                        if(top.contains(x, y)){
                            found = top;
                            break;
                        }
                        else{
                            tempStack.insertAtHead(top);
                        }
                    }
                }
                
                while(tempStack.getSize() > 0){
                    Window top = tempStack.deleteHead();
                    if(top != null){
                        stack.insertAtHead(top);
                    }
                }
                if(found != null){
                    stack.insertAtHead(found);
                    stack.getHead().data.setSelected(true);
                    return true;
                }
            }
        }
        else{
            if(stack.getHead() != null){
                stack.getHead().data.setSelected(false);
                if(stack.getHead().data.contains(x, y)){
                    stack.deleteHead();
                    return true;
                }
            }
            Window found = null;
            while(stack.getSize() > 0){
                Window top = stack.deleteHead();
                if(top != null){
                    if(top.contains(x, y)){
                        found = top;
                        break;
                    }
                    else{
                        tempStack.insertAtHead(top);
                    }
                }
            }
            while(tempStack.getSize() > 0){ 
                Window top = tempStack.deleteHead();
                if(top != null){
                    stack.insertAtHead(top);
                }
            }
            if(found != null){
                if(stack.getHead() != null){
                    stack.getHead().data.setSelected(true);
                }
                return true;
            }
        }
        
     
        
        return false; 
    }

    /**
     * Gets an iterator for the stack of windows. Windows are returned from
     * bottom to top.
     *
     * @return the iterator requested
     */
    public Iterator<Window> windows() {

        return new Iterator<Window>() {
            /**
             * The current node pointed to by the iterator (containing the next
             * value to be returned).
             */
            private Node<Window> current = getTail();

            /**
             * {@inheritDoc}
             */
            @Override
            public Window next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Window ret = current.data;
                current = current.prev;
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
     * Sorts windows by area.
     */
    public void sortSize() {
       
        this.getHead().data.setSelected(false);

       
        Comparator<Window> comp = new Comparator<Window>() {
            public int compare(Window w1, Window w2) {
                return (w1.getWidth() * w1.getHeight()) - (w2.getWidth() * w2.getHeight());
            }
        };

        ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());

        ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);

        this.getHead().data.setSelected(true);
    }

    /**
     * Sorts windows by their upper left corner location.
     */
    public void sortLoc() {
      
        this.getHead().data.setSelected(false);
        
        Comparator<Window> comp = new Comparator<Window>() {
            public int compare(Window w1, Window w2) {
                return (w1.getUpperLeftX() * w1.getUpperLeftY()) - (w2.getUpperLeftX() * w2.getUpperLeftY());
            }
        };
      
        ThreeTenLinkedList.NodePair<Window> pair = new ThreeTenLinkedList.NodePair<>(getHead(), getTail());
       
        ThreeTenLinkedList.NodePair<Window> ret = ThreeTenLinkedList.sort(pair, comp);
        this.getHead().data.setSelected(true);
        
    }
}
