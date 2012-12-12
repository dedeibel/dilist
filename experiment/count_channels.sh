egrep -o '"http.+">' playlists.txt | perl -pe 's#.*/(\w+)\.\w+">41#' | sort | uniq | wc -l
