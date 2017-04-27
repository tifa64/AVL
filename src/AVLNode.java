import java.util.Comparator;
import java.lang.*;

/**
 * Created by Krietallo on 4/27/2017.
 */
public class AVLNode<String> {

    AVLNode(String theElement) {
        this(theElement, null, null);
    }

    private String element; // The data in the node
    private AVLNode<String> left; // Left child
    private AVLNode<String> right; // Right child
    private int height; // Height
    private static final int ALLOWED_IMBALANCE = 1; //Difference between left and right subtrees

    AVLNode(String theElement, AVLNode<String> lt, AVLNode<String> rt) {
        element = theElement;
        left = lt;
        right = rt;
        height = 0;
    }


    public String getElement() {
        return element;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public AVLNode<String> getLeft() {
        return left;
    }

    public void setLeft(AVLNode<String> left) {
        this.left = left;
    }

    public AVLNode<String> getRight() {
        return right;
    }

    public void setRight(AVLNode<String> right) {
        this.right = right;
    }

    public int getHeight(AVLNode<String> avl) {

        if (avl == null)
            return 0;

        height = 1 + Math.max(getHeight(avl.left), getHeight(avl.right));
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }


    private AVLNode<String> insert(String x, AVLNode<String> t) {
        if (t == null)
            return new AVLNode<>(x, null, null);

        String treeElement = t.element;
        int compareResult = x.toString().compareTo(t.getElement().toString());

        if (compareResult < 0)
            t.left = insert(x, t.left);
        else if (compareResult > 0)
            t.right = insert(x, t.right);
        else
            System.out.println("Duplicate");
        return balance(t);
    }

    private AVLNode<String> balance(AVLNode<String> t) {
        if (t == null)
            return t;

        if (getHeight(t.left) - getHeight(t.right) > ALLOWED_IMBALANCE)
            if (getHeight(t.left.left) >= getHeight(t.left.right))
                t = rotateWithLeftChild(t);
            else
                t = doubleWithLeftChild(t);
        else if (getHeight(t.right) - getHeight(t.left) > ALLOWED_IMBALANCE)
            if (getHeight(t.right.right) >= getHeight(t.right.left))
                t = rotateWithRightChild(t);
            else
                t = doubleWithRightChild(t);

        t.setHeight(Math.max(getHeight(t.left), getHeight(t.right)) + 1);
        return t;
    }

    public AVLNode<String> rotateWithLeftChild(AVLNode<String> k2) {
        AVLNode<String> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.setHeight(Math.max(getHeight(k2.left), getHeight(k2.right)) + 1);
        k1.setHeight(Math.max(getHeight(k1.left), getHeight(k2)) + 1);
        return k1;
    }


}




