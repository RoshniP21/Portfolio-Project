/**
 * Enhanced interface for the Watchlist component. It adds extra methods on top
 * of {@code WatchlistKernel}. Every method here can be written using only the
 * kernel methods and the methods from {@code Standard}.
 */
public interface Watchlist extends WatchlistKernel {

    /**
     * Checks if {@code this} has no titles.
     *
     * @return true if {@code this} has no titles, false if it has some
     * @ensures the answer is true exactly when this has no titles
     */
    boolean isEmpty();

    /**
     * Moves all the titles from {@code other} into {@code this}. If a title is
     * in both lists, it only ends up in {@code this} once.
     *
     * @param other
     *            the watchlist to move titles out of
     * @updates this
     * @clears other
     * @requires this and other are not the same watchlist
     * @ensures this has every title that was in this or other before, and other
     *          is empty
     */
    void merge(Watchlist other);

}
