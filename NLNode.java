/**
 * This class represents a node in a tree structure.
 * It has a parent node, a list of child nodes, and some data.
 * @param <T> the type of data stored in the node
 */
import java.util.Iterator;
import java.util.Comparator;
public class NLNode<T> {
    private NLNode<T> parent; // the parent node
    private ListNodes<NLNode<T>> children; // a list of child nodes
    private T data; // the data stored in the node

    /**
     * Constructs a new NLNode with null parent, empty children list, and null data.
     */
    public NLNode() {
        parent = null;
        children = new ListNodes<>();
        data = null;
    }

    /**
     * Constructs a new NLNode with the specified data and parent node.
     * @param d the data stored in the node
     * @param p the parent node
     */
    public NLNode (T d, NLNode<T> p) {
        children = new ListNodes<>();
        data = d;
        parent = p;
    }

    /**
     * Sets the parent node of this node.
     * @param p the new parent node
     */
    public void setParent(NLNode<T> p) {
        parent = p;
    }

    /**
     * Returns the parent node of this node.
     * @return the parent node
     */
    public NLNode<T> getParent() {
        return parent;
    }

    /**
     * Adds a child node to this node's list of children.
     * @param newChild the new child node to add
     */
    public void addChild(NLNode<T> newChild) {
        children.add(newChild);
        newChild.setParent(this);
    }

    /**
     * Returns an iterator over the child nodes of this node.
     * @return an iterator over the child nodes
     */
    public Iterator<NLNode<T>> getChildren() {
        return children.getList();
    }

    /**
     * Returns a sorted iterator over the child nodes of this node, using the specified Comparator.
     * @param sorter the Comparator used to sort the child nodes
     * @return a sorted iterator over the child nodes
     */
    public Iterator<NLNode<T>> getChildren(Comparator<NLNode<T>> sorter) {
        return children.sortedList(sorter);
    }

    /**
     * Returns the data stored in this node.
     * @return the data stored in the node
     */
    public T getData() {
        return data;
    }

    /**
     * Sets the data stored in this node.
     * @param d the new data to store in the node
     */
    public void setData(T d) {
        data = d;
    }
}