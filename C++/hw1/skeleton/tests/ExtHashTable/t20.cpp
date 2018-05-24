#include <iostream>
#include <fstream>
#include <string>

#include "ExtHashTable.h"
#include "HashTableException.h"
#include <stdlib.h>

using namespace std;

int main(int argc, char *argv[]) {
  if(argc<3) {
    cout << "Insufficient number of arguments!\n";
    cout << "Usage: ./t20 <input_txt_file1> <input_txt_file2>\n" << endl;
    return -1;
  }
    
  try {  
    ifstream in_stream (argv[1]);
    if (!in_stream.is_open()) {
      cout << "Unable to open file " << argv[1];
      return -1;    
    }
    
    ExtHashTable table1, table2;
    
    while ( !in_stream.eof() ) {
      string word;
      in_stream >> word;
      if(table1 += word) 
        cout << "'" << word << "' added!" << endl;
      else
        cout << "'" << word << "' failed!" << endl;
    }
    in_stream.close();
    
    cout << " ###### HASH TABLE 1 ######" << endl;
    cout << table1.print() << endl;
    
    in_stream.open(argv[2]);
    if (!in_stream.is_open()) {
      cout << "Unable to open file " << argv[2];
      return -1;    
    }
    
    while ( !in_stream.eof() ) {
      string word;
      in_stream >> word;
      if(table2 += word) 
        cout << "'" << word << "' added!" << endl;
      else
        cout << "'" << word << "' failed!" << endl;
    }
    in_stream.close();
    
    cout << " ###### HASH TABLE 2 ######" << endl;
    cout << table2.print() << endl;
    
    table2 += table1;
    
    cout << " ###### HASH TABLE 2+=1 ######" << endl;
    cout << table2.print() << endl;
  
  } catch(std::bad_alloc ex) {
    cout << "std::bad_alloc occured!\n";
  } catch(HashTableException ex) {
    cout << "HashTableException occured!\n";
  }
}
