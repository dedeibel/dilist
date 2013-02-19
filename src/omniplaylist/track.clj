(ns omniplaylist.track)

(defrecord Track [title url format length])

(defn update-title [track update-fn]
  (update-in track [:title] update-fn))


