/*
===========================================================
Sr. No. 2
DESIGN AND ANALYSIS OF ALGORITHMS

Title: Huffman Encoding Using Greedy Strategy

CO4, CO5

Program: Implement Huffman Encoding using a greedy strategy.
===========================================================
*/

import java.util.PriorityQueue;
import java.util.Comparator;

public class DAAAssign2 {
    static int counter = 0;

    static class Node {
        char ch; int freq; int order; Node left, right;
        Node(char ch, int freq) {
            this.ch = ch; this.freq = freq; this.order = counter++;
            this.left = null; this.right = null;
        }
    }

    // lowest frequency first; ties broken by creation order so output is always the same
    static class NodeComparator implements Comparator<Node> {
        public int compare(Node a, Node b) {
            return a.freq != b.freq ? a.freq - b.freq : a.order - b.order;
        }
    }

    public static void generateCodes(Node root, String code, StringBuilder result) {
        if (root == null) return;
        if (root.left == null && root.right == null) {          // leaf node
            result.append(root.ch).append(": ").append(code).append("\n");
            return;
        }
        generateCodes(root.left, code + "0", result);
        generateCodes(root.right, code + "1", result);
    }

    public static void huffmanEncode(char[] chars, int[] freqs) {
        PriorityQueue<Node> pq = new PriorityQueue<>(new NodeComparator());
        for (int i = 0; i < chars.length; i++) pq.add(new Node(chars[i], freqs[i]));

        while (pq.size() > 1) {                                  // greedy: merge 2 smallest
            Node left = pq.poll();
            Node right = pq.poll();
            Node newNode = new Node('-', left.freq + right.freq);
            newNode.left = left;
            newNode.right = right;
            pq.add(newNode);
        }

        StringBuilder result = new StringBuilder();
        generateCodes(pq.poll(), "", result);
        System.out.print(result);
    }

    public static void main(String[] args) {
        char[] chars = {'p', 'q', 'r', 's', 't', 'u'};
        int[] freqs  = {5, 7, 10, 15, 20, 35};
        System.out.println("Huffman Codes:");
        huffmanEncode(chars, freqs);
    }
}


/*
==================== OUTPUT ====================
Huffman Codes:
s: 00
t: 01
r: 100
p: 1010
q: 1011
u: 11
================================================
*/
