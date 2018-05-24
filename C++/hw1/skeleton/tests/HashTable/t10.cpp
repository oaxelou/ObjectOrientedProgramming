#include <iostream>
#include <fstream>
#include <string>

#include "HashTable.h"
#include "HashTableException.h"
#include <stdlib.h>

using namespace std;

int main(int argc, char *argv[]) {
  if(argc<3) {
    cout << "Insufficient number of arguments!\n";
    cout << "Usage: ./t10 <table_size> <input_txt_file1> <input_txt_file2>\n" << endl;
    return -1;
  }
    
  try {
    int table_size = atoi(argv[1]);
  
    ifstream in_stream (argv[2]);
    if (!in_stream.is_open()) {
      cout << "Unable to open file " << argv[2];
      return -1;    
    }
    
    HashTable table1(table_size), table2(table_size), table3;
    
    while ( !in_stream.eof() ) {
      string word;
      in_stream >> word;
      table1 += word;
    }
    in_stream.close();
    
    cout << " ###### HASH TABLE 1 ######" << endl;
    cout << table1.print() << endl;    
    
    in_stream.open(argv[3]);
    if (!in_stream.is_open()) {
      cout << "Unable to open file " << argv[2];
      return -1;    
    }
    
    while ( !in_stream.eof() ) {
      string word;
      in_stream >> word;
      table2 += word;
    }
    in_stream.close();
    
    cout << " ###### HASH TABLE 2 ######" << endl;
    cout << table2.print() << endl;
    
    cout << " ###### HASH TABLE 3 ######" << endl;
    table3 = table1 + table2;
    cout << table3.print() << endl;
  
  } catch(std::bad_alloc ex) {
    cout << "std::bad_alloc occured!\n";
  } catch(HashTableException ex) {
    cout << "HashTableException occured!\n";
  } catch(...) {
    cout << "An exception of unknown type occured!" << endl;
  }
}
