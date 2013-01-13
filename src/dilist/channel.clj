(ns dilist.channel
  (:require [dilist.filter :as filter]))

(defrecord Channel [name streams])

(defn removeStreams [filterFn channel] 
  (assoc channel :streams (remove filterFn (:streams channel))))

(def remove-premium #(removeStreams filter/isPremium %))
