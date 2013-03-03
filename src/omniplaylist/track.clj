(ns omniplaylist.track)

(defrecord Track [title url format length])

(defn update-title [track update-fn]
  (update-in track [:title] update-fn))

(defn enrich-with-playlist-name [track playlist-name]
  (update-in track [:title]
             (fn [title] (str title \space playlist-name \space (:format track)))))


