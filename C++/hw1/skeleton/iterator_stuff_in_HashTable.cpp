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
