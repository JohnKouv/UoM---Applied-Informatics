# Boolean Satisfiability Problem Solver Using AI Techniques

## Overview

This project implements Artificial Intelligence algorithms for solving the **Boolean Satisfiability Problem (SAT)**. The system searches for a valid assignment of truth values to propositions that satisfies all logical constraints.

## Case Study

The problem is represented as a set of Boolean propositions and logical clauses. Each clause contains multiple propositions connected with an OR operation, and the objective is to determine whether there exists an assignment where all clauses evaluate to true.

Example:

```
(P1 OR NOT P2 OR P3)
```

The solver receives:

* Number of propositions
* Number of clauses
* Propositions contained in each clause

and attempts to find a satisfying assignment.

## Implemented Algorithms

The project includes two solving approaches:

### Hill Climbing

A heuristic local search algorithm that:

* Generates a random truth assignment
* Evaluates the number of unsatisfied clauses
* Flips proposition values to improve the solution
* Uses random restarts when no improvement is possible

### Depth-First Search

A systematic search algorithm that:

* Explores possible truth assignments recursively
* Uses constraint checking to prune invalid assignments
* Verifies whether a complete assignment satisfies the problem

## Additional Tools

The project also includes:

* **Problem Generator**: Creates random SAT problem instances.
* **Solution Validator**: Checks whether a generated solution satisfies all clauses.

## Implementation Details

Developed in **C** using:

* Dynamic memory allocation
* Stack-based search
* Constraint validation
* Heuristic evaluation
* Randomized optimization

## How to Run

Compile:

```bash
gcc bcsp.c -o bcsp -lm
```

Run:

```bash
./bcsp <method> <input_file> <output_file>
```

Methods:

```
hill  - Hill Climbing
depth - Depth First Search
```

Example:

```bash
./bcsp hill problem1.txt solution.txt
```

## Technologies

* C
* Artificial Intelligence Search Algorithms
* Constraint Satisfaction Problems
* Heuristic Optimization

## Project Report

The detailed report is available in:

```
report/BCSP_Report.pdf
```
