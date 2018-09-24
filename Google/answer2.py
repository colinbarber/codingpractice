def maxhench(total, sub1, sub2, n): # returns a number of henchmen corresponding
# to the fibonacci sequence
    diff = total - sub1
    if diff >= sub1+sub2:
        return maxhench(diff, sub1+sub2, sub1, n+1)
    else: 
        return n

def minhench(total, n): # returns a number of henchmen corresponding
# to 2^n + 1
    diff = total - 2**n
    
    if diff >= 2**n + 2**(n-1):
        return minhench(diff, n+1) 
    elif diff >= 2**(n*2):
        return minhench(diff, n+1)
    else:
        return n+1
    
def answer(total_lambs):

    return maxhench(total_lambs, 1, 0, 1) - minhench(total_lambs, 0)

lambs = 143
print(answer(lambs))
print(maxhench(lambs, 1, 0, 1))
print(minhench(lambs, 0))