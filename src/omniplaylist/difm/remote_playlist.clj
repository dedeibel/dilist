(ns omniplaylist.difm.remote-playlist)

(defn is-premium [stream]
  (= "/premium" (:url stream)))

(defn is-asx [stream]
  (.endsWith (:url stream) ".asx"))

