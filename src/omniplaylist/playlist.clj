(ns omniplaylist.playlist
  (:use omniplaylist.track))

(defrecord Playlist [tracks])

(defn update-titles [playlist update-fn]
  (update-in playlist [:tracks]
             (partial map
                        #(update-title % update-fn))))

