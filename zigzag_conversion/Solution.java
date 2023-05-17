class Solution {
    public String convert(String s, int numRows) {
        if (numRows == 1)
            return s;

        List<List<Integer>> indexMatrix = new ArrayList<>();
        for (int rowNum = 0; rowNum < numRows; rowNum++) {
            List<Integer> indexRow = new ArrayList<>();
            if (rowNum == 0) { // First row
                int firstRowIndexGap = (numRows - 1) * 2;
                int firstRowIndex = 0;
                while (firstRowIndex < s.length()) {
                    indexRow.add(firstRowIndex);
                    firstRowIndex += firstRowIndexGap;
                }
            } else if (rowNum == numRows - 1) { // Last row
                int lastRowIndexGap = (numRows - 1) * 2;
                int lastRowIndex = numRows - 1;
                while (lastRowIndex < s.length()) {
                    indexRow.add(lastRowIndex);
                    lastRowIndex += lastRowIndexGap;
                }
            } else { // The rows in-between
                int baseIndex = 0;
                int index = rowNum;
                boolean goingDown = true;
                while (index < s.length()) {
                    indexRow.add(index);
                    goingDown = !goingDown;
                    baseIndex += numRows - 1;
                    // This is the tricky part. I iterate one zigzag segment at a time. Based on whether I'm going down
                    // a vertical line or up a diagonal one (I probably should have called the boolean isVertical), I
                    // adjust the number of hops from the vertex at "baseIndex" to find the next index for the row.
                    index = goingDown ? baseIndex + rowNum : baseIndex + numRows - 1 - rowNum;
                }
            }

            indexMatrix.add(indexRow);
        }

        // To be honest, in my first submission I did string concatenation instead of using StringBuilder. Otherwise, I
        // didn't try to optimize my answer.
        StringBuilder result = new StringBuilder();
        for (List<Integer> row : indexMatrix) {
            for (int index : row) {
                result.append(s.charAt(index));
            }
        }

        return result.toString();
    }
}