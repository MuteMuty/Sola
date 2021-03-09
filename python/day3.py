
i = 0
tree = 0
j = 0
for line in open("C:\\Users\\Erik\\Downloads\\input.txt"):
    if j % 2 == 1:
        j = j + 1
        continue
    j = j + 1
    if i >= (len(line) - 1):
        i = i % (len(line) - 1)
    if line[i] == "#":
        tree = tree + 1
    i = i + 1
print(tree)