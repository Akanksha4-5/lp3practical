# ===========================================================
# Sr. No. 1
# DESIGN AND ANALYSIS OF ALGORITHMS
#
# Title: Non-Recursive and Recursive Fibonacci Program
#        with Time and Space Complexity Analysis
#
# CO4
#
# Program: Calculate Fibonacci numbers using recursive and non-recursive methods.
# ===========================================================

steps = 0

# Non-recursive: Time O(n), Space O(1)
def fib_iterative(n):
    global steps
    a, b = 0, 1
    steps += 1                  # initialisation
    for _ in range(n):
        a, b = b, a + b
        steps += 3              # 3 operations per loop
    return a

# Recursive: Time O(2^n), Space O(n) (call stack depth)
def fib_recursive(n):
    global steps
    steps += 1                  # one step per call
    if n <= 1:
        return n
    return fib_recursive(n - 1) + fib_recursive(n - 2)

n = 10
print(f"Fibonacci series (first {n} terms):")

print("Non-recursive:", *[fib_iterative(i) for i in range(n)], "")
steps = 0
fib_iterative(n)
print(f"Steps for F({n}) non-recursive = {steps}  -> Time O(n), Space O(1)")

print("Recursive    :", *[fib_recursive(i) for i in range(n)], "")
steps = 0
fib_recursive(n)
print(f"Steps for F({n}) recursive     = {steps}  -> Time O(2^n), Space O(n)")


# ==================== OUTPUT ====================
# Fibonacci series (first 10 terms):
# Non-recursive: 0 1 1 2 3 5 8 13 21 34 
# Steps for F(10) non-recursive = 31  -> Time O(n), Space O(1)
# Recursive    : 0 1 1 2 3 5 8 13 21 34 
# Steps for F(10) recursive     = 177  -> Time O(2^n), Space O(n)
# ================================================
