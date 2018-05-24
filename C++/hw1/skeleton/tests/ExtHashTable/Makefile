all: 15 16 17 18 19 20

INC_PATH = ../..
LIB_PATH = ../..
LIB = HashTable
FLAGS = -Wall -g -std=c++11

15:
	g++ ${FLAGS} t15.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t15
16:
	g++ ${FLAGS} t16.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t16
17:
	g++ ${FLAGS} t17.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t17
18:
	g++ ${FLAGS} t18.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t18
19:
	g++ ${FLAGS} t19.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t19
20:
	g++ ${FLAGS} t20.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t20

out:
	./t15 in2.txt > t15_1.out
	./t16 in2.txt > t16_1.out
	./t17 in2.txt > t17_1.out
	./t18 in2.txt > t18_1.out
	./t19 in1_un.txt in2.txt > t19_1.out
	./t20 in1_un.txt in2.txt > t20_1.out

diff:
	diff t15_1.out t15_1.std
	diff t16_1.out t16_1.std
	diff t17_1.out t17_1.std
	diff t18_1.out t18_1.std
	diff t19_1.out t19_1.std
	diff t20_1.out t20_1.std
	
	
clean:
	rm *.out t15 t16 t17 t18 t19 t20
	