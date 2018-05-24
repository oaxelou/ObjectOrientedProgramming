#include "HashTable.h"

int main() {
  try {
    HashTable table(-5);
  } catch(std::bad_alloc ex) {
    std::cout << "std::bad_alloc occured!\n";
  }
}
