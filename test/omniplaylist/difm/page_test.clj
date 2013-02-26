(ns omniplaylist.difm.page-test
  (:require [omniplaylist.track :as t]
            [omniplaylist.remote-playlist :as p]
            )
  (:use omniplaylist.difm.page 
        midje.sweet))

(fact "The tracks title's can be replaced by the playlist's title and format"
      (let [remote-playlist (p/map->RemotePlaylist { :name "Playlist" :format "Format" })
            tracks          (list 
                              (t/map->Track { :title "First" })
                              (t/map->Track { :title "Second" })
                              )]
        (set-track-titles-from-playlist-name-and-format remote-playlist tracks) =>
        (list
          (t/map->Track { :title "Playlist Format" })
          (t/map->Track { :title "Playlist Format" })
          )))
