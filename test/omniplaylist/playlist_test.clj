(ns omniplaylist.playlist-test
  (:use midje.sweet
        omniplaylist.playlist
        omniplaylist.track)
  (:require [omniplaylist.track     :as track]
            [omniplaylist.playlist  :as playlist]))

(fact "Update titles can change all titles of a playlist's tracks"
      (let [tracks [
                    (track/map->Track { :title "title1" })
                    (track/map->Track { :title "title2" })
                    ]
            playlist (playlist/map->Playlist { :tracks tracks })
            ]
        (-> playlist (playlist/update-titles #(str "prefix-" %)) :tracks)) => (list 
           (track/map->Track { :title "prefix-title1" })
           (track/map->Track { :title "prefix-title2" })))

(fact "The tracks title's can be replaced by the playlist's title and contain their format"
      (let [tracks          (list 
                              (track/map->Track { :title "First"  :format "Format" })
                              (track/map->Track { :title "Second" :format "Format" })
                              )
            playlist (playlist/map->Playlist { :name "Playlist" :tracks tracks })]
        (map :title (:tracks (enrich-track-titles-from-playlist-name-and-format playlist))) =>
        (list
               "First Playlist Format"
               "Second Playlist Format"
               )))

