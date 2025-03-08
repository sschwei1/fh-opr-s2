# Task 1 - Stack
The stack is realized by building a single linked list. It pushes and inserts new elements in the front of the list, so the list newer needs to iterated over for simple calls.

# Task 2 - Queue
The queue is implemented by using a double linked list. It saves a pointer to the first and last element which makes queuing and de-queuing very efficient since we have direct access to these elements.

# Task 3 - Stack v2

# Task 4 - Queue v2

# Extra
## Why use linked list over array?
Even tho it is technically not implemented as stated in our scripts, I still think lists have some advantages over arrays, here is why:

> Linked lists are more flexible than arrays. They can grow and shrink as needed, without the need to specify their size beforehand. This is a big advantage over arrays, which have a fixed size.

> Another advantage is for using it with queues. Since in queues the first as well as the last element can change, operations with list are more efficient since the elements don't need to be moved around like when using arrays.

## Node
Node is a class, which can be used to realise a implementation of a list. It includes a pointer to the previous and next element, so it can either be used as single linked list or double linked list.