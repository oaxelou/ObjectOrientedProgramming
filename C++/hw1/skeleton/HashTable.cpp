
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

//LAMBIS WORKED HERE
bool HashTable::isAvailable(int pos) const {

	if(isTomb(pos) || isEmpty(pos))
		return true;

	return false;

}

//LAMBIS WORKED HERE
HashTable::HashTable(int capacity) {

	std::bad_alloc ex;

	if(capacity < 0)
		throw std::bad_alloc();

	try{
		table = new string [capacity];
	}catch(...){
		throw ex;
	}

  this->capacity = capacity;
  size = 0;
	for(int i = 0; i < capacity; i++)
		table[i] = "";

}

//LAMBIS WORKED HERE
HashTable::HashTable(const HashTable &ht) {

	std::bad_alloc ex;

	size = ht.size;
	capacity = ht.capacity;

	try{
		table = new string [capacity];
	}catch(...){
		throw ex;
	}


	for(int i = 0; i < capacity; i++){
		table[i] = ht.table[i] ;
	}

}

//LAMBIS WORKED HERE
bool HashTable::contains(const string &s) const {
  //  return contains(s.c_str()); // olympia: tha mporousame na valoume mono auto
	int hashCode;
	int pos;

	hashCode = getHashCode( s.c_str() );

	for(int i = 0; i< capacity; i++){

		pos = (i + hashCode) % capacity;

		if( isEmpty(pos) )
			return false;

		if( table[pos].compare(s) == 0)
			return true;
	}

	return false;


}

//LAMBIS WORKED HERE
bool HashTable::contains(const char *s) const {

	int hashCode = getHashCode(s);
	string s_obj(s);
	int pos;

	for(int i = 0; i< capacity; i++){

		pos = (i + hashCode) % capacity;

		if( isEmpty(pos) )
			return false;

		if( table[pos].compare(s_obj) == 0)
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
// #define __USE_ITERATOR__
#ifdef  __USE_ITERATOR__
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

//LAMBIS WORKED HERE
bool HashTable::add(const string &str) {
  //return HashTable::add(str.c_str());
	int hashCode, pos;
	HashTableException ex;


	hashCode = getHashCode(str.c_str());

	if(contains(str))
		return false;

	for(int i = 0; i< capacity; i++){

		pos = (i + hashCode) % capacity;

		if( isEmpty(pos) || isTomb(pos) ){
			table[pos] = str;
      size++; // olympia
			return true;
		}

	}

  cout << "THROW Exception!\n";
	throw ex;
	return false;  // den xreiazetai

}

//LAMBIS WORKED HERE
bool HashTable::add(const char *s) {

	int hashCode, pos;
	HashTableException ex;


	hashCode = getHashCode(s);

	if(contains(s))
		return false;

	for(int i = 0; i< capacity; i++){

		pos = (i + hashCode) % capacity;

		if( isEmpty(pos) || isTomb(pos) ){
			table[pos].assign(s);
      size++; // olympia
      return true; // olympia
		}
	}

  cout << "THROW Exception!\n";
	throw ex;
	return false; // den xreiazetai

}

//LAMBIS WORKED HERE
bool HashTable::remove(const string &str) {

  int hashCode, pos;


	hashCode = getHashCode(str.c_str());


	for(int i = 0; i< capacity; i++){

		pos = (i + hashCode) % capacity;

		if( isEmpty(pos)){
			break;
		}

		if( table[pos].compare(str) == 0){
			table[pos] = "##tomb##";
      size--; // olympia
      return true; // olympia
		}
	}

	return false;
}

bool HashTable::remove(const char *s) {

	int hashCode, pos;
	string s_obj(s);



	hashCode = getHashCode(s);

	for(int i = 0; i< capacity; i++){

		pos = (i + hashCode) % capacity;

		if( isEmpty(pos)){
			break;
		}

		if( table[pos].compare(s_obj) == 0){
			table[pos] = "##tomb##";
      size--; // olympia
      return true; // olympia
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

HashTable HashTable::operator + (const string &str) const {

	HashTable newHashTable(*this);

  if(!contains(str))
	 newHashTable.add(str);

	return newHashTable;

}

HashTable HashTable::operator + (const char* s) const {

	HashTable newHashTable(*this);

  if(!contains(s))
	 newHashTable.add(s);

	return newHashTable;

}
HashTable HashTable::operator - (const string &str) const {

	HashTable newHashTable(*this);

	newHashTable.remove(str);

	return newHashTable;

}
HashTable HashTable::operator - (const char *s) const {

	HashTable newHashTable(*this);

	newHashTable.remove(s);

	return newHashTable;
}

HashTable HashTable::operator+(const HashTable &t) const {

	int i;

	HashTable newHashTable(capacity + t.getCapacity());

	for(i =0; i < capacity; i++){
		if(!isEmpty(i) && !isTomb(i) )
			newHashTable.add(table[i]);
	}

	for(i =0; i < t.getCapacity() ; i++){
		if(!t.isEmpty(i) && !t.isTomb(i) )
			newHashTable.add(t.table[i]);
	}

	return newHashTable;


}

HashTable& HashTable::operator+=(const HashTable &t) {

	// capacity += t.getCapacity(); //den allazei to capacity

	for( int i =0; i < t.getCapacity() ; i++){
		if(!t.isEmpty(i) && !t.isTomb(i) )
			add(t.table[i]);
	}

	return *this;
}

std::ostream& operator<<(std::ostream &os, HashTable &t) {
	// os << t.print();
  // return os;
  return os.write((char*)t.print().c_str() , (int)t.print().length());
}

HashTable::Iterator HashTable::begin() const {
  Iterator it(this);
  return it;
}

HashTable::Iterator HashTable::end() const {
  Iterator it(this);

  for(int i = 0; i < capacity; i++)
    it++;

  return it;
}
