class Solution:
    def minArray(self, numbers: List[int]) -> int:
        a, b = 0, len(numbers)-1
        while(a<b):
                mid = a + (b-a)//2
                if numbers[mid] > numbers[b] :
                    a = mid+1
                elif numbers[mid] < numbers[b]:
                    b = mid
                else:
                    b -= 1

        return numbers[a]