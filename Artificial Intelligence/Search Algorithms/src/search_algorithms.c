#include <time.h>
#include <string.h> 
#include <math.h>
#include <stdio.h>
#include <stdlib.h>

#define  BREADTH 1		 //  Constants denoting the four algorithms
#define  DEPTH	2

#define  BEST	3
#define  ASTAR	4

#define  INCREASE 1      //  Consonants denoting the last_comms executed by the processor
#define  DECREASE 2
#define  DOUBLE 3

#define  HALF 4
#define  SQUARE 5
#define  ROOT 6

//  A node of the frontier. Frontier is kept as a DOUBLE-linked list,
//  for efficiency reasons for the BREADTH-first search algorithm.
struct frontier_node
{
	struct tree_node *n ;			        // Pointer to a search-tree node
	struct frontier_node *previous ; 		// Pointer to the previous frontier node
	struct frontier_node *next ;		    // Pointer to the next frontier node
};


struct tree_node
{
	int curr_numb;      // Current value of the node
	int h;				// the value of the heuristic function for this node
	int g;				// the DEPTH of this node wrt the ROOT of the search tree
	int f;				// f=0 or f=h or f=h+g, depending on the search algorithm used
	struct tree_node *parent;	    // pointer to the parrent node (NULL for the ROOT)
	int last_comm;                  // last command of the register
	int sum_cost;                   // Sum costs of the node 
};


struct frontier_node *frontier_head = NULL ;	 //  The one end of the frontier

struct frontier_node *frontier_tail = NULL ;	 //  The other end of the frontier

#define TIMEOUT 60	    //  Program terminates after TIMEOUT secs

clock_t t1 ;				//  Start time of the search algorithm
clock_t t2 ;				//  End time of the search algorithm

int *sol_sum_costs ; // Pointer to a table with the sum_costs of the solution
int *sol_numb ; // Pointer to a table with the number of the solutions

int solution_length ;	//  The sum of the solutions
int *solution ;		    //  Pointer to a table with the moves of the solution
int *solution_number ;	


// Auxiliary function that displays a message in case of wrong input parameters
void errorVor_messae()
{
	printf("register <method> <number1> <number2> <output-file>\n\n") ;
	printf("where: ") ;
	printf("<method> = BREADTH|DEPTH|BEST|ASTAR\n") ;

	printf("<number1> is the number that you will have as a start.\n") ;
	printf("<number2> is the number that you want to end.\n") ;
	printf("<output-file> is the file where the solution will be written.\n") ;
}


// This function adds a pointer to a new leaf search-tree node at the front of the frontier.
// This function is called by the depth-first search algorithm.
// Inputs:
//		struct tree_node *node	: A (leaf) search-tree node.
// Output:
//		0 --> The new frontier node has been added successfully.
//		-1 --> Memory problem when inserting the new frontier node .
int add_frontier_front(struct tree_node *node)
{
	// Creating the new frontier node
	struct frontier_node *new_frontier_node=(struct frontier_node*)
                                malloc(sizeof(struct frontier_node));
	if (new_frontier_node == NULL)
		return -1 ; 


	new_frontier_node->n = node ;
	new_frontier_node->previous = NULL ;
	new_frontier_node->next = frontier_head ;

	if (frontier_head == NULL)
	{
		frontier_head = new_frontier_node ;
		frontier_tail = new_frontier_node ;
	}
	else
	{

		frontier_head->previous = new_frontier_node ;
		frontier_head = new_frontier_node ;
	}

#ifdef SHOW_COMMENTS
	printf("Added to the front...\n");
	display_puzzle(node->p);
#endif
	return 0;
}

// Checks if a number is the target
int is_solution(int number)
{
    if (number == solution_number){
        return 1 ;
    }
	return 0 ;
}

//Δίνεται το κόστος και επιστρέφεται ως έξοδος της συνάρτησης
int heuristic(int sum_cost)
{
	return sum_cost;
}

// Reading run-time parameters
int get_method(char* s)
{
	if (strcmp(s,"BREADTH")==0)
		return  BREADTH ;

	else if (strcmp(s,"DEPTH")==0)
		return  DEPTH ;

	else if (strcmp(s,"BEST")==0)
		return  BEST ;

	else if (strcmp(s,"ASTAR")==0)
		return  ASTAR ;

	else
		return -1;
}


