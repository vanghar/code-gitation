class Solution {
    // This algorithm picks the characters out of the input string by index and adds them
    // to a StringBuilder which is used for staging the output string.
    // Time complexity: O(n)
    // Space complexity: O(n)

    // It uses 2 pointers to do this, and 2 pieces of information are needed to do this 
    // in a single pass:
    // 1. The base distance between 2 neighboring characters on the same row
    //    This is given by 2 * (numRows - 1).
    // 2. The amount by which the distance between the low and high pointers shrinks on each 
    //    successive row
    //    This is given by 2 * rowIdx. It has the effect of shrinking the the offset distance by 2
    //    for each successive row, with an offset of zero on the final row.

    // On the first row, low starts at zero, while high starts at max distance from (1).
    // Each pointer moves along by the move distance from (1) on each cycle, with a check
    // that makes sure that if they were overlapping, then the overlap character is only read into
    // the target string once. 
    // This check ensure that only the low pointer value is added to the string for the first and
    // last rows.

    // 3 rows example below. Note that for each numbered while loop cycle, the
    // distance between successive locations of L (or H) is the same (4 in this case). 
    // Additionally, when L and H overlap, only the value from L is added to the output.
    //   [ 0, 1, 2, 3, 4, 5, 6, 7, 8, 9,10,11,12,13]
    //   [ P, A, Y, P, A, L, I, S, H, I, R, I, N, G]
    //1:   L           H                             // P
    //2:               L           H                 // PA
    //3:                           L           H     // PAH
    //4:                                       L     // PAHN
    //next row
    //5:      L     H                                // PAHNAP
    //6:                  L     H                    // PAHNAPLS
    //7:                              L     H        // PAHNAPLSII
    //8:                                         L   // PAHNAPLSIIG
    //next row
    //9:        L/H                                  // PAHNAPLSIIGY
    //10:                   L/H                      // PAHNAPLSIIGYI
    //11:                                L/H         // PAHNAPLSIIGYIR

    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        final StringBuilder sb = new StringBuilder();
        // The distance by which each pointer will move for each cycle. This is equivalent to the distance
        // between neighboring character indexes on the first row.
        final int move = 2 * (numRows - 1);

        for (int r=0;r<numRows;r++) {
            // The initial offset between the high and low pointers. Starting with the value of
            // move at row 0, and converging at 0 on the last row.
            final int offset = 2 * r;
            // Low ptr just starts at the current row index
            int low = r;
            // High ptr starts incrementally closer to low on each iteration of the for loop
            int high = low + move - offset;
            int t = -1;
            while (low < s.length()) {
                sb.append(s.charAt(low)); // pulls character at low into the output
                // Since low moves before the check conditions in the if block, we need to hold the old
                // value of low to compare to high so that a character isn't selected twice.
                t = low; // holds old low value
                low += move; // moves low by move
                if (high != low // Not the same as current low
                        && high < s.length() // within bounds
                        && high != t) // not the same as old low
                    sb.append(s.charAt(high));
                high += move; // moves high by move
            }
        }
        return sb.toString();
    }
}
