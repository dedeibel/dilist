(ns omniplaylist.difm.page
  (:require [omniplaylist.download        :as download]
            [omniplaylist.remote-playlist :as playlist]
            [omniplaylist.difm.parser     :as parser]
            [omniplaylist.difm.channel    :as channel]
            [omniplaylist.track           :as track]
            [omniplaylist.difm.remote-playlist      :as difm-playlist]
            [omniplaylist.difm.unaccepted-playlists :as unaccepted-playlists]))

(def ^:dynamic *digitally-imported-URL* "http://di.fm")

(defn difm-page-as-stream [] 
  (download/as-stream *digitally-imported-URL*))

(defn- download-all-channels-playlists [channels]
  (map playlist/download-and-parse-playlist (mapcat :streams channels)))

(defn- concatenate-all-playlists [playlists]
  (mapcat :tracks playlists))

; TODO remove me, I exist for debugging
;(defn- print-all-streams []
;  (pprint (all-streams-as-playlist)))

(defn all-streams-as-playlist []
  (-> (difm-page-as-stream)
      (parser/channels)
      (unaccepted-playlists/remove-unaccepted-playlists)
      ((partial take 3))
      (download-all-channels-playlists)
      (concatenate-all-playlists)
      ))

(defn set-track-titles-from-playlist-name-and-format [remote-playlist tracks]
  (map
    #(assoc % :title (str (:name remote-playlist) \space (:format remote-playlist))) tracks))

