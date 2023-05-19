class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;
        final StringBuilder sb = new StringBuilder();
        final int move = 2 * (numRows - 1);
	    for (int r=0;r<numRows;r++) {
		    final int offset = 2 * r;
		    int low = r;
		    int high = low + move - offset;
		    int t = -1;
		    while (low < s.length()) {
			    sb.append(s.charAt(low));
			    t = low;
			    low += move;
			    if (high != low 
                    && high < s.length()
                    && high != t)
				    sb.append(s.charAt(high));
			    high += move;
		    }
 	    }
	    return sb.toString();
    }
}
