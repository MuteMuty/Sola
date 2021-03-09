import signal
from datetime import datetime
import ssl

signal.signal(signal.SIGINT, signal.SIG_DFL)
import socket
import struct
import threading

PORT = 1234
HEADER_LENGTH = 2


def setup_SSL_context(self):
  #uporabi samo TLS, ne SSL
  context = ssl.SSLContext(ssl.PROTOCOL_TLSv1_2)
  # certifikat je obvezen
  context.verify_mode = ssl.CERT_REQUIRED
  #nalozi svoje certifikate
  context.load_cert_chain(certfile="server.pem", keyfile="serverkey.pem")
  # nalozi certifikate CAjev, ki jim zaupas
  # (samopodp. cert. = svoja CA!)
  context.load_verify_locations('clients.pem')
  # nastavi SSL CipherSuites (nacin kriptiranja)
  context.set_ciphers('ECDHE-RSA-AES128-GCM-SHA256')
  return context

my_ssl_ctx = self.setup_SSL_context()
ss = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

def receive_fixed_length_msg(sock, msglen):
    message = b''
    while len(message) < msglen:
        chunk = sock.recv(msglen - len(message))  # preberi nekaj bajtov
        if chunk == b'':
            raise RuntimeError("socket connection broken")
        message = message + chunk  # pripni prebrane bajte sporocilu

    return message


def receive_message(sock):
    header = receive_fixed_length_msg(sock,
                                      HEADER_LENGTH)  # preberi glavo sporocila (v prvih 2 bytih je dolzina sporocila)
    message_length = struct.unpack("!H", header)[0]  # pretvori dolzino sporocila v int

    message = None
    if message_length > 0:  # ce je vse OK
        message = receive_fixed_length_msg(sock, message_length)  # preberi sporocilo
        message = message.decode("utf-8")

    return message


def send_message(sock, message):
    encoded_message = message.encode("utf-8")  # pretvori sporocilo v niz bajtov, uporabi UTF-8 kodno tabelo

    # ustvari glavo v prvih 2 bytih je dolzina sporocila (HEADER_LENGTH)
    # metoda pack "!H" : !=network byte order, H=unsigned short
    header = struct.pack("!H", len(encoded_message))

    message = header + encoded_message  # najprj posljemo dolzino sporocilo, slee nato sporocilo samo
    sock.sendall(message)


# funkcija za komunikacijo z odjemalcem (tece v loceni niti za vsakega odjemalca)
def client_thread(client_sock, client_addr):
    global clients
    global imena

    print("[system] connected with " + client_addr[0] + ":" + str(client_addr[1]))
    print("[system] we now have " + str(len(clients)) + " clients")

    try:

        while True:  # neskoncna zanka
            msg_received = receive_message(client_sock)

            if not msg_received:  # ce ne obstaja sporocilo
                break

            if msg_received.split(" ")[-1] not in imena:
                imena[msg_received.split(" ")[-1]] = client_addr[1]

            current_time = datetime.now().strftime('%H:%M:%S')
            print("[RKchat][" + current_time + "][" + str(client_addr[1]) + ":" + msg_received.split(" ")[-1] + "]: " + ' '.join(msg_received.split(" ")[:-1]))

            if msg_received.split(" ")[0][0] == "/":
                if msg_received.split(" ")[0][1:] in imena.keys():
                    for ime, stevilka in imena.items():
                        if str(ime).upper() == msg_received.split(" ")[0][1:].upper() or str(ime).upper() == msg_received.split(" ")[-1].upper():
                            for client in clients:
                                if stevilka == client.getpeername()[1]:
                                    send_message(client, ' '.join(msg_received.split(" ")[1:]).upper() + "_privat")
                                    break
                elif msg_received.split(" ")[0][1:] == "help":
                    poss = ""
                    vej = " "
                    for key in imena.keys():
                        poss += vej + key
                        vej = ", "
                    for ime, stevilka in imena.items():
                        if ime == msg_received.split(" ")[-1]:
                            for client in clients:
                                if stevilka == client.getpeername()[1]:
                                    send_message(client, "Za privat sporocilo napisi: /up_ime sporocilo. Povezani uporabniki so:" + poss + " system")
                                    break
                            break
                else:
                    for ime, stevilka in imena.items():
                        if ime == msg_received.split(" ")[-1]:
                            for client in clients:
                                if stevilka == client.getpeername()[1]:
                                    send_message(client, "uporabnik " + msg_received.split(" ")[0][1:] + " ne obstja system")
                                    break
                            break
            else:
                for client in clients:
                    send_message(client, msg_received.upper())
    except:
        # tule bi lahko bolj elegantno reagirali, npr. na posamezne izjeme. Trenutno kar pozremo izjemo
        pass

    # prisli smo iz neskoncne zanke
    with clients_lock:
        clients.remove(client_sock)
        nam = ""
        for ime, stevilka in imena.items():
            if stevilka == client_sock.getpeername()[1]:
                nam = ime
    del imena[nam]
    print("[system] we now have " + str(len(clients)) + " clients")
    client_sock.close()


# kreiraj socket
server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
server_socket.bind(("localhost", PORT))
server_socket.listen(1)

# cakaj na nove odjemalce
print("[system] listening ...")
clients = set()
imena = dict()
clients_lock = threading.Lock()
while True:
    try:
        # pocakaj na novo povezavo - blokirajoc klic
        client_sock, client_addr = server_socket.accept()
        with clients_lock:
            clients.add(client_sock)

        thread = threading.Thread(target=client_thread, args=(client_sock, client_addr))
        thread.daemon = True
        thread.start()

    except KeyboardInterrupt:
        break

print("[system] closing server socket ...")
server_socket.close()
