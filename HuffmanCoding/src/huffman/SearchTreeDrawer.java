package huffman;

//import apple.laf.JRSUIUtils.Tree;

public class SearchTreeDrawer {
    
    private static final double NODE_RADIUS = 0.02;
    private static final double NODE_SPACING = 0.04;
    private static final double LEVEL_SPACING = 0.1;
    
    private TreeNode root;

    public  void main(TreeNode root) {
        this.root = root;
    }
    
    public void draw() {
        drawTree(root, 0.5, 0.9, 0, 0);
    }

    private void drawTree(TreeNode node, double x, double y, double xParent, double yParent) {
        if (node == null) {
            return;
        }
        
        // Draw the node
        //StdDraw.circle(x, y, NODE_RADIUS);
        CharFreq data = node.getData();
        StdDraw.text(x, y, String.format("%s (%.5f)", data.getCharacter(), data.getProbOcc()));

        // Draw the edge to the parent
        if (xParent != 0 && yParent != 0) {
            StdDraw.line(x, y, xParent, yParent);
        }

        // Draw the left and right subtrees
        double xLeft = x - NODE_SPACING - getSubtreeWidth(node.getLeft());
        double xRight = x + NODE_SPACING + getSubtreeWidth(node.getRight());
        double ySubtree = y - LEVEL_SPACING;
        drawTree(node.getLeft(), xLeft, ySubtree, x, y);
        drawTree(node.getRight(), xRight, ySubtree, x, y);
    }

    private double getSubtreeWidth(TreeNode node) {
        if (node == null) {
            return 0;
        }
        CharFreq data = node.getData();
        String text = String.format("%s (%.2f)", data.getCharacter(), data.getProbOcc());
        return NODE_RADIUS + NODE_SPACING + getSubtreeWidth(node.getLeft());
    }
}
