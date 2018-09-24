import numpy as np
import pandas as pd
pd.set_option('display.expand_frame_repr', False)
pd.set_option('display.max_rows', 5000)

order_id = 0
shop_id = 1
user_id = 2
order_amount = 3
total_items = 4
payment_method = 5
created_at = 6

df = pd.read_csv('data.csv', sep=',',header=None)

orders = np.copy(df[[order_amount,total_items]].copy().iloc[1:].astype(int))

total = 0
count = 0

for row in orders:
  total += row[0]
  count += 1

naive_aov = total/count
print(naive_aov)

total = 0
count = 0

for row in orders:
  if row[0]/row[1] < 1000:
    total += row[0]
    count += 1

real_aov = total/count
print(real_aov)

total = 0
count = 0

for row in orders:
  if row[0] < 10000:
    total += row[0]
    count += 1

small_aov = total/count
print(small_aov)


#calculate per price item

#amounts = df.iloc[1:,order_amount].astype(int)
#items = df.iloc[1:,total_items].astype(int)

#avg = np.divide(amounts, items)

#for row in avg:
  #if row < 500:
   # total += row
    #count += 1

#avg_item = total / count


