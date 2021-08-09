#include <stdio.h>
#include <stdlib.h>

#include "hash_table.h"

#define EXTRA_ESPACE 2

// This is actually a solution for https://leetcode.com/problems/two-sum/

int *twoSum(int *nums, int numsSize, int target, int *returnSize)
{
    int i;
    int *result = malloc(2 * sizeof(int));
    struct HashTable *ht = malloc(sizeof(struct HashTable));
    *returnSize = 2;

    HashTableInit(ht, numsSize * EXTRA_ESPACE);

    for (i = 0; i < numsSize; i++)
    {
        int looking = nums[i];
        if (HashTableContains(ht, looking))
        {
            result[0] = HashTableGet(ht, looking);
            result[1] = i;
            goto out;
        }
        HashTableInsert(ht, target - looking, i);
    }

    free(ht);

out:
    return result;
}

int main()
{
    int *nums;
    int n;
    scanf("%d", &n);
    nums = malloc(n * sizeof(int));

    int expected1, expected2;
    scanf("%d %d", &expected1, &expected2);

    for (int i = 0; i < n; i++)
    {
        int num;
        scanf("%d", &num);
        nums[i] = num;
    }

    int returnsize[] = {0};
    int *result = twoSum(nums, n, nums[expected1] + nums[expected2], returnsize);
    printf("Found %d %d\nExpected %d %d\n", result[0], result[1], expected1, expected2);
    return 0;
}