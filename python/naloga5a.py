import numpy as np
import matplotlib.pyplot as plt
from matplotlib.widgets import TextBox
import mysql.connector


mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="skrivnost",
    database='vaje'
)
mycursor = mydb.cursor()

fig, ax = plt.subplots()
plt.subplots_adjust(bottom=0.2)
t = np.arange(1, 13, 1)
s = [0, 0, 0, 0, 0, 0, 0, 0, 5, -2.5, 0, 0]
initial_text = "34"
l, = plt.plot(t, s, lw=2)

plt.xlabel('Mesec')
plt.ylabel('Količina blaga - prodano(-)/nabavljeno(+)(m)')
plt.title('GIBANJE ZALOG')


def submit(text):
    ydata = eval(text)
    mycursor.execute("SELECT m Mesec, ifnull((select sum(kolicina) from promet where stevilkablagovneskupine = " + str(ydata) + " and month(datumdokumenta) = m), 0) Skupaj FROM (select 1 as m union    select 2 as m union select 3 as m union select 4 as m union select 5 as m union select 6 as m union select 7 as m union select 8 as m union select 9 as m union select 10 as m union select 11 as m union select 12 as m ) months left join promet on(months.m = month(promet.datumdokumenta)) group by months.m")
    myresult = mycursor.fetchall()
    kolicina = []
    for x in myresult:
        if x[0] == '':
            continue
        kolicina.append(x[1])

    l.set_ydata(kolicina)
    ax.set_ylim(np.min(kolicina), np.max(kolicina))
    plt.draw()


axbox = plt.axes([0.45, 0.02, 0.4, 0.06])
text_box = TextBox(axbox, 'Vnesite številko blagovne skupine:', initial=initial_text)
text_box.on_submit(submit)

plt.show()
