/**
 * Abstract class for the Watchlist component. It writes the extra (secondary)
 * methods from the Watchlist interface, plus toString and equals.
 *
 * Every method here only uses the kernel methods (add, removeAny, contains,
 * size) and the Standard methods (newInstance, transferFrom). It never touches
 * how the watchlist is stored. That is the job of the kernel implementation,
 * which comes later.
 */
public abstract class WatchlistSecondary implements Watchlist {

    // ---------- Secondary methods from the Watchlist interface ----------

    // Checks if the watchlist has no titles.
    @Override
    public boolean isEmpty() {
        return this.size() == 0;
    }

    // Moves every title from other into this one.
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

    // ---------- Common methods (from Object) ----------

    // Makes a String like {Inception, Parasite}.
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

    // Checks if two watchlists have the same titles.
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

    // hashCode is left out on purpose to keep this class simple. It is only
    // needed if a Watchlist is used inside a HashSet or HashMap.

}