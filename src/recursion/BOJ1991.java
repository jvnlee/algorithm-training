package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class BOJ1991 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        Tree tree = new Tree();

        StringTokenizer st;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());

            String value = st.nextToken();
            String left = st.nextToken();
            String right = st.nextToken();

            tree.addNode(value, left, right);
        }

        System.out.println(
                tree.traversePreorder(tree.getRoot(), new StringBuilder()) + "\n"
                        + tree.traverseInorder(tree.getRoot(), new StringBuilder()) + "\n"
                        + tree.traversePostorder(tree.getRoot(), new StringBuilder())
        );
    }

    private static class Node {
        String value;
        Node left;
        Node right;

        public Node(String value) {
            this.value = value;
        }
    }

    private static class Tree {
        Map<String, Node> nodes = new HashMap<>();

        public Node getRoot() {
            return nodes.get("A");
        }

        public void addNode(String value, String left, String right) {
            Node node = nodes.computeIfAbsent(value, Node::new);

            if (!left.equals(".")) {
                node.left = nodes.computeIfAbsent(left, Node::new);
            }

            if (!right.equals(".")) {
                node.right = nodes.computeIfAbsent(right, Node::new);
            }
        }

        public String traversePreorder(Node node, StringBuilder result) {
            if (node == null) {
                return result.toString();
            }

            result.append(node.value);
            traversePreorder(node.left, result);
            traversePreorder(node.right, result);

            return result.toString();
        }

        public String traverseInorder(Node node, StringBuilder result) {
            if (node == null) {
                return result.toString();
            }

            traverseInorder(node.left, result);
            result.append(node.value);
            traverseInorder(node.right, result);

            return result.toString();
        }

        public String traversePostorder(Node node, StringBuilder result) {
            if (node == null) {
                return result.toString();
            }

            traversePostorder(node.left, result);
            traversePostorder(node.right, result);
            result.append(node.value);

            return result.toString();
        }
    }
}
