# Classical AI Search Algorithms in C

## Overview

This project implements classical Artificial Intelligence search algorithms to solve a state transformation problem. The system searches for an optimal sequence of operations that transforms an initial numerical state into a target state.

## Case Study

The problem is modeled as a **state-space search problem**, where each integer represents a possible state and available mathematical operations create transitions between states.

Available operations:

* `INCREASE` (+1)
* `DECREASE` (-1)
* `DOUBLE` (×2)
* `HALF` (÷2)
* `SQUARE` (x²)
* `ROOT` (√x)

The objective is to find a path from the starting state to the goal state while minimizing the total cost.

## Implemented Algorithms

The project includes four search strategies:

* **Breadth-First Search (BFS)** — explores states level by level.
* **Depth-First Search (DFS)** — explores states deeply before backtracking.
* **Best-First Search** — prioritizes states based on a heuristic value.
* **A* Search** — combines path cost and heuristic estimation to find efficient solutions.

## Implementation Details

The project was developed in **C** and includes:

* Tree-based state representation
* Frontier management using linked lists
* Heuristic evaluation
* Loop detection
* Solution path extraction and cost calculation

## Technologies

* C
* Data Structures
* Classical AI Search Algorithms

## How to Run

Compile the program:

```bash
gcc *.c -o search_solver -lm
```

Run:

```bash
./search_solver <ALGORITHM> <START_STATE> <TARGET_STATE> <OUTPUT_FILE>
```

Example:

```bash
./search_solver ASTAR 5 100 solution.txt
```

## Results

The program outputs the sequence of operations required to reach the target state, including intermediate states and accumulated cost.

## Project Report

The detailed project report is available in:

```
report/Search_Algorithms_Report.pdf
```
