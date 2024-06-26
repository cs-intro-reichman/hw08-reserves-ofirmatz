/** Represnts a list of musical tracks. The list has a maximum capacity (int),
 *  and an actual size (number of tracks in the list, an int). */
class PlayList {
    private Track[] tracks;  // Array of tracks (Track objects)   
    private int maxSize;     // Maximum number of tracks in the array
    private int size;        // Actual number of tracks in the array

    /** Constructs an empty play list with a maximum number of tracks. */ 
    public PlayList(int maxSize) {
        this.maxSize = maxSize;
        tracks = new Track[maxSize];
        size = 0;
    }

    /** Returns the maximum size of this play list. */ 
    public int getMaxSize() {
        return maxSize;
    }
    
    /** Returns the current number of tracks in this play list. */ 
    public int getSize() {
        return size;
    }

    /** Method to get a track by index */
    public Track getTrack(int index) {
        if (index >= 0 && index < size) {
            return tracks[index];
        } else {
            return null;
        }
    }
    
    /** Appends the given track to the end of this list. 
     *  If the list is full, does nothing and returns false.
     *  Otherwise, appends the track and returns true. */
    public boolean add(Track track) {
        //// replace the following statement with your code
        if (size < maxSize){ // checks that their is place in list
            tracks[size] = track;// reset the size of the array
            size++;// adds a new track
            return true;
        } else{
            return false;
        }
    }

    /** Returns the data of this list, as a string. Each track appears in a separate line. */
    //// For an efficient implementation, use StringBuilder.
    public String toString() {
        //// replace the following statement with your code
        StringBuilder tracklist = new StringBuilder();
        tracklist.append("\n");
        for (int i = 0; i < size; i++){
            tracklist.append(tracks[i]);
            tracklist.append("\n");
        }
        return tracklist.toString();
    }

    /** Removes the last track from this list. If the list is empty, does nothing. */
     public void removeLast() {
        //// replace this comment with your code
        if (size > 0){ // if size to tracklist isnt empty
            size--; // remove last track
        }
    }
    
    /** Returns the total duration (in seconds) of all the tracks in this list.*/
    public int totalDuration() {
        //// replace the following statement with your code
        int sumDuration = 0;
        for (int i = 0; i < size ; i++){
            sumDuration += tracks[i].getDuration();// adds the given duration of each track in the list
        }
        return sumDuration;
    }

    /** Returns the index of the track with the given title in this list.
     *  If such a track is not found, returns -1. */
    public int indexOf(String title) {
        //// replace the following statement with your code
        for (int i = 0; i < size; i++){
            String tempTrack = tracks[i].getTitle().toLowerCase();

           if ( tempTrack.equals(title.toLowerCase())){// looks for given title in lowercase
            return i; // returns the index if found
           } 
        }
        return -1;
    }

    /** Inserts the given track in index i of this list. For example, if the list is
     *  (t5, t3, t1), then just after add(1,t4) the list becomes (t5, t4, t3, t1).
     *  If the list is the empty list (), then just after add(0,t3) it becomes (t3).
     *  If i is negative or greater than the size of this list, or if the list
     *  is full, does nothing and returns false. Otherwise, inserts the track and
     *  returns true. */
    public boolean add(int i, Track track) {
        //// replace the following statement with your code
        if(i < 0 || i > size || size == maxSize) {         
            return false;
        } else {
            for (int j = size - 1; j > i ; j--){// makes space for the new track
                tracks[j + 1] = tracks[j];
            }
            size++;// enlarges the list
            tracks[i] = track;
            return true;
        }
    }
     
    /** Removes the track in the given index from this list.
     *  If the list is empty, or the given index is negative or too big for this list, 
     *  does nothing and returns -1. */
    public void remove(int i) {
        //// replace this comment with your code
        if (size == 0 || i < 0 || i > size) {
            return ;
        } else {
            for (int j = i; j < size - 1 ; j++){
                tracks[j] = tracks[j + 1];
            }
            size--; // do i need to return something (its void)
        }
    }

    /** Removes the first track that has the given title from this list.
     *  If such a track is not found, or the list is empty, or the given index
     *  is negative or too big for this list, does nothing. */
    public void remove(String title) {
        //// replace this comment with your code
        if (size == 0){
            return;
        }
        for (int i = 0; i < size; i++) {
            if (tracks[i].getTitle().equals(title)) {
            remove(i); // uses the remove function written beforhand
            }
        }    
    }

    /** Removes the first track from this list. If the list is empty, does nothing. */
    public void removeFirst() {
        //// replace this comment with your code
        if (size == 0){ // checks if list is empty
            return;
        } else{
            remove(0); // removes the first track
        } 
    }
    
    /** Adds all the tracks in the other list to the end of this list. 
     *  If the total size of both lists is too large, does nothing. */
    //// An elegant and terribly inefficient implementation.
     public void add(PlayList other) {
        //// replace this comment with your code
        int sizeOfPlaylist = size + other.size; //the size of the new playlist

        if (sizeOfPlaylist <= maxSize){ // checks if the combined lists are smaller then the maxSize
            for (int j = 0; j < other.size; j++){
                add(other.tracks[j]);
                size = sizeOfPlaylist;
            }
        }
    }

    /** Returns the index in this list of the track that has the shortest duration,
     *  starting the search in location start. For example, if the durations are 
     *  7, 1, 6, 7, 5, 8, 7, then min(2) returns 4, since this the index of the 
     *  minimum value (5) when starting the search from index 2.  
     *  If start is negative or greater than size - 1, returns -1.
     */
    private int minIndex(int start) {
        //// replace the following statement with your code // check how we learned in the class to do this?
        int minDuration = tracks[start].getDuration();
        int minInd = start;
        for (int i = minInd; i < size; i++){
            if( tracks[i].getDuration() <= minDuration){
                minDuration = tracks[i].getDuration();
                minInd = i;
            } 
        }
        return minInd ;
    }

    /** Returns the title of the shortest track in this list. 
     *  If the list is empty, returns null. */
    public String titleOfShortestTrack() {
        if (size == 0){ // checks if list is empty
            return null;
        } else {
            return tracks[minIndex(0)].getTitle();
        }   
    }

    /** Sorts this list by increasing duration order: Tracks with shorter
     *  durations will appear first. The sort is done in-place. In other words,
     *  rather than returning a new, sorted playlist, the method sorts
     *  the list on which it was called (this list). */
    public void sortedInPlace() {
        // Uses the selection sort algorithm,  
        // calling the minIndex method in each iteration.
        //// replace this statement with your code   
        for( int i = 0; i < size; i++){
            int minIndex = minIndex(i);  // uses minIndex to find the min duration the is in the unsorted part of the string      
            Track now = tracks[minIndex(i)]; // creates a new track thats temporary that is the minimumal duration track of the given list
            tracks[minIndex] = tracks[i];
            tracks[i] = now;// assigns the track to its new place
        }
    }
}