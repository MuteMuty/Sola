tab = set()
st = 0

for line in open("C:\\Users\\Erik\\Downloads\\input.txt"):
    print(tab, len(tab))
    if line == "\n":
        print(st,"add",len(tab))
        st = st + len(tab)
        print(st)
        tab.clear()
        continue
    for char in line.strip():
        # print(char)
        tab.add(char)
print(st)

with open("C:\\Users\\Erik\\Downloads\\input.txt") as f:
    l = f.read().split("\n\n")
print(sum(len(set.union(*(set(x) for x in group.splitlines()))) for group in l))
print(sum(len(set.intersection(*(set(x) for x in group.splitlines()))) for group in l))