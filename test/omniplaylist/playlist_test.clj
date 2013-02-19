(ns omniplaylist.playlist-test
  (:use midje.sweet
        omniplaylist.playlist
        omniplaylist.track))

(fact "Update titles can change all titles of a playlist's tracks"
      (let [tracks [
                    (map->Track { :title "title1" })
                    (map->Track { :title "title2" })
                    ]
            playlist (map->Playlist { :tracks tracks })]
        (-> playlist (update-titles #(str "prefix-" %)) :tracks))
      => (list 
           (map->Track { :title "prefix-title1" })
           (map->Track { :title "prefix-title2" })))
