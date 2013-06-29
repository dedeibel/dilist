(ns omniplaylist.difm.page
  (:require [omniplaylist.download        :as download]
            [omniplaylist.playlist        :as playlist]
            [omniplaylist.difm.playlist   :as difm-playlist]
            [omniplaylist.playlist-url    :as playlist-url]
            [omniplaylist.difm.parser     :as parser]
            [omniplaylist.difm.channel    :as channel]
            [omniplaylist.track           :as track]))

(def ^:dynamic *digitally-imported-URL* "http://di.fm")

(defn difm-page-as-stream [] 
  (download/as-stream *digitally-imported-URL*))

(defn- download-all-channels-playlists [channels]
  (doall (pmap playlist-url/download-and-parse-playlist (mapcat :streams (doall channels)))))

(defn- make-better-track-titles [playlists]
  (map difm-playlist/shorten-tracks-digitally-imported-name playlists))

(defn- concatenate-all-playlists [playlists]
  (mapcat :tracks playlists))

(defn all-streams-as-playlist []
  (-> (difm-page-as-stream)
      (parser/parse-all-channels-playlists)
      (download-all-channels-playlists)
      (make-better-track-titles)
      (concatenate-all-playlists)))

