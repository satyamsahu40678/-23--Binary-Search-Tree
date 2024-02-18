// Q2. Given a BST and a positive number k, find the k'th largest node in the BST.
// We know that an inorder traversal of a binary search tree returns the nodes in ascending order. To 
// find the k'th smallest node, we can perform inorder traversal and store the inorder sequence in an 
// array. Then the k'th largest node would be the (n-k)'th smallest node, where n is the total number of 
// nodes present in the BST.

// The problem with this approach is that it requires two traversals of the array. We can solve this 
// problem in a single traversal of the array by using reverse inorder traversal (traverse the right 
// subtree before the left subtree for every node). Then the reverse inorder traversal of a binary search 
// tree will process the nodes in descending order
import java.util.concurrent.atomic.AtomicInteger;

// A class to store a BST node
class Node {
    int data;
    Node left = null, right = null;

    Node(int data) {
        this.data = data;
    }
}

class Ques2 {
    // Recursive function to insert a key into a BST
    public static Node insert(Node root, int key) {
        // if the root is null, create a new node and return it
        if (root == null) {
            return new Node(key);
        }

        // if the given key is less than the root node, recur for the left subtree
        if (key < root.data) {
            root.left = insert(root.left, key);
        }

        // if the given key is more than the root node, recur for the right subtree
        else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    // Function to find the k'th largest node in the BST.
    // Here, `i` denotes the total number of nodes processed so far
    public static Node kthLargest(Node root, AtomicInteger i, int k) {
        // base case
        if (root == null) {
            return null;
        }

        // search in the right subtree
        Node left = kthLargest(root.right, i, k);

        // if k'th largest is found in the left subtree, return it
        if (left != null) {
            return left;
        }

        // if the current node is k'th largest, return its value
        if (i.incrementAndGet() == k) {
            return root;
        }

        // otherwise, search in the left subtree
        return kthLargest(root.left, i, k);
    }

    // Function to find the k'th largest node in the BST
    public static Node kthLargest(Node root, int k) {
        // maintain index to count the total number of nodes processed so far
        AtomicInteger i = new AtomicInteger(0);

        // traverse the tree in an inorder fashion and return k'th node
        return kthLargest(root, i, k);
    }

    public static void main(String[] args) {
        int[] keys = { 15, 10, 20, 8, 12, 16, 25 };

        Node root = null;
        for (int key : keys) {
            root = insert(root, key);
        }

        int k = 2;
        Node node = kthLargest(root, k);

        if (node != null) {
            System.out.println(node.data);
        } else {
            System.out.println("Invalid Input");
        }
    }
}
