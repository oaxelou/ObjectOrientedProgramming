
#include "HashTable.h"
#include "HashTableException.h"
#include <stdio.h>

using namespace std;

HashTable::Iterator::Iterator(const HashTable *t) {
  ht = t;
  curr = &(t->table[0]);
}

HashTable::Iterator::Iterator(const HashTable::Iterator &it) {
  ht = it.ht;
  curr = it.curr;
}

HashTable::Iterator& HashTable::Iterator::operator=(const HashTable::Iterator &it) {
  curr = it.curr;
  ht = it.ht;

  return *this;
}

HashTable::Iterator HashTable::Iterator::operator++() {
  curr++;
  return *this;
}

HashTable::Iterator HashTable::Iterator::operator++(int a) {
  Iterator it(*this);
  curr++;
  return it;
}

bool HashTable::Iterator::operator==(const HashTable::Iterator &it) const {
  if(ht == it.ht && curr == it.curr)
    return true;
  return false;
}

bool HashTable::Iterator::operator!=(const HashTable::Iterator &it) const {
  return !(*this == it);
}

const string& HashTable::Iterator::operator*() {
  return *curr;
}

const string* HashTable::Iterator::operator->() {
  return curr;
}
