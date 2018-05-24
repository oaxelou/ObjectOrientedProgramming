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
    cout << "Usage: ./t4 <table_size> <input_txt_file>\n" << endl;
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
      if(table.add(word)) 
        cout << "'" << word << "' added!" << endl;
      else
        cout << "'" << word << "' failed!" << endl;
    }
    
    cout << " ###### HASH TABLE ######" << endl;
    cout << table.print() << endl;
    in_stream.close();
    
    in_stream.open(argv[2]);
    if (!in_stream.is_open()) {
      cout << "Unable to open file " << argv[2];
      return -1;    
    }
    
    cout << " ###### START REMOVALS ######" << endl;
    
    while ( !in_stream.eof() ) {
      string word;
      in_stream >> word;
      if(table.remove(word)) 
        cout << "'" << word << "' removed!" << endl;
      else
        cout << "'" << word << "' failed!" << endl;
    }
    
    cout << " ###### HASH TABLE ######" << endl;
    cout << table.print() << endl;
    in_stream.close();
  
  } catch(std::bad_alloc ex) {
    cout << "std::bad_alloc occured!\n";
  } catch(HashTableException ex) {
    cout << "HashTableException occured!\n";
  } catch(...) {
    cout << "An exception of unknown type occured!" << endl;
  }
}
