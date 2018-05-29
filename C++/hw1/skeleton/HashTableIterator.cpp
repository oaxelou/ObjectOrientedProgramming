/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   C++ hw4 : HashTable, Iterator, Extensible HashTable
*
* HashTableIterator class
*/

#include "HashTable.h"
#include "HashTableException.h"
#include <stdio.h>

using namespace std;

/*
 * constructor: creates an iterator pointing to the first non available
 * element of the table pointed by t
 */
HashTable::Iterator::Iterator(const HashTable *t) {
  ht = t;
  curr = &(t->table[0]);

  string *end = &(ht->table[ht->capacity]);

  while(curr != end && (*curr == "" || *curr == "##tomb##"))
    curr++;
}

/*
 * the copy constuctor
 */
HashTable::Iterator::Iterator(const HashTable::Iterator &it) {
  ht = it.ht;
  curr = it.curr;
}

/*
 * constuctor:
 *  if start == TRUE : points to first non-available melement
 *  if start == FALSE: points to last  element
 */
HashTable::Iterator::Iterator(const HashTable *t, bool start){
  ht = t;

  if(start){
    string *end = &(ht->table[ht->capacity]);

    curr = &(t->table[0]);
    while(curr != end && (*curr == "" || *curr == "##tomb##"))
      curr++;
  }
  else
    curr = &(t->table[t->capacity]);
}

/*
 * assigns it to current object
 */
HashTable::Iterator& HashTable::Iterator::operator=(const HashTable::Iterator &it) {
  ht = it.ht;
  curr = it.curr;

  return *this;
}

/*
 * increases iterator so that it indicates the next non-available element
 */
HashTable::Iterator HashTable::Iterator::operator++() {
  string *end = &(ht->table[ht->capacity]);
  curr++;

  while(curr != end && (*curr == "" || *curr == "##tomb##"))
    curr++;
  return *this;
}

/*
 * increases iterator and returns an iterator with its previous position
 */
HashTable::Iterator HashTable::Iterator::operator++(int a) {
  Iterator it(*this);
  string *end = &(ht->table[ht->capacity]);

  curr++;

  while(curr != end && (*curr == "" || *curr == "##tomb##"))
    curr++;
  return it;
}

/*
 * checks if it and current iterator are the same (returns true or false)
 */
bool HashTable::Iterator::operator==(const HashTable::Iterator &it) const {
  if(ht == it.ht && curr == it.curr)
    return true;
  return false;
}

/*
* checks if it and current iterator are NOT the same (returns false or true)
*/
bool HashTable::Iterator::operator!=(const HashTable::Iterator &it) const {
  return !(*this == it);
}

/*
 * returns the string value of curr
 */
const string& HashTable::Iterator::operator*() {
    return *curr;
}

/*
 * returns the address of curr
 */
const string* HashTable::Iterator::operator->() {
  return curr;
}
