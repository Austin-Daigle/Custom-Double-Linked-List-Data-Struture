/**
 * Purpose: To demonstrate a doubly linked list with sentinels
 * @author Austin
 * Date: 2/24/2021
 * @version 1.0
 */
public class Driver {
		    public static void main(String[] args) throws Exception {
        //Below is the demonstration of the driver
        DList < Integer > example = new DList < Integer > ();
        example.addLast(8);
        example.removeFirst();
        example.addFirst(11);
        example.addFirst(1);
        System.out.println(example);
        example.removeLast();
        System.out.println(example);
        example.addLast(101);
        example.addLast(124);
        example.add(3, 3);
        System.out.println("Size: " + example.size());
        System.out.println(example);
        example.print();
        System.out.println("Iterator Demonstration:");
        Iterator < Integer > demo = example.Iterator();
        while (demo.hasNext()) {
            System.out.println(demo.next());
        }
        System.out.println("-------Search Function --------------");
        System.out.println("searching for 11: " + example.contains(11));
        System.out.println("searching for 100: " + example.contains(100));
    }
}	


//Iterator interface
interface Iterator < T > {
    public boolean hasNext();
    public T next() throws Exception;
}

//Abstract class class for data storage processing
abstract class dataStorage < T > {
    public abstract int size();
    public abstract boolean isEmpty();
}

//This is the class that creates the doubly linked list 
class DList < T > extends dataStorage < T > implements Iterator < T > {

    //Create the sentinel Nodes
    private Node < T > head;
    private Node < T > tail;
    private int size = 0;

    //Set the memory address of the sentinel nodes either each other or null depending on bound limits.
    public DList() {
        //Set the memory addresses for the head sentinel
        head = new Node < T > ();
        head.setNext(head);
        head.setPrevious(null);
        //Set the memory addresss for the tail sentinel
        tail = new Node < T > ();
        tail.setNext(null);
        tail.setPrevious(head);
    }

    /**
     * This method deletes all of the nodes present in a DList list object.
     */
    public void clear() {
        //Set the memory addresses for the head sentinel
        head.setNext(head);
        head.setPrevious(null);
        //Set the memory addresss for the tail sentinel
        tail.setNext(null);
        tail.setPrevious(head);
        //Update the size to reflect the amount new amount of nodes
        this.size = 0;
    }

    //This is the nested class that creates the nodes.
    class Node < T > {
        //node attributes
        private T element;
        private Node < T > next;
        private Node < T > prev;

        //constructor for making "dummy" nodes.
        public Node() {}

        /**
         * This constructor makes nodes with just an element attributes.
         * This is used for making nodes for operations and comparisons.
         * @param element This is the object that is parsed in for the data entry.
         */
        public Node(T element) {
            this.element = element;
        }

        /**
         * This is the primary constructor that creates most of the nodes that 
         * are being created.
         * @param element This is the object that is parsed that hold the node data.
         * @param next This is the memory address object that is used to find the next node.
         * @param prev This is the memory address object that is used to find the previous node.
         */
        public Node(T element, Node < T > next, Node < T > prev) {
            this.element = element;
            this.next = next;
            this.prev = prev;
        }

        /**
         * This getter method returns the node object of the previous field value.
         * @return returns the object of the field value.
         */
        public Node < T > getPrevious() {
            return prev;
        }

        /**
         * This is a setter method for the object field value previous.
         * @param previous This is the object that will set the value of the field in the object.
         */
        public void setPrevious(Node < T > previous) {
            this.prev = previous;
        }

        /**
         * This is a getter method that returns the object of the next address refference.
         * @return returns the node object address refference of the given node.
         */
        public Node < T > getNext() {
            return next;
        }

        /**
         * This is a setter method for the field next. 
         * @param next This sets the object refference for the field next.
         */
        public void setNext(Node < T > next) {
            this.next = next;
        }

        /**
         * This is the getter method for the field element.
         * @return returns the object of the field element from the given object.
         */
        public T getElement() {
            return element;
        }


    }

    //Creates the iterator variables.
    private DList < T > storage;
    private int currentLocation;
    //Default iterator contructor
    public DList(DList < T > listObject) throws Exception {
        this.storage = listObject;
        this.currentLocation = 0;
    }

    /**
     * This method return a boolean variable indicating that the iterator has another node.
     * @return returns a a true boolean value if there is another object present in the list
     */
    @Override
    public boolean hasNext() {
        return this.currentLocation < this.storage.size();
    }

    /**
     * This method returns the given object element at the node and incrament the iterator
     * value.
     * @return the object at the given current position of the iterator.
     */
    @Override
    public T next() throws Exception {
        currentLocation++;
        return this.storage.elementAt(currentLocation - 1);
    }

