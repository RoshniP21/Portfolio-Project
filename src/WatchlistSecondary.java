/**
 * Abstract class for the Watchlist component.It writes the
 * extra(secondary)*methods from the{@code Watchlist} interface, plus
 * {@code toString} and {@code equals}.
 *
 * Every method here only uses the kernel methods ({@code add},
 * {@code removeAny}, {@code contains}, {@code size}) and the Standard methods
 * ({@code newInstance}, {@code transferFrom}). It never touches how the
 * watchlist is stored. That is the job of the kernel implementation, which
 * comes later.
 */
public abstract class WatchlistSecondary implements Watchlist {

    //Secondary methods from the Watchlist interface

    /**
     * Checks if {@code this} has no titles.
     *
     * @return true if {@code this} has no titles, false if it has some
     */
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Moves all the titles from {@code other} into {@code this}. If a title is
     * in both watchlists, it only ends up in {@code this} once.
     *
     * @param other
     *            the watchlist to move titles out of
     * @updates this
     * @clears other
     * @requires this and other are not the same watchlist
     */
    @Override
    public void merge(Watchlist other) {
        while (other.size() > 0) {
            // The loop makes sure other has a title before we remove one.
            String title = other.removeAny();
            // add only works if the title is not already here, so check first.
            if (!this.contains(title)) {
                this.add(title);
            }
        }
    }

    //Common methods

    /**
     * Makes a String of all the titles in {@code this}, like
     * {@code {Inception, Parasite}}. The titles can come out in any order.
     *
     * @return a String showing the titles in {@code this}
     */
    @Override
    public String toString() {
        // Take every title off the list so we can read it, and keep them in a
        // temporary watchlist so we can put them back after.
        Watchlist temp = this.newInstance();
        String result = "{";
        int n = this.size();
        for (int i = 0; i < n; i++) {
            String title = this.removeAny();
            if (i > 0) {
                result = result + ", ";
            }
            result = result + title;
            temp.add(title);
        }
        result = result + "}";

        // Put all the titles back so the list is the same as before.
        this.transferFrom(temp);
        return result;
    }

    /**
     * Checks if {@code this} and {@code obj} are watchlists with the same
     * titles.
     *
     * @param obj
     *            the object to compare with {@code this}
     * @return true if {@code obj} is a watchlist with the same titles as
     *         {@code this}, false otherwise
     */
    @Override
    public boolean equals(Object obj) {
        // Quick checks first. These don't change the list.
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Watchlist)) {
            return false;
        }
        Watchlist other = (Watchlist) obj;
        if (this.size() != other.size()) {
            return false;
        }

        // Same size, so check that every title in this is also in other.
        // Keep the titles in a temporary watchlist so we can put them back.
        boolean same = true;
        Watchlist temp = this.newInstance();
        int n = this.size();
        for (int i = 0; i < n; i++) {
            String title = this.removeAny();
            if (!other.contains(title)) {
                same = false;
            }
            temp.add(title);
        }

        // Put all the titles back so the list is the same as before.
        this.transferFrom(temp);
        return same;
    }
}
