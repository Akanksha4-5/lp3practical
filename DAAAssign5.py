# ===========================================================
# Sr. No. 5
# DESIGN AND ANALYSIS OF ALGORITHMS
#
# Title: Analysis of Quick Sort Using Deterministic
#        and Randomized Variants
#
# CO4, CO5
#
# Program: Analyze Quick Sort using Deterministic and Randomized variants.
# ===========================================================

import random
import sys
import time

sys.setrecursionlimit(10000)     # sorted input makes deterministic version go ~n levels deep
random.seed(42)                  # fixed seed -> repeatable demo
comparisons = 0

def partition(a, low, high):
    global comparisons
    pivot = a[high]
    i = low - 1
    for j in range(low, high):
        comparisons += 1
        if a[j] <= pivot:
            i += 1
            a[i], a[j] = a[j], a[i]
    a[i + 1], a[high] = a[high], a[i + 1]
    return i + 1

def randomized_partition(a, low, high):
    r = random.randint(low, high)
    a[r], a[high] = a[high], a[r]          # random element becomes pivot
    return partition(a, low, high)

def deterministic_quick_sort(a, low, high):
    if low < high:
        p = partition(a, low, high)
        deterministic_quick_sort(a, low, p - 1)
        deterministic_quick_sort(a, p + 1, high)

def randomized_quick_sort(a, low, high):
    if low < high:
        p = randomized_partition(a, low, high)
        randomized_quick_sort(a, low, p - 1)
        randomized_quick_sort(a, p + 1, high)

def compare(name, data):
    global comparisons
    n = len(data)
    d, r = data[:], data[:]

    comparisons = 0
    t = time.perf_counter()
    deterministic_quick_sort(d, 0, n - 1)
    d_ms = (time.perf_counter() - t) * 1000
    d_comp = comparisons

    comparisons = 0
    t = time.perf_counter()
    randomized_quick_sort(r, 0, n - 1)
    r_ms = (time.perf_counter() - t) * 1000
    r_comp = comparisons

    print(f"{name:<14} | Deterministic: {d_comp:8d} comparisons, {d_ms:7.2f} ms | "
          f"Randomized: {r_comp:8d} comparisons, {r_ms:7.2f} ms")

arr = [25, 12, 18, 7, 30, 15, 3]
d, r = arr[:], arr[:]

print("Original Array:")
print(*arr, "")

deterministic_quick_sort(d, 0, len(d) - 1)
print("Sorted Array using Deterministic QuickSort:")
print(*d, "")

randomized_quick_sort(r, 0, len(r) - 1)
print("Sorted Array using Randomized QuickSort:")
print(*r, "")

# Real analysis: n = 2000 elements
n = 2000
rnd = [random.randint(0, 99999) for _ in range(n)]
srt = list(range(n))
rev = list(range(n, 0, -1))

print(f"\nAnalysis for n = {n}:")
compare("Random input", rnd)
compare("Sorted input", srt)
compare("Reverse input", rev)


# ==================== OUTPUT ====================
# Original Array:
# 25 12 18 7 30 15 3 
# Sorted Array using Deterministic QuickSort:
# 3 7 12 15 18 25 30 
# Sorted Array using Randomized QuickSort:
# 3 7 12 15 18 25 30 
# 
# Analysis for n = 2000:
# Random input   | Deterministic:    24226 comparisons,    3.72 ms | Randomized:    22950 comparisons,    4.22 ms
# Sorted input   | Deterministic:  1999000 comparisons,  198.55 ms | Randomized:    24802 comparisons,    2.48 ms
# Reverse input  | Deterministic:  1999000 comparisons,  137.55 ms | Randomized:    25943 comparisons,    2.60 ms
# ================================================
