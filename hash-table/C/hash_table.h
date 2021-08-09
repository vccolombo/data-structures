#ifndef HASH_TABLE_H
#define HASH_TABLE_H

#include <stdint.h>

struct HashTableEntry;

struct HashTable
{
    struct HashTableEntry **table;
    uint32_t size;
};

void HashTableInit(struct HashTable *ht, uint32_t size);

void HashTableFree(struct HashTable *ht);

void HashTableInsert(struct HashTable *ht, int key, int value);

int HashTableGet(struct HashTable *ht, int key);

int HashTableContains(struct HashTable *ht, int key);

#endif