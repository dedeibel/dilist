(ns omniplaylist.difm.stream
  (:require [name.benjaminpeter.clj-pls :as pls])
  (:require [omniplaylist.download :as download])
  (:require [omniplaylist.playlist :as playlist])
  (:require [omniplaylist.track    :as track]))

(defrecord Stream [format name url])

(defn is-premium [stream]
  (= "/premium" (:url stream)))

(defn- empty-playlist []
  (playlist/map->Playlist {:tracks []}))

(defn- create-tracks-from-pls-structure [pls-playlist]
  (for [file-entry (:files pls-playlist)]
    (track/map->Track 
      (select-keys file-entry [:url :title :length]))))

(defn- create-playlist-from-pls-structure [pls-playlist]
  (let [tracks (create-tracks-from-pls-structure pls-playlist)]
    (playlist/map->Playlist {:tracks tracks})))


(defn- parse-pls-playlist [playlist-stream]
  (let [pls-playlist (pls/parse playlist-stream)]
    (create-playlist-from-pls-structure pls-playlist)))

(defn download-playlist [stream]
  (download/as-stream (:url stream)))

(defn download-and-parse-playlist [stream]
  (if (clojure.string/blank? (:url stream))
    (empty-playlist)
    (parse-pls-playlist (download-playlist stream))))

