# Mini Hospital Emergency Management System

## Project Description
A beginner-friendly Java console application for managing hospital patients, emergency treatment, and visit records. It demonstrates four manually implemented data structures without using Java collection classes for the required structures.

## Objectives
- Apply object-oriented programming in Java.
- Implement and explain a BST, queue, stack, and singly linked list.
- Validate input and handle empty structures safely.
- Demonstrate a realistic emergency-care workflow.

## Data Structures and Features
- **PatientBST:** stores patients by numeric Patient ID; supports insert, search, delete, and ascending in-order display.
- **EmergencyQueue:** linked FIFO queue with front and rear references.
- **TreatmentStack:** linked LIFO stack for completed treatments.
- **VisitHistory:** a singly linked list owned by each Patient; supports add, search, remove, and display.
- Menu options cover registration, treatment, queue operations, treatment history, and visit history.

No `TreeMap`, `TreeSet`, `Queue`, `LinkedList`, or `Stack` is used to implement these structures.

## Project Structure
```text
MiniHospitalSystem/
├── src/
│   ├── Main.java
│   ├── Patient.java
│   ├── PatientNode.java
│   ├── PatientBST.java
│   ├── QueueNode.java
│   ├── EmergencyQueue.java
│   ├── TreatmentRecord.java
│   ├── TreatmentNode.java
│   ├── TreatmentStack.java
│   ├── Visit.java
│   ├── VisitNode.java
│   └── VisitHistory.java
└── README.md
```

## Compile and Run
From the project directory:

```bash
javac -d bin src/*.java
java -cp bin Main
```

On Windows PowerShell, the same commands work. Java 17 or newer is recommended because the menu uses modern switch syntax.

## Sample Operations
Register these patients: 1001 Ahmed Perera (25, 0712345678, Fever), 1005 Sara Fernando (32, 0771234567, Asthma), 1010 John Silva (45, 0769876543, Chest Pain), and 1020 Nimal Kumar (29, 0755555555, Injury). Displaying all patients shows IDs in ascending order. Enqueue 1001, 1005, and 1010; dequeueing selects Ahmed first. Complete two treatments; the newest treatment is removed first. Add visits V001 and V002 to patient 1001 and search/remove one visit.

## Testing Plan
- BST: insert several records, reject a duplicate, search existing and missing IDs, delete leaf/one-child/two-child nodes, and verify in-order output.
- Queue: enqueue multiple patients, verify display order, dequeue in FIFO order, then test an empty queue.
- Stack: add multiple treatments, verify newest-first display and pop order, then test an empty stack.
- Linked list: add V001 and V002, search an existing and missing ID, remove a visit, display the remaining history, and test an empty history.
- Validation: try blank fields, text for numeric values, invalid menu choices, and duplicate IDs.

## Time Complexity
Let `n` be the number of elements.

| Structure | Operation | Average | Worst case |
|---|---|---:|---:|
| BST | Insert/Search/Delete | O(log n) | O(n) |
| BST | In-order traversal | O(n) | O(n) |
| Queue | Enqueue/Dequeue | O(1) | O(1) |
| Queue | Display | O(n) | O(n) |
| Stack | Push/Pop | O(1) | O(1) |
| Stack | Display | O(n) | O(n) |
| Linked list | Add at head | O(1) | O(1) |
| Linked list | Search/Remove/Display | O(n) | O(n) |

The BST average assumes a reasonably balanced tree. A sorted insertion order can create a skewed tree, producing the O(n) worst case.

## Recommended Progressive GitHub Commits
1. **Created project structure** - Added `src` and README placeholder.
2. **Added Patient class** - Added patient fields and display behavior.
3. **Implemented Patient BST insertion** - Added nodes and duplicate-aware insertion.
4. **Added BST search and traversal** - Added ID search and ascending display.
5. **Added BST deletion** - Added leaf, one-child, and in-order-successor cases.
6. **Implemented emergency queue** - Added linked FIFO queue with front/rear handling.
7. **Implemented treatment stack** - Added linked LIFO treatment history.
8. **Implemented patient visit linked list** - Added per-patient visit operations.
9. **Added main menu** - Connected all user workflows.
10. **Added input validation** - Added safe numeric and required-text input loops.
11. **Added testing** - Recorded manual tests and expected behavior.
12. **Updated README** - Documented design, complexity, and usage.
13. **Final cleanup** - Compiled, smoke-tested, and removed avoidable issues.

## Report Outline
1. Introduction
2. Problem Statement
3. Aim and Objectives
4. Scope
5. Requirements
6. System Design
7. Binary Search Tree
8. Queue
9. Stack
10. Singly Linked List
11. Algorithms
12. Implementation
13. Testing
14. GitHub Development Evidence
15. Challenges and Solutions
16. Learning Outcomes
17. Conclusion
18. Future Improvements
19. References

Suggested report screenshots: the main menu, successful patient registration, duplicate-ID validation, ascending BST display, emergency queue before and after dequeue, treatment stack showing LIFO order, visit history with two visits, missing-record messages, and the successful compile/run command.

## Author and Repository
- **Student:** Add your name and student ID before submission.
- **Course:** CIT300 - Data Structures and Algorithms
- **GitHub repository:** Add your repository URL after creating the private or public assignment repository.
