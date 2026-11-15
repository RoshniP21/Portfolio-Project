import components.set.Set;
import components.set.Set1L;

/**
 * Kernel implementation of the Watchlist component. It is a "thin layer" (the L
 * in the name) over the OSU {@code Set} component, so it stores the titles in a
 * {@code Set<String>}.
 *
 * @convention The set {@code titles} is not null. (A Set can't hold the same
 *             title twice, so there are never any repeats.)
 *
 * @correspondence this = the titles in {@code titles}
 */
public class Watchlist1L extends WatchlistSecondary {

    /**
     * This is where the titles are stored.
     */
    private Set<String> titles;

    /**
     * Makes a new, empty set to hold the titles.
     */
    private void createNewRep() {
        this.titles = new Set1L<String>();
    }

    //Constructor

    /**
     * Makes a new watchlist that starts out empty.
     */
    public Watchlist1L() {
        this.createNewRep();
    }

    //Kernel methods

    @Override
    public final void add(String title) {
        assert !this.titles
                .contains(title) : "Violation of: title is not in this";
        this.titles.add(title);
    }

    @Override
    public final String removeAny() {
        assert this.titles
                .size() > 0 : "Violation of: this has at least one title";
        return this.titles.removeAny();
    }

    @Override
    public final boolean contains(String title) {
        return this.titles.contains(title);
    }

    @Override
    public final int size() {
        return this.titles.size();
    }

    //Standard methods

    @Override
    public final Watchlist newInstance() {
        return new Watchlist1L();
    }

    @Override
    public final void clear() {
        this.createNewRep();
    }

    @Override
    public final void transferFrom(Watchlist source) {
        assert source != null : "Violation of: source is not null";
        assert source != this : "Violation of: source is not this";
        assert source instanceof Watchlist1L : "Violation of: source is of type Watchlist1L";

        // Take the other watchlist's titles, then leave it empty.
        Watchlist1L localSource = (Watchlist1L) source;
        this.titles = localSource.titles;
        localSource.createNewRep();
    }

}
