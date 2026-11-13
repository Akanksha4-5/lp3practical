# ===========================================================
# Sr. No. 2
# DESIGN AND ANALYSIS OF ALGORITHMS
#
# Title: Huffman Encoding Using Greedy Strategy
#
# CO4, CO5
#
# Program: Implement Huffman Encoding using a greedy strategy.
# ===========================================================

import heapq

class Node:
    def __init__(self, ch, freq):
        self.ch, self.freq = ch, freq
        self.left = self.right = None

def generate_codes(root, code, result):
    if root is None:
        return
    if root.left is None and root.right is None:             # leaf node
        result.append(f"{root.ch}: {code}")
        return
    generate_codes(root.left, code + "0", result)
    generate_codes(root.right, code + "1", result)

def huffman_encode(chars, freqs):
    # heap item = (freq, creation order, node): ties broken by order, same as Java
    heap = [(f, i, Node(c, f)) for i, (c, f) in enumerate(zip(chars, freqs))]
    heapq.heapify(heap)
    order = len(heap)

    while len(heap) > 1:                                     # greedy: merge 2 smallest
        f1, _, left = heapq.heappop(heap)
        f2, _, right = heapq.heappop(heap)
        new_node = Node('-', f1 + f2)
        new_node.left, new_node.right = left, right
        heapq.heappush(heap, (new_node.freq, order, new_node))
        order += 1

    result = []
    generate_codes(heap[0][2], "", result)
    print("\n".join(result))

chars = ['p', 'q', 'r', 's', 't', 'u']
freqs = [5, 7, 10, 15, 20, 35]
print("Huffman Codes:")
huffman_encode(chars, freqs)


# ==================== OUTPUT ====================
# Huffman Codes:
# s: 00
# t: 01
# r: 100
# p: 1010
# q: 1011
# u: 11
# ================================================
