// David Gonzales
// Intro to AI-ITCS 3153
// Project 1: Queens 8 Hill Climbing

#ifndef BOARD_H
#define BOARD_H

#include <iostream>
#include <vector>
#include <cstdlib>
#include <ctime>
#include <cmath>

using namespace std;


class Board{
    
public:
    Board();
    
    void randomStart();
    
    int numH();
    
    void HillClimbingAlg();
    
    int numHeuristics(vector<int> a);
    
    void printBoard();
    
    void begin();
    
    void setH(int h);
    
    void Stats1();
    
    void Stats2();
    
    void Won();
    
    void Restart();
    
    
private:
    int heur;
    int tempHeuristics;
    int Neighbors;
    int numRestarts;
    int StateChanges;						
    int colLocation;
    int rowLocation;
    int bestHeuristic;
    int currRow;
    
};

#endif /* BOARD_H */

