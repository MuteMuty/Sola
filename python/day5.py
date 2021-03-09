rowmin = 0
rowmax = 127
colmin = 0
colmax = 7
list = []

for line in open("C:\\Users\\Erik\\Downloads\\input.txt"):
    for char in line[:7]:
        if char == "F":
            rowmax = (rowmax + rowmin) // 2
        else:
            rowmin = (rowmax + rowmin + 1) // 2
    # print(rowmin, rowmax)
    for char in line[-3:]:
        if char == "L":
            colmax = (colmax + colmin) // 2
        else:
            colmin = (colmax + colmin + 1) // 2
    # print(colmin, colmax)
    id = rowmin * 8 + colmin
    # print(rowmin, "* 8 +", colmin, "=", id)
    list.append(id)
    rowmin = 0
    rowmax = 127
    colmin = 0
    colmax = 7
print(max(list))
print(sorted(list))
prev = 43
for i in sorted(list):
    print(i)
    if i - prev > 1:
        print("MANKA")
        prev = i
    else:
        prev = prev + 1