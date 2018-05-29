
/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   C++ hw4 : HashTable, Iterator, Extensible HashTable
*
* HashTable class
*/

#include "HashTable.h"
#include "HashTableException.h"
#include <stdio.h>

using namespace std;

int HashTable::getHashCode(const char *str) {
  int code = 0;
  while(*str != 0) {
    code += *(str++);
  }
  return code;
}

int HashTable::getSize() const { return size; }
int HashTable::getCapacity() const {return capacity; }

bool HashTable::isEmpty(int pos) const {
  if(table[pos].empty())
    return true;
  return false;
}

bool HashTable::isTomb(int pos) const {
  if(table[pos].compare("##tomb##")==0)
    return true;
  return false;
}

/*
* checks a position in the table if it's available
*/
bool HashTable::isAvailable(int pos) const {
	if(isTomb(pos) || isEmpty(pos))
		return true;
	return false;
}

/*
 * the basic constructor
 */
HashTable::HashTable(int capacity) {
	std::bad_alloc ex;

	if(capacity < 0)
		throw ex;

  table = new (nothrow) string [capacity];
  if(table == NULL)
		throw ex;

  this->capacity = capacity;
  size = 0;
	for(int i = 0; i < capacity; i++)
		table[i] = "";
}

/*
 * the copy constructor
 */
HashTable::HashTable(const HashTable &ht) {
	std::bad_alloc ex;

	size = ht.size;
	capacity = ht.capacity;

	table = new (nothrow) string [capacity];
  if(table == NULL)
		throw ex;

	for(int i = 0; i < capacity; i++){
		table[i] = ht.table[i] ;
	}
}

/*
 * search s in hash table
 */
bool HashTable::contains(const string &s) const {
  return contains(s.c_str());
}

bool HashTable::contains(const char *s) const {
	int hashCode = getHashCode(s);
	string s_obj(s);
	int pos;

	for(int i = 0; i< capacity; i++){
		pos = (i + hashCode) % capacity;

		if(isEmpty(pos))
			return false;

		if(table[pos].compare(s_obj) == 0)
			return true;
	}
	return false;
}

string HashTable::print() const {
  string str;
  char buf[128];
/* Remove the following comment if you want
 * to try the iterator version.
 */
#define __USE_ITERATOR__
#ifdef  __USE_ITERATOR__
int j = 0;
  for(HashTable::Iterator it = begin(); it!=end(); it++) {
    sprintf(buf, "%2d. -%s-\n", j++, (*it).c_str());
    str.append(buf);
  }
#else
  for(int i=0, j=0; i<capacity; i++)
    if(!isAvailable(i)) {
      sprintf(buf, "%2d. -%s-\n", j++, table[i].c_str());
      str.append(buf);
    }
#endif
  sprintf(buf, " --- CAPACITY: %d, SIZE: %d ---\n", capacity, size);
  str.append(buf);
  return str;
}

/*
 * adds str to hash table
 */
bool HashTable::add(const string &str) {
  return HashTable::add(str.c_str());
}

bool HashTable::add(const char *s) {
	int pos;
	int hashCode = getHashCode(s);

	if(contains(s))
		return false;

	for(int i = 0; i< capacity; i++){
		pos = (i + hashCode) % capacity;

		if(isAvailable(pos)){
			table[pos].assign(s);
      size++;
      return true;
		}
	}

  cout << "THROW Exception!\n";
  throw HashTableException();
}

/*
 * removes str from hash table
 */
bool HashTable::remove(const string &str) {
  return HashTable::remove(str.c_str());;
}

bool HashTable::remove(const char *s) {
	int hashCode, pos;
	string s_obj(s);

	hashCode = getHashCode(s);

	for(int i = 0; i< capacity; i++){
		pos = (i + hashCode) % capacity;

		if(isEmpty(pos)){
			break;
		}

		if( table[pos].compare(s_obj) == 0){
			table[pos] = "##tomb##";
      size--;
      return true;
		}
	}
	return false;
}

HashTable& HashTable::operator=(const HashTable &ht) {
  capacity = ht.capacity;
  size = ht.size;

  delete[]table;
  table = new string[capacity];
  for(int i = 0; i < capacity; i++){
    table[i] = ht.table[i];
  }
  return *this;
}

bool HashTable::operator += (const string &str) {
  return add(str);
}
bool HashTable::operator += (const char* s) {
  return add(s);
}
bool HashTable::operator -= (const string &str) {
  return remove(str);
}
bool HashTable::operator -= (const char *s) {
  return remove(s);
}

/*
 * adds str to hash table
 */
HashTable HashTable::operator + (const string &str) const {
	HashTable newHashTable(*this);

  if(!contains(str))
	 newHashTable.add(str);

	return newHashTable;

}

/*
 * adds s to hash table
 */
HashTable HashTable::operator + (const char* s) const {
	HashTable newHashTable(*this);

  if(!contains(s))
	 newHashTable.add(s);

	return newHashTable;

}

/*
 * removes str from hash table
 */
HashTable HashTable::operator - (const string &str) const {
	HashTable newHashTable(*this);

	newHashTable.remove(str);

	return newHashTable;
}

/*
 * removes s from hash table
 */
HashTable HashTable::operator - (const char *s) const {
	HashTable newHashTable(*this);

	newHashTable.remove(s);

	return newHashTable;
}

/*
 * creates new hash table (is the sum of this and of t, its capacity as well)
 */
HashTable HashTable::operator+(const HashTable &t) const {
	HashTable newHashTable(capacity + t.getCapacity());

	for(int i = 0; i < capacity; i++){
    if(!t.isAvailable(i))
			newHashTable.add(table[i]);
	}

	for(int i = 0; i < t.getCapacity(); i++){
    if(!t.isAvailable(i))
			newHashTable.add(t.table[i]);
	}

	return newHashTable;
}

/*
 * adds the contents of t to the current hash table (capacity doesn't change)
 */
HashTable& HashTable::operator+=(const HashTable &t) {

	for( int i = 0; i < t.getCapacity(); i++){
		if(!t.isEmpty(i) && !t.isTomb(i) )
			add(t.table[i]);
	}
	return *this;
}

/*
 * with the function ostream.write the print() is inserted into the os stream
 */
std::ostream& operator<<(std::ostream &os, HashTable &t) {
  return os.write((char*)t.print().c_str() , (int)t.print().length());
}

HashTable::Iterator HashTable::begin() const {
  Iterator it(this);
  return it;
}

HashTable::Iterator HashTable::end() const {
  Iterator it(this, false);
  return it;
}