    /**
     * This method resets the currentlocation iterator variable to zero. 
     * This reset the iterator for the purposes of debugging and developement.
     */
    public void resetItr() {
        currentLocation = 0;
    }

    /**
     * This method return the integer value of the size of the doubly linked array list.
     * @return this return an int value of the size of the Dlist object.
     */
    @Override
    public int size() {
        return this.size;
    }

    /**
     * This method returns a a true boolean value if the doubly liked array object is empty.
     * @return a true boolean value if the list is empty.
     */
    @Override
    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * This method returns the object at the index of the node in the DList object
     * @param index this is the index of the node to return to in the DList object.
     * @return This returns an object at the index of the value of parse input.
     * @throws Exception throws an exception if the index is out of bounds.
     */
    public T elementAt(int index) throws Exception {
        //Throw exception if the index is out of bounds.
        if (index < 0 || index >= size()) {
            throw new Exception("Index Of Out Bounds Exception");
        } else {
            //Create node object for traversing
            Node < T > elementCheck = head.getNext();
            //create count variable.
            int count = 0;
            //while count is less than index.
            while (count < index) {
                //update the object element checek to the next node.
                elementCheck = elementCheck.getNext();
                //update the variable count 
                count++;
            }
            //return the object value.
            return (T) elementCheck.getElement();
        }
    }

    /**
     * This method removes a node that the given parsed index.
     * @param index This is the int value that is used to reffer and remove the
     * desired node.
     */
    public void remove(int index) throws Exception {
        //Throw an exception if the index is out of bounds
        if (index < 0 || index >= size()) {
            throw new Exception("IndexOutOfBoundsException");
        } else {
            //Create node object for searching through
            Node < T > remove = head.getNext();
            //while index is larger that 0
            while (index > 0) {
                //update the remove node object
                remove = remove.getNext();
                //Remove the index object.
                --index;
            }
            //Linked out the selected node thus causing a deletion.
            remove.getPrevious().setNext(remove.getNext());
            remove.getNext().setPrevious(remove.getPrevious());
            //Update the size variable.
            this.size--;
        }
    }

    /**
     * This method add a new node with the given element at the given index location
     * @param index this is the value in where to add the new node.
     * @param element This is the object to add to the new node
     * @throws Exception throws an exception if the index is out of bounds.
     */
    public void add(int index, T element) throws Exception {
        //If the index is out of bounds throw an exception
        if (index < 0 || index > size()) {
            throw new Exception("IndexOutOfBoundsException");
        } else {

            //Create node object to iteratate/operate through
            Node < T > newElement = new Node < T > (element);
            Node < T > runNode = head.getNext();
            //while index is less than zero
            while (index > 0) {
                //update node object
                runNode = runNode.getNext();
                //update the index variable.
                --index;
            }
            //Introduce the new node into the list by changing address associations.
            runNode.getPrevious().setNext(newElement);
            newElement.setPrevious(runNode.getPrevious());
            runNode.setPrevious(newElement);
            newElement.setNext(runNode);
            //Update the size variable.
            this.size++;
        }
    }

    /**
     * This method creates a node obejct with the given element object 
     * and places that at the begining of the list.
     * @param element This is the object parsed into the node.
     */
    public void addFirst(T element) {
        //Create the node for the new node to be put in front of the list
        Node < T > newFirstNode = new Node < T > (element);
        //Create the node object for the original first node in the list
        Node < T > oldFirstNode = head.getNext();
        //Update address values
        newFirstNode.setNext(head.getNext());
        oldFirstNode.setPrevious(newFirstNode);
        head.setNext(newFirstNode);
        //If the size is equal to zero set tail's previous field to newFirstNode.
        if (this.size == 0) {
            tail.setPrevious(newFirstNode);
        }
        //Update the size variable.
        this.size++;
    }

    /**
     * This method add a new node to the end of the list.
     * @param element This is the object that is set to the element field in the node.
     */
    public void addLast(T element) {
        //If size equals zero call the addFirst method with the element data to prevent any errors.
        if (this.size == 0) {
            addFirst(element);
        } else {
            //Create a node for processing data
            Node < T > endNode = head.getNext();
            //Cycle through all of the nodes in the list
            while (endNode.getNext().getElement() != null) {
                //Update the endNode object to the final updated value per run.
                endNode = (DList < T > .Node < T > ) endNode.getNext();
            }
            //create a node object for further processing.
            Node < T > newEnd = new Node < T > (element);
            //Update the link association to add the new "newEnd" object into the list.
            endNode.setNext(newEnd);
            newEnd.setNext(tail);
            tail.setPrevious(newEnd);
            //Update the size.
            this.size++;
        }

    }

