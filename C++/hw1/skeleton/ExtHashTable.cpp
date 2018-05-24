#include <iostream>
#include <cstdlib>
#include <string.h>

#include "ExtHashTable.h"


ExtHashTable::ExtHashTable( double upper_bound_ratio, 
                            double lower_bound_ratio, 
                            int size) {
  
}
  
ExtHashTable::ExtHashTable(const ExtHashTable &t) : HashTable(t) {
  
}

void ExtHashTable::rehash() {
  
}

bool ExtHashTable::add(const string &str) {

}

bool ExtHashTable::add(const char *s) {

}

bool ExtHashTable::remove(const string &str) {

}

bool ExtHashTable::remove(const char *s) {

}


bool ExtHashTable::operator += (const string &str) { 
  
}
bool ExtHashTable::operator += (const char* s) { 
  
}
bool ExtHashTable::operator -= (const string &str) {
  
}
bool ExtHashTable::operator -= (const char *s) {
  
}

ExtHashTable ExtHashTable::operator+(const ExtHashTable &table) const {
  
}

ExtHashTable & ExtHashTable::operator+=(const ExtHashTable &table) {
  
}

ExtHashTable & ExtHashTable::operator=(const ExtHashTable &t) {
  
}