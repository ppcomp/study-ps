room = input()
list(room)
number = [0, 0, 0, 0, 0, 0, 0, 0, 0, 0]
for i in range(0,len(room)):
    for j in range(0, 10):
        if(j == int(room[i])):
            number[j] = number[j] + 1
number[6] = number[6] + number[9]
if number[6] % 2 == 1:
    number[6] = number[6] + 1
number[6] = number[6] / 2
temp = 0
for i in range(0, 9):
    if temp < int(number[i]):
        temp = number[i]
print(int(temp))