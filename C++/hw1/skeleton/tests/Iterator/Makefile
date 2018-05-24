all: 14 13

INC_PATH = ../../
LIB_PATH = ../../
LIB = HashTable
FLAGS = -Wall -g -std=c++11

13:
	g++ ${FLAGS} t13.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t13
14:
	g++ ${FLAGS} t14.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t14

out:
	./t13 20 in1_un.txt > t13_1.out
	./t14 20 in1_un.txt > t14_1.out
	
diff:
	diff t13_1.out t13_1.std
	diff t14_1.out t14_1.std
	
clean:
	rm *.out t13 t14