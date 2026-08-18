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

## Reject malformed events without changing tasks

**Aim:** Verify that malformed events show specific errors and leave a valid event unchanged.

### Input
```text
event team meeting /from Mon 2pm /to 4pm
event team meeting
event team meeting /from Mon 2pm
event  /from Mon 2pm /to 4pm
event team meeting /from  /to 4pm
event team meeting /from Mon 2pm /to
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
  [E][ ] team meeting (from: Mon 2pm to: 4pm)
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Alfred needs `/from` followed by a start time for an event.
____________________________________________________________
____________________________________________________________
Alfred needs `/to` followed by an end time for an event.
____________________________________________________________
____________________________________________________________
Alfred needs an event description before `/from`.
____________________________________________________________
____________________________________________________________
Alfred needs a start time after `/from`.
____________________________________________________________
____________________________________________________________
Alfred needs an end time after `/to`.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[E][ ] team meeting (from: Mon 2pm to: 4pm)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Reject invalid task numbers without changing task status

**Aim:** Verify that invalid mark and unmark commands show specific errors and do not change the task status.

### Input
```text
mark 1
todo read book
mark
mark two
mark 0
mark 2
mark 1
unmark
unmark two
unmark 4
unmark -1
unmark 1
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
Alfred's task list is empty, so there is nothing to mark.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] read book
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Alfred needs a task number after `mark`.
____________________________________________________________
____________________________________________________________
Alfred needs a whole-number task number after `mark`.
____________________________________________________________
____________________________________________________________
Alfred cannot find task 0. Choose a number from 1 to 1.
____________________________________________________________
____________________________________________________________
Alfred cannot find task 2. Choose a number from 1 to 1.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
____________________________________________________________
Alfred needs a task number after `unmark`.
____________________________________________________________
____________________________________________________________
Alfred needs a whole-number task number after `unmark`.
____________________________________________________________
____________________________________________________________
Alfred cannot find task 4. Choose a number from 1 to 1.
____________________________________________________________
____________________________________________________________
Alfred cannot find task -1. Choose a number from 1 to 1.
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [T][ ] read book
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] read book
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Reject malformed deadlines without changing tasks

**Aim:** Verify that malformed deadlines show specific errors and leave a valid deadline unchanged.

### Input
```text
deadline submit report /by Friday
deadline submit report
deadline  /by Friday
deadline submit report /by
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
  [D][ ] submit report (by: Friday)
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Alfred needs `/by` followed by a due time for a deadline.
____________________________________________________________
____________________________________________________________
Alfred needs a deadline description before `/by`.
____________________________________________________________
____________________________________________________________
Alfred needs a due time after `/by`.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[D][ ] submit report (by: Friday)
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
