class Solution:
    def exist(self, board: List[List[str]], word: str) -> bool:
        if len(board) == 0:
            return False
        n, m, l = len(board), len(board[0]), len(word)-1

        def dfs(board, i, j, word, flagM, rank)->bool:
            if i<0 or i>=n or j<0 or j>=m or flagM[i][j] or board[i][j] != word[rank]:
                return False
            if rank == l:
                return True
            flagM[i][j] = True
            if dfs(board, i+1, j,  word,flagM, rank+1) or dfs(board, i-1, j,  word,flagM, rank+1) or dfs(board, i, j+1,  word,flagM, rank+1) or dfs(board, i, j-1,  word,flagM, rank+1):
                return True
            flagM[i][j] = False
            return False

        flagM = [[False]*m for i in range(n)]
        for i in range(n):
            for j in range(m):
                isExist = dfs(board, i, j, word, flagM, 0)
                if isExist:
                    return True
        
        return False

