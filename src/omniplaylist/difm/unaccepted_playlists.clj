(ns omniplaylist.difm.unaccepted-playlists
  (:require [omniplaylist.difm.channel      :as channel]
            [omniplaylist.difm.playlist-url :as playlist-url]))

(def unaccepted-playlists-filters (list
                                 #(playlist-url/is-premium %)
                                 #(playlist-url/is-asx %)
                                 ));

(def unacceptable-playlist-predicate (apply some-fn unaccepted-playlists-filters))

(defn remove-unaccepted-playlist-urls [channels]
  (map #(channel/remove-streams % unacceptable-playlist-predicate) channels))

