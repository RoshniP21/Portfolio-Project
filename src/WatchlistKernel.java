import components.standard.Standard;

/**
 * Kernel interface for the Watchlist component. A Watchlist keeps track of the
 * movies and TV shows I want to watch. Each title can only be on the list once.
 *
 * The kernel only has the basic methods: add a title, remove a title, check for
 * a title, and count the titles. Everything else is in the enhanced interface,
 * {@code Watchlist}.
 */
public interface WatchlistKernel extends Standard<Watchlist> {

    /**
     * Adds {@code title} to {@code this}.
     *
     * @param title
     *            the movie or show to add
     * @updates this
     * @requires title is not already in this
     * @ensures title is now in this, and everything that was in this before is
     *          still there
     */
    void add(String title);

    /**
     * Removes one title from {@code this} and returns it. You don't get to
     * choose which one.
     *
     * @return the title that was removed
     * @updates this
     * @requires this has at least one title
     * @ensures the returned title was in this before, and it is no longer in
     *          this
     */
    String removeAny();

    /**
     * Checks if {@code title} is in {@code this}.
     *
     * @param title
     *            the title to look for
     * @return true if {@code title} is in {@code this}, false if it is not
     * @ensures the answer is true exactly when title is in this
     */
    boolean contains(String title);

    /**
     * Tells how many titles are in {@code this}.
     *
     * @return the number of titles in {@code this}
     * @ensures the answer is the number of titles in this
     */
    int size();

}