// This function initializes the search, i.e. it creates the root node of the search tree
// and the first node of the frontier
void initialize_search(int number_arxis, int method, int number_telous)
{
	struct tree_node *ROOT = NULL ;

	// Initialize search tree
	ROOT = (struct tree_node*) malloc(sizeof(struct tree_node)) ;
	ROOT->parent=NULL ;

	ROOT->sum_cost = 0 ;
    ROOT->curr_numb = number_arxis ;

	ROOT->g = 0 ;

	ROOT->h = heuristic(ROOT->sum_cost) ;

	if (method == BEST)
		ROOT->f = ROOT->h ;
	else if (method == ASTAR)
		ROOT->f = ROOT->g+ROOT->h ;
	else
		ROOT->f = 0 ;

	// Initialize frontier
	add_frontier_front(ROOT) ;
}


// Checks if number is perfect square
int isPerfectSquare(int number)
{
    if (number >= 0) {
 
        long long sr = sqrt(number);
        if (sr * sr == number) {
            return 1 ;
        }
    }
    return 0;
}




// This function adds a pointer to a new leaf search-tree node at the back of the frontier.
// This function is called by the breadth-first search algorithm.
// Inputs:
//		struct tree_node *node	: A (leaf) search-tree node.
// Output:
//		0 --> The new frontier node has been added successfully.
//		-1 --> Memory problem when inserting the new frontier node .
int add_frontier_back(struct tree_node *node)
{
	// Creating the new frontier node
	struct frontier_node *new_frontier_node = (struct frontier_node*) malloc(sizeof(struct frontier_node)) ;
	if (new_frontier_node == NULL)
		return -1 ;

	new_frontier_node->n = node ;
	new_frontier_node->next = NULL ;

	new_frontier_node->previous = frontier_tail ;

	if (frontier_tail == NULL)
	{
		frontier_head = new_frontier_node ;
		frontier_tail = new_frontier_node ;
	}
	else
	{
		frontier_tail->next = new_frontier_node ;
		frontier_tail = new_frontier_node ;
	}

#ifdef SHOW_COMMENTS
	printf("Added to the back...\n");
	display_puzzle(node->p);
#endif

	return 0;
}

// This function checks whether a node in the search tree
// holds exactly the same puzzle with at least one of its
// predecessors. This function is used when creating the childs
// of an existing search tree node, in order to check for each one of the childs
// whether this appears in the path from the root to its parent.
// This is a moderate way to detect loops in the search.
// Inputs:
//		struct tree_node *new_node	: A search tree node (usually a new one)
// Output:
//		1 --> No coincidence with any predecessor
//		0 --> Loop detection
int check_with_parents(struct tree_node *new_node)
{
	struct tree_node *parent = new_node->parent ;

	while (parent != NULL)
	{
		if (new_node->curr_numb == parent->curr_numb)
			return 0 ;
		parent = parent->parent ;
	}
	return 1 ;
}

// This function adds a pointer to a new leaf search-tree node within the frontier.
// The frontier is always kept in increasing order wrt the f values of the corresponding
// search-tree nodes. The new frontier node is inserted in order.
// This function is called by the heuristic search algorithm.
// Inputs:
//		struct tree_node *node	: A (leaf) search-tree node.
// Output:
//		0 --> The new frontier node has been added successfully.
//		-1 --> Memory problem when inserting the new frontier node
int add_frontier_in_order(struct tree_node *node)
{
	// Creating the new frontier node
	struct frontier_node *new_frontier_node = (struct frontier_node*)
                malloc(sizeof(struct frontier_node)) ;
	
    if (new_frontier_node == NULL)
		return -1 ;

	new_frontier_node->n = node ;
	new_frontier_node->previous = NULL ;

	new_frontier_node->next = NULL ;

	if (frontier_head == NULL)
	{
		frontier_head = new_frontier_node ;
		frontier_tail = new_frontier_node ;
	}
	else
	{
		struct frontier_node *pt ;

		pt = frontier_head ;

        // Search in the frontier for the first node that corresponds to either a larger f value
		// or to an equal f value but larger h value
		// Note that for the best first search algorithm, f and h values coincide.

		while (pt != NULL && (pt->n->f<node->f || (pt->n->f==node->f && pt->n->h<node->h)))
			pt = pt->next ;

		if (pt != NULL)
		{
			// new_frontier_node is inserted before pt

			if (pt->previous != NULL)
			{
				pt->previous->next = new_frontier_node ;
				new_frontier_node->next = pt ;
				new_frontier_node->previous = pt->previous ;
				pt->previous = new_frontier_node ;
			}
			else
			{
				// In this case, new_frontier_node becomes the first node of the frontier.
				new_frontier_node->next = pt ;
				pt->previous  = new_frontier_node ;
				frontier_head = new_frontier_node ;
			}
		}
		else
		{
			// if pt==NULL, new_frontier_node is inserted at the back of the frontier
			frontier_tail->next = new_frontier_node ;
			new_frontier_node->previous = frontier_tail ;
			frontier_tail = new_frontier_node ;
		}
	}

#ifdef SHOW_COMMENTS
	printf("Added in order (f=%d)...\n",node->f);
	display_puzzle(node->p);
#endif

	return 0;
}

