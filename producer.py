import stomp
import time
import json
import random
from json import dumps

pubs = []
for l in open('producerDataset.csv', encoding='cp1252'):
	l_sp = l.split(";")
	pubs.append({"id":l_sp[0], "title":l_sp[1], "authors":l_sp[2][:-1]})

random.shuffle(pubs)

conn = stomp.Connection10()
conn.start()
conn.connect()
while 1:
	p = pubs.pop()
	for author in p['authors'].split(","):
		conn.send(author, dumps(p))
		print (author, ":")
	print (dumps(p))
	time.sleep(5)
conn.disconnect()