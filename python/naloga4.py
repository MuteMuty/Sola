import mysql.connector

mydb = mysql.connector.connect(
    host="localhost",
    user="root",
    passwd="skrivnost",
    database='vaje'
)
# autocommit je nastavljen na true
mycursor = mydb.cursor()

mycursor.execute("DROP TABLE IF EXISTS `AktivnostPoste`")
mycursor.execute("CREATE TABLE `AktivnostPoste` ( `PostnaStevilka` varchar(7) DEFAULT NULL, `SkupnaKolicina` double DEFAULT NULL, `StevilkBlagovnihSkupin` mediumint(9) DEFAULT NULL ) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4")


mycursor.execute("SELECT postnastevilka FROM poste")
rownum = mycursor.rowcount

myresult = mycursor.fetchall()

for x in myresult:
    if x[0] == '' and 'NN' not in str(myresult):
        mycursor.execute("insert into poste(postnastevilka) values(\"NN\")")
        continue
    mycursor.execute("select po.postnastevilka, sum(p.kolicina), count(distinct p.StevilkaBlagovneSkupine) from podjetja po join promet p using(stevilkapodjetja) where po.PostnaStevilka = '" + x[0] + "' group by po.postnastevilka")
    temp = mycursor.fetchall()
    if str(temp) != '[]':
        if temp[0][0] == '':
            mycursor.execute("INSERT INTO `AktivnostPoste`(`SkupnaKolicina`, `StevilkBlagovnihSkupin`) VALUES (" + str(temp[0][1]) + ", " + str(temp[0][2]) + ")")
            mydb.commit()
            continue
        mycursor.execute("INSERT INTO `AktivnostPoste` VALUES (\'" + temp[0][0] + "\', " + str(temp[0][1]) + ", " + str(temp[0][2]) + ")")
        mydb.commit()