// This function expands a leaf-node of the search tree.
// A leaf-node may have up to 4 childs. A table with 4 pointers
// to these childs is created, with NULLs for those childrens that do not exist.
// In case no child exists (due to loop-detections), the table is not created
// and a 'higher-level' NULL indicates this situation.
// Inputs:
//		struct tree_node *current_node	: A leaf-node of the search tree.
// Output:
//		The same leaf-node expanded with pointers to its children (if any).
int find_children(struct tree_node *current_node, int method)
{
    int current_number1 ;

    current_number1 = current_node->curr_numb ;

	if(current_number1 < pow(10,9))
	{
		struct tree_node *child = (struct tree_node*) malloc(sizeof(struct tree_node)) ;
		
        if (child==NULL) 
        {
            return -1;
        }

		child->parent = current_node ;
		child->sum_cost = child->parent->sum_cost + 2 ;
		
        child->last_comm = INCREASE ;
		child->g = current_node->g + 1 ;

        child->curr_numb = child->parent->curr_numb + 1 ;


		// loop check
		if (!check_with_parents(child))
			free(child) ;
		else
		{
			child->h = heuristic(child->sum_cost) ;
			if (method == BEST)
				child->f = child->h ;
			else if (method == ASTAR)
				child->f = child->g + child->h ;
			else
				child->f = 0 ;

            int errorV =  0 ;

            if (method == DEPTH)
				errorV = add_frontier_front(child) ;
			else if (method == BREADTH)
				errorV = add_frontier_back(child) ;
			else if (method == BEST || method == ASTAR)
				errorV = add_frontier_in_order(child) ;
			if (errorV < 0)
                return -1 ;
		}

	}

	if (current_number1 > 0)
	{
		struct tree_node *child=(struct tree_node*) malloc(sizeof(struct tree_node)) ;
		if (child == NULL) return -1 ;

		child->parent = current_node ;
		child->sum_cost = child->parent->sum_cost + 2 ;
		
        child->last_comm = DECREASE ;
		child->g = current_node->g + 1 ;		

        child->curr_numb = child->parent->curr_numb - 1 ;


		// loop check
		if (!check_with_parents(child))
			free(child) ;
		else
		{
			child->h = heuristic(child->sum_cost);
			if (method == BEST)
				child->f = child->h ;
			else if (method == ASTAR)
				child->f = child->g + child->h ;
			else
				child->f = 0 ;

            int errorV = 0 ;

            if (method == DEPTH)
				errorV = add_frontier_front(child) ;
			else if (method == BREADTH)
				errorV = add_frontier_back(child) ;
			else if (method == BEST || method == ASTAR)
				errorV = add_frontier_in_order(child) ;
			if (errorV < 0)
                return -1 ;
		}
	}

	if (current_number1 > 0 && current_number1*2 <= pow(10,9))
	{
		struct tree_node *child=(struct tree_node*) malloc(sizeof(struct tree_node)) ;
		if (child == NULL) {
            return -1;
            }

		child->parent = current_node ;
		child->sum_cost = child->parent->sum_cost + ceil(current_number1 / 2) + 1 ;
		
        child->last_comm = DOUBLE ;
		child->g = current_node->g + 1 ;

        child->curr_numb = child->parent->curr_numb * 2 ;


		// loop check
		if (!check_with_parents(child))
			free(child) ;
		else
		{
			child->h = heuristic(child->sum_cost) ;
			if (method == BEST)
				child->f = child->h ;
			else if (method == ASTAR)
				child->f = child->g + child->h ;
			else
				child->f = 0 ;

            int errorV = 0 ; 

            if (method == DEPTH)
				errorV = add_frontier_front(child) ;
			else if (method == BREADTH)
				errorV = add_frontier_back(child) ;
			else if (method == BEST || method == ASTAR)
				errorV = add_frontier_in_order(child) ;
			if (errorV < 0)
                return -1 ;
		}
	}

	if (current_number1 > 0)
	{
		struct tree_node *child = (struct tree_node*) malloc(sizeof(struct tree_node)) ;
		if (child == NULL) {
            return -1 ;
        }

		child->parent = current_node ;
		child->sum_cost = child->parent->sum_cost + ceil(current_number1 / 4) + 1 ;
		
        child->last_comm = HALF ;
		child->g = current_node->g + 1 ;	

        child->curr_numb = child->parent->curr_numb / 2 ;


		// loop check
		if (!check_with_parents(child))
			free(child) ;
		else
		{
			child->h = heuristic(child->sum_cost) ;
			if (method == BEST)
				child->f = child->h ;
			else if (method == ASTAR)
				child->f = child->g + child->h ;
			else
				child->f = 0 ;

            int errorV = 0  ;

            if (method == DEPTH)
				errorV = add_frontier_front(child) ;
			else if (method == BREADTH)
				errorV = add_frontier_back(child) ;
			else if (method == BEST || method == ASTAR)
				errorV = add_frontier_in_order(child) ;
			if (errorV < 0)
                return -1 ;
		}
	}

	if (pow(current_number1, 2) <= pow(10, 9))
	{
		struct tree_node *child = (struct tree_node*) malloc(sizeof(struct tree_node)) ;
		if (child==NULL) {
            return -1 ;
            }

		child->parent = current_node ;
		child->sum_cost = child->parent->sum_cost + ((pow(current_number1, 2) - current_number1)/4) + 1 ;
		
        child->last_comm = SQUARE ;
		child->g = current_node->g + 1 ;

        child->curr_numb = pow(child->parent->curr_numb, 2) ;


		// loop check
		if (!check_with_parents(child))
			free(child) ;
		else
		{
			child->h = heuristic(child->sum_cost) ;
			if (method == BEST)
				child->f = child->h ;
			else if (method == ASTAR)
				child->f = child->g + child->h ;
			else
				child->f = 0 ;

            int errorV = 0 ; 

            if (method == DEPTH)
				errorV = add_frontier_front(child) ;
			else if (method == BREADTH)
				errorV = add_frontier_back(child) ;
			else if (method == BEST || method == ASTAR)
				errorV = add_frontier_in_order(child) ;
			if (errorV < 0)
                return -1 ;
		}
	}

	if (current_number1>1 && isPerfectSquare(current_number1))
	{
		struct tree_node *child = (struct tree_node*) malloc(sizeof(struct tree_node)) ;
		if (child == NULL) {
            return -1 ;
        }

		child->parent = current_node ;
		child->sum_cost = child->parent->sum_cost + ((current_number1-sqrt(current_number1)) / 4) + 1 ;
		
        child->last_comm = ROOT ;
		child->g = current_node->g + 1 ; 

        child->curr_numb = sqrt(child->parent->curr_numb);

		// loop check
		if (!check_with_parents(child))
			free(child) ;
		else
		{
			child->h = heuristic(child->sum_cost) ;
			if (method == BEST)
				child->f = child->h ;
			else if (method == ASTAR)
				child->f = child->g + child->h ;
			else
				child->f = 0 ; 

            int errorV = 0 ; 

            if (method == DEPTH)
				errorV = add_frontier_front(child) ;
			else if (method == BREADTH)
				errorV = add_frontier_back(child) ;
			else if (method == BEST || method == ASTAR)
				errorV = add_frontier_in_order(child) ;
			if (errorV < 0)
                return -1 ;
		}
	}

	return 1;
}


