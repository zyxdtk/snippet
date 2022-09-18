from collections import Counter
import numpy as np
x = np.random.random_integers(1, 10, 100)
count = Counter(x)
print(count)
print(count.elements)

for i in count.elements():
    print(i)