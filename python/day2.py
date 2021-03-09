valid = 0
boo = False

for line in open("C:\\Users\\Erik\\Downloads\\input.txt"):
    fields = line.split(" ")
    mini = int(fields[0].split("-")[0])
    maxi = int(fields[0].split("-")[1])
    lett = fields[1].split(":")[0]
    strin = fields[2].split("\\")[0]
    #print(min, max, lett, strin)
    for element in range(0, len(strin)):
        if element + 1 == mini or element + 1 == maxi:
            if strin[element] == lett:
                boo = not boo
    if boo:
        valid = valid + 1
        boo = False
print(valid)
