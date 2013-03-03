(ns omniplaylist.difm.playlist-url)

(defn is-premium [stream]
  (= "/premium" (:url stream)))

(defn is-asx [stream]
  (.endsWith (:url stream) ".asx"))

