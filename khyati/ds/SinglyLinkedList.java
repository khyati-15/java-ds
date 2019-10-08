package khyati.ds;
public class SinglyLinkedList<E extends Comparable<E>> 
{
  //---------------- nested Node class ----------------
  /**
   * Node of a singly linked list, which stores a reference to its
   * element and to the subsequent node in the list (or null if this
   * is the last node).
   */
  private static class Node<E extends Comparable<E>>
  {
    /** The element stored at this node */
    private E element;            // reference to the element stored at this node

    /** A reference to the subsequent node in the list */
    private Node<E> next;         // reference to the subsequent node in the list

    /**
     * Creates a node with the given element and next node.
     *
     * @param e  the element to be stored
     * @param n  reference to a node that should follow the new node
     */
    public Node(E e, Node<E> n) 
    {
      element = e;
      next = n;
    }

    // Accessor methods
    /**
     * Returns the element stored at the node.
     * @return the element stored at the node
     */
    public E getElement() 
    { 
      return element; 
    }
	
	public void setElement(E val){
		element=val;
	}
    /**
     * Returns the node that follows this one (or null if no such node).
     * @return the following node
     */
    public Node<E> getNext() { return next; }

    // Modifier methods
    /**
     * Sets the node's next reference to point to Node n.
     * @param n    the node that should follow this one
     */
    public void setNext(Node<E> n) { next = n; }
	
	
  } //----------- end of nested Node class -----------


  // instance variables of the SinglyLinkedList
  /** The head node of the list */

  private Node<E> head = null;               // head node of the list (or null if empty)


  /** The last node of the list */
  private Node<E> tail = null;               // last node of the list (or null if empty)


  /** Number of nodes in the list */
  private int size = 0;                      // number of nodes in the list


  /** Constructs an initially empty list. */
  public SinglyLinkedList() { }              // constructs an initially empty list


  // access methods
  /**
   * Returns the number of elements in the linked list.
   * @return number of elements in the linked list
   */
  public int size() { return size; }


  /**
   * Tests whether the linked list is empty.
   * @return true if the linked list is empty, false otherwise
   */
  public boolean isEmpty() { return size == 0; }

  /**
   * Returns (but does not remove) the first element of the list
   * @return element at the front of the list (or null if empty)
   */
  public E first() {             // returns (but does not remove) the first element
    if (isEmpty()) return null;
    return head.getElement();
  }

  /**
   * Returns (but does not remove) the last element of the list.
   * @return element at the end of the list (or null if empty)
   */
  public E last() {              // returns (but does not remove) the last element
    if (isEmpty()) return null;
    return tail.getElement();
  }

  // update methods
  /**
   * Adds an element to the front of the list.
   * @param e  the new element to add
   */
  public void addFirst(E e) {                // adds element e to the front of the list
    head = new Node<>(e, head);              // create and link a new node
    if (size == 0)
      tail = head;                           // special case: new node becomes tail also
    size++;
  }

  /**
   * Adds an element to the end of the list.
   * @param e  the new element to add
   */
  public void addLast(E e) {                 // adds element e to the end of the list
    Node<E> newest = new Node<>(e, null);    // node will eventually be the tail
    if (isEmpty())
      head = newest;                         // special case: previously empty list
    else
      tail.setNext(newest);                  // new node after existing tail
    tail = newest;                           // new node becomes the tail
    size++;
  }

  /**
   * Removes and returns the first element of the list.
   * @return the removed element (or null if empty)
   */
  public E removeFirst() {                   // removes and returns the first element
    if (isEmpty()) return null;              // nothing to remove
    E answer = head.getElement();
    head = head.getNext();                   // will become null if list had only one node
    size--;
    if (size == 0)
      tail = null;                           // special case as list is now empty
    return answer;
  }
  /**
   * Produces a string representation of the contents of the list.
   * This exists for debugging purposes only.
   */
  public String toString() {
    StringBuilder sb = new StringBuilder("(");
    Node<E> walk = head;
    while (walk != null) {
      sb.append(walk.getElement());
      if (walk != tail)
        sb.append(", ");
      walk = walk.getNext();
    }
    sb.append(")");
    return sb.toString();
  }
  
  public void reverse(){
  tail=head;
  head=reverse(head);
  }
  
  private Node reverse(Node node){
  	if(node.getNext()==null || node==null){
  		return node;
  	}
	Node newnode=reverse(node.getNext());
	node.getNext().setNext(node);
	node.setNext(null);
	return newnode;
  }
  
  public E findMiddle(){
  	Node<E> slowptr=head;
	Node<E> fastptr=head;
	if(head!=null){
		while(fastptr!=null && fastptr.getNext()!=null ){
		fastptr=fastptr.getNext().getNext();
		slowptr=slowptr.getNext();
		}
		return slowptr.getElement();
	}
	else
	return null;
  }
  
  public E findNthLast(int n){
  	if(size<n)
  		return null;
	else if(head==null)
		return null;
	else{
		Node<E> mainptr=head;
		Node<E> refptr=head;
		int count=0;
		while(count<n){
			refptr=refptr.getNext();
			count++;
		}
		
		while(refptr!=null){
			mainptr=mainptr.getNext();
			refptr=refptr.getNext();
		}
	return mainptr.getElement();
	}
  
  }
  
  public boolean isPalindrome(){
  boolean result=checkPal(head);
  return result;
  }
  Node<E> left=null;
  private boolean checkPal(Node ryt){
 	left=head;
  if(ryt==null)
  return true;
  boolean res=checkPal(ryt.getNext());
  if(res==false)
  return false;
  boolean com=(ryt.getElement()==left.getElement());
  left=left.getNext();
  return com;
  }
  
  private void removeLoop(Node<E> node){
  	Node<E> ptr1=head;
	Node<E> ptr2;
	while(true){
		ptr2=node;
		while(ptr2.getNext()!=node && ptr2.getNext()!=ptr1)
  			ptr2.getNext();
  		if(ptr2.getNext()==ptr1)
  			break;
  		ptr1=ptr1.getNext();
	}
	ptr2.setNext(null);
  }
  
  public boolean DetectandRemoveLoop(){
  	Node<E> slow=head;
  	Node<E> fast=head;
	while(slow!=null && fast!=null && fast.getNext()!=null){
  		fast=fast.getNext().getNext();
  		slow=slow.getNext();
		if(fast==slow)
			break;
	}
	
	if(slow!=fast)
		return false;
	slow=head;
	while(slow!=fast){
		slow=slow.getNext();
		fast=fast.getNext();
	}
	removeLoop(fast);
	return true;
  }
  
  public void addFirstLast(){
  addFirstLast(head,head);
  }
  
  private Node<E> temp=null;
  private void addFirstLast(Node<E> slow,Node<E> fast){
  	if(fast==null){
		temp=slow;
		return;
	}
	if(fast.getNext()==null){
		temp=slow.getNext();
		return;
	}
	
	addFirstLast(slow.getNext(),fast.getNext().getNext());
	E val=slow.getElement();
	E val1=temp.getElement();
	if(val instanceof Integer && val1 instanceof Integer){
	val+=val1;
	val1=val;
	}
	slow.setElement(val);
	temp.setElement(val1);
	
	
  }
  
}
