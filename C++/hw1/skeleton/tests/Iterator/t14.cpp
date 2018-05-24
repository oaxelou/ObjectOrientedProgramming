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
    cout << "Usage: ./t14 <table_size> <input_txt_file>\n" << endl;
    return -1;
  }
    
  try {
    int table_size = atoi(argv[1]);
  
    ifstream in_stream (argv[2]);
    if (!in_stream.is_open()) {
      cout << "Unable to open file " << argv[2];
      return -1;    
    }
    
    HashTable table(table_size);
    
    while ( !in_stream.eof() ) {
      string word;
      in_stream >> word;
      table.add(word);
    }
    in_stream.close();
        
    HashTable::Iterator it1 = table.begin(), it2=table.end();
    it1++;
    it2 = it1;
    if(it2==it1)
      cout << "it1 == it2\n";
    else
      cout << "it1 != it2\n";
    if(it2==it1++)
      cout << "it1++ == it2\n";
    else
      cout << "it1++ != it2\n";
    if(++it2==it1)
      cout << "it1 == ++it2\n";
    else
      cout << "it1 != ++it2\n";
  
  } catch(std::bad_alloc ex) {
    cout << "std::bad_alloc occured!\n";
  } catch(HashTableException ex) {
    cout << "HashTableException occured!\n";
  }
}
