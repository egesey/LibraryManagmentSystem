package LibraryManagementSystem;

import java.util.ArrayList;
import java.util.List;

class TreeNode {
    String name;
    List<TreeNode> children;

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }

    public boolean removeChild(String bookName) {
        return children.removeIf(child -> child.name.startsWith(bookName + " -"));
    }

    public TreeNode findChild(String name) {
        for (TreeNode child : children) {
            if (child.name.equals(name)) {
                return child;
            }
        }
        return null;
    }

    public boolean findPath(String bookName, List<String> path) {
        path.add(this.name);
        if (this.name.startsWith(bookName + " -")) {
            return true;
        }
        for (TreeNode child : children) {
            if (child.findPath(bookName, path)) {
                return true;
            }
        }
        path.remove(path.size() - 1);
        return false;
    }

    public void display(int level) {
        System.out.println(" ".repeat(level * 4) + "|-- " + name);
        for (TreeNode child : children) {
            child.display(level + 1);
        }
    }
}
