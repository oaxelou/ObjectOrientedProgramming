t1: Ελέγχει την εισαγωγή αρνητικής τιμής στον κατασκευαστή HashTable(int)
t2: Ελέγχει τη μέθοδο HashTable::add
t3: Ελέγχει τον τελεστή HashTable::operator+=(string&)
t4: Ελέγχει τη μέθοδο HashTable::remove. 
    Προϋπoθέσεις: HashTable::add(string &)
t5: Ελέγχει τον τελεστή HashTable::operator-=(string&). 
    Προϋπoθέσεις: HashTable::add(string &)
t6: Ελέγχει τον copy constructor.
t7: Ελέγχει τον τελεστή HashTable::operator=(HashTable &t)
t8: Ελέγχει τον τελεστή HashTable::operator+(string&)
t9: Ελέγχει τον τελεστή HashTable::operator-(string&)
    Προϋπoθέσεις: HashTable::operator+(string&)
t10:Ελέγχει τον τελεστή HashTable::operator+(HashTable&)
    Προϋπoθέσεις: HashTable::operator+=(string&)
t11:Ελέγχει τον τελεστή HashTable::operator+=(HashTable&)
    Προϋπoθέσεις: HashTable::operator+=(string&)
t12:Ελέγχει τον τελεστή std::ostream& operator<<(std::ostream &stream, HashTable &t)
