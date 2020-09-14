from typing import List


class Solution:
    def findSubsequences(self, nums: List[int]) -> List[List[int]]:
        cache = {}
        temp_setA = set()
        for i in range(1, len(nums)):
            cache[i] = []
            temp_set = set()
            for j in range(i + 1, len(nums)):
                if nums[i] < nums[j]:
                    if nums[j] not in temp_set:
                        temp_set.add(nums[j])
                        cache[i].append([nums[i], nums[j]])
                    if j in cache:
                        for item in cache[j]:
                            cache[i].append([nums[i]] + item)
                elif nums[i] == nums[j]:
                    if j in cache:
                        for item in cache[j]:
                            cache[i].append([nums[i]] + item)
                    if nums[i] not in temp_setA:
                        temp_setA.add(nums[i])
                        cache[i].append([nums[i], nums[j]])
                    break


        result = []
        for i in range(0, len(nums) - 1, 1):
            if len(cache[i]) > 0:
                print(cache[i])
                result = result + cache[i]
        return result


s = Solution()
result = s.findSubsequences(nums=[4, 6,6,7,6, 7, 7, 7, 7,7,5,5])
print(result)
