(ns omniplaylist.difm.page
  (:require [omniplaylist.download        :as download]
            [omniplaylist.playlist        :as playlist]
            [omniplaylist.difm.playlist   :as difm-playlist]
            [omniplaylist.playlist-url    :as playlist-url]
            [omniplaylist.difm.parser     :as parser]
            [omniplaylist.difm.channel    :as channel]
            [omniplaylist.track           :as track]
            [omniplaylist.difm.playlist-url         :as difm-url]
            [omniplaylist.difm.unaccepted-playlists :as unaccepted-playlists]))

(def ^:dynamic *digitally-imported-URL* "http://di.fm")

(defn difm-page-as-stream [] 
  (download/as-stream *digitally-imported-URL*))

(defn- download-all-channels-playlists [channels]
  (map playlist-url/download-and-parse-playlist (mapcat :streams channels)))

(defn- make-better-track-titles [playlists]
  (map (comp
         playlist/enrich-track-titles-from-playlist-name-and-format
         difm-playlist/shorten-tracks-digitally-imported-name)
       playlists))

(defn- concatenate-all-playlists [playlists]
  (mapcat :tracks playlists))

(defn all-streams-as-playlist []
  (-> (difm-page-as-stream)
      (parser/parse-all-channels-playlists)
      (unaccepted-playlists/remove-unaccepted-playlist-urls)
      (download-all-channels-playlists)
      (make-better-track-titles)
      (concatenate-all-playlists)
      ))

