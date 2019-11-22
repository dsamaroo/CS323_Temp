/**
 * Running Trials
 * Author: Carolyn Yao, Students: Choun H. Lee, Divya Samaroo, Kenneth Hill, Mohammed Rahat
 * Does this compile or finish running within 5 seconds? Y/N Yes
 */

public class RunningTrials {

  // Do not change the parameters!
  public int runTrialsRecur(int possibleSpeeds, int days) {
	  	int minTests = 0;
	  	//*******************************************************************************************************
	  	// Beginning of our code
	  	
	  	/* Note: I found the same example on Wikipedia of the Egg Drop Puzzle. 
	  	 * I think this puzzle is too vague. The story doesn't explain the logic behind the code properly
	  	 * especially the part where if 1 egg is available, you check all the floors. That only adds to
	  	 * confusion. Logically, minimum test you can run should be only 1, because if the egg breaks 
	  	 * at the first attempt, there's no need to test anymore, and since the story appears to be
	  	 * we are taking in all possibility into consideration, the MINIMUM amongst all possibility IS 1
	  	 * I think this is just poor choice as assignment as students are more or less forced to research
		 * this problem to even get firm grasp of what the problem is asking for. 
		 * I'd suggest giving students different Dynamic Programming problem in the future. This is probably 
		 * a good example of DP in order to memorize for the preparation of job interview questions, so 
		 * it might be a good example to cover during class lecture as an example of DP, but leaving students 
		 * to do this on their own is practically forcing them to research the material in order to get 
		 * some kind of context to understand the logic behind the story and algorithm, which in essence is the
		 * answer to the question, robbing students chance to apply critical thinking and practice DP
	  	 * - Choun
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
