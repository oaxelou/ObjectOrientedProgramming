
all: HashTable Iterator ExtHashTable archive

HashTable:
	g++ -Wall -g -std=c++11 HashTable.cpp -c 

Iterator:
	g++ -Wall -g -std=c++11 HashTableIterator.cpp -c 
	
ExtHashTable:
	g++ -Wall -g -std=c++11 ExtHashTable.cpp -c 

archive: HashTable Iterator ExtHashTable
	ar cr libHashTable.a HashTable.o HashTableIterator.o ExtHashTable.o
