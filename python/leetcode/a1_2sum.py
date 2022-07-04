class Solution:
    def twoSum(self, nums: List[int], target: int) -> List[int]:
        cache = {nums[i]:i for i in range(len(nums))}
        for i in range(len(nums)):
            other = target-nums[i]
            if other in cache and i != cache[other]:
                return [i, cache[other]]
        return []

