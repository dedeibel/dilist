(ns omniplaylist.playlist
  (:require [omniplaylist.track      :as track]
            [omniplaylist.difm.track :as difm-track]))

(defrecord Playlist [name tracks])

(defn update-tracks [playlist update-fn]
  (update-in playlist [:tracks]
             (partial map update-fn)))

(defn update-titles [playlist update-fn]
  (update-tracks playlist #(track/update-title % update-fn)))

(defn shorten-tracks-digitally-imported-name [playlist]
  (update-tracks playlist difm-track/shorten-digitally-imported-name))

(defn update-format-for-all-tracks [playlist format-name]
  (update-tracks playlist #(assoc % :format format-name)))

(defn enrich-track-titles-from-playlist-name-and-format [playlist]
  (update-tracks playlist #(track/enrich-with-playlist-name % (:name playlist))))

