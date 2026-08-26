# Alfred UI test plan

## Start with no saved task file

**Aim:** Verify that Alfred starts with an empty task list when its relative data directory and file do not exist yet.

### Input
```text
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

    _    _  __             _
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Load saved task state after a restart

**Aim:** Verify that add, mark, unmark, and delete changes are saved, then that every task type, date/time field, and completion status is restored by a fresh Alfred session.

### Setup input
```text
todo keep this task
todo remove this task
deadline return book /by 2019-12-02
event project meeting /from 2/12/2019 1800 /to 2019-12-03
mark 3
unmark 3
mark 4
delete 2
bye
```

### Input
```text
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

    _    _  __             _
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] keep this task
2.[D][ ] return book (by: Dec 02 2019)
3.[E][X] project meeting (from: Dec 02 2019 18:00 to: Dec 03 2019)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Add, update, and list all task types

**Aim:** Verify that ToDos, Deadlines, and Events are stored, formatted, marked, unmarked, and listed correctly.

### Input
```text
todo borrow book
deadline return book /by 2019-10-15
event project meeting /from 2/12/2019 1800 /to 2019-12-03
mark 2
unmark 2
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

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
  [D][ ] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 03 2019)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [D][X] return book (by: Oct 15 2019)
____________________________________________________________
____________________________________________________________
OK, I've marked this task as not done yet:
  [D][ ] return book (by: Oct 15 2019)
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] borrow book
2.[D][ ] return book (by: Oct 15 2019)
3.[E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 03 2019)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Delete a task and renumber the remaining list

**Aim:** Verify that deletion removes the requested task, preserves the other task types and statuses, and rejects an invalid task number.

### Input
```text
todo read book
deadline return book /by 2019-10-15
event project meeting /from 2/12/2019 1800 /to 2019-12-03
mark 1
delete 5
delete 2
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

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
Got it. I've added this task:
  [D][ ] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 03 2019)
Now you have 3 tasks in the list.
____________________________________________________________
____________________________________________________________
Nice! I've marked this task as done:
  [T][X] read book
____________________________________________________________
____________________________________________________________
Alfred cannot find task 5. Choose a number from 1 to 3.
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [D][ ] return book (by: Oct 15 2019)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][X] read book
2.[E][ ] project meeting (from: Dec 02 2019 18:00 to: Dec 03 2019)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Reject malformed events without changing tasks

**Aim:** Verify that malformed events show specific errors and leave a valid event unchanged.

### Input
```text
event team meeting /from 2/12/2019 1800 /to 2019-12-03
event team meeting
event team meeting /from Mon 2pm
event  /from 2/12/2019 1800 /to 2019-12-03
event team meeting /from  /to 2019-12-03
event team meeting /from Mon 2pm /to
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

    _    _  __             _
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] team meeting (from: Dec 02 2019 18:00 to: Dec 03 2019)
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
1.[E][ ] team meeting (from: Dec 02 2019 18:00 to: Dec 03 2019)
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
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

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
deadline submit report /by 2019-10-15
deadline submit report
deadline  /by 2019-10-15
deadline submit report /by
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

    _    _  __             _
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] submit report (by: Oct 15 2019)
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
1.[D][ ] submit report (by: Oct 15 2019)
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
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

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

## Validate delete inputs and the empty-list lifecycle

**Aim:** Verify every delete-number error category, deletion of the final task, and listing an empty task collection.

### Input
```text
delete 1
todo keep me
delete
delete two
delete 0
delete -1
delete 2
list
delete 1
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

    _    _  __             _
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Alfred's task list is empty, so there is nothing to delete.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [T][ ] keep me
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Alfred needs a task number after `delete`.
____________________________________________________________
____________________________________________________________
Alfred needs a whole-number task number after `delete`.
____________________________________________________________
____________________________________________________________
Alfred cannot find task 0. Choose a number from 1 to 1.
____________________________________________________________
____________________________________________________________
Alfred cannot find task -1. Choose a number from 1 to 1.
____________________________________________________________
____________________________________________________________
Alfred cannot find task 2. Choose a number from 1 to 1.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[T][ ] keep me
____________________________________________________________
____________________________________________________________
Noted. I've removed this task:
  [T][ ] keep me
Now you have 0 tasks in the list.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```

## Parse, format, and reject task dates

**Aim:** Verify both supported date formats are displayed consistently and invalid dates do not change tasks.

### Input
```text
deadline do homework /by 2019-10-15
event orientation /from 2/12/2019 1800 /to 2019-12-03
deadline invalid deadline /by no idea :-p
event invalid event /from sometime soon /to 2019-12-03
list
bye
```

### Expected output
```text
____________________________________________________________
      *==/          |     |            \==*
     /XX/           |\__\/|             \XX\
   /XXXX\           |XXXXX|             /XXXX\
 |XXXXXX\_         *XXXXXXX*         \_/XXXXXX|
XXXXXXXXXXXxxxxxxxXXXXXXXXXXXxxxxxxxXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX
|XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX|
 XXXXXX/^^^^"\XXXXXXXXXXXXXXXXXXXXX/^^^^^\XXXXXX
 |XXX|       \XXX/^^\XXXXX/^^\XXX/       |XXX|
   \XX\       \X/    \XXX/    \X/       /XX/
      "\       "      \X/      "       /

    _    _  __             _
   / \  | |/ _|_ __ ___  __| |
  / _ \ | | |_| '__/ _ \/ _` |
 / ___ \| |  _| | |  __/ (_| |
/_/   \_\_|_| |_|  \___|\__,_|

How can I assist from the cave?
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [D][ ] do homework (by: Oct 15 2019)
Now you have 1 tasks in the list.
____________________________________________________________
____________________________________________________________
Got it. I've added this task:
  [E][ ] orientation (from: Dec 02 2019 18:00 to: Dec 03 2019)
Now you have 2 tasks in the list.
____________________________________________________________
____________________________________________________________
Alfred needs a valid date in yyyy-MM-dd or d/M/yyyy HHmm format.
____________________________________________________________
____________________________________________________________
Alfred needs a valid date in yyyy-MM-dd or d/M/yyyy HHmm format.
____________________________________________________________
____________________________________________________________
Here are the tasks in your list:
1.[D][ ] do homework (by: Oct 15 2019)
2.[E][ ] orientation (from: Dec 02 2019 18:00 to: Dec 03 2019)
____________________________________________________________
____________________________________________________________
Bye. Hope to see you again soon sir!
____________________________________________________________
```
