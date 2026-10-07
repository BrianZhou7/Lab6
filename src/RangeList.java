public class RangeList extends IntegerList {

    public RangeList() {
        super();
    }

    public RangeList(int lowerBound, int upperBound) {
        super();

        if (lowerBound > upperBound) {
            throw new IllegalArgumentException(
                    "The upper bound must be greater than or equal to the lower bound.");
        }

        for (int i = lowerBound; i <= upperBound; i++) {
        	super.insert(size(), i);
        }
    }

    public void add(int lowerBound, int upperBound) {

        if (lowerBound > upperBound) {
            throw new IllegalArgumentException(
                    "The upper bound must be greater than or equal to the lower bound.");
        }

        // If the list is empty, add the entire range.
        if (size() == 0) {
            for (int i = lowerBound; i <= upperBound; i++) {
                super.insert(size(), i);
            }
            return;
        }

        int currentLowerBound = get(0);
        int currentUpperBound = get(size() - 1);

        // Extend the range to the left.
        if (lowerBound < currentLowerBound) {
            for (int i = currentLowerBound - 1; i >= lowerBound; i--) {
                super.insert(0, i);
            }
        }

        // Extend the range to the right.
        if (upperBound > currentUpperBound) {
            for (int i = currentUpperBound + 1; i <= upperBound; i++) {
                super.insert(size(), i);
            }
        }
    }
    
    public void remove(int lowerBound, int upperBound) {

        // 1. Validate the bounds.
        if (lowerBound > upperBound) {
            throw new IllegalArgumentException(
                    "The upper bound must be greater than or equal to the lower bound.");
        }

        // 2. Cannot remove a range from an empty list.
        if (size() == 0) {
            throw new UnsupportedOperationException(
                    "Cannot remove range from an empty list.");
        }

        int currentLowerBound = get(0);
        int currentUpperBound = get(size() - 1);

        // 3. Both requested bounds must be inside the current range.
        if (lowerBound < currentLowerBound || upperBound > currentUpperBound) {
            throw new IllegalArgumentException(
                    "Lower and/or upper bounds are out of the current list range.");
        }

        // 4. The range must touch at least one end of the current list.
        if (lowerBound != currentLowerBound
                && upperBound != currentUpperBound) {
            throw new IllegalArgumentException(
                    "Cannot remove non-terminal ranges.");
        }

        // 5. Find where the removal begins.
        int startIndex = indexOf(lowerBound);
        int numberToRemove = upperBound - lowerBound + 1;

        // Always remove at the same index because elements shift left.
        for (int i = 0; i < numberToRemove; i++) {
            super.remove(startIndex);
        }
    }
    
    @Override
    public void insert(int index, int integer) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void add(int integer) {
        throw new UnsupportedOperationException();
    }
    
}