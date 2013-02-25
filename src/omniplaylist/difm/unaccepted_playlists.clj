(ns omniplaylist.difm.unaccepted-playlists
  (:require [omniplaylist.difm.channel         :as channel]
            [omniplaylist.difm.remote-playlist :as difm-playlist]))

(def unaccepted-playlists-filters [
                                 #(difm-playlist/is-premium %)
                                 #(difm-playlist/is-asx %)
                                 ]);

(def unacceptable-playlist-predicate (apply some-fn unaccepted-playlists-filters))

(defn remove-unaccepted-playlists [channels]
  (map #(channel/remove-streams % unacceptable-playlist-predicate) channels))

