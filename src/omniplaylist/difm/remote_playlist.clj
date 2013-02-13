(ns omniplaylist.difm.remote-playlist)

(defn is-premium [stream]
  (= "/premium" (:url stream)))

