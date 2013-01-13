(ns dilist.stream
  (:require [dilist.filter :as filter]))

(defrecord Stream [format name url])

(defn isPremium [stream]
  (= "/premium" (:url stream)))

(defmethod filter/isPremium Stream [stream] (isPremium stream))

