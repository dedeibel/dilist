(ns dilist.stream)

(defrecord Stream [format name url])

(defn is-premium [stream]
  (= "/premium" (:url stream)))

