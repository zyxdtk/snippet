

from collections import deque


class CQueue:
    from collections import deque
    def __init__(self):
        self.q1, self.q2 = deque(), deque()

    def appendTail(self, value: int) -> None:
        self.q1.append(value)


    def deleteHead(self) -> int:
        if len(self.q2) <=0:
            while(len(self.q1)>0):
                self.q2.append(self.q1.pop())
        
        if len(self.q2) > 0:
            return self.q2.pop()
        return 
