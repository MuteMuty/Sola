#C:\Users\Erik\Downloads
for x in open("C:\\Users\\Erik\\Downloads\\input.txt"):
  for y in open("C:\\Users\\Erik\\Downloads\\input.txt"):
    for z in open("C:\\Users\\Erik\\Downloads\\input.txt"):
      if int(x)+int(y)+int(z) == 2020:
        print(int(x)*int(y)*int(z))
