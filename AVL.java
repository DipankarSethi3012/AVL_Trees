class AVL {
    public class Node{
        int value;
        int height;
        Node left;
        Node right;
        
        Node(int value) {
            this.value = value;
            this.height = 1;
        }
        
        public int getValue(){
            return this.value;
        }
    }
    
    private Node root;
    
    public AVL() {}
    
    private int height(Node node) {
        return node == null ? 0 : node.height;
    }
    
    private int getBalanceFactor(Node node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }
    
    public void insert(int value) {
        root = insert(value, root);
    }
    
    private Node insert(int value, Node node) {
        if(node == null) {
            return new Node(value);
        }
        
        if(value < node.value) {
            node.left = insert(value, node.left);
        }
        
        if(value > node.value) {
            node.right = insert(value, node.right);
        } else{
            return node;
        }
        
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }
    
    private Node balance(Node node) {
        int balanceFactor = getBalanceFactor(node);
        
        //left-heavy 
        if(balanceFactor > 1) {
            if(getBalanceFactor(node.left) < 0) {
                node.left = leftRotate(node.left); //left-right case
            }
            
            return rightRotate(node); //left-left case
        }
        
        //right-heavy case
        if(balanceFactor < -1) {
            if(getBalanceFactor(node.right) > 0) {
                node.right = rightRotate(node.right); //right-left case
            }
            
            return leftRotate(node); // right-right case
        }
        
        return node;
    }
    
    private Node rightRotate(Node parent) {
        Node child = parent.left;
        Node grandChild = child.right;
        
        child.right = parent;
        parent.left = grandChild;
        
        parent.height = Math.max(height(parent.left) , height(parent.right)) + 1;
        child.height = Math.max(height(child.left), height(child.right)) + 1;
        
        return child;
    }
    
    private Node leftRotate(Node parent) {
        Node child = parent.right;
        Node grandChild = child.left;
        
        parent.right = grandChild;
        child.left = parent;
        
        parent.height = Math.max(height(parent.left), height(parent.right)) + 1;
        child.height = Math.max(height(child.left), height(child.right)) + 1;
        
        return child;
        
    }
    
    public void delete(int value) {
        root = delete(root, value);
    }
    
    public Node delete(Node node, int value) {
        if(node == null) return null;
        
        if(value < node.value) {
            node.left = delete(node.left, value);
        } else if(value > node.value) {
            node.right = delete(node.right, value);
        }else{
            if(node.left == null || node.right == null) {
                node = (node.left != null) ? node.left : node.right;
            } else{
                Node successor = findMin(node.right);
                node.value = successor.value;
                node.right = delete(node.right, successor.value);
            }
        }
        
        if(node == null) return node;
        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return balance(node);
    }
    
    private Node findMin(Node node) {
        while(node.left != null) {
            node = node.left;
        }
        return node;
    }
    
    public boolean isBalanced(){
        return isBalanced(root);
    }
    
    private boolean isBalanced(Node node) {
        if(node == null) return true;
        int balanceFactor = getBalanceFactor(node);
        
        return Math.abs(balanceFactor) <= 1 && isBalanced(node.left) && isBalanced(node.right);
    }
    
    public void display(){
        display(root, "Root Node: ");
    }
    
    private void display(Node node, String details) {
        if(node == null) return;
        System.out.println(details + node.value);
        display(node.left , "left child of : " + node.value + " : ");
        display(node.right, " right child of: " + node.value + " : ");
        
    }
    
}