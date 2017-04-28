package Core;

/**
 * Created by Krietallo on 4/27/2017.
 */
public class AVLNode<T> {

    private static final int ALLOWED_IMBALANCE = 1; //Difference between left and right subtrees
    private T element; // The data in the node
    private AVLNode<T> left; // Left child
    private AVLNode<T> right; // Right child


    public AVLNode(T theElement) {
        this(theElement, null, null);
    }

    public AVLNode(T theElement, AVLNode<T> lt, AVLNode<T> rt) {
        element = theElement;
        left = lt;
        right = rt;
    }

    private static AVLNode balance(AVLNode t) {
        if (t == null)
            return t;

        if (t.getLeftHeight() - t.getRightHeight() > ALLOWED_IMBALANCE)
            if ((t.left == null? 0 : t.left.getLeftHeight()) >= (t.left == null? 0 : t.left.getRightHeight()))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (t.getRightHeight() - t.getLeftHeight() > ALLOWED_IMBALANCE)
            if ((t.right == null? 0 : t.right.getRightHeight()) >= (t.right == null? 0 : t.right.getLeftHeight()))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);

        return t;
    }

    private static AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k1.left;
        k1.left = k1;
        return k2;
    }

    private static AVLNode rotateWithLeftChild(AVLNode k1) {
        AVLNode k2 = k1.left;
        k1.left = k1.right;
        k1.right = k1;
        return k2;
    }

    private static AVLNode doubleWithRightChild(AVLNode k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }

    private static AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private static void setNode(AVLNode source, AVLNode destination) {
        if (source == null) {
            destination = null;
        } else {
            destination.left = source.left;
            destination.right = source.right;
            destination.element = source.element;
        }
    }

    private int getLeftHeight() {
        return this.left == null? 0 : this.left.getHeight();
    }

    private int getRightHeight() {
        return this.right == null? 0 : this.right.getHeight();
    }

    private AVLNode<T> remove(T x) {
        int compareResult = x.toString().compareTo(this.getElement().toString());

        if (compareResult < 0) {
            if (this.left == null)
                return null;
            this.left = this.left.remove(x);
        } else if (compareResult > 0) {
            if (this.right == null)
                return null;
            this.right = this.right.remove(x);
        } else if (this.left != null && this.right != null) // Two children
        {
            this.element = findMin(this.right).element;
            this.right = this.right.remove(this.element);
        } else
            this.setNode((this.left != null) ? this.left : this.right, this);
        return balance(this);
    }

    public T getElement() {
        return element;
    }

    public void setElement(T element) {
        this.element = element;
    }

    public AVLNode<T> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<T> left) {
        this.left = left;
    }

    public AVLNode<T> getRight() {
        return right;
    }

    public void setRight(AVLNode<T> right) {
        this.right = right;
    }

    public int getHeight() {

        return 1 + Math.max(this.left == null ? 0 : this.left.getHeight(), this.right == null ? 0 : this.right.getHeight());
    }

    public AVLNode<T> insert(T x) throws Exception {
        int compareResult = x.toString().compareTo(this.element.toString());
        if (compareResult < 0) {
            if (this.left != null){
                this.left = this.left.insert(x);
            } else {
                this.left = new AVLNode<T>(x);
            }
        }
        else if (compareResult > 0){
            if (this.right != null){
                this.right = this.right.insert(x);
            } else {
                this.right = new AVLNode<T>(x);
            }
        } else {
            throw new Exception("Duplicate");
        }
        return balance(this);
    }

    public AVLNode<T> findMin(AVLNode<T> t) {
        while (t != null)
            t = t.left;

        return t;
    }

    public boolean search(T x) {
        int compareResult = x.toString().compareTo(this.getElement().toString());
        boolean found = true;
        if (compareResult < 0) {
            if (this.left == null)
                return false;
            found = this.left.search(x);
        } else if (compareResult > 0) {
            if (this.right == null)
                return false;
            found = this.right.search(x);
        }
        return found;

    }

    public AVLNode<T> delete(T x) {
        if (search(x))
            return remove(x);

        return null;
    }
}