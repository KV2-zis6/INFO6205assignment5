package edu.neu.coe.info6205.symbolTable;

import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;

public class BSTSimple<Key extends Comparable<Key>, Value> implements BSTdetail<Key, Value> {
    @Override
    public Boolean contains(Key key) {
        return get(key) != null;
    }

    @Override
    public void putAll(Map<Key, Value> map) {
        //There is a TODO here. Professor said it doesn't need to be implemented
        for (Key k : map.keySet()) put(k, map.get(k));
    }

    @Override
    public int size() {
        return root != null ? root.count : 0;
    }
    
    

    @Override
    public void inOrderTraverse(BiFunction<Key, Value, Void> f) {
        doTraverse(0, root, f);
    }

    @Override
    public Value get(Key key) {
        return get(root, key);
    }

    @Override
    public Value put(Key key, Value value) {
        NodeValue nodeValue = put(root, key, value);
        if (root == null) root = nodeValue.node;
        if (nodeValue.value==null) root.count++;
        return nodeValue.value;
    }

    @Override
    public Set<Key> keySet() {
        return null;
    }

    public BSTSimple() {
    }

    public BSTSimple(Map<Key, Value> map) {
        this();
        putAll(map);
    }

    Node root = null;
    
    public int showDepth() {
    	return findDepth(root);
    }
    
    public int findDepth(Node node)  
    {  
        int deep = 0;  
        if(node != null)  
        {  
            int lchilddeep = findDepth(node.smaller);  
            int rchilddeep = findDepth(node.larger);  
            deep = lchilddeep > rchilddeep ? lchilddeep + 1 : rchilddeep + 1;  
        }  
        return deep;  
    }  

    //------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void delete(Key key) {
        // TODO- Implement this delete method or add your variations of delete.
        delete(root, key);

    }
    
    private Node delete(Node root,Key key) { 
    	if(root==null) return null; 
    	int cmp=key.compareTo(root.key); 
    	if (cmp<0) root.smaller=delete(root.smaller, key); 
    	else if(cmp>0) root.larger=delete(root.larger, key); 
    	else{
    		if(root.larger==null) return root.smaller;
    		if(root.smaller==null) return root.larger; 
    		Node node=root;
    		root=min(node.larger);
    		root.larger=deleteMin(node.larger); 
    		root.smaller=node.smaller; 
    		} 
    	
    	return root; 
    	}
    public void deleteMin(){ 
    	root=deleteMin(root); 
    	} 
    
    private Node deleteMin(Node root){ 
    	if(root.smaller==null) return root.larger; 
    	root.smaller=deleteMin(root.smaller);
    	return root; 
    }
    
    public Key min() { 
    	return min(root).key; 
    	} 
    private Node min(Node x) {
    	if(x.smaller==null) return x;
    	else return min(x.smaller);
    	}
   //------------------------------------------------------------------------------------------------------------------------------------------
    private Value get(Node node, Key key) {
        if (node == null) return null;
        int cf = key.compareTo(node.key);
        if (cf < 0) return get(node.smaller, key);
        else if (cf > 0) return get(node.larger, key);
        else return node.value;
    }

    /**
     * Method to put the key/value pair into the subtree whose root is node.
     *
     * @param node  the root of a subtree
     * @param key   the key to insert
     * @param value the value to associate with the key
     * @return a tuple of Node and Value: Node is the
     */
    private NodeValue put(Node node, Key key, Value value) {
        // If node is null, then we return the newly constructed Node, and value=null
        if (node == null) return new NodeValue(new Node(key, value), null);
        int cf = key.compareTo(node.key);
        if (cf == 0) {
            // If keys match, then we return the node and its value
            NodeValue result = new NodeValue(node, node.value);
            node.value = value;
            return result;
        } else if (cf < 0) {
            // if key is less than node's key, we recursively invoke put in the smaller subtree
            NodeValue result = put(node.smaller, key, value);
            if (node.smaller == null)
                node.smaller = result.node;
            if (result.value==null) node.count++;
            return result;
        } else {
            // if key is greater than node's key, we recursively invoke put in the larger subtree
            NodeValue result = put(node.larger, key, value);
            if (node.larger == null)
                node.larger = result.node;
            if (result.value==null) node.count++;
            return result;
        }
    }

    /**
     * Do a generic traverse of the binary tree starting with node
     * @param q determines when the function f is invoked ( lt 0: pre, ==0: in, gt 0: post)
     * @param node the node
     * @param f the function to be invoked
     */
    private void doTraverse(int q, Node node, BiFunction<Key, Value, Void> f) {
        if (node == null) return;
        if (q<0) f.apply(node.key, node.value);
        doTraverse(q, node.smaller, f);
        if (q==0) f.apply(node.key, node.value);
        doTraverse(q, node.larger, f);
        if (q>0) f.apply(node.key, node.value);
    }

    private class NodeValue {
        private final Node node;
        private final Value value;

        NodeValue(Node node, Value value) {
            this.node = node;
            this.value = value;
        }

        @Override
        public String toString() {
            return node + "<->" + value;
        }
    }

    class Node {
        Node(Key key, Value value) {
            this.key = key;
            this.value = value;
        }

        final Key key;
        Value value;
        Node smaller = null;
        Node larger = null;
        int count = 0;

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder("Node: " + key + ":" + value);
            if (smaller != null) sb.append(", smaller: " + smaller.key);
            if (larger != null) sb.append(", larger: " + larger.key);
            return sb.toString();
        }

    }

    private Node makeNode(Key key, Value value) {
        return new Node(key, value);
    }

    private Node getRoot() {
        return root;
    }

    private void setRoot(Node node) {
        root = node;
    }

    private void show(Node node, StringBuffer sb, int indent) {
        if (node == null) return;
        for (int i = 0; i < indent; i++) sb.append("  ");
        sb.append(node.key);
        sb.append(": ");
        sb.append(node.value);
        sb.append("\n");
        if (node.smaller != null) {
            for (int i = 0; i <= indent; i++) sb.append("  ");
            sb.append("smaller: ");
            show(node.smaller, sb, indent + 1);
        }
        if (node.larger != null) {
            for (int i = 0; i <= indent; i++) sb.append("  ");
            sb.append("larger: ");
            show(node.larger, sb, indent + 1);
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        show(root, sb, 0);
        return sb.toString();
    }
    
    
    private int size(Node x) {
    	return x==null?0:x.count;
    }
}
