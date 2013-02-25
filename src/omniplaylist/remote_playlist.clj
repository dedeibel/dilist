(ns omniplaylist.remote-playlist
  (:require [name.benjaminpeter.clj-pls :as pls]
            [omniplaylist.download :as download]
            [omniplaylist.playlist :as playlist]
            [omniplaylist.track    :as track]))

(def ^:dynamic *ignore-playlist-parse-exceptions* false)

(defrecord RemotePlaylist [format name url])

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

(defn- download-and-parse-playlist-throwing-exceptions [stream]
  (if (clojure.string/blank? (:url stream))
    (empty-playlist)
    (parse-pls-playlist (download-playlist stream))))


(defn- download-and-parse-playlist-ignoring-exceptions [stream]
  (try (download-and-parse-playlist-throwing-exceptions stream)
    (catch java.io.IOException ioe nil)
    (catch org.ini4j.InvalidFileFormatException fe nil)
    (finally (empty-playlist))))

(defn download-and-parse-playlist [stream]
  (if *ignore-playlist-parse-exceptions*
    (download-and-parse-playlist-ignoring-exceptions stream)
    (download-and-parse-playlist-throwing-exceptions stream)))

