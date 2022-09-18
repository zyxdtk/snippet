import queue
from collections import deque

q = queue.Queue
dq = deque()
dq.append(1)
dq.append(2)
dq.appendleft(3)
print(dq.pop())
print(dq.popleft())