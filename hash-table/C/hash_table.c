#include <stdint.h>
#include <stdlib.h>
#include <string.h>

#include "hash_table.h"

#define M 31

struct HashTableEntry
{
    int key;
    int value;
};

struct HashTable
{
    struct HashTableEntry **table;
    uint32_t size;
};

static inline void *_zalloc(uint32_t size)
{
    void *m = malloc(size * sizeof(struct HashTableEntry *));
    memset(m, 0, size * sizeof(struct HashTableEntry *));
    return m;
}

uint32_t _hashFunction(int key)
{
    uint32_t i, h = 7;

    for (i = 0; i < sizeof(int); i++)
    {
        h = (M * h) + (key & 0xFF);
        key = key >> 0x8;
    }

    return h;
}

int _checkAtIndex(struct HashTable *ht, uint32_t index, int key)
{
    return ht->table[index] && ht->table[index]->key != key;
}

void _allocTable(struct HashTable *ht, uint32_t size)
{
    ht->table = _zalloc(size * sizeof(struct HashTableEntry *));
    ht->size = size;
}

void _freeTableEntries(struct HashTable *ht)
{
    uint32_t i, size = ht->size;
    for (i = 0; i < size; i++)
    {
        if (ht->table[i])
            free(ht->table[i]);
    }
}
struct HashTable *HashTableAlloc(uint32_t size)
{
    struct HashTable *ht;

    ht = malloc(sizeof(struct HashTable));
    if (ht)
        _allocTable(ht, size);

    return ht;
}

// Expects that ht is valid
void HashTableFree(struct HashTable *ht)
{
    if (ht->table)
    {
        _freeTableEntries(ht);
        free(ht->table);
    }
    free(ht);
}

void HashTableInsert(struct HashTable *ht, int key, int value)
{
    uint32_t index = _hashFunction(key) % ht->size;
    while (_checkAtIndex(ht, index, key))
    {
        index = (index + 1) % ht->size;
    }

    if (!ht->table[index])
        ht->table[index] = malloc(sizeof(struct HashTableEntry));

    ht->table[index]->key = key;
    ht->table[index]->value = value;
}

int HashTableGet(struct HashTable *ht, int key)
{
    uint32_t index = _hashFunction(key) % ht->size;
    while (_checkAtIndex(ht, index, key))
        index = (index + 1) % ht->size;

    if (!ht->table[index])
    {
        return 0;
    }

    return ht->table[index]->value;
}

int HashTableContains(struct HashTable *ht, int key)
{
    uint32_t index = _hashFunction(key) % ht->size;
    while (_checkAtIndex(ht, index, key))
        index = (index + 1) % ht->size;

    return ht->table[index] && ht->table[index]->key == key;
}
