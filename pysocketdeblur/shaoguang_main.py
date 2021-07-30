from app import predict
import time
import socket
from threading import Thread


# import server

def tcp_link(sock, addr):
    print('Accept new connection from %s' % (addr,))
    # sock.send(b'Welcome!')
    while True:
        data = sock.recv(1024)
        time.sleep(1)
        if not data or data.decode('utf-8') == 'exit':
            print(data.decode('utf-8'))
            break
        print("server is processing...")

        img_name = data.decode('utf-8')
        print(img_name)

        predict('D:/predeblur/' + img_name)

        msg = 'http://yelsonsg.nat300.top/predeblur/Deblurring/' + img_name[:img_name.find('.')] + '_restoration' + img_name[img_name.find('.'):]
        sock.send(msg.encode('utf-8'))
        print("发送成功")
    sock.close()
    print('Completed And Connection from %s is closed.' % (addr,))


if __name__ == '__main__':
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind(('localhost', 8087,))
    server_socket.listen(10)
    print('Waiting for connection to processing...')
    while True:
        sock, addr = server_socket.accept()
        thr = Thread(target=tcp_link, args=(sock, addr))
        thr.start()
