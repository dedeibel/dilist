(ns dilist.stream
  (:require [dilist.filter :as filter]))

(defrecord Stream [format name url])

(defn is-premium [stream]
  (= "/premium" (:url stream)))

(defmethod filter/is-premium Stream [stream] (is-premium stream))

