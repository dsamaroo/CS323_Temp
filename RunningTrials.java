/**
 * Running Trials
 * Author: Carolyn Yao, Students: Choun H. Lee, Divya Samaroo(1:40PM SECTION), Kenneth Hill, Mohammed Rahat
 * Does this compile or finish running within 5 seconds? Y/N Yes
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
	  	int minTests = 0;
	  	//*******************************************************************************************************
	  	// Beginning of our code
	  	
	  	/* Note: I found the same example on Wikipedia of the Egg Drop Puzzle. There was also a helpful link on geeksforgeeks
	  	 * The source for geeksforgeeks is : https://www.geeksforgeeks.org/egg-dropping-puzzle-dp-11/
	  	 * We used these sources to help us better understand the problem because we were really confused. 
	  	 * The source for wikipedia is : https://en.wikipedia.org/wiki/Dynamic_programming
	  	 
	  	 * - Choun Lee
	  	 * */
	  	
	  	// If there is less than 1 possible speeds, only 1 test required 
	  	if(possibleSpeeds <= 1)
	        return possibleSpeeds;
	    
	  	// If there is only 1 day test all possible speeds
	    if(days == 1)
	        return days;
	    
	    // If there is 0 day, can't test so return 0
	    if(days == 0)
	    	return 0;
	    
	    minTests = Integer.MAX_VALUE;
	    int result = 0;
	    // Comparison between possibleSpeeds -= 1 and days -= 1, to finding the converging point
	    for(int i = 1; i <= days; i++)
	    {
	    	result = Math.max(runTrialsRecur(possibleSpeeds - 1, i - 1), runTrialsRecur(possibleSpeeds, days - i));
	    	if(result < minTests)
	    		minTests = result;
	    }
	    	
	    return minTests + 1;
	    
	  // End of our code
	  //*******************************************************************************************************
  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  public int runTrialsMemoized() {
    int minTests = 0;
    // Your optional code here
    return minTests;
  }

  // Do not change the parameters!
  public int runTrialsBottomUp(int possibleSpeeds, int days) {
    int minTests = 0;
    // Your code here
    //*******************************************************************************************************
  	// Beginning of our code
    
    int tests[][] = new int[possibleSpeeds + 1][days + 1];
    
    // Initialization of our Dynamic programming table, 0 for 0th day and 1 for 1st day
    for(int i = 1; i <= possibleSpeeds; i++)
    {
    	tests[i][0] = 0;
    	tests[i][1] = 1;
    }
    // Filling up for the case when days = 1, and have to run full number of tests.
    for(int i = 1; i <= days; i++)
    {
    	tests[1][i] = i;
    }
    // temp variable for max comparison
    int result = 0;
    // DP code. Two outer nested loops goes through the DP table of 2D array
    for (int i = 2; i <= possibleSpeeds; i++) 
    { 
        for (int j = 2; j <= days; j++) 
        { 
        	// Max Integer to avoid problems with below comparison set up
            tests[i][j] = Integer.MAX_VALUE; 
            // Same optimal substructure of the recursion code above
            for (int k = 1; k <= j; k++) 
            { 
                 result = 1 + Math.max(tests[i - 1][k - 1], tests[i][j - k]); 
                 if (result < tests[i][j])
                    tests[i][j] = result; 
            } 
        } 
    } 
    minTests = tests[possibleSpeeds][days];
	// End of our code
	//*******************************************************************************************************

    return minTests;
  }

  public static void main(String args[]){
      RunningTrials running = new RunningTrials();

      // Do not touch the below lines of code, your output will automatically be formatted
      int minTrials1Recur = running.runTrialsRecur(12, 5);
      int minTrials1Bottom = running.runTrialsBottomUp(12, 5);
      int minTrials2Recur = running.runTrialsRecur(20, 8);
      int minTrials2Bottom = running.runTrialsBottomUp(20, 8);
      System.out.println("12 speeds, 5 weeks: " + minTrials1Recur + " " + minTrials1Bottom);
      System.out.println("20 speeds, 8 weeks: " + minTrials2Recur + " " + minTrials2Bottom);
      
  }
}
