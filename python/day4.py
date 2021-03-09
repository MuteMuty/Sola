byr = False
iyr = False
eyr = False
hgt = False
hcl = False
ecl = False
pid = False
cid = False
b = False
valid = 0

for line in open("C:\\Users\\Erik\\Downloads\\input.txt"):
    for word in line.split():
        # print("+"+word.split(":")[0]+"+")
        if word.split(":")[0] == "byr":
            if len(word.split(":")[1]) != 4 or int(word.split(":")[1]) < 1920 or int(word.split(":")[1]) > 2020:
                continue
            byr = True
        elif word.split(":")[0] == "iyr":
            if len(word.split(":")[1]) != 4 or int(word.split(":")[1]) < 2010 or int(word.split(":")[1]) > 2020:
                continue
            iyr = True
        elif word.split(":")[0] == "eyr":
            if len(word.split(":")[1]) != 4 or int(word.split(":")[1]) < 2020 or int(word.split(":")[1]) > 2030:
                continue
            eyr = True
        elif word.split(":")[0] == "hgt":
            if word.split(":")[1][-2:] == "cm":
                if int(word.split(":")[1][:-2]) < 150 or int(word.split(":")[1][:-2]) > 193:
                    continue
            elif word.split(":")[1][-2:] == "in":
                if int(word.split(":")[1][:-2]) < 59 or int(word.split(":")[1][:-2]) > 76:
                    continue
            else:
                continue
            hgt = True
        elif word.split(":")[0] == "hcl":
            if word.split(":")[1][0] == "#":
                if len(word.split(":")[1][1:]) == 6:
                    for char in word.split(":")[1][1:]:
                        if char not in ("0","1","2","3","4","5","6","7","8","9","a","b","c","d","e","f"):
                            b = True
                            break
                else:
                    continue
            else:
                continue
            if b:
                b = False
                continue
            hcl = True
        elif word.split(":")[0] == "ecl":
            if word.split(":")[1] not in ("amb", "blu", "brn", "gry", "grn", "hzl", "oth"):
                continue
            ecl = True
        elif word.split(":")[0] == "pid":
            if len(word.split(":")[1]) == 9:
                for char in word.split(":")[1]:
                    if char not in ("0","1","2","3","4","5","6","7","8","9"):
                        b = True
                        break
            else:
                continue
            if b:
                b = False
                continue
            pid = True
    # print(byr , iyr , eyr , hgt , hcl , ecl , pid)
    if line == "\n":
        # print(byr, iyr, eyr, hgt, hcl, ecl, pid)
        if byr and iyr and eyr and hgt and hcl and ecl and pid:
            valid = valid + 1
        byr = False
        iyr = False
        eyr = False
        hgt = False
        hcl = False
        ecl = False
        pid = False
        cid = False
        # print(valid)
if byr and iyr and eyr and hgt and hcl and ecl and pid:
    valid = valid + 1
print(valid)