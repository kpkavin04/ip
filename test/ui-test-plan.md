# Alfred UI test plan

## Add, update, and list all task types

**Aim:** Verify that ToDos, Deadlines, and Events are stored, formatted, marked, unmarked, and listed correctly.

### Input
```text
todo borrow book
deadline return book /by Sunday
event project meeting /from Mon 2pm /to 4pm
mark 2
unmark 2
list
bye
```

### Expected output
```text
____________________________________________________________
    _    _  __             _ 
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] borrow book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] return book (by: Sunday)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Mon 2pm to: 4pm)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [D][ ] return book (by: Sunday)
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Sunday)
3.[E][ ] project meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Reject empty and unknown commands without changing tasks

**Aim:** Verify that invalid commands show Alfred-themed errors and do not change the existing task list.

### Input
```text
todo read book
todo
blah
list
bye
```

### Expected output
```text
____________________________________________________________
    _    _  __             _ 
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Alfred cannot add a to-do without a mission description.
____________________________________________________________
____________________________________________________________
Alfred does not recognize that command. Please try again.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```