// Giving a (solution) leaf-node of the search tree, this function computes
// the moves of the blank that have to be done, starting from the root puzzle,
// in order to go to the leaf node's puzzle.
// Inputs:
//		struct tree_node *solution_node	: A leaf-node
// Output:
//		The sequence of blank's moves that have to be done, starting from the root puzzle,
//		in order to receive the leaf-node's puzzle, is stored into the global variable solution.
void extract_solution(struct tree_node *solution_node)
{
	int i ;

	struct tree_node *temp_node = solution_node ;

	solution_length = solution_node->g ;

	solution = (int*) malloc(solution_length*sizeof(int)) ;
	sol_sum_costs = (int*) malloc(solution_length*sizeof(int)) ;
	sol_numb = (int*) malloc(solution_length*sizeof(int)) ;
	temp_node = solution_node ;
	
    i = solution_length;
	
    while (temp_node->parent!=NULL)
	{
		i-- ;
		solution[i] = temp_node->last_comm ;

		sol_sum_costs[i] = temp_node->sum_cost ;
		sol_numb[i] = temp_node->curr_numb ;

		temp_node = temp_node->parent ;
	}
}


// This function implements at the higest level the search algorithms.
// The various search algorithms differ only in the way the insert
// new nodes into the frontier, so most of the code is commmon for all algorithms.
// Inputs:
//		Nothing, except for the global variables root, frontier_head and frontier_tail.
// Output:
//		NULL --> The problem cannot be solved
//		struct tree_node*	: A pointer to a search-tree leaf node that corresponds to a solution.
struct tree_node *search(int method)
{
	clock_t t ;
	int i, errorV ;

