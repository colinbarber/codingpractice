test_set = [10, 15, 3, 7]
k = 25

def is_sum(set, k):
  recip_set = []
  for x in set:
    if x in recip_set:
      return True
    else: recip_set.append(k - x)
  return False

print(is_sum(test_set, k))