    /**
     * This method delete the first node in the list.
     * @throws Exception throws an exception is the list is empty.
     */
    public void removeFirst() throws Exception {
        //Create an object for processing the first node.
        Node < T > newFirstNode = head.getNext();
        //If the list is empty through an exception
        if (this.size == 0) {
            throw new Exception("Elements cannot be deleted from an empty list");
        }
        //If the size is one then clear the list and update the size.
        if (this.size == 1) {
            head.setNext(head);
            tail.setPrevious(head);
            this.size--;
        } else {
            //create the appriate object and "link-out" the first object and update the rest of the list.
            newFirstNode = (DList < T > .Node < T > ) newFirstNode.getNext();
            head.setNext(newFirstNode);
            newFirstNode.setPrevious(head);
            //Update the size.
            this.size--;
        }
    }

    /**
     * This method removes the last node in the list
     * @throws Exception throws an exception if the list is empty.
     */
    public void removeLast() throws Exception {
        //If the list is empty throw an exception.
        if (this.size == 0) {
            throw new Exception("Elements cannot be deleted from an empty list");
        }
        //if the list size is equal to one then delete all data in the list minus the "dummy" nodes
        if (this.size == 1) //to prevent a null point error and to save resources.
        {
            clear();
        } else {
            //Create a node for processing data
            Node < T > endNode = head.getNext();
            //Iteratate through all of the nodes and update endNode with the last non-tail node.
            while (endNode.getNext().getElement() != null) {
                endNode = (DList < T > .Node < T > ) endNode.getNext();

            }
            //Link out the last node in the list and adjust the addresses.
            Node < T > newEnd = endNode.getPrevious();
            tail.setPrevious(newEnd);
            newEnd.setNext(tail);
            //Update the size.
            this.size--;
        }
    }

    /**
     * This method returns a string value of all of the element value 
     * contains by each node in the doubly linked list.
     * @return returns a string value of all of the real nodes in the DList.
     */
    @Override
    public String toString() {
        //Create a string builder to collect the result
        StringBuilder result = new StringBuilder("[");
        //Create a node object for processing and iteration
        Node < T > print = head.getNext();
        //Iterate thoough all of the non-dummy nodes
        while (print.getElement() != null) {

            //If the next value is the tail
            if (print.getNext().getElement() == null) {
                //then update the string builder to not have space and comma.
                result.append(print.getElement());
            } else {
                //Have a space and comma otherwise.
                result.append(print.getElement() + ", ");
            }
            //Update the print node object
            print = (DList < T > .Node < T > ) print.getNext();
        }
        //Add the ending bracket to the string builder
        result.append("]");
        //return the result.
        return result.toString();
    }

    /**
     * This method prints out all of the values in the Dlist. 
     * This is functionally similar to toString().
     */
    public void print() {
        //Create iteration object
        Node < T > print = head.getNext();
        System.out.print("[");
        //Iterate through all of the non-dummy nodes
        while (print.getElement() != null) {
            //Printout the correct formatting
            if (print.getNext().getElement() == null) {
                System.out.print(print.getElement());
            } else {
                System.out.print(print.getElement() + ", ");
            }
            //update the print node object.
            print = (DList < T > .Node < T > ) print.getNext();
        }
        System.out.println("]");
    }

    /**
     * This method take the given object and searches
     * the Dlist to return a boolean value if the 
     * list has the value or not.
     * @return returns a boolean value if the object is found in the Dlist
     * @param this the object that is being searched for.
     */
    public boolean contains(Object searchFor) {
        //Create object for iteration
        Node < T > cycleObject = head;
        //Iterate througt the given list
        while (cycleObject.getNext().getElement() != null) {
            cycleObject = (DList < T > .Node < T > ) cycleObject.getNext();
            //If the object parsed matches a result node inside of the DList return a true.
            if (searchFor == cycleObject.getElement()) {
                return true;
            }
        }
        //otherwise return a false
        return false;
    }

    /**
     * This method returns an iterator for the primary iterator interface.
     * @return returns an interator object.
     * @throws Exception throws an exception if there is an incompatability.
     */
    public Iterator < T > Iterator() throws Exception {
        return new DList < T > (this);
    }

    /**
     * This method inserts a new node object after a given index. If the iddex is
     * at the compacity of the of Dlist then the node will be added to the end.
     * @param index this is the index to where to insert the object.
     * @param valueToInsert this is the object value to insert to the new node to be placed.
     * @throws Exception throws an exception if there is an incompatabilty.
     */
    public void insertAfter(int index, T valueToInsert) throws Exception {
        //If the size is zero then call the addFirst method
        if (this.size == 0) {
            addFirst(valueToInsert);
        }
        //If the size is less than the index then call the addLast method
        else if (this.size < index) {
            addLast(valueToInsert);
        } else //Else: call the add method to add the object.
        {
            add(index, valueToInsert);
        }
    }






}