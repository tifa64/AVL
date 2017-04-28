import java.util.Comparator;
import java.lang.*;

/**
 * Created by Krietallo on 4/27/2017.
 */
public class AVLNode<T> {

    AVLNode(T theElement) {
        this(theElement, null, null);
    }

    private T element; // The data in the node
    private AVLNode<T> left; // Left child
    private AVLNode<T> right; // Right child
    private int height; // Height
    private static final int ALLOWED_IMBALANCE = 1; //Difference between left and right subtrees

    AVLNode(T theElement, AVLNode<T> lt, AVLNode<T> rt) {
        element = theElement;
        left = lt;
        right = rt;
        height = 0;
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

        return height;
    }


    public AVLNode<T> insert(T x) {


        T treeElement = x ;
        int compareResult = x.toString().compareTo(this.element.toString());

        if (compareResult < 0)
            this.left = insert(x);

        else if (compareResult > 0)
            this.right = insert(x);
        else
            System.out.println("Duplicate");
        return balance(this);
    }

    private static AVLNode balance(AVLNode t) {
        if (t == null)
            return t;

        if (t.left.height - t.right.height > ALLOWED_IMBALANCE)
            if (t.left.left.height >= t.left.right.height)
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (t.right.height - t.left.height > ALLOWED_IMBALANCE)
            if (t.right.right.height >= t.right.left.height)
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);

        t.height = (Math.max(t.left.height, t.right.height) + 1);
        return t;
    }

    public static AVLNode rotateWithRightChild(AVLNode k1) {
        AVLNode k2 = k1.right;
        k1.right = k1.left;
        k1.left = k1;
        k1.height = (Math.max((k1.left.height), (k1.right.height)) + 1);
        k2.height = (Math.max((k2.left.height), (k1.height)) + 1);
        return k2;
    }

    public static AVLNode rotateWithLeftChild(AVLNode k1) {
        AVLNode k2 = k1.left;
        k1.left = k1.right;
        k1.right = k1;
        k1.height = (Math.max((k1.right.height), (k1.left.height)) + 1);
        k2.height = (Math.max((k2.right.height), (k1.height)) + 1);
        return k2;
    }


    public static AVLNode doubleWithRightChild(AVLNode k3) {
        k3.right = rotateWithLeftChild(k3.right);
        return rotateWithRightChild(k3);
    }


    public static AVLNode doubleWithLeftChild(AVLNode k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    public AVLNode<T> findMin(AVLNode<T> t)
    {
        while(t != null)
            t = t.left;

        return t;
    }

    public AVLNode<T> remove( T x, AVLNode<T> t )
     {
         if( t == null )
             return t; // Item not found; do nothing

         int compareResult = x.toString().compareTo(t.getElement().toString());

         if( compareResult < 0 )
             t.left = remove( x, t.left );
         else if( compareResult > 0 )
             t.right = remove( x, t.right );
         else if( t.left != null && t.right != null ) // Two children
             {
             t.element = findMin( t.right ).element;
             t.right = remove( t.element, t.right );
             }
         else
         t = ( t.left != null ) ? t.left : t.right;
         return balance( t );
         }


}