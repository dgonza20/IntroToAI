// David Gonzales
// Intro to AI-ITCS 3153
// Project 1: Queens 8 Hill Climbing

#include "board.h"


vector<int> state(8,0);


Board::Board(){
    
}


// Randomly determine the row each queen will begin in
void Board::randomStart(){
    srand(time(0));
    for(int i =0; i<8; i++){
        int randNum = rand()%8;
        state[i] = randNum;
    }
}

int Board::numH(){
    return this->heur;
}

// Implements the hill climbing algorithm by looking for a heuristics of lower value
void Board::HillClimbingAlg(){
    
    bool solved = false; 
    
	while(!solved){
		StateChanges = 0;
		randomStart();
		int currentH = numHeuristics(state);
		Neighbors = 0;
		
		// Check to see if the first board = goalstate
		if(currentH == 0){
			solved = true;
            Won();
            break;
		}
		
		// While there are hueristics
		while(currentH > 0){
			StateChanges = StateChanges + 1;
			bestHeuristic = currentH;
			Stats1();
			printBoard();
			
			// Iterate through the rows
			for(int i = 0; i < 8; i++){
				// Store postion of each queen in its row
				currRow = state[i]; 
				
				//Iterate through the columns
				for(int j = 0; j < 8; j++){ 
					
					// Begin checking for heuristics
					if(state[i] != j){					
						// Change column location of queen
						state[i] = j;				
						
						// Get the number of heuristics due to position of Queen
						tempHeuristics = numHeuristics(state);
						
						// Best location for queens
						if(tempHeuristics < bestHeuristic){	
							Neighbors++;
							bestHeuristic = tempHeuristics;
							rowLocation = j;
							colLocation = i;
						}
						// Update locations of queens
						state[i] = currRow;				
					}
				}
			}
			
			// When no better solutions can be found, reset the board
			if(bestHeuristic == currentH){ 		
				numRestarts++;
                Restart();
				break;							
			}
			else{
				// Update postions of the queens and number of heursitcs
				state[colLocation] = rowLocation;
				currentH = bestHeuristic;
			}
			
			// Display stats of board
			Stats2();

			// If heurstics equals zero, solution found
			if (bestHeuristic == 0){	
				solved = true;						
                Won();
				break;
			}
		}
	}
}

// Calculate the number of heuristics currently on the board
int Board::numHeuristics(vector<int> board){
    int h=0;
    
    for (int i = 0; i < 8; i++){
		for (int j = i + 1; j < 8; j++){
			// Checks to see if queens are on the same ros
			if(board[i] == board[j]){
				h++;
			}
			// Checks to see if queens are on the same diagonal
			if((abs(i - j) == abs(board[i] - board[j]))){
				h++;
			}
		}
	}
    setH(h);
    return h;
}

// Print out the current board
void Board::printBoard(){
    
    int board[8][8];
    
    for(int i = 0; i < 8; i++){
        for(int k = 0; k < 8; k++) {
            board[i][k]=0;
        }
    }
    for(int i = 0; i < 8; i++){
        board[state[i]][i]=1;
    }
    for(int i = 0; i < 8; i++){
        for(int k = 0; k < 8; k++){
            cout << board[i][k] << "  ";
        }
        cout << endl;
    }
}

void Board::begin(){
    HillClimbingAlg();
}

void Board::setH(int h){
    this->heur=h;
}


void Board::Stats1(){
    cout << endl;
    cout << "Current h: " << numHeuristics(state) << endl;// numH() << endl;
    cout << "Current State " << endl;
}

void Board::Stats2(){
    cout << "Neighbors found with lower h: " << Neighbors << endl;
    cout << "Setting new current state " << endl;
}

void Board::Won(){
    cout << endl;
    cout << "Current State " << endl;
    printBoard();
    cout << "Solution Found!" << endl;
    cout << "State changes: " << StateChanges << endl;
    cout << "Restarts: " << numRestarts  << endl;
}

void Board::Restart(){
    cout << endl;
    cout << "Current h: " << numH() << endl;
    cout << "Current State " << endl;
    printBoard();
    cout << "Neighbors found with lower h: 0" << endl;
    cout << "RESTART " << endl;
}
