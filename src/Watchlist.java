import java.util.ArrayList;
import java.util.List;

/**
 * Watchlist keeps track of movies and TV shows I want to watch.
 *
 * Kernel methods (the basic ones): add, removeAny, contains, size Secondary
 * methods (built using only the kernel methods): isEmpty, merge
 *
 * This is just a proof of concept, so everything is in one simple class.
 */
public class Watchlist {

    /**
     * This is where the titles are stored. There are no repeats.
     */
    private List<String> titles;

    /**
     * Makes a new watchlist that starts out empty.
     */
    public Watchlist() {
        this.titles = new ArrayList<String>();
    }

    // Kernel methods

    /**
     * Adds title to list. If title is already in this, nothing changes.
     *
     * @param title
     *            the movie or show to add
     */
    public void add(String title) {
        if (!this.titles.contains(title)) {
            this.titles.add(title);
        }
    }

    /**
     * Removes and returns an arbitrary title from this.
     *
     * @return the removed title
     * @requires this.size() > 0
     */
    public String removeAny() {
        return this.titles.remove(this.titles.size() - 1);
    }

    /**
     * Checks if a title is on the list.
     *
     * @param title
     *            the title to look for
     * @return true if title is in this, false otherwise
     */
    public boolean contains(String title) {
        return this.titles.contains(title);
    }

    /**
     * Tells the number of titles on the list.
     *
     * @return the number of titles
     */
    public int size() {
        return this.titles.size();
    }

    // Secondary methods (written using only the kernel methods above)

    /**
     * Checks if the list has no titles.
     *
     * @return true if this is empty, false otherwise
     */
    public boolean isEmpty() {
        return this.size() == 0;
    }

    /**
     * Moves all titles from other into this. Afterwards, other is empty.
     *
     * @param other
     *            the Watchlist to move titles out of
     */
    public void merge(Watchlist other) {
        while (other.size() > 0) {
            this.add(other.removeAny());
        }
    }

    // Main method

    /**
     * Demonstrates the component by managing a movie night watchlist.
     *
     * @param args
     *            command line arguments (unused)
     */
    public static void main(String[] args) {
        Watchlist movies = new Watchlist();
        System.out.println("Starting with an empty watchlist");
        System.out.println("Is empty: " + movies.isEmpty());

        movies.add("Inception");
        movies.add("Interstellar");
        movies.add("The Matrix");
        movies.add("Parasite");
        movies.add("Spirited Away");

        System.out.println();
        System.out.println("After adding five titles");
        System.out.println("Titles on list: " + movies.size());
        System.out.println("Is empty: " + movies.isEmpty());
        System.out.println("Contains Parasite: " + movies.contains("Parasite"));
        System.out.println("Contains Titanic: " + movies.contains("Titanic"));

        // Adding a duplicate should not change the list.
        movies.add("Inception");
        System.out.println();
        System.out.println("After trying to add Inception again");
        System.out.println("Titles on list (still 5): " + movies.size());

        // Combine a friend's list with mine.
        Watchlist friends = new Watchlist();
        friends.add("Parasite");
        friends.add("Coco");
        friends.add("Up");
        System.out.println();
        System.out.println("Merging a friend's erge(friends");
        System.out.println(
                "Titles on list (Parasite was a repeat): " + movies.size());
        System.out.println("Contains Coco: " + movies.contains("Coco"));
        System.out.println("Friend's list is now empty: " + friends.isEmpty());

        // Watch every movie until the list is empty
        System.out.println();
        System.out.println("Movie marathon");
        while (!movies.isEmpty()) {
            System.out.println("Now watching: " + movies.removeAny());
        }
        System.out.println("Watchlist empty again: " + movies.isEmpty());
    }
}
