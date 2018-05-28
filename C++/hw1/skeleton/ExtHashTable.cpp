#include <iostream>
#include <cstdlib>
#include <string.h>

#include "ExtHashTable.h"

ExtHashTable::ExtHashTable( double upper_bound_ratio,
                            double lower_bound_ratio,
                            int size) : HashTable(size){
  this->upper_bound_ratio = upper_bound_ratio;
  this->lower_bound_ratio = lower_bound_ratio;
  // cout << "In constuctor!\n";
}

ExtHashTable::ExtHashTable(const ExtHashTable &t) : HashTable(t) {
  upper_bound_ratio = t.upper_bound_ratio;
  lower_bound_ratio = t.lower_bound_ratio;
}

bool ExtHashTable::add_norehash(const char *s) {
  int hashCode = getHashCode(s);
  int pos, n;
  if(contains(s)) return false;

  for(n = 0; n < capacity; n++){
    pos = (hashCode + n) % capacity;
    if(isAvailable(pos)){
      table[pos] = s;
      // cout << "Added (no_rehash)" << s << ". size = " << size << endl;
      return true;
    }
  }
  // doesn't reach this point
  return false;
}

void ExtHashTable::rehash() {
  if(size == 0){
    return;
  }
  int action = 0; //0:no change, 1:double, 2: half
  if(((double)size) / capacity > upper_bound_ratio){
    // cout << "ratio: " << ((double)size) / capacity << endl;
    action = 1;
  } else if(((double)size) / capacity < lower_bound_ratio){
    action = 2;
  }

  if(action > 0){   // there is a change
    ExtHashTable temp(*this);

    if(action == 1){
      table = new string[2 * capacity];
      capacity *= 2;
    }
    else if(action == 2){
      table = new string[(int)(0.5 * capacity)];
      capacity = (int)(0.5 * capacity);
    }

    for(int i = 0; i < temp.capacity; i++){
      if(!temp.isAvailable(i)){
        // cout << "\t\t\t\tCopied " << temp.table[i] << ". size = " << size << endl;
        add_norehash(temp.table[i].c_str());
      }
    }

    delete[] temp.table;
    cout << "--> Size: " << size << ", New capacity: " << capacity << endl;
  }
}

bool ExtHashTable::add(const string &str) {
  return ExtHashTable::add(str.c_str());
}

bool ExtHashTable::add(const char *s) {
  int hashCode = getHashCode(s);
  int pos, n;
  if(contains(s)) return false;
  // cout << "In add\n";
  for(n = 0; n < capacity; n++){
    pos = (hashCode + n) % capacity;
    if(isAvailable(pos)){
      table[pos] = s;
      size++;
      // cout << "Added " << s << ". size = " << size << endl;
      rehash();
      return true;
    }
  }
  // doesn't reach this point
  return false;
}

bool ExtHashTable::remove(const string &str) {
  return ExtHashTable::remove(str.c_str());
}

bool ExtHashTable::remove(const char *s) {
  if(!contains(s))
    return false;

  int hashCode = getHashCode(s);
  int pos, n;

  for(n = 0; n < capacity; n++){
    pos = (hashCode + n) % capacity;
    if(table[pos] == s){
      table[pos] = "##tomb##";
      size--;
      rehash();
      return true;
    }
  }
  // doesn't reach this point
  return false;
}

ExtHashTable ExtHashTable::operator+(const string &str) const{
  ExtHashTable ht(*this);

  ht.add(str.c_str());
  return ht;
}

ExtHashTable ExtHashTable::operator+(const char* s) const{
  ExtHashTable ht(*this);

  ht.add(s);
  return ht;
}

ExtHashTable ExtHashTable::operator-(const string &str) const{
  ExtHashTable ht(*this);

  ht.remove(str.c_str());
  return ht;
}

ExtHashTable ExtHashTable::operator-(const char *s) const{
  ExtHashTable ht(*this);

  ht.remove(s);
  return ht;
}


bool ExtHashTable::operator += (const string &str) {
  return ExtHashTable::add(str.c_str());
}

bool ExtHashTable::operator += (const char* s) {
  return ExtHashTable::add(s);
}

bool ExtHashTable::operator -= (const string &str) {
  return ExtHashTable::remove(str.c_str());
}

bool ExtHashTable::operator -= (const char *s) {
  return ExtHashTable::remove(s);
}

ExtHashTable ExtHashTable::operator+(const ExtHashTable &table) const {
  ExtHashTable ht(*this);

  for(int i = 0; i < table.capacity; i++){
    if(!table.isAvailable(i)){
      ht.add(table.table[i]);
    }
  }
  return ht;
}

ExtHashTable & ExtHashTable::operator+=(const ExtHashTable &table) {
  for(int i = 0; i < table.capacity; i++){
    if(!table.isAvailable(i)){
      // cout << "going to add " << table.table[i] << endl;
      ExtHashTable::add(table.table[i].c_str());
    }
  }
  return *this;
}

ExtHashTable & ExtHashTable::operator=(const ExtHashTable &t) {
  size = t.size;
  capacity = t.capacity;
  upper_bound_ratio = t.upper_bound_ratio;
  lower_bound_ratio = t.lower_bound_ratio;

  delete[]table;
  table = new string[capacity];
  for(int i = 0; i < capacity; i++){
    table[i] = t.table[i];
  }
  return *this;
}