	struct frontier_node *temp_frontier_node ;
	struct tree_node *current_node ;

	while (frontier_head!=NULL)
	{
		t = clock() ;
		if (t-t1 > CLOCKS_PER_SEC*TIMEOUT)
		{
			printf("Timeout\n") ;
			return NULL ;
		}

		// Extract the first node from the frontier
		current_node = frontier_head->n ;
#ifdef SHOW_COMMENTS
		printf("Extracted from frontier...\n");
		display_puzzle(current_node->p);
#endif
		if (is_solution(current_node->curr_numb))
			return current_node ;

		// Delete the first node of the frontier
		temp_frontier_node = frontier_head ;
		frontier_head = frontier_head->next ;

		free(temp_frontier_node) ;
		if (frontier_head == NULL)
			frontier_tail = NULL ;
		else
			frontier_head->previous=NULL ;

		// Find the children of the extracted node
		int errorV = find_children(current_node, method) ;

		if (errorV < 0)
	        {
            		printf("Memory exhausted while creating new frontier node. Search is terminated...\n") ;
			return NULL ;
        	}
	}

	return NULL ;
}


// This function writes the solution into a file
// Inputs:
//		char* filename	: The name of the file where the solution will be written.
// Outputs:
//		Nothing (apart from the new file)
void write_solution_to_file(char* filename, int solution_length, int *solution, int *sol_sum_costs, int *sol_numb)
{
	int i ;

	FILE *fout ;

	fout = fopen(filename,"w") ;

	if (fout == NULL)
	{
		printf("Cannot open output file to write solution.\n") ;
		printf("Now exiting...") ;
		return ;

	}
	fprintf(fout,"%d, \n",solution_length) ;

	for (i=0;i<solution_length;i++)
    {
		switch(solution[i])
		{
		case INCREASE:
			fprintf(fout,"INCREASE\t") ;
			break ;
		case DECREASE:
			fprintf(fout,"DECREASE\t") ;
			break ;
		case DOUBLE:
			fprintf(fout,"DOUBLE\t\t") ;
			break ;
		case HALF:
			fprintf(fout,"HALF\t\t") ;
			break ;
		case SQUARE:
			fprintf(fout,"SQUARE\t\t") ;
			break ;
		case ROOT:
			fprintf(fout,"ROOT\t\t") ;
			break ;
		}

		fprintf(fout, "%d\t", sol_numb[i]) ;

		fprintf(fout, "%d\n", sol_sum_costs[i]) ;
	}
	fclose(fout) ;
}



int main(int argc, char** argv)
{
	struct tree_node *solution_node ;
	char* endPtr; 
	int number_arxis, number_telous ; 

	int method ;	         // The algorithm used to solve the problem

	if (argc!=5)
	{
		printf("Wrong number of arguments. Use correct syntax:\n");
		errorVor_messae();
		return -1;
	}

	method = get_method(argv[1]) ;
	if (method<0)
	{
		printf("Wrong method. Use correct syntax:\n") ;
		errorVor_messae() ;
		return -1 ;
	}

	number_arxis = strtol(argv[2],&endPtr,10) ;
	number_telous = strtol(argv[3],&endPtr,10) ;

    solution_number = number_telous;

	printf("Solving using %s...\n",argv[1]) ;
	t1 = clock() ;

	initialize_search(number_arxis, method, number_telous) ;

	solution_node = search(method) ;

	t2 = clock() ;

	if (solution_node != NULL)
		extract_solution(solution_node) ; 
	else
		printf("No solution found.\n") ;

	if (solution_node != NULL)
	{
		printf("Solution found! (%d steps)\n",solution_length) ;
		printf("Time spent: %f secs\n",((float) t2-t1)/CLOCKS_PER_SEC) ;
		write_solution_to_file(argv[4], solution_length, solution, sol_sum_costs, sol_numb) ;
	}

	return 0 ;
}
