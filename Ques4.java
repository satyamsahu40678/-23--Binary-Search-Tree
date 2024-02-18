// 4. Given a BST, find the inorder predecessor of a given key in it. If the key does not lie in the BST, 
// return the previous greater node (if any) present in the BST.
// To find which ancestors are the predecessor, move up the tree towards the root until we encounter 
// a node that is the right child of its parent. If any such node is found, then the inorder predecessor is 
// its parent; otherwise, the inorder predecessor does not exist for the node.

// We can recursively check the above conditions. The idea is to search for the given node in the tree 
// and update the predecessor to the current node before visiting its right subtree. If the node is found 
// in the BST, return the maximum value node in its left subtree. If the left subtree of the node doesn’t 
// exist, then the inorder predecessor is one of its ancestors, which is already being updated while 
// searching for the given key.

class Node {
    int data;
    Node left = null, right = null;

    Node(int data) {
        this.data = data;
    }
}

class Ques4 {
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

    // Helper function to find the maximum value node in a given BST
    public static Node findMaximum(Node root) {
        while (root.right != null) {
            root = root.right;
        }

        return root;
    }

    // Recursive function to find inorder predecessor for a given key in the BST
    public static Node findPredecessor(Node root, Node prec, int key) {
        // base case
        if (root == null) {
            return prec;
        }

        // if a node with the desired value is found, the predecessor is the maximum
        // value node in its left subtree (if any)
        if (root.data == key) {
            if (root.left != null) {
                return findMaximum(root.left);
            }
        }

        // if the given key is less than the root node, recur for the left subtree
        else if (key < root.data) {
            return findPredecessor(root.left, prec, key);
        }

        // if the given key is more than the root node, recur for the right subtree
        else {
            // update predecessor to the current node before recursing
            // in the right subtree
            prec = root;
            return findPredecessor(root.right, prec, key);
        }
        return prec;
    }

    public static void main(String[] args) {
        int[] keys = { 15, 10, 20, 8, 12, 16, 25 };
        Node root = null;
        for (int key : keys) {
            root = insert(root, key);
        }

        // find inorder predecessor for each key
        for (int key : keys) {
            Node prec = findPredecessor(root, null, key);

            if (prec != null) {
                System.out.println("The predecessor of node " + key + " is "
                        + prec.data);
            } else {
                System.out.println("The predecessor doesn't exist for node "
                        + key);
            }
        }
    }
}