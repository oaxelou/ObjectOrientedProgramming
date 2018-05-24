#include <iostream>
#include <fstream>
#include <string>

#include "HashTable.h"
#include "HashTableException.h"
#include <stdlib.h>

using namespace std;

int main(int argc, char *argv[]) {
  string str; char buf[128]; int j=0;
  if(argc<3) {
    cout << "Insufficient number of arguments!\n";
    cout << "Usage: ./t13 <table_size> <input_txt_file>\n" << endl;
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
    
    cout << " ###### HASH TABLE ######" << endl;
    for(HashTable::Iterator it = table.begin(); it!=table.end(); ) {
      sprintf(buf, "%2d. -%s-\n", j++, (*(it++)).c_str());
      str.append(buf);
    }
    sprintf(buf, " --- CAPACITY: %d, SIZE: %d ---\n", table.getCapacity(), table.getSize());
    str.append(buf);
    cout << str;
    
    cout << " ###### HASH TABLE ######" << endl;
    for(HashTable::Iterator it = table.begin(); it!=table.end(); ++it) {
      sprintf(buf, "%2d. -%s-\n", j++, (*it).c_str());
      str.append(buf);
    }
    sprintf(buf, " --- CAPACITY: %d, SIZE: %d ---\n", table.getCapacity(), table.getSize());
    str.append(buf);
    cout << str;
  
  } catch(std::bad_alloc ex) {
    cout << "std::bad_alloc occured!\n";
  } catch(HashTableException ex) {
    cout << "HashTableException occured!\n";
  }
}
