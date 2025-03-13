# Task 1/3 - Stack
The stack is realized by building a single linked list. It pushes and inserts new elements in the front of the list, so the list newer needs to iterated over for simple calls.

Since we only insert in the front of the list, technically our list is saved in reverse order. To print the list in the correct order, a StringBuilder is used for efficient string concatenation and also we can reverse the inserted elements to print the Stack in the correct order.

## V2 Changes

The only noteable inclusion in v2 is, the possibility to push a stack onto another one and clone the stack. To insert the elements in correct order, a recursive method is used.

# Task 2/4 - Queue
The queue is realized by building a single linked list. We keep a reference to the first and last element of the queue. New elements are inserted at the end and elements are removed from the front.

## V2 Changes
There are no noteable changes in v2.

# Extra
## Why use linked list over array?
Even tho it is technically not implemented as stated in our scripts, I still think lists have some advantages over arrays, here is why:

> Linked lists are more flexible than arrays. They can grow and shrink as needed, without the need to specify their size beforehand. This is a big advantage over arrays, which have a fixed size.

> Another advantage is for using it with queues. Since in queues the first as well as the last element can change, operations with list are more efficient since the elements don't need to be moved around like when using arrays.

There are also some disadvantages of using lists over arrays, like:

> Linked lists use more memory than arrays because of the storage used by their pointers. Especially if we only store values of type int, we basically double the memory usage.

> Another disadvantage is that they are not cache friendly. Since the elements are not stored in contiguous memory locations, the CPU cache is not used efficiently.

After a bit of research I came to the conclusion that an implementation of a stack or queue with a linked list should be avoided, however since my implementation is already completed, I will not change it now.

Also the advantage of queues where we do not need to move elements around is not relevant since we can just use some kind of ring-buffering to avoid moving elements around.

In general the implementation of an existing collection type is most likely not needed, since internal implementations in the JRE are already optimized for the use case.

## Additional Classes
### Node
Node is a class, which can be used to realise an implementation of a single linked list.