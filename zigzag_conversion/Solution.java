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
                    index = goingDown ? baseIndex + rowNum : baseIndex + numRows - 1 - rowNum;
                }
            }

            indexMatrix.add(indexRow);
        }

        StringBuilder result = new StringBuilder();
        for (List<Integer> row : indexMatrix) {
            for (int index : row) {
                result.append(s.charAt(index));
            }
        }

        return result.toString();
    }
}