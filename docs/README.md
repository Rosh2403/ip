# Roshy - Task Manager Chatbot

Roshy is a simple command-line chatbot that helps you manage your tasks. It supports todos, deadlines, and events — and saves your tasks automatically so they persist across sessions.

---

## Quick Start

1. Ensure you have Java 17 installed
2. Download the latest `roshy.jar` from the releases page
3. Run the app:
```
java -jar roshy.jar
```
4. Type commands to manage your tasks!

---

## Features

### Add a Todo
A simple task with no date.
```
todo <description>
```
Example:
```
todo read book
```
Output:
```
[T][ ] read book
```

---

### Add a Deadline
A task with a due date.
```
deadline <description> /by <date>
```
Example:
```
deadline return book /by June 6th
```
Output:
```
[D][ ] return book (by: June 6th)
```

---

### Add an Event
A task with a start and end time.
```
event <description> /from <start> /to <end>
```
Example:
```
event project meeting /from monday 2pm /to 4pm
```
Output:
```
[E][ ] project meeting (from: monday 2pm to: 4pm)
```

---

### List All Tasks
```
list
```
Shows all tasks with their index, type, and status.

---

### Mark a Task as Done
```
mark <task number>
```
Example:
```
mark 2
```
Output:
```
Nice I've marked this as done!
[D][X] return book (by: June 6th)
```

---

### Delete a Task
```
delete <task number>
```
Example:
```
delete 3
```
Output:
```
Noted. I have removed this task
[E][ ] project meeting (from: monday 2pm to: 4pm)
Now you have 2 tasks in the list
```

---

### Find Tasks by Keyword
Search for tasks containing a keyword in their description.
```
find <keyword>
```
Example:
```
find book
```
Output:
```
Here are the matching tasks in your list:
1: [T][ ] read book
2: [D][X] return book (by: June 6th)
```

---

### Exit
```
bye
```

---

## Task Types Summary

| Symbol | Type     |
|--------|----------|
| `[T]`  | Todo     |
| `[D]`  | Deadline |
| `[E]`  | Event    |

| Symbol | Status      |
|--------|-------------|
| `[ ]`  | Not done    |
| `[X]`  | Done        |

---

## Data Storage
Tasks are automatically saved to `data/roshy.txt` and reloaded every time you start the app. You do not need to do anything to save your tasks.
