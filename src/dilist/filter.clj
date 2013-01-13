(ns dilist.filter)

(defmulti is-premium class)

(defn no-premium [stream] (remove is-premium stream))

