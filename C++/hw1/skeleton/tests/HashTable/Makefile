all: 1 2 3 4 5 6 7 8 9 10 11 12

INC_PATH = ../..
LIB_PATH = ../..
LIB = HashTable
FLAGS = -Wall -g -std=c++11

1:
	g++ ${FLAGS} t1.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t1
2:
	g++ ${FLAGS} t2.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t2
3:
	g++ ${FLAGS} t3.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t3
4:
	g++ ${FLAGS} t4.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t4
5:
	g++ ${FLAGS} t5.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t5
6:
	g++ ${FLAGS} t6.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t6
7:
	g++ ${FLAGS} t7.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t7
8:
	g++ ${FLAGS} t8.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t8
9:
	g++ ${FLAGS} t9.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t9
10:
	g++ ${FLAGS} t10.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t10
11:
	g++ ${FLAGS} t11.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t11
12:
	g++ ${FLAGS} t12.cpp -I ${INC_PATH} -L ${LIB_PATH} -l${LIB} -o t12
	
out:
	./t1 > t1_1.out
	./t2 10 in1_un.txt > t2_1.out
	./t2 16 in1_un.txt > t2_2.out
	./t2 16 in1_db.txt > t2_3.out
	./t3 10 in1_un.txt > t3_1.out
	./t3 16 in1_db.txt > t3_2.out
	./t4 16 in1_db.txt > t4_1.out
	./t5 16 in1_db.txt > t5_1.out
	./t6 16 in1_db.txt > t6_1.out
	./t7 16 in1_db.txt > t7_1.out
	./t8 16 in1_db.txt > t8_1.out
	./t8 10 in1_db.txt > t8_2.out
	./t9 16 in1_db.txt > t9_1.out
	./t10 20 in1_un.txt in2.txt > t10_1.out
	./t11 20 in1_un.txt in2.txt > t11_1.out
	./t11 40 in1_un.txt in2.txt > t11_2.out
	./t12 16 in1_un.txt > t12_1.out
	
diff:
	diff t1_1.out t1_1.std
	diff t2_1.out t2_1.std
	diff t2_2.out t2_2.std
	diff t2_3.out t2_3.std
	diff t3_1.out t3_1.std
	diff t3_2.out t3_2.std
	diff t4_1.out t4_1.std
	diff t5_1.out t5_1.std
	diff t6_1.out t6_1.std
	diff t7_1.out t7_1.std
	diff t8_1.out t8_1.std
	diff t8_2.out t8_2.std
	diff t9_1.out t9_1.std
	diff t10_1.out t10_1.std
	diff t11_1.out t11_1.std
	diff t12_1.out t12_1.std
	
clean:
	rm *.out t1 t2 t3 t4 t5 t6 t7 t8 t9 t10 t11 t12